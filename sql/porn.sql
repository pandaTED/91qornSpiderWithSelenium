SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for porn91
-- ----------------------------
DROP TABLE IF EXISTS `porn91`;
CREATE TABLE `porn91`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `view_times` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `video_download_time` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `img_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `page_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `video_play_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `video_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `is_img_download` int(11) NULL DEFAULT NULL,
  `is_video_download` int(11) NULL DEFAULT NULL,
  `click_times` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `page_url`) USING BTREE,
  UNIQUE INDEX `page_url`(`page_url`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 614989 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for spider_history
-- ----------------------------
DROP TABLE IF EXISTS `spider_history`;
CREATE TABLE `spider_history`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `last_time` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `spider_flag` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33623 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
