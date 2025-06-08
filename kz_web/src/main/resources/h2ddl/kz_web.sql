/*
 Navicat Premium Data Transfer

 Source Server         : mylocal
 Source Server Type    : MySQL
 Source Server Version : 90200
 Source Host           : localhost:3306
 Source Schema         : kz_web

 Target Server Type    : MySQL
 Target Server Version : 90200
 File Encoding         : 65001

 Date: 23/05/2025 19:23:21
*/



-- ----------------------------
-- Table structure for kz_account
-- ----------------------------
DROP TABLE IF EXISTS `kz_account`;
CREATE TABLE `kz_account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lock_sign` int(10) UNSIGNED ZEROFILL NOT NULL,
  `version` int(10) UNSIGNED ZEROFILL NOT NULL,
  `create_time` bigint(0) NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `update_time` bigint(0) NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_k_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_account
-- ----------------------------
INSERT INTO `kz_account` VALUES (1, '123', '123123213', 0000000000, 0000000000, 0, '', 0, '');
INSERT INTO `kz_account` VALUES (2, 'aa', 'bb', 0000000000, 0000000000, 111, 'a', 111, 'b');
INSERT INTO `kz_account` VALUES (3, 'kzkz111', '$2a$10$rmNBpLXVTz6puHkhcpCeJujd2q.zq0dbWCTcY.qMq3Zlt88n3bHlq', 0000000000, 0000000000, 1743824667061, 'system', 1743824667061, 'system');

-- ----------------------------
-- Table structure for kz_blog
-- ----------------------------
DROP TABLE IF EXISTS `kz_blog`;
CREATE TABLE `kz_blog`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `simple_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `excerpt` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `file_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'md',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `file_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `create_time` bigint(0) NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` bigint(0) NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_blog
-- ----------------------------
INSERT INTO `kz_blog` VALUES (3, 'this is api test title(命运黑白棋)', 'kzkz111', 'this is api test title(命运黑白棋)', '', 'md', 'E:\\files\\book\\952426036_kzkz111_1743836878819.md', 'LOCAL', 1743836878819, 'kzkz111', 1743836878819, 'kzkz111');
INSERT INTO `kz_blog` VALUES (4, 'this is api test title(命运黑白棋)', 'kzkz111', 'this is api test title(命运黑白棋)', '', 'md', 'E:\\files\\book\\679e5998ae561dd76e3098008226d2950e0b0b65_kzkz111_1743841064539.md', 'LOCAL', 1743841064538, 'kzkz111', 1743841064538, 'kzkz111');
INSERT INTO `kz_blog` VALUES (5, 'this is api test title(命运黑白棋)', 'kzkz111', 'this is api test title(命运黑白棋)', '', 'md', 'E:\\files\\book\\679e5998ae561dd76e3098008226d2950e0b0b65_kzkz111_1743841834000.md', 'LOCAL', 1743841834000, 'kzkz111', 1743841834000, 'kzkz111');
INSERT INTO `kz_blog` VALUES (6, 'asdasdsa', 'kzkz111', 'asdasdsa', 'asdasdasd', 'md', 'E:\\files\\book\\1747eea56fc7e9f2ad29c2c204a5b22fe63b918b_kzkz111_1743868664154.md', 'LOCAL', 1743868664152, 'kzkz111', 1743868664152, 'kzkz111');
INSERT INTO `kz_blog` VALUES (7, 'aaaa', 'kzkz111', 'aaaa', 'sssss', 'md', 'E:\\files\\book\\0988de25e34d8ab786f604b80d403ca0e6460214_kzkz111_1744445044178.md', 'LOCAL', 1744445044177, 'kzkz111', 1744445044177, 'kzkz111');
INSERT INTO `kz_blog` VALUES (8, 'aaaa', 'kzkz111', 'aaaa', 'asdasd', 'md', 'E:\\files\\book\\91f180f4c78951c84ed7ffb71dc4b5807571eccd_kzkz111_1746952780959.md', 'LOCAL', 1746952780958, 'kzkz111', 1746952780958, 'kzkz111');

-- ----------------------------
-- Table structure for kz_test
-- ----------------------------
DROP TABLE IF EXISTS `kz_test`;
CREATE TABLE `kz_test`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_test
-- ----------------------------
INSERT INTO `kz_test` VALUES (1);

-- ----------------------------
-- Table structure for kz_user
-- ----------------------------
DROP TABLE IF EXISTS `kz_user`;
CREATE TABLE `kz_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `kz_account_id` int(0) NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_kz_user_account_id`(`kz_account_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
