/*
Navicat MySQL Data Transfer

Source Server         : form
Source Server Version : 50641
Source Host           : 172.20.54.82:5001
Source Database       : form_relation

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2019-08-14 16:29:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `timing_item`
-- ----------------------------
DROP TABLE IF EXISTS `timing_item`;
CREATE TABLE `timing_item` (
  `id` varchar(36) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `type` int(10) DEFAULT '0',
  `rule_item_id` varchar(36) CHARACTER SET utf8 DEFAULT NULL COMMENT '//内容，存储前端图形的格式化字符串',
  `rule_item_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `relation_item_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `relation_item_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `source_form_template_id` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '//表单关系名称',
  `source_form_template_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `source_form_instance_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `source_sub_form_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `source_sub_form_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `source_sub_form_data_pk` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `process_definition_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `process_instance_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `process_key` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_user_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '//制单人id',
  `create_user_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `form_data` longtext COLLATE utf8_bin,
  `locale` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `target_form_template_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `target_form_template_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `last_running_time` timestamp NULL DEFAULT NULL,
  `push_time_date` varchar(255) COLLATE utf8_bin DEFAULT '',
  `error_msg` varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  `is_running` tinyint(4) DEFAULT NULL,
  `is_end` tinyint(4) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `cron_expression` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `environment_tag` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `desc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenantId` (`tenant_id`),
  KEY `idx_tenantIdAndRuleId` (`tenant_id`,`rule_item_id`) USING BTREE,
  KEY `idx_tenantIdAndRelationId` (`tenant_id`,`relation_item_id`) USING BTREE,
  KEY `idx_tenantIdAndSourceFormInstanceId` (`tenant_id`,`source_form_instance_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

