package com.thinkgem.jeesite.modules.sys.web;

import com.thinkgem.jeesite.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/sys/upload")
public class UploadController extends BaseController{

    @RequestMapping(value = {""})
    public String index() {
        return "modules/uploadPDF/CKFinderUpload";
    }
}
