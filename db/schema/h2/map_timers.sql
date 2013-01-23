-- ----------------------------
-- Table structure for `MAP_TIMERS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `MAP_TIMERS` (
  `CHAR_ID` INT NOT NULL,
  `MAP_ID` INT NOT NULL,
  `AREA_ID` INT NOT NULL,
  `ENTER_TIME` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `MAP_TIMERS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `MAP_TIMERS_PK` ON `MAP_TIMERS`(`CHAR_ID`,`AREA_ID`);
CREATE INDEX IF NOT EXISTS `MAP_TIMERS_MAP_ID` ON `MAP_TIMERS`(`MAP_ID`);
