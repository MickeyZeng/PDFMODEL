package com.thinkgem.jeesite.modules.uploadPDF.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.modelement.entity.SysModelement;
import com.thinkgem.jeesite.modules.modelement.service.SysModelementService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uploadPDF.Service.UploadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/uploadPDF/upload")
public class UploadController extends BaseController{

    @Autowired
    UploadService uploadService;

    @Autowired
    SysModelementService sysModelementService;

    @RequiresPermissions("uploadPDF:Data:view")
    @RequestMapping(value = {""})
    public String index() {
        //显示查看模版页面
        return "modules/uploadPDF/CKFinderUpload";
    }

    @RequiresPermissions("uploadPDF:Data:view")
    @RequestMapping(value = {"make"})
    public String make(Model model){
        //获取数据库里面该公司的元素
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
        return "modules/makePDF/printPDF";
    }

}
