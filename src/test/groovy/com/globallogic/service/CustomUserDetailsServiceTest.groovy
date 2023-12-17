package com.globallogic.service

import com.globallogic.entities.UserData
import com.globallogic.repository.UserRepository
import spock.lang.Specification

class CustomUserDetailsServiceTest extends Specification {

    UserRepository repository = Mock(UserRepository)
    CustomUserDetailsService service = new CustomUserDetailsService(repository)

    def "test loadUserByUsername method"() {
        given:
            UserData userData = new UserData()
            userData.setPassword("password")
            userData.setName("name")
        when:
            repository.findByName(_ as String) >> userData
            def response = service.loadUserByUsername("name")
        then:
            response.getPassword() == "password"
    }

}
