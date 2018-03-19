/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sysmod.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sysmod.entity.ModTimes;

/**
 * 设计模版次数DAO接口
 * @author Mickey_zjq
 * @version 2018-03-19
 */
@MyBatisDao
public interface ModTimesDao extends CrudDao<ModTimes> {
	
}