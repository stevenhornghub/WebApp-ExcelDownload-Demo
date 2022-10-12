package com.hornghub.webappexceldemo.utils;

import com.hornghub.webappexceldemo.model.DataModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * ExcelUtils - utils for excel download
 *
 * @author stevenhorng
 * @date 2022/11/10
 */
public class ExcelUtils {
    public static void exportExcel(List<DataModel> dataList, ServletOutputStream servletOutputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Excel Data");
        XSSFRow row;
        String[] columnHeader = {"ID", "Name", "Picture", "Vaccine Card", "Primary ID", "Secondary ID"};
        row = spreadsheet.createRow(0);
        for (int index = 0; index < columnHeader.length; index++) {
            row.createCell(index).setCellValue(columnHeader[index]);
        }
        for (int outer = 0; outer < dataList.size(); outer++) {
            row = spreadsheet.createRow(outer + 1);
            Object[] objectArr = {dataList.get(outer).getId(), dataList.get(outer).getName(), dataList.get(outer).getPicture(),
                    dataList.get(outer).getVaccineCard(), dataList.get(outer).getPrimaryId(), dataList.get(outer).getSecondaryId()};
            for (int inner = 0; inner < columnHeader.length; inner++) {
                if (inner <= 1) {
                    createStringCell(row, inner, spreadsheet, String.valueOf(objectArr[inner]));
                } else {
                    insertCellImage(spreadsheet, workbook, outer, inner, objectArr);
                }
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            servletOutputStream.write(bos.toByteArray());
            servletOutputStream.flush();
            workbook.close();
        } finally {
            bos.close();
        }
    }

    public static void createStringCell(XSSFRow row, int index, XSSFSheet spreadsheet, String cellValue) {
        Cell cell = row.createCell(index);
        cell.setCellValue(cellValue);
        cell.getRow().setHeight((short) 1200);
        spreadsheet.setColumnWidth(index, 5000);
    }

    public static void insertCellImage(XSSFSheet spreadsheet, XSSFWorkbook workbook, int outer, int inner, Object[] objectArr) {
        spreadsheet.setColumnWidth(inner, 7000);
        int inputImagePictureID1 = workbook.addPicture((byte[]) objectArr[inner], Workbook.PICTURE_TYPE_JPEG);
        XSSFDrawing drawing = spreadsheet.createDrawingPatriarch();
        XSSFClientAnchor imageAnchor = new XSSFClientAnchor();
        imageAnchor.setCol1(inner);
        imageAnchor.setCol2(inner + 1);
        imageAnchor.setRow1(outer + 1);
        imageAnchor.setRow2(outer + 2);
        drawing.createPicture(imageAnchor, inputImagePictureID1);
    }
}
