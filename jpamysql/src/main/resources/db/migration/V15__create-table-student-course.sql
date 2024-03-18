-- Create student_course join table
CREATE TABLE `student_course` (
  `course_id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
  CONSTRAINT `FK_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FK_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
);