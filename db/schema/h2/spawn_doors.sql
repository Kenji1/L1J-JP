-- ----------------------------
-- Table structure for `SPAWN_DOORS`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_DOORS`;
CREATE TABLE `SPAWN_DOORS` (
  `ID` INT NOT NULL,
  `MAP_ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `GFX_ID` INT NOT NULL,
  `LOC_X` INT NOT NULL,
  `LOC_Y` INT NOT NULL,
  `HP` INT NOT NULL DEFAULT '0',
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `IS_OPEN` BOOLEAN NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWN_DOORS`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_DOORS_PK` ON `SPAWN_DOORS`(`ID`);
