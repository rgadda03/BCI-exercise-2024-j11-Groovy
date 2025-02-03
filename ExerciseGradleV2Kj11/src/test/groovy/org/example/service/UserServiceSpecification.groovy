package org.example.service

import org.example.dto.ErrorValidacionDTO
import org.example.dto.PhoneDTO
import org.example.dto.RegisterRequestDTO
import org.example.dto.RegisterResponseDTO
import org.example.repository.PhoneRepository
import org.example.repository.UserRepository
import org.example.security.JwtTokenUtil
import org.example.service.impl.UserServiceImpl
import org.example.util.RequestValidator
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class UserServiceSpecification extends Specification{

    def "sign-up service bad request"() {
        given:
            //----------------------  Request ---------------------
            RegisterRequestDTO requestDTO = new RegisterRequestDTO()
            requestDTO.setName("nombre")
            requestDTO.setEmail("email")
            requestDTO.setPassword("password")
            List<PhoneDTO> phones = new ArrayList<>()
            PhoneDTO phoneDTO1 = new PhoneDTO()
            phoneDTO1.setCitycode(1)
            phoneDTO1.setCountrycode("1")
            phoneDTO1.setNumber(10L)
            phones.add(phoneDTO1)
            requestDTO.setPhones(phones)
            //----------------------  Response Stub Request Validator---------------------
            String respuestaValidator = "No valido por y"
            //----------------------  ResponseToSend ---------------------
            RegisterResponseDTO responseToSend = new RegisterResponseDTO()
            responseToSend.setId(UUID.fromString("c78f3f56-a190-4711-8ed9-703c14e2858c"))
            responseToSend.setCreated(new LocalDate(2000,10,20))
            responseToSend.setLastLogin(new LocalDate(2000,12,20))
            responseToSend.setToken("token")
            responseToSend.setIsActive(true)
            //----------------------  Stub ---------------------
            def RequestValidator requestValidator = Stub()
            requestValidator.validarRequestSignUp(requestDTO) >> respuestaValidator
            def UserRepository userRepository = Stub()
            def JwtTokenUtil jwtTokenUtil = Stub()
            def PhoneRepository phoneRepository = Stub()
            @Subject
            UserServiceImpl userServiceImpl = new UserServiceImpl(requestValidator, userRepository, jwtTokenUtil, phoneRepository)

        when:
            RegisterResponseDTO response = userServiceImpl.signUp(requestDTO)

        then:
            thrown(ErrorValidacionDTO)

    }

}
