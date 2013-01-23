-- ----------------------------
-- Table structure for `SPAWN_NPC`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_NPC`;
CREATE TABLE `SPAWN_NPC` (
  `ID` INT NOT NULL,
  `LOCATION` varchar(255) NOT NULL DEFAULT '',
  `COUNT` INT NOT NULL DEFAULT '0',
  `NPC_TEMPLATE_ID` INT NOT NULL DEFAULT '0',
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
-- Index for `SPAWN_NPC`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_NPC_PK` ON `SPAWN_NPC`(`ID`);
