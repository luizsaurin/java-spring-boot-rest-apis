INSERT INTO `users` (
	`username`, 
	`password`, 
	`account_non_expired`, 
	`account_non_locked`, 
	`credentials_non_expired`, 
	`enabled`
) VALUES
	('admin.user', '$2a$10$RwJRwclJUzNR1KeteS9OH.9rV8/GmOyA8UlVBJHBQQZcbZDhzcIQ2', 1, 1, 1, 1),
	('manager.user', '$2a$10$/b8VKcNH.coaOE5bMI56ZeioWgekj5fMliKdLCo/g4x3IZ7aOWP1O', 1, 1, 1, 1),
	('common.user', '$2a$10$vyW53TtBnWE/d4YyR4IuXu2JTKKcfv/L6KWQmdETd.DuxhDKzrZKW', 1, 1, 1, 1)
;