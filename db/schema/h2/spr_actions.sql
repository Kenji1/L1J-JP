-- ----------------------------
-- Table structure for SPR_ACTIONS
-- ----------------------------
DROP TABLE IF EXISTS `SPR_ACTIONS`;
CREATE TABLE `SPR_ACTIONS` (
  `SPR_ID` INT NOT NULL,
  `ACT_ID` INT NOT NULL,
  `FRAME_COUNT` INT NOT NULL DEFAULT '0',
  `FRAME_RATE` INT NOT NULL DEFAULT '24'
);
-- ----------------------------
-- Index for `SPR_ACTIONS`
-- ----------------------------
CREATE PRIMARY KEY `SPR_ACTIONS_PK` ON `SPR_ACTIONS`(`SPR_ID`,`ACT_ID`);
