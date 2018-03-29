package com.thinkgem.jeesite.modules.PDFData.service;

import com.itextpdf.text.DocumentException;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.PDFData.utils.PDFUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PDFDataService extends BaseService {

    public void uploadData(ImportExcel ei,String path) throws IOException, InvalidFormatException, DocumentException {
        List PDFList = PDFUtils.getPDFID(path);
        Row r = ei.getRow(1);
        List ExcelList = PDFUtils.read(r);
        PDFUtils.fill(PDFList,ExcelList,path);
    }
}
