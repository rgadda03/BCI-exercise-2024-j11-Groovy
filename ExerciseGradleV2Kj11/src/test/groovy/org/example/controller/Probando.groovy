package org.example.controller

import spock.lang.Specification

class Probando extends Specification{

    def resultHola = "hola"
    UserControllerImpl userControllerImpl;

    def "test sign-up endpoint"() {
        when:
        def resultExpected = resultHola

        then:
        String resultReceived = userControllerImpl.signUp();
        resultReceived == resultExpected
    }

}
