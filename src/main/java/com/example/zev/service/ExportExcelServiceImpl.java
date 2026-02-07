package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.entity.BaseEntity;
import com.example.zev.entity.ExportExcel;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.CrudRepository;
import java.util.Objects;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public abstract class ExportExcelServiceImpl<T extends BaseEntity & ExportExcel> implements ExportExcelService {
    protected String reportName;

    @Setter(onMethod = @__({@Autowired}))
    private CrudRepository<T> crudRepository;


    /**
     * Xuất danh sách dữ liệu ra file Excel dạng Base64
     * @return Base64 string của file Excel (.xlsx)
     */
    @Override
    public String exportExcel(String reportName) throws BusinessException {

        List<T> data = crudRepository.findAll();

        if (data.isEmpty())
            throw new BusinessException(ErrorCode.EXPORT_FILE_ERROR);


        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet(reportName);

            // ===== 1️⃣ Tạo header =====
            Row headerRow = sheet.createRow(0);
            List<String> headers = data.getFirst().getExcelHeaders();

            // Style cho header (in đậm)
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
                cell.setCellStyle(headerStyle);
            }

            // ===== 2️⃣ Ghi dữ liệu =====
            int rowIdx = 1;
            for (T item : data) {
                Row row = sheet.createRow(rowIdx++);
                List<Object> values = item.getExcelData();
                for (int i = 0; i < values.size(); i++) {
                    if (Objects.isNull(values.get(i))) continue;
                    Object value = values.get(i);
                    row.createCell(i).setCellValue(value != null ? value.toString() : "");
                }
            }

            // ===== 3️⃣ Auto-size cột =====
            for (int i = 0; i < headers.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // ===== 4️⃣ Xuất ra Base64 =====
            workbook.write(out);
            byte[] bytes = out.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.EXPORT_FILE_ERROR);
        }
    }


}
