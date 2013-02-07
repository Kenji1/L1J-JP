SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ITEM_RATES`
-- ----------------------------
DROP TABLE IF EXISTS `ITEM_RATES`;
CREATE TABLE `ITEM_RATES` (
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `ITEM_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `SELLING_PRICE` INT NOT NULL DEFAULT '-1',
  `PURCHASING_PRICE` INT NOT NULL DEFAULT '-1'
);
-- ----------------------------
-- Index for `ITEM_RATES`
-- ----------------------------
CREATE PRIMARY KEY `ITEM_RATES_PK` ON `ITEM_RATES`(`ITEM_ID`);
