package com.yourorg.telemedicine.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username is required")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be at least 6 characters")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "email is required")
    @Email(message = "email must be a valid email address")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "fullName is required")
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @NotBlank(message = "gender is required")
    @Column(nullable = false)
    private String gender;
    
    @NotBlank(message = "contact is required")
    @Column(nullable = false)
    private String contact;

    @NotBlank(message = "role is required")
    @Column(nullable = false)
    private String role; // USER (patient) or DOCTOR
    
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

