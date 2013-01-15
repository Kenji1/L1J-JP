-- ----------------------------
-- Table structure for SPR_ACTION
-- ----------------------------
DROP TABLE IF EXISTS `SPR_ACTION`;
CREATE TABLE `SPR_ACTION` (
  `SPR_ID` INT NOT NULL,
  `ACT_ID` INT NOT NULL,
  `FRAME_COUNT` INT NOT NULL DEFAULT '0',
  `FRAME_RATE` INT NOT NULL DEFAULT '24'
);
-- ----------------------------
-- Index for `SPR_ACTION`
-- ----------------------------
CREATE PRIMARY KEY `SPR_ACTION_PK` ON `SPR_ACTION`(`SPR_ID`,`ACT_ID`);
