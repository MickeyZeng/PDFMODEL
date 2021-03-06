/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sysmod.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sysmod.entity.ModTimes;

import java.util.List;

/**
 * 设计模版次数DAO接口
 * @author Mickey_zjq
 * @version 2018-03-19
 */
@MyBatisDao
public interface ModTimesDao extends CrudDao<ModTimes> {


    /**
     * 根据公司的id查找相关的数据
     */
     public ModTimes getByComId(ModTimes modTimes);

    /**
     * 根据当前用户的公司id获取该公司的数据
     * @param id
     * @return
     */
    ModTimes getByUser(String id);

    /**
     * 直接删除数据从数据库中
     * @param modTimes
     * @return
     */
    public int deleteDate(ModTimes modTimes);

    /**
     * 从数据库读取还没有审核的信息
     * @param modTimes
     * @return
     */
    List<ModTimes> findCheckList(ModTimes modTimes);

    /**
     * 审核信息，将信息转为可用
     * @param modTimes
     * @return
     */
    void updateCheck(ModTimes modTimes);

    /**
     * 保存审核信息
     * @param modTimes
     * @return
     */
    void saveCheck(ModTimes modTimes);
}