-- ----------------------------
-- Table structure for `CASTLE`
-- ----------------------------
DROP TABLE IF EXISTS `CASTLE`;
CREATE TABLE `CASTLE` (
  `CASTLE_ID` TINYINT NOT NULL DEFAULT '0',
  `NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `WAR_TIME` DATETIME DEFAULT NULL,
  `TAX_RATE` TINYINT NOT NULL DEFAULT '0',
  `PUBLIC_MONEY` INT NOT NULL DEFAULT '0',
);
-- ----------------------------
-- Index for `CASTLE`
-- ----------------------------
CREATE PRIMARY KEY `CASTLE_PK` ON `CASTLE`(`CASTLE_ID`);
