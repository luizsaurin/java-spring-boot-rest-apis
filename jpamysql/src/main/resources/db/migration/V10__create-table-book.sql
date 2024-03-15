-- Create book table
CREATE TABLE `book` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) DEFAULT NULL,
  `author_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
);