CREATE TABLE `kz_user` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `kz_account_id` int NOT NULL,
                           `nickname` varchar(255) NOT NULL DEFAULT '',
                           `email` varchar(255) NOT NULL DEFAULT '',
                           `phone` varchar(255) NOT NULL DEFAULT '',
                           `avatar` varchar(255) NOT NULL DEFAULT '',
                           PRIMARY KEY (`id`),
                           KEY `idx_kz_user_account_id` (`kz_account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;