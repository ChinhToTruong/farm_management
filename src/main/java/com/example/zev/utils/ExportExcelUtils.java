package com.example.zev.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelUtils {

  public static String exportExcelBase64(
      String title,
      List<String> headers,
      List<Object[]> rows
  ) throws IOException {

    try (Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      Sheet sheet = workbook.createSheet("Report");

      int columnCount = headers.size();

      createTitle(sheet, workbook, title, columnCount);
      createHeader(sheet, workbook, headers);
      createDataRows(sheet, workbook, rows, columnCount);
      autoSizeColumns(sheet, columnCount);

      sheet.createFreezePane(0, 4);

      workbook.write(out);

      // 👉 convert sang base64
      return Base64.getEncoder()
          .encodeToString(out.toByteArray());
    }
  }

  // ================= TITLE =================
  private static void createTitle(
      Sheet sheet,
      Workbook workbook,
      String title,
      int columnCount
  ) {

    for (int i = 0; i < 3; i++) {
      sheet.createRow(i).setHeightInPoints(28);
    }

    sheet.addMergedRegion(
        new CellRangeAddress(0, 2, 0, columnCount - 1)
    );

    Row row = sheet.getRow(0);
    Cell cell = row.createCell(0);
    cell.setCellValue(title);

    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setBold(true);
    font.setFontHeightInPoints((short) 18);

    style.setFont(font);
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setWrapText(true);

    cell.setCellStyle(style);
  }

  // ================= HEADER =================
  private static void createHeader(
      Sheet sheet,
      Workbook workbook,
      List<String> headers
  ) {

    Row headerRow = sheet.createRow(3);

    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setBold(true);

    style.setFont(font);
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    setBorder(style);

    for (int i = 0; i < headers.size(); i++) {
      Cell cell = headerRow.createCell(i);
      cell.setCellValue(headers.get(i));
      cell.setCellStyle(style);
    }
  }

  // ================= DATA =================
  private static void createDataRows(
      Sheet sheet,
      Workbook workbook,
      List<Object[]> rows,
      int columnCount
  ) {

    CellStyle style = workbook.createCellStyle();
    setBorder(style);

    int rowIdx = 4;

    for (Object[] rowData : rows) {

      Row row = sheet.createRow(rowIdx++);

      for (int col = 0; col < columnCount; col++) {

        Cell cell = row.createCell(col);
        cell.setCellStyle(style);

        Object value =
            col < rowData.length ? rowData[col] : null;

        setCellValue(cell, value);
      }
    }
  }

  // ================= AUTO SIZE =================
  private static void autoSizeColumns(Sheet sheet, int columnCount) {
    for (int i = 0; i < columnCount; i++) {
      sheet.autoSizeColumn(i);
    }
  }

  // ================= HELPER =================
  private static void setBorder(CellStyle style) {
    style.setBorderTop(BorderStyle.THIN);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
  }

  private static void setCellValue(Cell cell, Object value) {

    if (value instanceof Number number) {
      cell.setCellValue(number.doubleValue());
    } else if (value instanceof LocalDate date) {
      cell.setCellValue(date.toString());
    } else if (value instanceof LocalDateTime dateTime) {
      cell.setCellValue(dateTime.toString());
    } else if (value != null) {
      cell.setCellValue(value.toString());
    } else {
      cell.setCellValue("");
    }
  }
}
