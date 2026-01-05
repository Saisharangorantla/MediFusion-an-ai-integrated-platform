-- SQL script to manually create the doctors table if Hibernate doesn't create it
-- Run this in MySQL if the table is not being created automatically

USE MediFusion_db;

-- Drop table if exists (be careful with this in production!)
DROP TABLE IF EXISTS doctors;

-- Create doctors table
-- This matches the Doctor entity structure
CREATE TABLE IF NOT EXISTS doctors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    contact VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    rating DOUBLE,
    experience_years INT,
    qualification VARCHAR(255),
    hospital VARCHAR(255),
    consultation_fee DOUBLE,
    user_id BIGINT,
    INDEX idx_email (email),
    INDEX idx_user_id (user_id),
    INDEX idx_name (name),
    INDEX idx_specialization (specialization)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Verify table creation
SELECT * FROM doctors;

