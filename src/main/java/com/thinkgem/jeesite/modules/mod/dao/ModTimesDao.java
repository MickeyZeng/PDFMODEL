/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mod.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mod.entity.ModTimes;

/**
 * 显示管理查询模版使用次数DAO接口
 * @author Mickey_zjq
 * @version 2018-03-08
 */
@MyBatisDao
public interface ModTimesDao extends CrudDao<ModTimes> {
	
}