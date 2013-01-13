-- ----------------------------
-- Table structure for `DROPLIST`
-- ----------------------------
DROP TABLE IF EXISTS `DROPLIST`;
CREATE TABLE `DROPLIST`(
  `MOB_ID` INT NOT NULL DEFAULT '0',
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `MIN` INT NOT NULL DEFAULT '0',
  `MAX` INT NOT NULL DEFAULT '0',
  `CHANCE` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `DROPLIST`
-- ----------------------------
CREATE PRIMARY KEY `DROPLIST_PK` ON `DROPLIST`(`MOB_ID`,`ITEM_ID`);
