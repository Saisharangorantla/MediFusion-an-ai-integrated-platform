# Doctor Service Troubleshooting Guide

## Issue: Timeout when creating doctor profile

### Check if services are running:

1. **Eureka Server** (if using service discovery)
   - Should be running on port 8761
   - Check: http://localhost:8761
   - Verify DOCTOR-SERVICE is registered

2. **API Gateway**
   - Should be running on port 8080
   - Check: http://localhost:8080/actuator/health
   - Verify it can route to doctor service

3. **Doctor Service**
   - Should be running on port 8085
   - Check logs for startup errors
   - Verify database connection is successful
   - Check if table `doctors` exists in database

4. **MySQL Database**
   - Should be running on port 3306
   - Database: `MediFusion_db`
   - Verify table exists: `SHOW TABLES;` then `DESCRIBE doctors;`

### Manual Table Creation (if Hibernate doesn't create it):

Run this SQL in MySQL:
```sql
USE MediFusion_db;

CREATE TABLE IF NOT EXISTS doctors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    contact VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    rating DOUBLE,
    experience_years INT,
    user_id BIGINT,
    INDEX idx_email (email),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### Test Endpoint Directly:

If API Gateway is not working, test directly:
```bash
# Test doctor service directly (bypass gateway)
curl -X POST http://localhost:8085/doctors/createdoctor \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Dr. Test Doctor",
    "specialization": "Cardiology",
    "email": "test@example.com",
    "experienceYears": 5
  }'
```

### Check Console Logs:

When you try to create a profile, check browser console for:
- Full API URL being called
- JWT token presence
- Any network errors
- Response status codes











