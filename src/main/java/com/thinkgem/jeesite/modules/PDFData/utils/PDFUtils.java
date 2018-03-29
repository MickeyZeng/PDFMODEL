package com.thinkgem.jeesite.modules.PDFData.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.Map;
import java.util.Set;


public class PDFUtils {
    public static void findID(String filename) throws IOException, DocumentException {

        //开始创建excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("FormOne");
        HSSFRow row = sheet.createRow(0);

        int i = 0 ;
        String filename1 = "/Users/mickey/document/PDFModel/PDFTest/Test.pdf";
        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        out = new FileOutputStream(new File(filename1));
        reader=new PdfReader(new FileInputStream(new File(filename)));
        pdfStamper=new PdfStamper(reader,out);
        AcroFields s = pdfStamper.getAcroFields();
        Map<String, AcroFields.Item> acroFieldMap=s.getFields();
        Set<String> keySet = acroFieldMap.keySet();
        Object[] keySetStr = keySet.toArray();
        for (Object s1 : keySetStr) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(s1.toString().substring(2));
            ++i;
        }
        try {
            FileOutputStream fos = new FileOutputStream("/Users/mickey/document/PDFModel/ExcelTest/Test.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfStamper.close();
        out.close();
    }



}
