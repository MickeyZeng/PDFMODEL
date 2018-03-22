/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.modelement.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.modelement.entity.SysModelement;

import java.util.List;

/**
 * 模版元素查看DAO接口
 * @author Mickey_zjq
 * @version 2018-03-19
 */
@MyBatisDao
public interface SysModelementDao extends CrudDao<SysModelement> {

    public List<SysModelement> findCheckPage(SysModelement sysModelement);

    SysModelement getByUser(String id);
}