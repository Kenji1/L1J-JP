-- ----------------------------
-- Table structure for `CHARACTER_CONFIG`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CHARACTER_CONFIG` (
  `OBJECT_ID` INT NOT NULL DEFAULT '0',
  `LENGTH` INT NOT NULL DEFAULT '0',
  `DATA` BLOB
);
-- ----------------------------
-- Index for `CHARACTER_CONFIG`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CHARACTER_CONFIG_PK` ON `CHARACTER_CONFIG`(`OBJECT_ID`);
