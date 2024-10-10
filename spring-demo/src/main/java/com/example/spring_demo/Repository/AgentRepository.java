package com.example.spring_demo.Repository;


import com.example.spring_demo.Entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByUsername(String username);
}
