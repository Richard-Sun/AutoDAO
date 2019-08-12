/*
Navicat MySQL Data Transfer

Source Server         : form
Source Server Version : 50641
Source Host           : 172.20.54.82:5001
Source Database       : form_relation

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2019-08-12 15:47:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mapping_item`
-- ----------------------------
DROP TABLE IF EXISTS `mapping_item`;
CREATE TABLE `mapping_item` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `rule_item_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '//规则id',
  `rule_item_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `relation_item_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `relation_item_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `data_item_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `source_field_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '//来源字段id',
  `source_field_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '//来源字段名称',
  `source_option_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `source_option_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `target_field_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '//目标字段id',
  `target_field_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '//目标字段名称',
  `target_option_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `target_option_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_dataItemId` (`data_item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of mapping_item
-- ----------------------------
