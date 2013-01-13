-- ----------------------------
-- Table structure for `CHARACTER_QUESTS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `CHARACTER_QUESTS` (
  `CHAR_ID` INT NOT NULL DEFAULT '0',
  `QUEST_ID` INT NOT NULL DEFAULT '0',
  `QUEST_STEP` TINYINT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `CHARACTER_QUESTS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `CHARACTER_QUESTS_PK` ON `CHARACTER_QUESTS`(`CHAR_ID`,`QUEST_ID`);
