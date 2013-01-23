-- ----------------------------
-- Table structure for `SPAWN_DOORS`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_DOORS`;
CREATE TABLE `SPAWN_DOORS` (
  `ID` INT NOT NULL DEFAULT '0',
  `LOCATION` VARCHAR(255) NOT NULL DEFAULT '',
  `GFX_ID` INT NOT NULL DEFAULT '0',
  `LOC_X` INT NOT NULL DEFAULT '0',
  `LOC_Y` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0',
  `HP` INT NOT NULL DEFAULT '0',
  `KEEPER` INT NOT NULL DEFAULT '0',
  `IS_OPEN` BOOLEAN NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWN_DOORS`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_DOORS_PK` ON `SPAWN_DOORS`(`ID`);
