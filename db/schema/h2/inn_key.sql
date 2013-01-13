-- ----------------------------
-- Table structure for `INN_KEY`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `INN_KEY` (
  `ITEM_OBJ_ID` INT NOT NULL,
  `KEY_ID` INT NOT NULL,
  `NPC_ID` INT DEFAULT NULL,
  `HALL` INT DEFAULT NULL,
  `DUE_TIME` DATETIME DEFAULT NULL
);
-- ----------------------------
-- Index for `INN_KEY`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `INN_KEY_PK` ON `INN_KEY`(`ITEM_OBJ_ID`,`KEY_ID`);
