-- ----------------------------
-- Table structure for `SPAWN_TIMES`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_TIMES`;
CREATE TABLE `SPAWN_TIMES` (
  `NPC_ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `TIME_START` TIME DEFAULT NULL,
  `TIME_END` TIME DEFAULT NULL,
  `DELETE_AT_ENDTIME` BOOLEAN NOT NULL DEFAULT '1'
);
-- ----------------------------
-- Index for `SPAWN_TIMES`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_TIMES_PK` ON `SPAWN_TIMES`(`NPC_ID`);
