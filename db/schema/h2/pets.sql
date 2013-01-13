-- ----------------------------
-- Table structure for `PETS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PETS` (
  `ITEM_OBJ_ID` INT NOT NULL DEFAULT '0',
  `OBJID` INT NOT NULL DEFAULT '0',
  `NPCID` INT NOT NULL DEFAULT '0',
  `NAME` BOOLEAN(45) NOT NULL DEFAULT '',
  `LVL` INT NOT NULL DEFAULT '0',
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
