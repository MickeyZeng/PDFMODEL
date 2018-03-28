package com.thinkgem.jeesite.modules.PDFData.web;


import com.itextpdf.text.DocumentException;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.PDFData.service.PDFDataService;
import com.thinkgem.jeesite.modules.PDFData.utils.PDFUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping(value = "${adminPath}/PDFData/Data")
public class PDFDataController extends BaseController {
    @Autowired
    PDFDataService pdfDataService;

    @RequiresPermissions("PDFData:Data:view")
    @RequestMapping(value = {"index"})
    public String index() {
        return "modules/PDFData/FirstStep";
    }

    @RequiresPermissions("PDFData:Data:view")
    @RequestMapping(value = {"sure"})
    public String sure(String filename,Model model) throws IOException, DocumentException {
//      System.out.println(filename);
        //PDFUtils.findID(filename);
        String path = "/file" + filename.substring(38);
        model.addAttribute("path",path);
        return "modules/PDFData/SecondStep";
    }

    @RequiresPermissions("PDFData:Data:view")
    @RequestMapping(value = {"upload"})
    public String upload(){

        return "modules/PDFData/ThirdStep";
    }
}
