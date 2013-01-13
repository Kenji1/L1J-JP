-- ----------------------------
-- Table structure for `MAP_TIMER`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `MAP_TIMER` (
  `CHAR_ID` INT NOT NULL,
  `MAP_ID` INT NOT NULL,
  `AREA_ID` INT NOT NULL,
  `ENTER_TIME` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `MAP_TIMER`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `MAP_TIMER_PK` ON `MAP_TIMER`(`CHAR_ID`,`AREA_ID`);
CREATE INDEX IF NOT EXISTS `MAP_TIMER_MAP_ID` ON `MAP_TIMER`(`MAP_ID`);
