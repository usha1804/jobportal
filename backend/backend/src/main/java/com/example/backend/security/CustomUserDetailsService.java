package com.example.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.backend.model.User;
import com.example.backend.repo.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // return org.springframework.security.core.userdetails.User.builder()
        //     .username(user.getEmail())
        //     .password(user.getPassword())
        //     .roles(user.getRole().toUpperCase()) // ✅ FIX HERE
        //     .build();
        return new CustomUserDetails(user);
    }
}
