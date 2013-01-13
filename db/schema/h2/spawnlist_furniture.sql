-- ----------------------------
-- Table structure for `SPAWNLIST_FURNITURE`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_FURNITURE`;
CREATE TABLE `SPAWNLIST_FURNITURE` (
  `ITEM_OBJ_ID` INT NOT NULL DEFAULT '0',
  `NPCID` INT NOT NULL DEFAULT '0',
  `LOCX` INT NOT NULL DEFAULT '0',
  `LOCY` INT NOT NULL DEFAULT '0',
  `MAPID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWNLIST_FURNITURE`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_FURNITURE_PK` ON `SPAWNLIST_FURNITURE`(`ITEM_OBJ_ID`);
