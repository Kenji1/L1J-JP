-- ----------------------------
-- Table structure for `LOG_ENCHANT`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `LOG_ENCHANT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CHAR_ID` INT NOT NULL DEFAULT '0',
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `OLD_ENCHANTLVL` TINYINT NOT NULL DEFAULT '0',
  `NEW_ENCHANTLVL` TINYINT DEFAULT '0'
);
-- ----------------------------
-- Index for `LOG_ENCHANT`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `LOG_ENCHANT_PK` ON `LOG_ENCHANT`(`ID`);
CREATE INDEX IF NOT EXISTS `LOG_ENCHANT_CHAR_ID` ON `LOG_ENCHANT`(`CHAR_ID`);
