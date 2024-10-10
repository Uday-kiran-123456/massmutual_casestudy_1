package com.example.spring_demo.Service;



import com.example.spring_demo.DTO.UserDto;
import com.example.spring_demo.Entity.User;
import com.example.spring_demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt the password
        userRepository.save(user); // Save the user to the repository
        return user;
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username); // Fetch user by username
    }
}
