-- ----------------------------
-- Table structure for `LETTER`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `LETTER` (
  `ITEM_OBJECT_ID` INT NOT NULL DEFAULT '0',
  `CODE` INT NOT NULL DEFAULT '0',
  `SENDER` VARCHAR(255) DEFAULT NULL,
  `RECEIVER` VARCHAR(255) DEFAULT NULL,
  `DATE` VARCHAR(255) DEFAULT NULL,
  `TEMPLATE_ID` INT NOT NULL DEFAULT '0',
  `SUBJECT` BLOB,
  `CONTENT` BLOB
);
-- ----------------------------
-- Index for `LETTER`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `LETTER_PK` ON `LETTER`(`ITEM_OBJECT_ID`);
