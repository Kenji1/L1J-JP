-- ----------------------------
-- Table structure for `CLAN_DATA`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CLAN_DATA` (
  `CLAN_ID` INT NOT NULL AUTO_INCREMENT,
  `CLAN_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `LEADER_ID` INT NOT NULL DEFAULT '0',
  `LEADER_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `HAS_CASTLE` TINYINT NOT NULL DEFAULT '0',
  `HAS_HOUSE` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `CLAN_DATA`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CLAN_DATA_PK` ON `CLAN_DATA`(`CLAN_ID`);
