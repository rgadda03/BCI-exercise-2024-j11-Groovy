package org.example.controller

import groovy.util.logging.Slf4j
import org.example.dto.LoginResponseDTO
import org.example.dto.RegisterRequestDTO
import org.example.dto.RegisterResponseDTO
import org.example.exception.SuperErrorException
import org.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class UserControllerImpl {

    private UserService userService;

    @Autowired
    UserControllerImpl(UserService userService) {
        this.userService = userService
    }

    @PostMapping("/sign-up")
    public RegisterResponseDTO signUp(@RequestBody RegisterRequestDTO request) throws SuperErrorException {

        log.info("sign-up - Inicio ControllerImpl");

        RegisterResponseDTO response = userService.signUp(request);

        log.info("sign-up - Fin ControllerImpl");

        return response;
    }

    @GetMapping("/login")
    public LoginResponseDTO login(@RequestParam String token) throws SuperErrorException {

        log.info("login - Inicio ControllerImpl");

        LoginResponseDTO response = userService.login(token);

        log.info("login - Fin ControllerImpl");

        return response;
    }

}
