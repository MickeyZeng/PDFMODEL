package com.thinkgem.jeesite.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class ExcelTest {

    public static void main(String[] args) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        File xlsFile = new File("/Users/mickey/document/PDFModel/ExcelTest/TestForData.xls");
        // 获得工作簿
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        System.out.println("工作表个数="+sheetCount);
        // 遍历工作表
        for (int i = 0; i < sheetCount; i++)
        {
            Sheet sheet = workbook.getSheetAt(i);
            // 获得行数
            int rows = sheet.getLastRowNum() + 1;
            System.out.println("行数="+rows);
            // 获得列数，先获得一行，在得到改行列数
            Row tmp = sheet.getRow(0);
            if (tmp == null)
            {
                continue;
            }
            int cols = tmp.getPhysicalNumberOfCells();
            System.out.println("列数="+cols);
            // 读取数据
            for (int row = 0; row < rows; row++)
            {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cols; col++)
                {
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
//                    System.out.println(r.getCell(col).getStringCellValue());
                    System.out.println(cellValue);
                }
                System.out.println();
            }
        }
    }

}
