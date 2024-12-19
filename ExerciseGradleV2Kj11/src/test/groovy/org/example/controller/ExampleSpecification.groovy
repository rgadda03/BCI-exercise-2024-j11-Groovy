package org.example.controller


import spock.lang.Specification

class ExampleSpecification extends Specification{

    def "should be a simple asset"() {
        expect:
        1 == 1
    }

    def "should given when then"() {
        given:
        def a = 1
        def b = 2

        when:
        def c = a + b

        then:
        c == 3
    }

    def "exception test"() {
        when:
        throw new RuntimeException("This is an exception")

        then:
        thrown(RuntimeException)
    }

    def "clase inyectada"(){
        given:
        UserControllerImpl userControllerImpl = new UserControllerImpl()

        when:
        def resultReceived = userControllerImpl.signUp();

        then:
        resultReceived == "sign-up - Fin ControllerImpl"
    }

    //https://youtu.be/i5Qu3qYOfsM?t=1330

}
