-- ----------------------------
-- Table structure for `HOUSES`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `HOUSES` (
  `ID` INT NOT NULL DEFAULT '0',
  `NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `AREA` INT NOT NULL DEFAULT '0',
  `LOCATION` VARCHAR(255) NOT NULL DEFAULT '',
  `KEEPER_ID` INT NOT NULL DEFAULT '0',
  `IS_ON_SALE` BOOLEAN NOT NULL DEFAULT '0',
  `IS_PURCHASE_BASEMENT` BOOLEAN NOT NULL DEFAULT '0',
  `TAX_DEADLINE` DATETIME DEFAULT NULL
);
-- ----------------------------
-- Index for `HOUSES`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `HOUSES_PK` ON `HOUSES`(`ID`);
