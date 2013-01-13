-- ----------------------------
-- Table structure for `DOOR_GFXS`
-- ----------------------------
DROP TABLE IF EXISTS `DOOR_GFXS`;
CREATE TABLE `DOOR_GFXS` (
  `GFX_ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `DIRECTION` TINYINT NOT NULL,
  `LEFT_EDGE_OFFSET` INT NOT NULL,
  `RIGHT_EDGE_OFFSET` INT NOT NULL
);
-- ----------------------------
-- Index for `DOOR_GFXS`
-- ----------------------------
CREATE PRIMARY KEY `DOOR_GFXS_PK` ON `DOOR_GFXS`(`GFX_ID`);
