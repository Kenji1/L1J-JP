-- ----------------------------
-- Table structure for `CHARACTER_CONFIGS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CHARACTER_CONFIGS` (
  `OBJECT_ID` INT NOT NULL DEFAULT '0',
  `LENGTH` INT NOT NULL DEFAULT '0',
  `DATA` BLOB
);
-- ----------------------------
-- Index for `CHARACTER_CONFIGS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CHARACTER_CONFIGS_PK` ON `CHARACTER_CONFIGS`(`OBJECT_ID`);
