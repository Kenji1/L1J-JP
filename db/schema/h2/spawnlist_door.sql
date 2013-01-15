-- ----------------------------
-- Table structure for `SPAWNLIST_DOOR`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_DOOR`;
CREATE TABLE `SPAWNLIST_DOOR` (
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
-- Index for `SPAWNLIST_DOOR`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_DOOR_PK` ON `SPAWNLIST_DOOR`(`ID`);
