/*Table structure for table 'mod_times' */

DROP TABLE IF EXISTS `mod_times`;

CREATE TABLE `mod_times`(
	`id` varchar(5) COLLATE utf8_bin NOT NULL COMMENT '编号',
	`comId` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '公司编号',
	`times` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '次数',
	`create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  	`create_date` datetime NOT NULL COMMENT '创建时间',
  	`update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
 	`update_date` datetime NOT NULL COMMENT '更新时间',
  	`remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  	`del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模版使用次数表';

/*Data for the table 'mod_times' */

insert into `mod_times`(`id`,`comId`,`times`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values('00001','0000000001','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0');
insert into `mod_times`(`id`,`comId`,`times`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values('00002','0000000013','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0');

