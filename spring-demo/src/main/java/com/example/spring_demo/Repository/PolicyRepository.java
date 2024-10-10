package com.example.spring_demo.Repository;


import com.example.spring_demo.Entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
