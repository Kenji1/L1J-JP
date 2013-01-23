-- ----------------------------
-- Table structure for `DROP_ITEMS`
-- ----------------------------
DROP TABLE IF EXISTS `DROP_ITEMS`;
CREATE TABLE `DROP_ITEMS`(
  `MOB_ID` INT NOT NULL DEFAULT '0',
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `MIN` INT NOT NULL DEFAULT '0',
  `MAX` INT NOT NULL DEFAULT '0',
  `CHANCE` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `DROP_ITEMS`
-- ----------------------------
CREATE PRIMARY KEY `DROP_ITEMS_PK` ON `DROP_ITEMS`(`MOB_ID`,`ITEM_ID`);
