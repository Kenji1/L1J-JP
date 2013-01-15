-- ----------------------------
-- Table structure for `SPAWNLIST_FURNITURE`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_FURNITURE`;
CREATE TABLE `SPAWNLIST_FURNITURE` (
  `ITEM_OBJ_ID` INT NOT NULL DEFAULT '0',
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `LOC_X` INT NOT NULL DEFAULT '0',
  `LOC_Y` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWNLIST_FURNITURE`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_FURNITURE_PK` ON `SPAWNLIST_FURNITURE`(`ITEM_OBJ_ID`);
