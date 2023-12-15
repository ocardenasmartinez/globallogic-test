package com.globallogic.service

import com.globallogic.entities.Phones
import com.globallogic.entities.PhonesData
import com.globallogic.entities.User
import com.globallogic.entities.UserData
import com.globallogic.entities.UserLoginData
import com.globallogic.entities.UserResponse
import com.globallogic.repository.UserRepository
import com.globallogic.security.JwtUtil
import spock.lang.Specification
import com.globallogic.exceptions.UserCreatedException

class UserServiceTest extends Specification {

    UserRepository repository = Mock(UserRepository)
    JwtUtil jwtUtil = Mock(JwtUtil)
    UserService userService = new UserService(repository, jwtUtil)

    def "test save method"() {
        given:
            def user = new User()
            user.setName("Juan")
            user.setPassword("passsword")
            user.setEmail("email@email.com")
            user.setPhones(new ArrayList<Phones>())
            def date = new Date()
            def uuid = UUID.randomUUID();
            def userResponse = new UserResponse()
            userResponse.setActive(true)
            userResponse.setCreated(date)
            userResponse.setId(uuid)
            userResponse.setToken("token")
            userResponse.setLastLogin(date)
            def userData = new UserData()
            userData.setLastLogin(date)
            userData.setToken("token")
            userData.setId(uuid)
            userData.setEmail("email@email.com")
            userData.setCreated(date)
            userData.setPassword("password")
            userData.setActive(true)
            userData.setName("Juan")
            var phones = new ArrayList<PhonesData>();
            userData.setPhones(phones)
        when:
            jwtUtil.generateToken(_ as String) >> "token"
            repository.findByName(_ as String) >> null
            repository.save(_ as UserData) >> userData
            def response = userService.save(user)
        then:
            response.getId() == userResponse.getId()
        noExceptionThrown()
    }

    def "test error save method"() {
        given:
            def user = new User()
            user.setName("Juan")
            user.setPassword("passsword")
            user.setEmail("email@email.com")
            user.setPhones(new ArrayList<Phones>())
            def date = new Date()
            def uuid = UUID.randomUUID();
            def userResponse = new UserResponse()
            userResponse.setActive(true)
            userResponse.setCreated(date)
            userResponse.setId(uuid)
            userResponse.setToken("token")
            userResponse.setLastLogin(date)
            def userData = new UserData()
            userData.setLastLogin(date)
            userData.setToken("token")
            userData.setId(uuid)
            userData.setEmail("email@email.com")
            userData.setCreated(date)
            userData.setPassword("password")
            userData.setActive(true)
            userData.setName("Juan")
            var phones = new ArrayList<PhonesData>();
            userData.setPhones(phones)
        when:
            jwtUtil.generateToken(_ as String) >> "token"
            repository.findByName(_ as String) >> userData
            repository.save(_ as UserData) >> userData
            userService.save(user)
        then:
            thrown UserCreatedException
    }

    def "test getUserLoginData method"() {
        given:
            def uuid = UUID.randomUUID()
            def userLoginData = new UserLoginData()
            userLoginData.setPhones(new ArrayList<Phones>())
            userLoginData.setEmail("email@email.com")
            userLoginData.setPassword("password")
            userLoginData.setActive(true)
            userLoginData.setId(uuid)
            userLoginData.setToken("token")
            userLoginData.setName("Juan")
            userLoginData.setLastLogin(new Date())
            userLoginData.setCreated(new Date())
            def userData = new UserData();
            userData.setPhones(new ArrayList<PhonesData>())
            userData.setEmail("email@email.com")
            userData.setPassword("password")
            userData.setActive(true)
            userData.setId(uuid)
            userData.setToken("token")
            userData.setName("Juan")
            userData.setLastLogin(new Date())
            userData.setCreated(new Date())
        when:
            repository.findByToken(_ as String) >> userData
            def response = userService.getUserLoginData("token")
        then:
            response.getId() == uuid
    }

}
