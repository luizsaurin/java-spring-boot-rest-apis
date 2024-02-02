INSERT INTO `users` (
	`username`, 
	`password`, 
	`account_non_expired`, 
	`account_non_locked`, 
	`credentials_non_expired`, 
	`enabled`
) VALUES
	('john.doe', '$2a$10$hbWdPAX3y7WB492CA6guHO2nkH3IMdnsvlE8whxWZCgm80BEujWdW', 1, 1, 1, 1)
;