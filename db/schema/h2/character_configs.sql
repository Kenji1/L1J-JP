-- ----------------------------
-- Table structure for `CHARACTER_CONFIGS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CHARACTER_CONFIGS` (
  `CHAR_ID` INT NOT NULL,
  `LENGTH` INT NOT NULL DEFAULT '0',
  `DATA` BLOB
);
-- ----------------------------
-- Index for `CHARACTER_CONFIGS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CHARACTER_CONFIGS_PK` ON `CHARACTER_CONFIGS`(`CHAR_ID`);
