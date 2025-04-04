CREATE TABLE `kz_user` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `username` varchar(255) NOT NULL,
                           `password` varchar(512) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;