-- ----------------------------
-- Table structure for `SHOPS`
-- ----------------------------
DROP TABLE IF EXISTS `SHOPS`;
CREATE TABLE `SHOPS` (
  `NPC_ID` INT NOT NULL,
  `ITEM_ID` INT NOT NULL,
  `ORDER_ID` INT NOT NULL,
  `PACK_COUNT` INT NOT NULL DEFAULT '0',
);
-- ----------------------------
-- Index for `SHOPS`
-- ----------------------------
CREATE PRIMARY KEY `SHOPS_PK` ON `SHOPS`(`NPC_ID`,`ITEM_ID`,`ORDER_ID`);
