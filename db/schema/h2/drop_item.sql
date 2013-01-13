-- ----------------------------
-- Table structure for `DROP_ITEM`
-- ----------------------------
DROP TABLE IF EXISTS `DROP_ITEM`;
CREATE TABLE `DROP_ITEM` (
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `DROP_RATE` FLOAT NOT NULL DEFAULT '1',
  `DROP_AMOUNT` FLOAT NOT NULL DEFAULT '1',
  `UNIQUE_RATE` FLOAT NOT NULL DEFAULT '1',
  `NOTE` VARCHAR(255) NOT NULL DEFAULT ''
);
-- ----------------------------
-- Index for `DROP_ITEM`
-- ----------------------------
CREATE PRIMARY KEY `DROP_ITEM_PK` ON `DROP_ITEM`(`ITEM_ID`);
