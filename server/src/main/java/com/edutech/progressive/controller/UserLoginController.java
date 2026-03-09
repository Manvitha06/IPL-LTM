// src/main/java/com/edutech/progressive/controller/UserLoginController.java
package com.edutech.progressive.controller;

import com.edutech.progressive.dto.LoginRequest;
import com.edutech.progressive.dto.LoginResponse;
import com.edutech.progressive.entity.User;
import com.edutech.progressive.jwt.JwtUtil;
import com.edutech.progressive.service.impl.UserLoginServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserLoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserLoginServiceImpl userLoginService;

    public UserLoginController(AuthenticationConfiguration authenticationConfiguration,
                               JwtUtil jwtUtil,
                               UserLoginServiceImpl userLoginService) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.jwtUtil = jwtUtil;
        this.userLoginService = userLoginService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User created = userLoginService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(created);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            String token = jwtUtil.generateToken(loginRequest.getUsername());
            User u = userLoginService
                    .createUser(new User())  // Replace with repo call in your service if needed
                    ; // This line is placeholder — use your service/repo to fetch the user again

            // Prefer using repository/service to fetch the existing user:
            // User u = userLoginService.getAllUsers().stream()
            //        .filter(x -> x.getUsername().equals(loginRequest.getUsername()))
            //        .findFirst()
            //        .orElse(null);

            String role = (u != null) ? u.getRole() : "USER";
            Integer userId = (u != null) ? u.getUserId() : null;

            return ResponseEntity.ok(new LoginResponse(token, role, userId));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }
}