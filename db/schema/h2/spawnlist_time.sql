-- ----------------------------
-- Table structure for `SPAWNLIST_TIME`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWNLIST_TIME`;
CREATE TABLE `SPAWNLIST_TIME` (
  `SPAWN_ID` INT NOT NULL,
  `TIME_START` TIME DEFAULT NULL,
  `TIME_END` TIME DEFAULT NULL,
  `DELETE_AT_ENDTIME` BOOLEAN NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWNLIST_TIME`
-- ----------------------------
CREATE PRIMARY KEY `SPAWNLIST_TIME_PK` ON `SPAWNLIST_TIME`(`SPAWN_ID`);
