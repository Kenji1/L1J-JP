-- ----------------------------
-- Table structure for `SPAWNLIST_LIGHT`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_LIGHT`;
CREATE TABLE `SPAWNLIST_LIGHT` (
  `ID` INT NOT NULL,
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `LOC_X` INT NOT NULL DEFAULT '0',
  `LOC_Y` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWNLIST_LIGHT`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_LIGHT_PK` ON `SPAWNLIST_LIGHT`(`ID`);
