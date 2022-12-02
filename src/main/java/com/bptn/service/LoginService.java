/*
 * package com.bptn.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.bptn.models.UserID; import com.bptn.repository.LoginRepository;
 * 
 * @Service public class LoginService {
 * 
 * // Dependency Injection
 * 
 * @Autowired LoginRepository loginRepository;
 * 
 * public String validateUserCredentials(UserID userParam) {
 * 
 * String message = null;
 * 
 * UserID userID =
 * this.loginRepository.findById(userParam.getUsername()).orElse(null);
 * 
 * if (userID!=null) { if
 * (userID.getUserPassword().equals(userParam.getUserPassword())){ message =
 * "Login Successful"; } else { message = "Password Incorrect"; } } else {
 * message = "User doesn't exist"; }
 * 
 * return message;
 * 
 * }
 * 
 * }
 */