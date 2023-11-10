CREATE DATABASE IF NOT EXISTS coffee;

USE coffee;

CREATE TABLE `products` (
                            `product_id` bigint NOT NULL AUTO_INCREMENT,
                            `product_name` varchar(20) NOT NULL,
                            `category` varchar(50) NOT NULL,
                            `price` bigint NOT NULL,
                            `description` varchar(50) DEFAULT NULL,
                            `product_img` mediumblob DEFAULT NULL,
                            `created_at` datetime(6) NOT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            PRIMARY KEY (`product_id`)
);


CREATE DATABASE IF NOT EXISTS coffee_test;

USE coffee_test;

CREATE TABLE `products` (
                            `product_id` bigint NOT NULL AUTO_INCREMENT,
                            `product_name` varchar(20) NOT NULL,
                            `category` varchar(50) NOT NULL,
                            `price` bigint NOT NULL,
                            `description` varchar(50) DEFAULT NULL,
                            `product_img` mediumblob DEFAULT NULL,
                            `created_at` datetime(6) NOT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            PRIMARY KEY (`product_id`)
);

