-- ----------------------------
-- Table structure for `UB_MANAGERS`
-- ----------------------------
DROP TABLE IF EXISTS `UB_MANAGERS`;
CREATE TABLE `UB_MANAGERS` (
  `UB_ID` INT NOT NULL,
  `NPC_ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL
);
-- ----------------------------
-- Index for `UB_MANAGERS`
-- ----------------------------
CREATE PRIMARY KEY `UB_MANAGERS_PK` ON `UB_MANAGERS`(`UB_ID`,`NPC_ID`);
