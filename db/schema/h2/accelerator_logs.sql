-- ----------------------------
-- Table structure for `ACCELERATOR_LOGS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ACCELERATOR_LOGS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` INT NOT NULL,
  `CHAR_ID` INT NOT NULL,
  `CLAN_ID` INT NOT NULL,
  `MAP_ID` INT NOT NULL,
  `LOC_X` INT NOT NULL,
  `LOC_Y` INT NOT NULL,
  `DATETIME` DATETIME NOT NULL
);
-- ----------------------------
-- Index for `ACCELERATOR_LOGS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `ACCELERATOR_LOGS_PK` ON `ACCELERATOR_LOGS`(`ID`);
