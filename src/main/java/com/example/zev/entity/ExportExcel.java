package com.example.zev.entity;

import java.util.List;

public interface ExportExcel {
    // Trả về danh sách header theo thứ tự mong muốn
    List<String> getExcelHeaders();

    // Trả về dữ liệu tương ứng theo cùng thứ tự
    List<Object> getExcelData();
}
