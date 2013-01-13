-- ----------------------------
-- Table structure for `SHOP`
-- ----------------------------
DROP TABLE IF EXISTS `SHOP`;
CREATE TABLE `SHOP` (
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `ORDER_ID` INT NOT NULL DEFAULT '0',
  `SELLING_PRICE` INT NOT NULL DEFAULT '-1',
  `PACK_COUNT` INT NOT NULL DEFAULT '0',
  `PURCHASING_PRICE` INT NOT NULL DEFAULT '-1'
);
-- ----------------------------
-- Index for `SHOP`
-- ----------------------------
CREATE PRIMARY KEY `SHOP_PK` ON `SHOP`(`NPC_ID`,`ITEM_ID`,`ORDER_ID`);
