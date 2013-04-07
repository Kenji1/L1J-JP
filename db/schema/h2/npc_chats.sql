-- ----------------------------
-- Table structure for `NPC_CHATS`
-- ----------------------------
DROP TABLE IF EXISTS `NPC_CHATS`;
CREATE TABLE `NPC_CHATS` (
  `NPC_ID` INT NOT NULL DEFAULT '0',
  `CHAT_TIMING` TINYINT NOT NULL DEFAULT '0',
  `START_DELAY_TIME` INT NOT NULL DEFAULT '0',
  `CHAT_ID1` VARCHAR(255) NOT NULL DEFAULT '',
  `CHAT_ID2` VARCHAR(255) NOT NULL DEFAULT '',
  `CHAT_ID3` VARCHAR(255) NOT NULL DEFAULT '',
  `CHAT_ID4` VARCHAR(255) NOT NULL DEFAULT '',
  `CHAT_ID5` VARCHAR(255) NOT NULL DEFAULT '',
  `CHAT_INTERVAL` INT NOT NULL DEFAULT '0',
  `IS_SHOUT` BOOLEAN NOT NULL DEFAULT '0',
  `IS_WORLD_CHAT` BOOLEAN NOT NULL DEFAULT '0',
  `IS_REPEAT` BOOLEAN NOT NULL DEFAULT '0',
  `REPEAT_INTERVAL` INT NOT NULL DEFAULT '0',
  `GAME_TIME` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `NPC_CHATS`
-- ----------------------------
CREATE PRIMARY KEY `NPC_CHATS_PK` ON `NPC_CHATS`(`NPC_ID`,`CHAT_TIMING`);
