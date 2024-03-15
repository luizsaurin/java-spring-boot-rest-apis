-- Create comment table
CREATE TABLE `comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(255) DEFAULT NULL,
  `post_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
);