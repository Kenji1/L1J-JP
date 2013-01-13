-- ----------------------------
-- Table structure for `NPCACTION`
-- ----------------------------
DROP TABLE IF EXISTS `NPCACTION`;
CREATE TABLE `NPCACTION` (
  `NPCID` INT NOT NULL DEFAULT '0',
  `NORMAL_ACTION` VARCHAR(255) NOT NULL DEFAULT '',
  `CAOTIC_ACTION` VARCHAR(255) NOT NULL DEFAULT '',
  `TELEPORT_URL` VARCHAR(255) NOT NULL DEFAULT '',
  `TELEPORT_URLA` VARCHAR(255) NOT NULL DEFAULT ''
);
-- ----------------------------
-- Index for `NPCACTION`
-- ----------------------------
CREATE PRIMARY KEY `NPCACTION_PK` ON `NPCACTION`(`NPCID`);
