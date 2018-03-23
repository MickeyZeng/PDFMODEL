/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sysmod.service;

import java.util.List;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sysmod.entity.ModTimes;
import com.thinkgem.jeesite.modules.sysmod.dao.ModTimesDao;

/**
 * 设计模版次数Service
 * @author Mickey_zjq
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly = true)
public class ModTimesService extends CrudService<ModTimesDao, ModTimes> {

	@Autowired
	private ModTimesDao modTimesDao;

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
		//modTimes.setIsNewRecord(false);
		super.save(modTimes);
	}
	
	@Transactional(readOnly = false)
	public void delete(ModTimes modTimes) {
		super.delete(modTimes);
	}

	@Transactional(readOnly = false)
	public void updateTimes(ModTimes modTimes) {
		ModTimes times = modTimesDao.getByComId(modTimes);
		int i = Integer.parseInt(times.getTimes());
		int j = Integer.parseInt(modTimes.getTimes());
		modTimes.setTimes(Integer.toString(i+j));
		modTimes.setId(times.getId());
		modTimes.preUpdate();
		modTimesDao.update(modTimes);
	}

	public ModTimes getByUser(String id) {
		return modTimesDao.getByUser(id);
	}

	@Transactional(readOnly = false)
	public void deleteByComId(Office office) {
		ModTimes modTimes = new ModTimes();
		modTimes.setCompany(office);
		List<ModTimes> modTimesList = modTimesDao.findList(modTimes);
		for (int i = 0 ; i < modTimesList.size() ; i++) {
			modTimesDao.delete(modTimesList.get(i));
		}
	}
}