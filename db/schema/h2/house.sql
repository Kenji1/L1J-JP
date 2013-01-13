-- ----------------------------
-- Table structure for `HOUSE`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `HOUSE` (
  `HOUSE_ID` INT NOT NULL DEFAULT '0',
  `HOUSE_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `HOUSE_AREA` INT NOT NULL DEFAULT '0',
  `LOCATION` VARCHAR(255) NOT NULL DEFAULT '',
  `KEEPER_ID` INT NOT NULL DEFAULT '0',
  `IS_ON_SALE` BOOLEAN NOT NULL DEFAULT '0',
  `IS_PURCHASE_BASEMENT` BOOLEAN NOT NULL DEFAULT '0',
  `TAX_DEADLINE` DATETIME DEFAULT NULL
);
-- ----------------------------
-- Index for `HOUSE`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `HOUSE_PK` ON `HOUSE`(`HOUSE_ID`);
