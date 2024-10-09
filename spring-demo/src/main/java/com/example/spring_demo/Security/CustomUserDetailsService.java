package com.example.spring_demo.Security;


import com.example.spring_demo.Entity.User;
import com.example.spring_demo.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // Password must be encoded (BCrypt)
                .authorities(user.getRoles().stream()
                        .map(role -> role.getName().name()) // Role names should be "ROLE_ADMIN", "ROLE_STUDENT"
                        .toArray(String[]::new))
                .build();
    }

}
