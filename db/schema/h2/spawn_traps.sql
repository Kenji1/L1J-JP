-- ----------------------------
-- Table structure for `SPAWN_TRAPS`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_TRAPS`;
CREATE TABLE `SPAWN_TRAPS` (
  `ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `TRAP_ID` INT NOT NULL,
  `MAP_ID` INT NOT NULL,
  `LOC_X` INT NOT NULL,
  `LOC_Y` INT NOT NULL,
  `LOC_RND_X` INT NOT NULL DEFAULT '0',
  `LOC_RND_Y` INT NOT NULL DEFAULT '0',
  `COUNT` INT NOT NULL DEFAULT '1',
  `SPAN` INT NOT NULL
);
-- ----------------------------
-- Index for `SPAWN_TRAPS`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_TRAPS_PK` ON `SPAWN_TRAPS`(`ID`);
