-- ----------------------------
-- Table structure for `MAIL`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `MAIL` (
  `ID` INT NOT NULL DEFAULT '0',
  `TYPE` INT NOT NULL DEFAULT '0',
  `SENDER` VARCHAR(255) DEFAULT NULL,
  `RECEIVER` VARCHAR(255) DEFAULT NULL,
  `DATE` VARCHAR(255) DEFAULT NULL,
  `READ_STATUS` BOOLEAN NOT NULL DEFAULT '0',
  `SUBJECT` BLOB,
  `CONTENT` BLOB
);
-- ----------------------------
-- Index for `MAIL`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `MAIL_PK` ON `MAIL`(`ID`);
