package com.thinkgem.jeesite.modules.uploadPDF.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uploadPDF.Service.UploadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/uploadPDF/upload")
public class UploadController extends BaseController{

    @Autowired
    UploadService uploadService;

    @RequiresPermissions("uploadPDF:Data:view")
    @RequestMapping(value = {""})
    public String index() {
        return "modules/uploadPDF/CKFinderUpload";
    }

    @RequiresPermissions("uploadPDF:Data:view")
    @RequestMapping(value = {"make"})
    public String make(){
        //获取数据库里面该公司的元素
//        uploadService.findElementByComId(UserUtils.getUser());
        return "modules/makePDF/printPDF";
    }

}
