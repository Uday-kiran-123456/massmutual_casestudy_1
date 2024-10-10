package com.example.spring_demo.Entity;


import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "policies") // Assuming you're renaming it to "policies"
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String policyNumber;

    @Column(nullable = false)
    private String policyDetails;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The user who created the policy

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent; // The agent responsible for the policy (optional)

    // Constructors
    public Policy() {}

    public Policy(String policyNumber, String policyDetails, Date startDate, Date endDate, User user, Agent agent) {
        this.policyNumber = policyNumber;
        this.policyDetails = policyDetails;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.agent = agent;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyDetails() {
        return policyDetails;
    }

    public void setPolicyDetails(String policyDetails) {
        this.policyDetails = policyDetails;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}

