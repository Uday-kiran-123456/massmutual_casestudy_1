package com.example.spring_demo.Service;


import com.example.spring_demo.DTO.PolicyDto;
import com.example.spring_demo.Entity.Policy;
import com.example.spring_demo.Entity.User; // Import User entity
import com.example.spring_demo.Repository.PolicyRepository;
import com.example.spring_demo.Repository.UserRepository; // Import User repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository; // Inject User repository

    @Autowired
    public PolicyService(PolicyRepository policyRepository, UserRepository userRepository) {
        this.policyRepository = policyRepository;
        this.userRepository = userRepository; // Initialize User repository
    }

    public Policy createPolicy(PolicyDto policyDto) {
        // Create a new Policy entity
        Policy policy = new Policy();

        // Set the policy details from the DTO
        policy.setPolicyDetails(policyDto.getPolicyDetails()); // Assuming you have a getPolicyDetails method in PolicyDto

        // Get the user by username
        User user = userRepository.findByUsername(policyDto.getUsername()); // Assuming you have a getUsername method in PolicyDto

        // Set the user or agent based on their role
        if (user != null) {
            policy.setUser(user); // Set user (for policies created by users)
        } else {
            // Handle the case where the user is not found or is an agent
            // For example, you can throw an exception or return null
            throw new RuntimeException("User not found");
        }

        // Save the policy to the database
        return policyRepository.save(policy);
    }
}

