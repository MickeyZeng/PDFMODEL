/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sysmod.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设计模版次数Entity
 * @author Mickey_zjq
 * @version 2018-03-19
 */
public class ModTimes extends DataEntity<ModTimes> {
	
	private static final long serialVersionUID = 1L;
	private Office company;		// 归属公司
	private User user;		// 公司负责人
	private String times;		// 次数
	
	public ModTimes() {
		super();
	}

	public ModTimes(String id){
		super(id);
	}

	@NotNull(message="归属公司不能为空")
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	@NotNull(message="公司负责人不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=2, message="次数长度必须介于 1 和 2 之间")
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
}