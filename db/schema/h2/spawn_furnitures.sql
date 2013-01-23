-- ----------------------------
-- Table structure for `SPAWN_FURNITURES`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_FURNITURES`;
CREATE TABLE `SPAWN_FURNITURES` (
  `ITEM_OBJ_ID` INT NOT NULL DEFAULT '0',
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `LOC_X` INT NOT NULL DEFAULT '0',
  `LOC_Y` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `SPAWN_FURNITURES`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_FURNITURES_PK` ON `SPAWN_FURNITURES`(`ITEM_OBJ_ID`);
