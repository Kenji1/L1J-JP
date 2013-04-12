-- ----------------------------
-- Table structure for `RACE_TICKETS`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `RACE_TICKETS` (
  `ITEM_OBJ_ID` INT NOT NULL,
  `ROUND` INT NOT NULL,
  `ALLOTMENT_PERCENTAGE` DOUBLE NOT NULL,
  `VICTORY` BOOLEAN NOT NULL,
  `RUNNER_NUM` TINYINT NOT NULL
);
-- ----------------------------
-- Index for `RACE_TICKETS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `RACE_TICKETS_PK` ON `RACE_TICKETS`(`ITEM_OBJ_ID`,`ROUND`);
