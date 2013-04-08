-- ----------------------------
-- Table structure for `MAILS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `MAILS` (
  `ID` INT NOT NULL,
  `TYPE` INT NOT NULL DEFAULT '0',
  `SENDER` VARCHAR(255) DEFAULT NULL,
  `RECEIVER` VARCHAR(255) DEFAULT NULL,
  `DATE` VARCHAR(255) DEFAULT NULL,
  `READ_STATUS` BOOLEAN NOT NULL DEFAULT '0',
  `SUBJECT` BLOB,
  `CONTENT` BLOB
);
-- ----------------------------
-- Index for `MAILS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `MAILS_PK` ON `MAILS`(`ID`);
