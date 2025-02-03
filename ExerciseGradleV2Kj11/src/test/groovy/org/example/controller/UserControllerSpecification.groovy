package org.example.controller

import org.example.dto.LoginResponseDTO
import org.example.dto.PhoneDTO
import org.example.dto.RegisterRequestDTO
import org.example.dto.RegisterResponseDTO
import org.example.service.UserService
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class UserControllerSpecification extends Specification{

    def "sign-up ok"() {
        //given ----------- give -----------------------
        given:

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
            //-----------------------------------------------------------------------

            RegisterResponseDTO responseToSend = new RegisterResponseDTO()
            responseToSend.setId(UUID.fromString("c78f3f56-a190-4711-8ed9-703c14e2858c"))
            responseToSend.setCreated(new LocalDate(2000,10,20))
            responseToSend.setLastLogin(new LocalDate(2000,12,20))
            responseToSend.setToken("token")
            responseToSend.setIsActive(true)
            //------------------------------------------------------------------------
            UserService userService = Stub()
            userService.signUp(requestDTO) >> responseToSend
            @Subject
            def userController = new UserControllerImpl(userService)

            when:
            RegisterResponseDTO response = userController.signUp(requestDTO)

        then:
        RegisterResponseDTO responseExpected = new RegisterResponseDTO()
        responseExpected.setId(UUID.fromString("c78f3f56-a190-4711-8ed9-703c14e2858c"))

        responseExpected.setCreated(new LocalDate(2000,10,20))
        responseExpected.setLastLogin(new LocalDate(2000,12,20))
        responseExpected.setToken("token")
        responseExpected.setIsActive(true)

        response.getId() == responseExpected.getId()
        response.getCreated() == responseExpected.getCreated()
        response.getLastLogin() == responseExpected.getLastLogin()
        response.getToken() == responseExpected.getToken()
        response.getIsActive() == responseExpected.getIsActive()
    }

    def "login ok"(){
       given:
            LoginResponseDTO responseToSend = new LoginResponseDTO()
            responseToSend.setId(UUID.fromString("c78f3f56-a190-4711-8ed9-703c14e2858c"))
            responseToSend.setCreated(new LocalDate(2000,10,20))
            responseToSend.setLastLogin(new LocalDate(2000,12,20))
            responseToSend.setToken("token")
            responseToSend.setIsActive(true)
            responseToSend.setName("nombre")
            responseToSend.setEmail("email")
            responseToSend.setPassword("password")

            List<PhoneDTO> phones = new ArrayList<>()
            PhoneDTO phoneDTO1 = new PhoneDTO()
            phoneDTO1.setCitycode(1)
            phoneDTO1.setCountrycode("1")
            phoneDTO1.setNumber(10L)
            phones.add(phoneDTO1)

            responseToSend.setPhones(phones)

            UserService userService = Stub()
            userService.login("token") >> responseToSend
            @Subject
            def userController = new UserControllerImpl(userService)

       when:
            LoginResponseDTO response = userController.login("token")

       then:
            LoginResponseDTO responseExpected = new LoginResponseDTO()
            responseExpected.setId(UUID.fromString("c78f3f56-a190-4711-8ed9-703c14e2858c"))
            responseExpected.setCreated(new LocalDate(2000,10,20))
            responseExpected.setLastLogin(new LocalDate(2000,12,20))
            responseExpected.setToken("token")
            responseExpected.setIsActive(true)
            responseExpected.setName("nombre")
            responseExpected.setEmail("email")
            responseExpected.setPassword("password")

            List<PhoneDTO> phonesExpected = new ArrayList<>()
            PhoneDTO phoneExpectedDTO1 = new PhoneDTO()
            phoneDTO1.setCitycode(1)
            phoneDTO1.setCountrycode("1")
            phoneDTO1.setNumber(10L)
            phones.add(phoneDTO1)

            responseExpected.setPhones(phones)

            response.getId() == responseExpected.getId()
            response.getCreated() == responseExpected.getCreated()
            response.getLastLogin() == responseExpected.getLastLogin()
            response.getToken() == responseExpected.getToken()
            response.getIsActive() == responseExpected.getIsActive()
            response.getName() == responseExpected.getName()
            response.getEmail() == responseExpected.getEmail()
            response.getPassword() == responseExpected.getPassword()
            for (int i = 0; i < response.getPhones().size(); i++) {
                response.getPhones().get(i).getCitycode() == responseExpected.getPhones().get(i).getCitycode()
                response.getPhones().get(i).getCountrycode() == responseExpected.getPhones().get(i).getCountrycode()
                response.getPhones().get(i).getNumber() == responseExpected.getPhones().get(i).getNumber()
            }

    }

}
