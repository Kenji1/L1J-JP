-- ----------------------------
-- Table structure for `DROP_ITEMS`
-- ----------------------------
DROP TABLE IF EXISTS `DROP_ITEMS`;
CREATE TABLE `DROP_ITEMS`(
  `NPC_ID` INT NOT NULL,
  `ITEM_ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `MIN` INT NOT NULL DEFAULT '0',
  `MAX` INT NOT NULL DEFAULT '0',
  `CHANCE` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `DROP_ITEMS`
-- ----------------------------
CREATE PRIMARY KEY `DROP_ITEMS_PK` ON `DROP_ITEMS`(`NPC_ID`,`ITEM_ID`);
