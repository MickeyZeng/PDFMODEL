package com.thinkgem.jeesite.modules.sys.web;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${frontPath}/sys/user")
public class CreateUserController extends BaseController{

    @Autowired
    private SystemService systemService;

    @Autowired
    private OfficeService officeService;

    @ModelAttribute
    public User get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return systemService.getUser(id);
        }else{
            return new User();
        }
    }

    @RequestMapping(value = "form")
    public String form(User user, Model model) {
        officeChoice();
        if (user.getCompany()==null || user.getCompany().getId()==null){
            user.setCompany(UserUtils.getUser().getCompany());
        }
        if (user.getOffice()==null || user.getOffice().getId()==null){
            user.setOffice(UserUtils.getUser().getOffice());
        }
        model.addAttribute("user", user);
        model.addAttribute("allRoles", systemService.findAllRole());
        return "modules/sys/createUser";
    }

    @RequestMapping(value = "save")
    public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        // 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
        user.setCompany(new Office(request.getParameter("company.id")));
        user.setOffice(new Office(request.getParameter("office.id")));
        // 如果新密码为空，则不更换密码
        if (StringUtils.isNotBlank(user.getNewPassword())) {
            user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
        }
        if (!beanValidator(model, user)){
            return form(user, model);
        }
        if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
            addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
            return form(user, model);
        }
        // 角色数据有效性验证，过滤不在授权内的角色
        List<Role> roleList = Lists.newArrayList();
        List<String> roleIdList = user.getRoleIdList();
        for (Role r : systemService.findAllRole()){
            if (roleIdList.contains(r.getId())){
                roleList.add(r);
            }
        }
        user.setRoleList(roleList);
        // 保存用户信息
        systemService.saveUser(user);
        // 清除当前用户缓存
        if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
            UserUtils.clearCache();
            //UserUtils.getCacheMap().clear();
        }
        addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
        return "redirect:" + adminPath + "/sys/user/list";
    }

    @ResponseBody
    @RequestMapping(value = "checkLoginName")
    public String checkLoginName(String oldLoginName, String loginName) {
        if (loginName !=null && loginName.equals(oldLoginName)) {
            return "true";
        } else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "officeChoice")
    @ResponseBody
    //从数据库提取数据，然后放入json数组，在这里返回jason数据给前端，前端进行插入
    public String officeChoice(){
        int j = 0;
        List<Office> list = officeService.findAllChoice();
        Map<Integer,Object> com = new HashMap<Integer,Object>();
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getType().equals("1")) {
                com.put(j, list.get(i));
                ++j;
            }
        }
        return JsonMapper.toJsonString(com);
    }

    @RequestMapping(value = "groupChoice")
    @ResponseBody
    public String groupChoice(@RequestParam(value = "comValue") String comValue){
        int k = 0;
        List<Office> list = officeService.findAllChoice();
        Map<Integer,Object> group = new HashMap<Integer,Object>();
        for (int i = 0 ; i<list.size(); i++){
            if(list.get(i).getType().equals("2") && list.get(i).getParentId().equals(comValue)){
                group.put(k, list.get(i));
                ++k;
            }
        }
        return JsonMapper.toJsonString(group);
    }

}
