package com.thinkgem.jeesite.modules.PDFData.web;


import com.itextpdf.text.DocumentException;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.PDFData.service.PDFDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
