/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.modelement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.modelement.entity.SysModelement;
import com.thinkgem.jeesite.modules.modelement.dao.SysModelementDao;

/**
 * 模版元素查看Service
 * @author Mickey_zjq
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly = true)
public class SysModelementService extends CrudService<SysModelementDao, SysModelement> {

	public SysModelement get(String id) {
		return super.get(id);
	}
	
	public List<SysModelement> findList(SysModelement sysModelement) {
		return super.findList(sysModelement);
	}
	
	public Page<SysModelement> findPage(Page<SysModelement> page, SysModelement sysModelement) {
		return super.findPage(page, sysModelement);
	}
	
	@Transactional(readOnly = false)
	public void save(SysModelement sysModelement) {
		super.save(sysModelement);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysModelement sysModelement) {
		super.delete(sysModelement);
	}
	
}