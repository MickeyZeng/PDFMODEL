/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mod.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mod.entity.ModTimes;
import com.thinkgem.jeesite.modules.mod.dao.ModTimesDao;

/**
 * 显示管理查询模版使用次数Service
 * @author Mickey_zjq
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class ModTimesService extends CrudService<ModTimesDao, ModTimes> {

	public ModTimes get(String id) {
		return super.get(id);
	}
	
	public List<ModTimes> findList(ModTimes modTimes) {
		return super.findList(modTimes);
	}
	
	public Page<ModTimes> findPage(Page<ModTimes> page, ModTimes modTimes) {
		return super.findPage(page, modTimes);
	}
	
	@Transactional(readOnly = false)
	public void save(ModTimes modTimes) {
		super.save(modTimes);
	}
	
	@Transactional(readOnly = false)
	public void delete(ModTimes modTimes) {
		super.delete(modTimes);
	}
	
}