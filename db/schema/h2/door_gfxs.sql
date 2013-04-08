-- ----------------------------
-- Table structure for `DOOR_GFXS`
-- ----------------------------
DROP TABLE IF EXISTS `DOOR_GFXS`;
CREATE TABLE `DOOR_GFXS` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(255) DEFAULT NULL,
  `DIRECTION` TINYINT NOT NULL DEFAULT '0',
  `LEFT_EDGE_OFFSET` INT NOT NULL DEFAULT '0',
  `RIGHT_EDGE_OFFSET` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `DOOR_GFXS`
-- ----------------------------
CREATE PRIMARY KEY `DOOR_GFXS_PK` ON `DOOR_GFXS`(`ID`);
