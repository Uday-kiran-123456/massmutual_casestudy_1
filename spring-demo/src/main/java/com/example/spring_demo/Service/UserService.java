package com.example.spring_demo.Service;

import com.example.spring_demo.DTO.RegisterRequest;
import com.example.spring_demo.Entity.Role;
import com.example.spring_demo.Entity.User;
import com.example.spring_demo.Enum.ERole;
import com.example.spring_demo.Repository.RoleRepository;
import com.example.spring_demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest registerRequest) {
        // Check if the username already exists
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }

        // Create new user object
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Assign roles based on the provided role in the request
        Set<Role> roles = new HashSet<>();
        if (registerRequest.getRole().equals("ROLE_ADMIN")) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(adminRole);
        } else {
            Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(userRole);
        }
        user.setRoles(roles);

        // Save the new user
        userRepository.save(user);

        return "User registered successfully!";
    }
}
