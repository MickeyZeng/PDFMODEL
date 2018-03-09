/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mod.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 显示管理查询模版使用次数Entity
 * @author Mickey_zjq
 * @version 2018-03-08
 */
public class ModTimes extends DataEntity<ModTimes> {
	
	private static final long serialVersionUID = 1L;
	private String comid;		// 公司编号
	private String times;		// 次数
	
	public ModTimes() {
		super();
	}

	public ModTimes(String id){
		super(id);
	}

	@Length(min=1, max=10, message="公司编号长度必须介于 1 和 10 之间")
	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}
	
	@Length(min=1, max=2, message="次数长度必须介于 1 和 2 之间")
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
}