package com.thinkgem.jeesite.modules.uploadPDF.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.modelement.entity.SysModelement;
import com.thinkgem.jeesite.modules.modelement.service.SysModelementService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sysmod.entity.ModTimes;
import com.thinkgem.jeesite.modules.uploadPDF.Service.UploadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/uploadPDF/upload")
public class UploadController extends BaseController{

    @Autowired
    UploadService uploadService;

    @Autowired
    SysModelementService sysModelementService;

    @RequestMapping(value = {""})
    public String index() {
        //显示查看模版页面
        return "modules/uploadPDF/CKFinderUpload";
    }

    @RequiresPermissions("uploadPDF:Data:view")
    @RequestMapping(value = {"make"})
    public String make(Model model){
        int flag = 0 ;
        //获取数据库里面该公司的元素
        ModTimes modTimes = new ModTimes();
        SysModelement sysModelement = new SysModelement();
        sysModelement.setOffice(UserUtils.getUser().getOffice());
        List<SysModelement> list = uploadService.findElementByComId(sysModelement);
        //获取对应的elementName
        List<String> elementNames = new ArrayList<>();
        //获取对应的PDFName
        List<String> PDFIDs = new ArrayList<>();

        for (int i = 0 ; i < list.size() ; i++){
            elementNames.add(i,list.get(i).getElementname());
            PDFIDs.add(i,list.get(i).getPdfelementname());
        }

        String element = String.join(",",elementNames);
        String IDs = String.join(",",PDFIDs);
        model.addAttribute("elementNames",element);
        model.addAttribute("PDFIDs",IDs);

        modTimes.setCompany(UserUtils.getUser().getCompany());
        flag = uploadService.checkUse(modTimes);
        model.addAttribute("flag",flag);
        return "modules/makePDF/printPDF";
    }

    @ResponseBody
    @RequestMapping(value = {"reduceTimes"})
    public void reduceTimes(){
        ModTimes modTimes = new ModTimes();
        modTimes.setCompany(UserUtils.getUser().getCompany());
        uploadService.reduce(modTimes);
    }
}
