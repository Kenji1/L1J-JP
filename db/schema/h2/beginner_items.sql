-- ----------------------------
-- Table structure for `BEGINNER_ITEMS`
-- ----------------------------
DROP TABLE IF EXISTS `BEGINNER_ITEMS`;
CREATE TABLE `BEGINNER_ITEMS` (
  `ID` INT NOT NULL,
  `ITEM_ID` INT NOT NULL DEFAULT '0',
  `ITEM_COUNT` INT NOT NULL DEFAULT '0',
  `CHARGE_COUNT` INT NOT NULL DEFAULT '0',
  `ENCHANT_LEVEL` TINYINT NOT NULL DEFAULT '0',
  `CLASS_INITIAL` VARCHAR(1) NOT NULL DEFAULT 'A'
);
-- ----------------------------
-- Index for `BEGINNER_ITEMS`
-- ----------------------------
CREATE PRIMARY KEY `BEGINNER_ITEMS_PK` ON `BEGINNER_ITEMS`(`ID`);
CREATE INDEX `BEGINNER_ITEMS_CLASS_INITIAL` ON `BEGINNER_ITEMS`(`CLASS_INITIAL`);
