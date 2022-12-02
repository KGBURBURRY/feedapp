package com.bptn.service;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.exceptions.InvalidUserCredentialsException;
import com.bptn.models.AuthenticationUser;
import com.bptn.models.UserID;
import com.bptn.repository.AuthenticationUserRepository;
import com.bptn.request.LoginRequest;

@Service
public class LoginService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationUserRepository authenticationUserRepository;

    public UserID verifyUserCredentials(LoginRequest loginRequest) throws InvalidUserCredentialsException {
        LOGGER.debug("Invoking login = {}", "verifyUserCredentials");
        if (StringUtils.isEmpty(loginRequest.getUsername()) || StringUtils.isEmpty(loginRequest.getPassword())){
            LOGGER.debug("Username = {} or Password = {} can't be empty", loginRequest.getUsername(), loginRequest.getPassword());
            throw new InvalidUserCredentialsException("Username or Password can't be empty");
        }
        AuthenticationUser authenticationUser =
                authenticationUserRepository.findAuthenticationUserByIdAndUserPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (authenticationUser == null) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }
        LOGGER.debug("Username = {} or Password = {} validated successfully", loginRequest.getUsername(), loginRequest.getPassword());
        return authenticationUser.getUserID();
    }

}