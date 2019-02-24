package org.letsgettin.hospitalmanagement.services;


import javax.servlet.http.HttpServletRequest;

import org.letsgettin.hospitalmanagement.model.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.letsgettin.hospitalmanagement.model.repository.UserRepository;
import org.letsgettin.hospitalmanagement.security.JwtTokenProvider;
import org.letsgettin.hospitalmanagement.security.exceptions.CustomHttpException;
import org.letsgettin.hospitalmanagement.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomHttpException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signupUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setEmail(user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_APPLICANT)));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomHttpException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signupDoctor(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setEmail(user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_DOCTOR)));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomHttpException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signupHospital(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setEmail(user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_HOSPITAL)));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomHttpException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomHttpException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }
}

