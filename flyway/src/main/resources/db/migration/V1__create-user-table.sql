CREATE TABLE `users` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(255),
	`last_name` VARCHAR(255),
	`age` INT,
	`email` VARCHAR(255),
	`is_active` BIT(1),
	PRIMARY KEY (`id`)
);