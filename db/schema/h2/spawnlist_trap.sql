-- ----------------------------
-- Table structure for `SPAWNLIST_TRAP`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_TRAP`;
CREATE TABLE `SPAWNLIST_TRAP` (
  `ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `TRAP_ID` INT NOT NULL,
  `MAPID` INT NOT NULL,
  `LOCX` INT NOT NULL,
  `LOCY` INT NOT NULL,
  `LOC_RND_X` INT NOT NULL DEFAULT '0',
  `LOC_RND_Y` INT NOT NULL DEFAULT '0',
  `COUNT` INT NOT NULL DEFAULT '1',
  `SPAN` INT NOT NULL
);
-- ----------------------------
-- Index for `SPAWNLIST_TRAP`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_TRAP_PK` ON `SPAWNLIST_TRAP`(`ID`);
