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

    public int uploadData(ImportExcel ei,String path) throws IOException, InvalidFormatException, DocumentException {
        List PDFList = PDFUtils.getPDFID(path);
        int numRow = ei.getDataRowNum();
        //每一行数据生成一个PDF 第0行并不需要 直接从有数据的第一行开始
        for (int flag = 1 ; flag < numRow ; flag++) {
            Row r = ei.getRow(flag);
            List ExcelList = PDFUtils.read(r);
            PDFUtils.fill(PDFList, ExcelList, path);
        }
        return numRow;
    }

    public int uploadDataForOne(List<String> resultList, String filepath, List<String> list) throws IOException, DocumentException {
        PDFUtils.fill(list,resultList,filepath);
        return 1;
    }
}
