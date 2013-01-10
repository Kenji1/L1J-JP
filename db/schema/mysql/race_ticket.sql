SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `race_ticket`
-- ----------------------------
DROP TABLE IF EXISTS `race_ticket`;
CREATE TABLE IF NOT EXISTS `race_ticket` (
  `item_obj_id` int(11) NOT NULL,
  `round` int(7) NOT NULL,
  `allotment_percentage` double NOT NULL,
  `victory` int(1) NOT NULL,
  `runner_num` int(2) NOT NULL,
  PRIMARY KEY (`item_obj_id`,`round`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
