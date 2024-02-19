INSERT INTO `users` (
	`username`, 
	`password`, 
	`account_non_expired`, 
	`account_non_locked`, 
	`credentials_non_expired`, 
	`enabled`
) VALUES
	('admin.user', '$2a$10$RwJRwclJUzNR1KeteS9OH.9rV8/GmOyA8UlVBJHBQQZcbZDhzcIQ2', 1, 1, 1, 1)
;