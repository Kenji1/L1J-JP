-- ----------------------------
-- Table structure for `DUNGEONS`
-- ----------------------------
DROP TABLE IF EXISTS `DUNGEONS`;
CREATE TABLE `DUNGEONS` (
  `SRC_X` INT NOT NULL DEFAULT '0',
  `SRC_Y` INT NOT NULL DEFAULT '0',
  `SRC_MAP_ID` INT NOT NULL DEFAULT '0',
  `NEW_X` INT NOT NULL DEFAULT '0',
  `NEW_Y` INT NOT NULL DEFAULT '0',
  `NEW_MAP_ID` INT NOT NULL DEFAULT '0',
  `NEW_HEADING` TINYINT NOT NULL DEFAULT '1',
  `NOTE` VARCHAR(255) NOT NULL DEFAULT ''
);
-- ----------------------------
-- Index for `DUNGEONS`
-- ----------------------------
CREATE PRIMARY KEY `DUNGEONS_PK` ON `DUNGEONS`(`SRC_X`,`SRC_Y`,`SRC_MAP_ID`);
