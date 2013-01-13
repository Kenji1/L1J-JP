-- ----------------------------
-- Table structure for `SPAWNLIST_LIGHT`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_LIGHT`;
CREATE TABLE `SPAWNLIST_LIGHT` (
  `ID` INT NOT NULL,
  `NPCID` INT NOT NULL DEFAULT '0',
  `LOCX` INT NOT NULL DEFAULT '0',
  `LOCY` INT NOT NULL DEFAULT '0',
  `MAPID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWNLIST_LIGHT`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_LIGHT_PK` ON `SPAWNLIST_LIGHT`(`ID`);
