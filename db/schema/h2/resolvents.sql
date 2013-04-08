-- ----------------------------
-- Table structure for `RESOLVENTS`
-- ----------------------------
DROP TABLE IF EXISTS `RESOLVENTS`;
CREATE TABLE `RESOLVENTS` (
  `ITEM_ID` INT NOT NULL,
  `CRYSTAL_COUNT` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `RESOLVENTS`
-- ----------------------------
CREATE PRIMARY KEY `RESOLVENTS_PK` ON `RESOLVENTS`(`ITEM_ID`);
