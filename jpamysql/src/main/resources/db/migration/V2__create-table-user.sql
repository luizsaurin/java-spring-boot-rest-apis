-- Create user table
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) DEFAULT NULL,
  `user_profile_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_username` (`username`),
  UNIQUE KEY `UK_user_profile_id` (`user_profile_id`),
  CONSTRAINT `FK_user_profile_id` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
);