-- ----------------------------
-- Table structure for `UB_TIMES`
-- ----------------------------
DROP TABLE IF EXISTS `UB_TIMES`;
CREATE TABLE `UB_TIMES` (
  `UB_ID` INT NOT NULL DEFAULT '0',
  `UB_TIME` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `UB_TIMES`
-- ----------------------------
CREATE PRIMARY KEY `UB_TIMES_PK` ON `UB_TIMES`(`UB_ID`,`UB_TIME`);
