-- ----------------------------
-- Table structure for `PETS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PETS` (
  `ITEM_OBJ_ID` INT NOT NULL DEFAULT '0',
  `OBJ_ID` INT NOT NULL DEFAULT '0',
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `LEVEL` INT NOT NULL DEFAULT '0',
  `HP` INT NOT NULL DEFAULT '0',
  `MP` INT NOT NULL DEFAULT '0',
  `EXP` INT NOT NULL DEFAULT '0',
  `LAWFUL` INT NOT NULL DEFAULT '0',
  `FOOD` TINYINT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `PETS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `PETS_PK` ON `PETS`(`ITEM_OBJ_ID`);
