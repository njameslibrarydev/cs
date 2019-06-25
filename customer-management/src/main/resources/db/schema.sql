CREATE schema IF NOT EXISTS CustomerManagement;
USE CustomerManagement;
DROP TABLE customers IF EXISTS;

CREATE TABLE customers (
  account_number bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255),
  surname VARCHAR(255),
  address VARCHAR(255)  
);