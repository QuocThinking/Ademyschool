CREATE DATABASE `ademyschool`;

USE `ademyschool`;




CREATE TABLE IF NOT EXISTS `contact_msg`
(
    `contact_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `mobile_num` VARCHAR(10) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `subject` VARCHAR(50) NOT NULL,
    `message` VARCHAR(500) NOT NULL,
    `status` VARCHAR(10) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` VARCHAR(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `holidays`(
    `day` VARCHAR(20) NOT NULL,
    `reason` VARCHAR(100) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` VARCHAR(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` VARCHAR(50) DEFAULT NULL
)

CREATE TABLE IF NOT EXISTS `roles`(
    `role_id` int NOT NULL AUTO_INCREMENT,
    `role_name` varchar(50) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(100) NOT NULL,
    `updated_at` TIMESTAMP NOT NULL,
    `updated_by` varchar(50) NOT NULL,
    PRIMARY KEY (`role_id`)
)


CREATE TABLE IF NOT EXISTS `address`(
    `address_id` int NOT NULL AUTO_INCREMENT,
    `address1` varchar(200) NOT NULL,
    `address2` varchar(200) DEFAULT NULL,
    `city` varchar(50) NOT NULL,
    `state` varchar(50) NOT NULL,
    `zip_code` int NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`address_id`)
);


CREATE TABLE IF NOT EXISTS `person`(
    `person_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `email` varchar(50) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `pwd` varchar(200) NOT NULL,
    `role_id` int NOT NULL,
    `address_id` int NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`person_id`),
    FOREIGN KEY (role_id) REFERENCES roles(role_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);