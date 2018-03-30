package com.thinkgem.jeesite.modules.PDFData.web;


import com.itextpdf.text.DocumentException;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.PDFData.service.PDFDataService;
import com.thinkgem.jeesite.modules.PDFData.utils.PDFUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
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
    public String sure(String filename,Model model,String file) throws IOException, DocumentException {
//      System.out.println(filename);
        PDFUtils.findID(filename,file);
        String path = "/file" + filename.substring(38);
        model.addAttribute("path",path);
        return "modules/PDFData/SecondStep";
    }

    @RequiresPermissions("PDFData:Data:view")
    @RequestMapping(value = {"jump"})
    public String jump(String filename,Model model){
        String path = "/file" + filename.substring(38);
        model.addAttribute("path",path);
        return "modules/PDFData/SecondStep";
    }

    @RequiresPermissions("PDFData:Data:view")
    @RequestMapping(value = {"upload"},method= RequestMethod.POST)
    public String upload(MultipartFile file,String path) throws IOException, InvalidFormatException, DocumentException {
        String filepath = "/Users/mickey/document/PDFModel/CKFile"+path.substring(5);
        ImportExcel ei = new ImportExcel(file, 1, 0);
        int num = pdfDataService.uploadData(ei,filepath);
        return "redirect:" + adminPath + "/PDFData/Data/finish?num="+ num;
    }

    @RequiresPermissions("PDFData:Data:view")
    @RequestMapping(value = {"finish"})
    public String finish(RedirectAttributes redirectAttributes,int num){
        addMessage(redirectAttributes, "已成功导入 "+num+"的PDF证照");
        return "redirect:" + adminPath + "/PDFData/Data/index";
    }

}
