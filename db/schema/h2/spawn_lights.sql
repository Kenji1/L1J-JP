-- ----------------------------
-- Table structure for `SPAWN_LIGHTS`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_LIGHTS`;
CREATE TABLE `SPAWN_LIGHTS` (
  `ID` INT NOT NULL,
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `LOC_X` INT NOT NULL DEFAULT '0',
  `LOC_Y` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWN_LIGHTS`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_LIGHTS_PK` ON `SPAWN_LIGHTS`(`ID`);
