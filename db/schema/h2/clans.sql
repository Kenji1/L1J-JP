-- ----------------------------
-- Table structure for `CLANS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CLANS` (
  `CLAN_ID` INT NOT NULL AUTO_INCREMENT,
  `CLAN_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `LEADER_ID` INT NOT NULL DEFAULT '0',
  `LEADER_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `HAS_CASTLE` TINYINT NOT NULL DEFAULT '0',
  `HAS_HOUSE` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `CLANS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CLANS_PK` ON `CLANS`(`CLAN_ID`);
