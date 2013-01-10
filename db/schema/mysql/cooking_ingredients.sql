SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cooking_ingredients`
-- ----------------------------
DROP TABLE IF EXISTS `cooking_ingredients`;
CREATE TABLE IF NOT EXISTS `cooking_ingredients` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cooking_recipe_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  `amount` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=58 ;
