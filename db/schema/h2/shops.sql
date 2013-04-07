-- ----------------------------
-- Table structure for `SHOPS`
-- ----------------------------
DROP TABLE IF EXISTS `SHOPS`;
CREATE TABLE `SHOPS` (
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `ORDER_ID` INT NOT NULL DEFAULT '0',
  `PACK_COUNT` INT NOT NULL DEFAULT '0',
);
-- ----------------------------
-- Index for `SHOPS`
-- ----------------------------
CREATE PRIMARY KEY `SHOPS_PK` ON `SHOPS`(`NPC_ID`,`ITEM_ID`,`ORDER_ID`);
