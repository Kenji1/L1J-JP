-- ----------------------------
-- Table structure for `SPAWN_UB_MOBS`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_UB_MOBS`;
CREATE TABLE `SPAWN_UB_MOBS` (
  `ID` INT NOT NULL,
  `UB_ID` INT NOT NULL DEFAULT '0',
  `PATTERN` INT NOT NULL DEFAULT '0',
  `GROUP_ID` INT NOT NULL DEFAULT '0',
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `COUNT` INT NOT NULL DEFAULT '0',
  `SPAWN_DELAY` INT NOT NULL DEFAULT '0',
  `SEAL_COUNT` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWN_UB_MOBS`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_UB_MOBS_PK` ON `SPAWN_UB_MOBS`(`ID`);
