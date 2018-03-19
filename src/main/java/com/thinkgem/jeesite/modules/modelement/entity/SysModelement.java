/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.modelement.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 模版元素查看Entity
 * @author Mickey_zjq
 * @version 2018-03-19
 */
public class SysModelement extends DataEntity<SysModelement> {
	
	private static final long serialVersionUID = 1L;
	private String elementname;		// 元素名字
	private String pdfelementname;		// 元素PDF里的名字
	private Office company;		// 归属公司
	private Office office;		// 归属部门
	private String useFlag;		// 是否可以使用
	
	public SysModelement() {
		super();
	}

	public SysModelement(String id){
		super(id);
	}

	@Length(min=1, max=100, message="元素名字长度必须介于 1 和 100 之间")
	public String getElementname() {
		return elementname;
	}

	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	
	@Length(min=1, max=100, message="元素PDF里的名字长度必须介于 1 和 100 之间")
	public String getPdfelementname() {
		return pdfelementname;
	}

	public void setPdfelementname(String pdfelementname) {
		this.pdfelementname = pdfelementname;
	}
	
	@NotNull(message="归属公司不能为空")
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	@NotNull(message="归属部门不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=64, message="是否可以使用长度必须介于 0 和 64 之间")
	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	
}