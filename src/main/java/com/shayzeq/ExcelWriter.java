package com.shayzeq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ExcelWriter {

    public static void writeToExcel(List<FinalDataModel> list) {


        // счетчик для строк
        int rowNum = 0;
        HSSFWorkbook wb = new HSSFWorkbook(); // создание самого excel файла в памяти
        HSSFSheet sheet = wb.createSheet("Статистика"); // создание листа с названием
        String fileName = "C:\\Users\\Igor.Frolov\\IdeaProjects\\LogParser\\Statistic.xls";
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(" Название метода ");
        row.createCell(1).setCellValue(" Appkid ");
        row.createCell(2).setCellValue(" Среднее время (мс) ");
        row.createCell(3).setCellValue(" Максимальное время (мс) ");
        row.createCell(4).setCellValue(" Минимальное время (мс) ");
        for (FinalDataModel dm : list){
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dm.getMethodName());
            row.createCell(1).setCellValue(dm.getAppkId());
            row.createCell(2).setCellValue(dm.getAvgWorkTime());
            row.createCell(3).setCellValue(dm.getMaxWorkTime());
            row.createCell(4).setCellValue(dm.getMinWorkTime());
        }

            try {
                FileOutputStream fileOut = new FileOutputStream(fileName); // записываем созданный в памяти Excel документ в файл
                wb.write(fileOut);
                fileOut.close();
                System.out.println("Excel файл успешно создан!");
            } catch (Exception e) {
                System.out.println("Не заработало, Excel файл - не создан!");
            }

    }
}