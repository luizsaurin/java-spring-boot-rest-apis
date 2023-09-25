CREATE TABLE `users` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`age` INT NOT NULL,
	`email` VARCHAR(255) NOT NULL,
	`is_active` BIT(1) NOT NULL,
	PRIMARY KEY (`id`)
);