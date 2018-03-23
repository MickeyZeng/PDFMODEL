package com.thinkgem.jeesite.modules.uploadPDF.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.uploadPDF.Service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/uploadPDF/upload")
public class UploadController extends BaseController{

    @Autowired
    UploadService uploadService;

    @RequestMapping(value = {""})
    public String index() {
        return "modules/uploadPDF/CKFinderUpload";
    }
}
