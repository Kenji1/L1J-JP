-- ----------------------------
-- Table structure for `SPAWNLIST_UB`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_UB`;
CREATE TABLE `SPAWNLIST_UB` (
  `ID` INT NOT NULL,
  `UB_ID` INT NOT NULL DEFAULT '0',
  `PATTERN` INT NOT NULL DEFAULT '0',
  `GROUP_ID` INT NOT NULL DEFAULT '0',
  `LOCATION` VARCHAR(255) NOT NULL DEFAULT '',
  `NPC_TEMPLATE_ID` INT NOT NULL DEFAULT '0',
  `COUNT` INT NOT NULL DEFAULT '0',
  `SPAWN_DELAY` INT NOT NULL DEFAULT '0',
  `SEAL_COUNT` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWNLIST_UB`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_UB_PK` ON `SPAWNLIST_UB`(`ID`);
