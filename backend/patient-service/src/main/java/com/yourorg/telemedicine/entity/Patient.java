package com.yourorg.telemedicine.entity;


import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name",nullable = false)
    private String fullname;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Integer age;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
