package org.example.service.impl

import groovy.util.logging.Slf4j
import lombok.AllArgsConstructor
import org.example.dto.ErrorGeneralDTO
import org.example.dto.ErrorValidacionDTO
import org.example.dto.LoginResponseDTO
import org.example.dto.PhoneDTO
import org.example.dto.RegisterRequestDTO
import org.example.dto.RegisterResponseDTO
import org.example.entity.PhoneEntity
import org.example.entity.UserEntity
import org.example.exception.SuperErrorException
import org.example.repository.PhoneRepository
import org.example.repository.UserRepository
import org.example.security.JwtTokenUtil
import org.example.util.JasyptEncryptorConfig
import org.example.util.RequestValidator
import org.jasypt.encryption.StringEncryptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.example.service.UserService

import java.time.LocalDate

@Service
@Slf4j
class UserServiceImpl implements UserService{

    @Autowired
    private RequestValidator requestValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public RegisterResponseDTO signUp(RegisterRequestDTO request) throws SuperErrorException {

        try {

            log.info("sign-up - Inicio ServiceImpl");

            //validar request
            String validationRequest = requestValidator.validarRequestSignUp(request);
            if (!validationRequest.equals("Valido")) {
                log.error("sign-up - error al validar request por [{}]", validationRequest);
                throw new ErrorValidacionDTO(1, validationRequest);
            }

            //Validar email
            boolean isEmailValido = requestValidator.validarEmail(request.getEmail());
            if (!isEmailValido) {
                log.error("sign-up - error al validar el formato del email");
                throw new ErrorValidacionDTO(2, "El email no tiene formato valido.");
            }

            //Validar password
            String validationPassword =  requestValidator.validarPassword(request.getPassword());
            if (!validationPassword.equals("Valido")) {
                log.error("sign-up - error al validar password por [{}]", validationPassword);
                throw new ErrorValidacionDTO(3, validationPassword);
            }

            //validar que no exista en la base ese email ya - funcion hecha
            Optional<UserEntity> userEntityOptional =  null;
            try {
                userEntityOptional = userRepository.findByEmail(request.getEmail());
                log.info("sign up -  la busqueda por el email para validar es [{}]", userEntityOptional.toString());
            } catch (RuntimeException e) {
                log.error("sign-up - error al validar si existe el mail en la base -  detalle:[{}]",e.toString());
                throw new ErrorGeneralDTO(3, "error al validar si existe mail en la base - "+e.toString());
            }

            if ( userEntityOptional!=null && userEntityOptional.isPresent() ) {
                //existe el valor
                log.error("sign-up - ese email ya esta registrado");
                throw new ErrorValidacionDTO(4, "Ese email ya esta registrado.");
            }

            //encriptar contraseña - funcion hecha
            String passwordEncrypt = JasyptEncryptorConfig.passwordEncryptor().encrypt(request.getPassword());
            log.info("sign up -  passwordEncrypt es [{}]", passwordEncrypt);

            //creacion de jwt para siempre - funcion hecha
            String jwt = jwtTokenUtil.generarToken(request.getEmail());

            //crear variable
            UserEntity userEntity = new UserEntity()
            userEntity.setName(request.getName())
            userEntity.setEmail(request.getEmail())
            userEntity.setPassword(passwordEncrypt)
            userEntity.setCreated(LocalDate.now())
            userEntity.setLast_login(null)
            userEntity.setToken(jwt)
            userEntity.setIsactive(true)
            userEntity.setPhones(new ArrayList<>())

            //se guarda en base H2 - funcion hecha
            UserEntity userEntitySaved = null;
            try {
                userEntitySaved = userRepository.save(userEntity);
            } catch (RuntimeException e) {
                log.error("sign-up - error al guardar user -  detalle:[{}]",e.toString());
                throw new ErrorGeneralDTO(5, "error al guardar user - "+e.toString());
            }

            PhoneEntity phoneEntity = null;
            if (request.getPhones() != null) {
                List<PhoneDTO> phones = request.getPhones();
                for (Iterator iterator = phones.iterator(); iterator.hasNext();) {
                    PhoneDTO phoneRequestDTO = (PhoneDTO) iterator.next();

                    phoneEntity = new PhoneEntity()
                    phoneEntity.setNumber(phoneRequestDTO.getNumber())
                    phoneEntity.setCity_code(phoneRequestDTO.getCitycode())
                    phoneEntity.setCountry_code(phoneRequestDTO.getCountrycode())
                    phoneEntity.setUser(userEntitySaved)

                    PhoneEntity phoneEntitySaved = phoneRepository.save(phoneEntity);
                }
            }
            log.info("sign-up -  se formo el entity [{}]",userEntity.toString());



            //armar respuesta
            RegisterResponseDTO response = new RegisterResponseDTO()
            response.setId(userEntitySaved.getUser_id())
            response.setCreated(userEntitySaved.getCreated())
            response.setLastLogin(userEntitySaved.getLast_login())
            response.setToken(userEntitySaved.getToken())
            response.setIsActive(userEntitySaved.isactive)

            log.info("sign-up - Fin ServiceImpl");

            return response;

        } catch (Exception e) {
            log.error("sign-up - error general : [{}] ", e.getMessage());
            throw new ErrorGeneralDTO(0, "error general: "+e.getMessage());
        }


    }

    @Override
    public LoginResponseDTO login(String token) throws SuperErrorException {
        try {

            log.info("login - Inicio ServiceImpl");

            //validar que el token sea dif a null y vacio
            if (token == null || token.equals("")) {
                log.error("login - error al validar token por vacio o nulo");
                throw new ErrorValidacionDTO(1, "error al validar token por vacio o nulo");
            }

            //validar expiration
            if (jwtTokenUtil.isTokenExpirado(token)) {
                log.error("login - error al validar token por token expirado");
                throw new ErrorValidacionDTO(2, "error al validar token por expirado");
            }

            //sacar email
            String email = jwtTokenUtil.getEmailFromJwt(token);
            log.info("login - email obtenido: {}", email);

            //conseguir datos dado el email
            UserEntity userEntity = new UserEntity()
            try {
                userEntity = userRepository.findByEmail(email);
                log.info("login -  la busqueda por el email es [{}]", userEntity.toString());
            } catch (RuntimeException e) {
                log.error("login - error al conseguir informacion del user es -  detalle:[{}]",e.toString());
                throw new ErrorGeneralDTO(3, "error al conseguir informacion del user es "+e.toString());
            }

            if (UserEntity == null) {
                log.error("login - ese email no esta registrado en el sistema");
                throw new ErrorValidacionDTO(4, "ese email no esta registrado en el sistema.");
            }

            if (!userEntity.getToken().equals(token)) {
                log.error("login - el jwt no es mas valido");
                throw new ErrorValidacionDTO(5, "el jwt no es mas valido.");
            }

            //conseguir nuevo token
            String jwt = jwtTokenUtil.generarToken(email);

            //actualizar base de datos
            try {
                userRepository.actualizarTokenYLastLoginPorEmail(jwt, LocalDate.now(), email);
                log.info("login -  update de token exitoso");
            } catch (RuntimeException e) {
                log.error("login - update de token con error: [{}] ", e.getMessage());
                throw new ErrorGeneralDTO(6, "update de token con error: "+e.toString());
            }

            //armar respuesta
            StringEncryptor stringEncryptor = JasyptEncryptorConfig.passwordEncryptor();
            //reemplaza lo de arriba por un new con todo
            LoginResponseDTO response = new LoginResponseDTO(userEntity.getUser_id(), userEntity.getCreated(), LocalDate.now(), jwt, userEntity.isIsactive(), userEntity.getName(), userEntity.getEmail(), stringEncryptor.decrypt(userEntity.getPassword()), convertirEntityAPhone(userEntity.getPhones()));
            System.out.println(response.toString());

            log.info("login - Fin ServiceImpl");

            return response;

        } catch (Exception e) {
            log.error("login - error general : [{}] ", e.getMessage());
            throw new ErrorGeneralDTO(0, "error general: "+e.getMessage());
        }
    }

    private static List<PhoneDTO> convertirEntityAPhone (List<PhoneEntity> phonesRequest){

        List<PhoneDTO> list =  null;
        if (phonesRequest != null) {
            list =  new ArrayList<>();
            for (Iterator iterator = phonesRequest.iterator(); iterator.hasNext();) {
                PhoneEntity phoneEntity = (PhoneEntity) iterator.next();

                PhoneDTO temp = new PhoneDTO(phoneEntity.getNumber(), phoneEntity.getCity_code(), phoneEntity.getCountry_code());
                list.add(temp);
            }
        }



        return list;
    }

}
