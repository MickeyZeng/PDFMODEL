package com.thinkgem.jeesite.modules.PDFData.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class PDFUtils {

    public static final String CHARACTOR_FONT_CH_FILE = "/Users/mickey/document/PDFModel/jeesite-master/lib/simhei.ttf";

    //为用户下载一个与用户选择的模版匹配的数据模版 excel格式。
    public static void findID(String filename, String file) throws IOException, DocumentException {

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
            FileOutputStream fos = new FileOutputStream("/Users/mickey/document/PDFModel/ExcelTest/"+ file +"模版.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfStamper.close();
        out.close();
    }

    //获取PDF里面的iD
    public static List<String> getPDFID(String filename) throws IOException, DocumentException {
        List<String> list = new ArrayList<String>();
        String filename1 = "/Users/mickey/document/PDFModel/PDFTest/Test.pdf";

        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        reader=new PdfReader(new FileInputStream(new File(filename)));
        out = new FileOutputStream(new File(filename1));
        pdfStamper=new PdfStamper(reader,out);
        AcroFields s = pdfStamper.getAcroFields();
        Map<String, AcroFields.Item> acroFieldMap=s.getFields();
        Set<String> keySet = acroFieldMap.keySet();
        Object[] keySetStr = keySet.toArray();
        for (Object s1 : keySetStr) {
            list.add(s1.toString());
            System.out.println(s1.toString());
        }

        pdfStamper.close();
        out.close();
        return list;
    }


    //用户上传一个具有数据的excel后。
    //excel读取行数函数(传参 row 行数，)
    public  static List<String> read(Row row) throws IOException, InvalidFormatException {
        List<String> list = new ArrayList<String>();

        int cols = row.getPhysicalNumberOfCells();
        if(row == null){
            return list;
        }
        for (int col = 0 ; col < cols ; col++){
            Cell cell = row.getCell(col);
            String cellValue = "";
            if (null != cell) {
                // 以下是判断数据的类型
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        cellValue = cell.getNumericCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        cellValue = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        cellValue = cell.getBooleanCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        cellValue = cell.getCellFormula() + "";
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        cellValue = "";
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        cellValue = "非法字符";
                        break;
                    default:
                        cellValue = "未知类型";
                        break;
                }
            }
            list.add(cellValue);
            System.out.println(cellValue);
        }
        System.out.println();
        return list;
    }

    public static void fill(List PDFList, List excelList,String path) throws IOException, DocumentException {
        if(!(PDFList.size() == excelList.size())){
            //需要系统报错！

        }else{
            //为PDF命名的函数方法。
            fillTemplate(PDFList,excelList,path, name(excelList));
        }
    }

    public static String name(List list){
        return "/Users/mickey/document/PDFModel/PDFTest/" + list.get(0) +""+ list.get(list.size()-1) + ".pdf";
    }


    /**
     * 功能： 中文分离日期
     *
     * @param date   日期
     * @param choice 返回参数
     * @return day 日
     */
    public static String extractYear(Date date, int choice) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = format.format(date);
        String year = tempDate.substring(0, 4);
        String month = tempDate.substring(5, 7);
        String day = tempDate.substring(8, 10);
        if (choice == 1) {
            return year;
        } else if (choice == 2) {
            return month;
        } else {
            return day;
        }

    }

    /**
     * 功能： 英文分离日期
     *
     * @param date   日期
     * @param choice 返回参数
     * @return month 月份
     */

    public static String extractEnYear(String date, int choice) {
        String year = date.substring(date.length() - 4, date.length());
        String monthDay = date.substring(0, date.length() - 4);
        if (choice == 1) {
            return year;
        } else {
            return monthDay;
        }
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
     *
     * @return int 得到的字符串长度
     */
    public static double getLength(String s, int fontSize) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        String number = "^[0-9]*$";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1.56;
                //System.out.println("中文:" + valueLength);
            } else if (temp.matches(number)) {
                // 数字字符长度为0.5
                valueLength += 0.45;
                //System.out.println("英文::" + valueLength);
            } else {
                //英文字符长度
                valueLength += 0.45;
            }
            if (temp.matches(" ")) {
                valueLength += 1.56;
                //System.out.println("空格:" + valueLength);
            }
        }

        return valueLength * fontSize;
    }

    public static String fillSpace(String value, int fontSize, double tfWidth) {
        // System.out.println("宽:" + tfWidth);

        if (getLength(value, fontSize) <= tfWidth) {
            double fill = tfWidth - getLength(value, fontSize);
            double fillSpace = fill / 2 * 1.56 / fontSize;
            //System.out.println(fill+"  "+fillSpace);
            for (int i = 1; i <= Math.ceil(fillSpace); i++) {
                value = " " + value;
            }
        }
        return value;
    }

    public static double isNull(AcroFields s, String name) {
        try {
            double width = s.getFieldPositions(name).get(0).position.getWidth();
            return width;
        } catch (NullPointerException e) {
            double width = 1;
            return width;
        }
    }


    public static void fillTemplate(List PDFList, List excelList, String path, String outputFileName)
            throws IOException, DocumentException {

        PdfReader reader = new PdfReader(path); // 模版文件目录

        //String outputFileName = "E:\\pdf\\" + BusinessLicense.getStuNo() + BusinessLicense.getStuName() + ".pdf" ;
        PdfStamper ps = new PdfStamper(reader, new FileOutputStream(
                outputFileName)); // 生成的输出流

        AcroFields s = ps.getAcroFields();

        BaseFont bfChinese = BaseFont.createFont(CHARACTOR_FONT_CH_FILE, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        //DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");

        s.addSubstitutionFont(bfChinese);

        for(int index = 0 ; index < PDFList.size() ; index++){
            s.setFieldProperty(PDFList.get(index).toString(), "clrflags", 1, null);
            s.setFieldProperty(PDFList.get(index).toString(), "textfont", bfChinese, null);
            s.setFieldProperty(PDFList.get(index).toString(), "textsize", new Float(15), null);

            s.setField(PDFList.get(index).toString(),excelList.get(index).toString());
        }
//        中文姓名 注入功能代码
//        s.setFieldProperty("idcertificateCode", "clrflags", 1, null);
//        s.setFieldProperty("idcertificateCode", "textfont", bfChinese, null);
//        s.setFieldProperty("idcertificateCode", "textsize", new Float(15), null);
//
//        s.setField("idcertificateCode","Fuckyou!!!");

        ps.setFormFlattening(true); // 这句不能少
        ps.close();
        reader.close();
    }

}
