-- ----------------------------
-- Table structure for `SPAWN_UB`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_UB`;
CREATE TABLE `SPAWN_UB` (
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
-- Index for `SPAWN_UB`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_UB_PK` ON `SPAWN_UB`(`ID`);
