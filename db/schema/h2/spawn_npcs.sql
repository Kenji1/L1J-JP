-- ----------------------------
-- Table structure for `SPAWN_NPCS`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_NPCS`;
CREATE TABLE `SPAWN_NPCS` (
  `ID` INT NOT NULL,
  `COUNT` INT NOT NULL DEFAULT '0',
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `LOC_X` INT NOT NULL DEFAULT '0',
  `LOC_Y` INT NOT NULL DEFAULT '0',
  `RANDOM_X` INT NOT NULL DEFAULT '0',
  `RANDOM_Y` INT NOT NULL DEFAULT '0',
  `HEADING` INT NOT NULL DEFAULT '0' COMMENT '↑1 ↗2→3↘4↓5 ↙6←7↖0',
  `RESPAWN_DELAY` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0',
  `MOVEMENT_DISTANCE` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWN_NPCS`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_NPCS_PK` ON `SPAWN_NPCS`(`ID`);
