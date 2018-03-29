package com.thinkgem.jeesite.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    //excel读取行数函数(传参 row 行数， path 文件路径， sheetCount 工作表数量)
    public  static List<String> read(int row, String path) throws IOException, InvalidFormatException {
        List<String> list = new ArrayList<String>();
        File xlsFile = new File(path);
        Workbook workbook = WorkbookFactory.create(xlsFile);

        //只读excel表里面的第一个数据表
        Sheet sheet = workbook.getSheetAt(0);

        Row tmp = sheet.getRow(0);
        if(tmp == null){
            return list;
        }

        int cols = tmp.getPhysicalNumberOfCells();
        Row r = sheet.getRow(row);
        if(r == null){
            return list;
        }
        for (int col = 0 ; col < cols ; col++){
            Cell cell = r.getCell(col);
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
            System.out.println(cellValue);
        }
        System.out.println();
        return list;
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        List<String> data = read(1,"/Users/mickey/document/PDFModel/ExcelTest/TestForData.xls");
        for(int i = 0 ; i < data.size(); i++){
            System.out.println(data.get(i));
        }
    }

}
