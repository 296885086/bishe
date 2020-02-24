package com.ljq.bishe;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestPoi {
    public static void main(String[] args) throws Exception{
        String filepath = "d:/测试.xlsx";
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("sheet2");
        int totalRow = sheet.getPhysicalNumberOfRows();
        Row row = sheet.createRow(totalRow);
        Row row1 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        //写到excel
        FileOutputStream fos = new FileOutputStream(filepath);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("性别");
        row1.createCell(0).setCellValue(123456);
        row1.createCell(1).setCellValue("李家庆");
        row1.createCell(2).setCellValue("男");
        row2.createCell(0).setCellValue(123456);
        row2.createCell(1).setCellValue("李家庆");
        row2.createCell(2).setCellValue("男");
        //中文自适应宽度
/*        int length = row.getCell(3).getStringCellValue().getBytes("UTF-8").length * 256;
        sheet.setColumnWidth(3,length);*/
        book.write(fos);
        book.close();
        fos.close();
        //从excel读数据
        BufferedInputStream ins = new BufferedInputStream(new FileInputStream(filepath));
        Workbook readBook = WorkbookFactory.create(ins);
        sheet = readBook.getSheetAt(0);
        row = sheet.getRow(1);
        String value = row.getCell(1).getStringCellValue();
        String value1 = row.getCell(2).getStringCellValue();
        System.out.println(value + " " + value1);
        readBook.close();
    }
}
