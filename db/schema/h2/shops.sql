-- ----------------------------
-- Table structure for `SHOPS`
-- ----------------------------
DROP TABLE IF EXISTS `SHOPS`;
CREATE TABLE `SHOPS` (
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `NPC_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `ITEM_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `ORDER_ID` INT NOT NULL DEFAULT '0',
  `SELLING_PRICE` INT NOT NULL DEFAULT '-1',
  `PACK_COUNT` INT NOT NULL DEFAULT '0',
  `PURCHASING_PRICE` INT NOT NULL DEFAULT '-1'
);
-- ----------------------------
-- Index for `SHOPS`
-- ----------------------------
CREATE PRIMARY KEY `SHOPS_PK` ON `SHOPS`(`NPC_ID`,`ITEM_ID`,`ORDER_ID`);
