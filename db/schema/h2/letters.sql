-- ----------------------------
-- Table structure for `LETTERS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `LETTERS` (
  `ITEM_OBJ_ID` INT NOT NULL,
  `CODE` INT NOT NULL DEFAULT '0',
  `SENDER` VARCHAR(255) DEFAULT NULL,
  `RECEIVER` VARCHAR(255) DEFAULT NULL,
  `DATE` VARCHAR(255) DEFAULT NULL,
  `TEMPLATE_ID` INT NOT NULL DEFAULT '0',
  `SUBJECT` BLOB,
  `CONTENT` BLOB
);
-- ----------------------------
-- Index for `LETTERS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `LETTERS_PK` ON `LETTERS`(`ITEM_OBJ_ID`);
