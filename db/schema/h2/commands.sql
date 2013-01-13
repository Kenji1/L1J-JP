-- ----------------------------
-- Table structure for `COMMANDS`
-- ----------------------------
DROP TABLE IF EXISTS `COMMANDS`;
CREATE TABLE `COMMANDS` (
  `NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `ACCESS_LEVEL` INT NOT NULL DEFAULT '200',
  `CLASS_NAME` VARCHAR(255) NOT NULL DEFAULT ''
);
-- ----------------------------
-- Index for `COMMANDS`
-- ----------------------------
CREATE PRIMARY KEY `COMMANDS_PK` ON `COMMANDS`(`NAME`);
