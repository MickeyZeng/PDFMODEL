/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.modelement.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.modelement.entity.SysModelement;
import com.thinkgem.jeesite.modules.modelement.service.SysModelementService;

/**
 * 模版元素查看Controller
 * @author Mickey_zjq
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/modelement/sysModelement")
public class SysModelementController extends BaseController {

	@Autowired
	private SysModelementService sysModelementService;
	
	@ModelAttribute
	public SysModelement get(@RequestParam(required=false) String id) {
		SysModelement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysModelementService.get(id);
		}
		if (entity == null){
			entity = new SysModelement();
		}
		return entity;
	}
	
	@RequiresPermissions("modelement:sysModelement:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysModelement sysModelement, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getUserType().equals("3")){
			sysModelement.setOffice(UserUtils.getUser().getOffice());
		}else if(UserUtils.getUser().getUserType().equals("2")){
			sysModelement.setCompany(UserUtils.getUser().getCompany());
		}
		Page<SysModelement> page = sysModelementService.findPageByUserType(new Page<SysModelement>(request, response),sysModelement);
		model.addAttribute("page", page);
		return "modules/modelement/sysModelementList";
	}

	@RequiresPermissions("modelement:sysModelement:view")
	@RequestMapping(value = "checkList")
	public String checkList(SysModelement sysModelement, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getUserType().equals("3")){
			sysModelement.setOffice(UserUtils.getUser().getOffice());
		}
		Page<SysModelement> page = sysModelementService.findCheckPage(new Page<SysModelement>(request, response), sysModelement);
		model.addAttribute("page", page);
		return "modules/modelement/sysModelementCheckList";
	}

	@RequiresPermissions("modelement:sysModelement:view")
	@RequestMapping(value = "form")
	public String form(SysModelement sysModelement, Model model) {
		model.addAttribute("sysModelement", sysModelement);
		return "modules/modelement/sysModelementForm";
	}

	@RequiresPermissions("modelement:sysModelement:view")
	@RequestMapping(value = "create")
	public String create(SysModelement sysModelement, Model model) {

		if(!(UserUtils.getUser().getUserType().equals("1"))){
			sysModelement.setCompany(UserUtils.getUser().getCompany());
			sysModelement.setOffice(UserUtils.getUser().getOffice());
			model.addAttribute("sysModelement", sysModelement);
			return "modules/modelement/createElementType";
		}else {
			model.addAttribute("sysModelement", sysModelement);
			return "modules/modelement/createElement";
		}

	}

	@RequiresPermissions("modelement:sysModelement:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(SysModelement sysModelement, Model model) {
		model.addAttribute("sysModelement", sysModelement);
		return "modules/modelement/sysModelementCheckForm";
	}

	@RequiresPermissions("modelement:sysModelement:edit")
	@RequestMapping(value = "save")
	public String save(SysModelement sysModelement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysModelement)){
			return form(sysModelement, model);
		}
		Boolean flag = sysModelement.getIsNewRecord();
		sysModelementService.save(sysModelement);
		if(flag) {
			addMessage(redirectAttributes, "申请模版元素成功");
			return "redirect:" + Global.getAdminPath() + "/modelement/sysModelement/create?repage";
		}else{
			addMessage(redirectAttributes, "保存模版元素成功");
			return "redirect:" + Global.getAdminPath() + "/modelement/sysModelement/?repage";
		}
	}
	
	@RequiresPermissions("modelement:sysModelement:edit")
	@RequestMapping(value = "delete")
	public String delete(SysModelement sysModelement, RedirectAttributes redirectAttributes) {
		sysModelementService.delete(sysModelement);
		addMessage(redirectAttributes, "删除模版元素成功");
		return "redirect:"+Global.getAdminPath()+"/modelement/sysModelement/?repage";
	}

	@RequiresPermissions("modelement:sysModelement:edit")
	@RequestMapping(value = "updateCheck")
	public String updateCheck(SysModelement sysModelement, RedirectAttributes redirectAttributes) throws Exception {
		sysModelementService.updateCheck(sysModelement);
		sysModelementService.sendMail(sysModelement);
		addMessage(redirectAttributes, "元素成功通过审核。通知邮件已发送。");
		return "redirect:" + adminPath + "/modelement/sysModelement/checkList?repage";
	}

}