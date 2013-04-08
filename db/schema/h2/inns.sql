-- ----------------------------
-- Table structure for `INNS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `INNS` (
  `NAME` VARCHAR(255) DEFAULT NULL,
  `NPC_ID` INT NOT NULL,
  `ROOM_NUMBER` INT NOT NULL,
  `KEY_ID` INT NOT NULL DEFAULT '0',
  `LODGER_ID` INT NOT NULL DEFAULT '0',
  `HALL` INT NOT NULL DEFAULT '0',
  `DUE_TIME` DATETIME DEFAULT NULL
);
-- ----------------------------
-- Index for `INNS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `INNS_PK` ON `INNS`(`NPC_ID`,`ROOM_NUMBER`);
