-- ----------------------------
-- Table structure for `CHARACTER_BUDDYS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CHARACTER_BUDDYS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CHAR_ID` INT NOT NULL,
  `BUDDY_ID` INT NOT NULL
);
-- ----------------------------
-- Index for `CHARACTER_BUDDYS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CHARACTER_BUDDYS_PK` ON `CHARACTER_BUDDYS`(`ID`);
CREATE UNIQUE INDEX IF NOT EXISTS `CHARACTER_BUDDYS_UK` ON `CHARACTER_BUDDYS`(`CHAR_ID`,`BUDDY_ID`);
