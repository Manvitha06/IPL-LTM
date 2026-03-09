package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edutech.progressive.repository.UserRepository;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserLoginServiceImpl(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.edutech.progressive.entity.User u = userRepository.findByUsername(username);
        if (u == null) throw new UsernameNotFoundException("User not found: " + username);

        // Using authorities "ADMIN" / "USER" per your spec
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(u.getRole())
                .build();
    }

    // createUser should encode password
    public com.edutech.progressive.entity.User createUser(com.edutech.progressive.entity.User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new IllegalStateException("Username already exists");
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new IllegalStateException("Email already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}