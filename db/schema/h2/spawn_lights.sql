-- ----------------------------
-- Table structure for `SPAWN_LIGHTS`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_LIGHTS`;
CREATE TABLE `SPAWN_LIGHTS` (
  `ID` INT NOT NULL,
  `NPC_ID` INT NOT NULL,
  `LOC_X` INT NOT NULL,
  `LOC_Y` INT NOT NULL,
  `MAP_ID` INT NOT NULL
);
-- ----------------------------
-- Index for `SPAWN_LIGHTS`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_LIGHTS_PK` ON `SPAWN_LIGHTS`(`ID`);
