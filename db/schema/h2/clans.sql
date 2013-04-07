-- ----------------------------
-- Table structure for `CLANS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CLANS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `LEADER_ID` INT NOT NULL DEFAULT '0',
  `HAS_CASTLE` TINYINT NOT NULL DEFAULT '0',
  `HAS_HOUSE` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `CLANS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CLANS_PK` ON `CLANS`(`ID`);
