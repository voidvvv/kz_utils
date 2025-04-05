CREATE TABLE `kz_blog` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `title` varchar(255) NOT NULL,
                           `author_user_id` varchar(255) NOT NULL,
                           `simple_description` varchar(255) NOT NULL,
                           `file_format` varchar(255) NOT NULL DEFAULT 'md',
                           `file_url` varchar(255) NOT NULL DEFAULT '',
                           `file_method` varchar(255) NOT NULL DEFAULT '',
                           `create_time` bigint NOT NULL,
                           `create_by` varchar(255) NOT NULL,
                           `update_time` bigint NOT NULL,
                           `update_by` varchar(255) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;