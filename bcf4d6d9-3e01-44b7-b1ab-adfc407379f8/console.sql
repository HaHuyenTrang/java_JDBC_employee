CREATE TABLE account (
                         username VARCHAR(50) PRIMARY KEY,
                         password VARCHAR(100) NOT NULL,
                         status ENUM('ACTIVE', 'INACTIVE') NOT NULL
);
CREATE TABLE department (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE,
                            description VARCHAR(255),
                            status ENUM('ACTIVE', 'INACTIVE') NOT NULL
);
CREATE TABLE employee (
                          id VARCHAR(5) PRIMARY KEY, -- bắt đầu bằng E
                          name VARCHAR(150) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          phone VARCHAR(15) NOT NULL UNIQUE,
                          gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
                          salary_level INT NOT NULL CHECK (salary_level > 0),
                          salary DOUBLE NOT NULL CHECK (salary > 0),
                          dob DATE NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          status ENUM('ACTIVE', 'INACTIVE', 'ONLEAVE', 'POLICYLEAVE') NOT NULL,
                          department_id INT NOT NULL,
                          FOREIGN KEY (department_id) REFERENCES department(id)
);
