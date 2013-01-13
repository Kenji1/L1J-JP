-- ----------------------------
-- Table structure for `RACE_TICKET`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `RACE_TICKET` (
  `ITEM_OBJ_ID` INT NOT NULL,
  `ROUND` INT NOT NULL,
  `ALLOTMENT_PERCENTAGE` DOUBLE NOT NULL,
  `VICTORY` BOOLEAN NOT NULL,
  `RUNNER_NUM` TINYINT NOT NULL
);
-- ----------------------------
-- Index for `RACE_TICKET`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `RACE_TICKET_PK` ON `RACE_TICKET`(`ITEM_OBJ_ID`,`ROUND`);
