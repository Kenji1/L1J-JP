-- ----------------------------
-- Table structure for `INN`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `INN` (
  `NAME` VARCHAR(255) DEFAULT '',
  `NPCID` INT NOT NULL,
  `ROOM_NUMBER` INT NOT NULL,
  `KEY_ID` INT DEFAULT NULL,
  `LODGER_ID` INT DEFAULT NULL,
  `HALL` INT DEFAULT NULL,
  `DUE_TIME` DATETIME DEFAULT NULL
);
-- ----------------------------
-- Index for `INN`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `INN_PK` ON `INN`(`NPCID`,`ROOM_NUMBER`);
