package com.example.spring_demo.controller;


import com.example.spring_demo.DTO.PolicyDto;
import com.example.spring_demo.Entity.Policy;
import com.example.spring_demo.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping
    public ResponseEntity<String> createPolicy(@RequestBody PolicyDto policyDto) {
        Policy newPolicy = policyService.createPolicy(policyDto);
        return ResponseEntity.ok("Policy Created Successfully");
    }
}
