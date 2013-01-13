-- ----------------------------
-- Table structure for POLYMORPHS
-- ----------------------------
DROP TABLE IF EXISTS `POLYMORPHS`;
CREATE TABLE `POLYMORPHS` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(255) DEFAULT NULL,
  `POLY_ID` INT DEFAULT NULL,
  `MIN_LEVEL` TINYINT NOT NULL DEFAULT '0',
  `WEAPON_EQUIP` INT NOT NULL DEFAULT '0',
  `ARMOR_EQUIP` INT NOT NULL DEFAULT '0',
  `CAN_USE_SKILL` BOOLEAN DEFAULT '1',
  `CAUSE` TINYINT NOT NULL DEFAULT '7'
);
-- ----------------------------
-- Index for `POLYMORPHS`
-- ----------------------------
CREATE PRIMARY KEY `POLYMORPHS_PK` ON `POLYMORPHS`(`ID`);
