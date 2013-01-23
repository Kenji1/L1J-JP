-- ----------------------------
-- Table structure for `INN_KEYS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `INN_KEYS` (
  `ITEM_OBJ_ID` INT NOT NULL,
  `KEY_ID` INT NOT NULL,
  `NPC_ID` INT DEFAULT NULL,
  `HALL` INT DEFAULT NULL,
  `DUE_TIME` DATETIME DEFAULT NULL
);
-- ----------------------------
-- Index for `INN_KEYS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `INN_KEYS_PK` ON `INN_KEYS`(`ITEM_OBJ_ID`,`KEY_ID`);
