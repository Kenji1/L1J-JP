-- ----------------------------
-- Table structure for `RESOLVENT`
-- ----------------------------
DROP TABLE IF EXISTS `RESOLVENT`;
CREATE TABLE `RESOLVENT` (
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `NOTE` VARCHAR(255) NOT NULL,
  `CRYSTAL_COUNT` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `RESOLVENT`
-- ----------------------------
CREATE PRIMARY KEY `RESOLVENT_PK` ON `RESOLVENT`(`ITEM_ID`);
