-- ----------------------------
-- Table structure for `BOARD`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `BOARD` (
  `ID` INT NOT NULL DEFAULT '0',
  `NAME` VARCHAR(255) DEFAULT NULL,
  `DATE` VARCHAR(255) DEFAULT NULL,
  `TITLE` VARCHAR(255) DEFAULT NULL,
  `CONTENT` VARCHAR(1000) DEFAULT NULL
);
-- ----------------------------
-- Index for `BOARD`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `BOARD_PK` ON `BOARD`(`ID`);
