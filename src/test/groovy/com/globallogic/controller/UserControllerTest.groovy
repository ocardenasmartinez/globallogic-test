package com.globallogic.controller

import com.globallogic.entities.Phones
import com.globallogic.entities.User
import com.globallogic.entities.UserResponse
import com.globallogic.exceptions.EmailException
import com.globallogic.exceptions.PasswordException
import com.globallogic.service.UserService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class UserControllerTest extends Specification {

    UserService service = Mock(UserService)
    UserController userController = new UserController(service)

    def "test signup method"(){
        given:
            def uuid = UUID.randomUUID();
            def user = new User()
            user.setName("name")
            user.setPassword("a2asfGfdfdf4")
            user.setPhones(new ArrayList<Phones>())
            user.setEmail("email@email.com")
            def userResponse = new UserResponse()
            userResponse.setId(uuid)
            userResponse.setCreated(new Date())
            userResponse.setToken("token")
            userResponse.setLastLogin(new Date())
            userResponse.setActive(true)
        when:
            service.save(_ as User) >> userResponse
            def response = userController.signup(user)
        then:
            response.getStatusCode() == HttpStatus.CREATED
    }

    def "test signup email error method"(){
        given:
            def uuid = UUID.randomUUID();
            def user = new User()
            user.setName("name")
            user.setPassword("a2asfGfdfdf4")
            user.setPhones(new ArrayList<Phones>())
            user.setEmail("emailemail.com")
            def userResponse = new UserResponse()
            userResponse.setId(uuid)
            userResponse.setCreated(new Date())
            userResponse.setToken("token")
            userResponse.setLastLogin(new Date())
            userResponse.setActive(true)
        when:
            service.save(_ as User) >> userResponse
            def response = userController.signup(user)
        then:
            thrown EmailException
    }

    def "test signup email error method"(){
        given:
            def uuid = UUID.randomUUID();
            def user = new User()
            user.setName("name")
            user.setPassword("123")
            user.setPhones(new ArrayList<Phones>())
            user.setEmail("email@email.com")
            def userResponse = new UserResponse()
            userResponse.setId(uuid)
            userResponse.setCreated(new Date())
            userResponse.setToken("token")
            userResponse.setLastLogin(new Date())
            userResponse.setActive(true)
        when:
            service.save(_ as User) >> userResponse
            def response = userController.signup(user)
        then:
            thrown PasswordException
    }

}
