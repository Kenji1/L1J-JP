-- ----------------------------
-- Table structure for `SPAWN_FURNITURES`
-- ----------------------------
DROP TABLE IF EXISTS `SPAWN_FURNITURES`;
CREATE TABLE `SPAWN_FURNITURES` (
  `ITEM_OBJ_ID` INT NOT NULL,
  `NPC_ID` INT NOT NULL,
  `LOC_X` INT NOT NULL,
  `LOC_Y` INT NOT NULL,
  `MAP_ID` INT NOT NULL
);
-- ----------------------------
-- Index for `SPAWN_FURNITURES`
-- ----------------------------
CREATE PRIMARY KEY `SPAWN_FURNITURES_PK` ON `SPAWN_FURNITURES`(`ITEM_OBJ_ID`);
