package com.globallogic.service;

import com.globallogic.entities.*;
import com.globallogic.exceptions.UserCreatedException;
import com.globallogic.repository.UserRepository;
import com.globallogic.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    public static final String BEARER_ = "Bearer ";
    public static final String NOTHING = "";
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserResponse save(User user){
        if(isUserCreated(user.getName())) {
            throw new UserCreatedException();
        }else{
            return saveUser(user);
        }
    }

    public UserLoginData getUserLoginData(String token) {
        var tokenToQuery = token.replace(BEARER_, NOTHING);
        var userData = userRepository.findByToken(tokenToQuery);
        var userLoginData = new UserLoginData();
        userLoginData.setActive(userData.isActive());
        userLoginData.setLastLogin(userData.getLastLogin());
        userLoginData.setCreated(userData.getCreated());
        userLoginData.setId(userData.getId());
        userLoginData.setName(userData.getName());
        userLoginData.setEmail(userData.getEmail());
        userLoginData.setPassword(userData.getPassword());
        var phones = new ArrayList<Phones>();
        userData.getPhones().forEach(phonesData -> {
            var phone = new Phones();
            phone.setCityCode(phonesData.getCityCode());
            phone.setNumber(phonesData.getNumber());
            phone.setCountryCode(phonesData.getCountryCode());
            phones.add(phone);
        });
        userLoginData.setPhones(phones);
        return userLoginData;
    }

    private UserResponse saveUser(User user) {
        var userData = new UserData();
        userData.setActive(true);
        userData.setCreated(new Date());
        userData.setId(UUID.randomUUID());
        userData.setName(user.getName());
        userData.setPassword(user.getPassword());
        userData.setLastLogin(new Date());
        userData.setEmail(user.getEmail());
        userData.setToken(jwtUtil.generateToken(user.getName()));
        var phonesData = new ArrayList<PhonesData>();
        user.getPhones().forEach(phoneData -> {
            var phones = new PhonesData();
            phones.setCityCode(phoneData.getCityCode());
            phones.setNumber(phoneData.getNumber());
            phones.setCountryCode(phoneData.getCountryCode());
            phonesData.add(phones);
        });
        userData.setPhones(phonesData);
        var userDataOut = userRepository.save(userData);
        var userResponse = new UserResponse();
        userResponse.setId(userDataOut.getId());
        userResponse.setActive(userDataOut.isActive());
        userResponse.setLastLogin(userData.getLastLogin());
        userResponse.setCreated(userData.getCreated());
        userResponse.setToken(userData.getToken());
        return userResponse;
    }

    private boolean isUserCreated(String name) {
        var userDataName = userRepository.findByName(name);
        return userDataName != null;
    }
}
