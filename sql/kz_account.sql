CREATE TABLE `kz_account` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `username` varchar(255) NOT NULL,
                              `password` varchar(512) NOT NULL,
                              `lock_sign` int(10) unsigned zerofill NOT NULL,
                              `version` int(10) unsigned zerofill NOT NULL,
                              `create_time` bigint NOT NULL,
                              `create_by` varchar(255) NOT NULL DEFAULT '',
                              `update_time` bigint NOT NULL,
                              `update_by` varchar(255) NOT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `idx_k_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;