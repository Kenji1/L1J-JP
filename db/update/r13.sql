-- ----------------------------
-- Add columns to inventory_items
-- ----------------------------
ALTER TABLE  `inventory_items` 
ADD  `is_haste` TINYINT( 1 ) NOT NULL DEFAULT  '0',
ADD  `can_be_dmg` TINYINT( 1 ) NOT NULL DEFAULT  '1',
ADD  `is_unique` TINYINT( 1 ) NOT NULL DEFAULT  '0';

-- ----------------------------
-- Update is_haste
-- ----------------------------
UPDATE `inventory_items`, `weapon`
SET `inventory_items`.`is_haste` = `weapon`.`is_haste`
WHERE `inventory_items`.`item_id` = `weapon`.`item_id`;

UPDATE `inventory_items`, `armor`
SET `inventory_items`.`is_haste` = `armor`.`is_haste`
WHERE `inventory_items`.`item_id` = `armor`.`item_id`;

-- ----------------------------
-- Update can_be_dmg
-- ----------------------------
UPDATE `inventory_items`, `weapon`
SET `inventory_items`.`can_be_dmg` = `weapon`.`can_be_dmg`
WHERE `inventory_items`.`item_id` = `weapon`.`item_id`;

-- ----------------------------
-- Update is_unique
-- ----------------------------
UPDATE `inventory_items`
SET `is_unique` = 1
WHERE `ac` > 0
OR `str` > 0
OR `con` > 0
OR `dex` > 0
OR `wis` > 0
OR `cha` > 0
OR `int` > 0
OR `hp` > 0
OR `hpr` > 0
OR `mp` > 0
OR `mpr` > 0
OR `mr` > 0
OR `sp` > 0
OR `hit_modifier` > 0
OR `dmg_modifier` > 0
OR `defense_earth` > 0
OR `defense_water` > 0
OR `defense_fire` > 0
OR `defense_wind` > 0
OR `resist_stun` > 0
OR `resist_stone` > 0
OR `resist_sleep` > 0
OR `resist_freeze` > 0
OR `resist_hold` > 0
OR `resist_blind` > 0
OR `exp_bonus` > 0;
