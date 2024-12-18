package org.example.controller

import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class UserControllerImpl {

    @PostMapping("/sign-up")
    public String signUp()  {

        log.info("sign-up - Inicio ControllerImpl")

        String response = "sign-up - Fin ControllerImpl"

        log.info("sign-up - Fin ControllerImpl");

        return response;
    }

}
