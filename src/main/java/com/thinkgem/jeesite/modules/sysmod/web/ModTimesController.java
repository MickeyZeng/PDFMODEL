/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sysmod.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.sysmod.entity.ModTimes;
import com.thinkgem.jeesite.modules.sysmod.service.ModTimesService;

/**
 * 设计模版次数Controller
 * @author Mickey_zjq
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sysmod/modTimes")
public class ModTimesController extends BaseController {

	@Autowired
	private ModTimesService modTimesService;
	
	@ModelAttribute
	public ModTimes get(@RequestParam(required=false) String id) {
		ModTimes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = modTimesService.get(id);
		}
		if (entity == null){
			entity = new ModTimes();
		}
		return entity;
	}
	
	@RequiresPermissions("sysmod:modTimes:view")
	@RequestMapping(value = {"list", ""})
	public String list(ModTimes modTimes, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ModTimes> page = modTimesService.findPage(new Page<ModTimes>(request, response), modTimes); 
		model.addAttribute("page", page);
		return "modules/sysmod/modTimesList";
	}

	@RequiresPermissions("sysmod:modTimes:view")
	@RequestMapping(value = "form")
	public String form(ModTimes modTimes, Model model) {
		model.addAttribute("modTimes", modTimes);
		return "modules/sysmod/modTimesForm";
	}

	@RequiresPermissions("sysmod:modTimes:edit")
	@RequestMapping(value = "save")
	public String save(ModTimes modTimes, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, modTimes)){
			return form(modTimes, model);
		}
		modTimesService.save(modTimes);
		addMessage(redirectAttributes, "保存设计模版次数成功");
		return "redirect:"+Global.getAdminPath()+"/sysmod/modTimes/?repage";
	}
	
	@RequiresPermissions("sysmod:modTimes:edit")
	@RequestMapping(value = "delete")
	public String delete(ModTimes modTimes, RedirectAttributes redirectAttributes) {
		modTimesService.delete(modTimes);
		addMessage(redirectAttributes, "删除设计模版次数成功");
		return "redirect:"+Global.getAdminPath()+"/sysmod/modTimes/?repage";
	}

}