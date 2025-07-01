CREATE TABLE `kz_blog_comment` (
                                   `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `blog_id` int NOT NULL,
                                   `parent_comment_id` int NOT NULL,
                                   `comment` text,
                                   `create_time` bigint NOT NULL,
                                   `create_by` varchar(255) DEFAULT NULL,
                                   `update_time` bigint DEFAULT NULL,
                                   `update_by` varchar(255) DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `idx_blog_id` (`blog_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;