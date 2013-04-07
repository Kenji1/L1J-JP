-- ----------------------------
-- Table structure for `DROP_RATES`
-- ----------------------------
DROP TABLE IF EXISTS `DROP_RATES`;
CREATE TABLE `DROP_RATES` (
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `DROP_RATE` FLOAT NOT NULL DEFAULT '1',
  `DROP_AMOUNT` FLOAT NOT NULL DEFAULT '1',
  `UNIQUE_RATE` FLOAT NOT NULL DEFAULT '1'
);
-- ----------------------------
-- Index for `DROP_RATES`
-- ----------------------------
CREATE PRIMARY KEY `DROP_RATES_PK` ON `DROP_RATES`(`ITEM_ID`);
