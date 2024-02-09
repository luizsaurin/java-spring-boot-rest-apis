CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `account_non_expired` BIT(1) DEFAULT NULL,
  `account_non_locked` BIT(1) DEFAULT NULL,
  `credentials_non_expired` BIT(1) DEFAULT NULL,
  `enabled` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
);