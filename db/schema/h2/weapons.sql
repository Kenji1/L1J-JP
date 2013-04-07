-- ----------------------------
-- Table structure for `WEAPONS`
-- ----------------------------
DROP TABLE IF EXISTS `WEAPONS`;
CREATE TABLE `WEAPONS` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(255) DEFAULT NULL,
  `UNIDENTIFIED_NAME_ID` VARCHAR(255) NOT NULL DEFAULT '',
  `IDENTIFIED_NAME_ID` VARCHAR(255) NOT NULL DEFAULT '',
  `TYPE` VARCHAR(255) NOT NULL DEFAULT '',
  `IS_TWOHANDED` BOOLEAN NOT NULL DEFAULT '0',
  `MATERIAL` VARCHAR(255) NOT NULL DEFAULT '',
  `WEIGHT` INT NOT NULL DEFAULT '0',
  `INV_GFX_ID` INT NOT NULL DEFAULT '0',
  `GRD_GFX_ID` INT NOT NULL DEFAULT '0',
  `ITEM_DESC_ID` INT NOT NULL DEFAULT '0',
  `DMG_SMALL` TINYINT NOT NULL DEFAULT '0',
  `DMG_LARGE` TINYINT NOT NULL DEFAULT '0',
  `RANGE` TINYINT NOT NULL DEFAULT '0',
  `SAFE_ENCHANT` TINYINT NOT NULL DEFAULT '0',
  `USE_ROYAL` BOOLEAN NOT NULL DEFAULT '0',
  `USE_KNIGHT` BOOLEAN NOT NULL DEFAULT '0',
  `USE_WIZARD` BOOLEAN NOT NULL DEFAULT '0',
  `USE_ELF` BOOLEAN NOT NULL DEFAULT '0',
  `USE_DARKELF` BOOLEAN NOT NULL DEFAULT '0',
  `USE_DRAGONKNIGHT` BOOLEAN NOT NULL DEFAULT '0',
  `USE_ILLUSIONIST` BOOLEAN NOT NULL DEFAULT '0',
  `HIT_MODIFIER` TINYINT NOT NULL DEFAULT '0',
  `DMG_MODIFIER` TINYINT NOT NULL DEFAULT '0',
  `STR` TINYINT NOT NULL DEFAULT '0',
  `CON` TINYINT NOT NULL DEFAULT '0',
  `DEX` TINYINT NOT NULL DEFAULT '0',
  `INT` TINYINT NOT NULL DEFAULT '0',
  `WIS` TINYINT NOT NULL DEFAULT '0',
  `CHA` TINYINT NOT NULL DEFAULT '0',
  `HP` INT NOT NULL DEFAULT '0',
  `MP` INT NOT NULL DEFAULT '0',
  `HPR` INT NOT NULL DEFAULT '0',
  `MPR` INT NOT NULL DEFAULT '0',
  `SP` TINYINT NOT NULL DEFAULT '0',
  `MR` TINYINT NOT NULL DEFAULT '0',
  `IS_HASTE` BOOLEAN NOT NULL DEFAULT '0',
  `DOUBLE_DMG_CHANCE` TINYINT NOT NULL DEFAULT '0',
  `WEAKNESS_EXPOSURE` TINYINT NOT NULL DEFAULT '0',
  `MAGIC_DMG_MODIFIER` TINYINT NOT NULL DEFAULT '0',
  `CAN_BE_DMG` BOOLEAN NOT NULL DEFAULT '0',
  `MIN_LEVEL` TINYINT NOT NULL DEFAULT '0',
  `MAX_LEVEL` TINYINT NOT NULL DEFAULT '0',
  `BLESS` BOOLEAN NOT NULL DEFAULT '0',
  `TRADABLE` BOOLEAN NOT NULL DEFAULT '1',
  `DELETABLE` BOOLEAN NOT NULL DEFAULT '1',
  `CHARGE_TIME` INT NOT NULL DEFAULT '0',
  `EXPIRATION_TIME` VARCHAR(255) DEFAULT NULL
);
-- ----------------------------
-- Index for `WEAPONS`
-- ----------------------------
CREATE PRIMARY KEY `WEAPONS_PK` ON `WEAPONS`(`ID`);
