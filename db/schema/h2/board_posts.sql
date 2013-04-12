-- ----------------------------
-- Table structure for `BOARD_POSTS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `BOARD_POSTS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) DEFAULT NULL,
  `DATE` VARCHAR(255) DEFAULT NULL,
  `TITLE` VARCHAR(255) DEFAULT NULL,
  `CONTENT` VARCHAR(1000) DEFAULT NULL
);
-- ----------------------------
-- Index for `BOARD_POSTS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `BOARD_PK` ON `BOARD_POSTS`(`ID`);
