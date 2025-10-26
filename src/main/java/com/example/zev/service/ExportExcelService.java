package com.example.zev.service;

import com.example.zev.entity.BaseEntity;
import com.example.zev.entity.ExportExcel;
import com.example.zev.exception.BusinessException;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface ExportExcelService {

    String exportExcel(String reportName) throws BusinessException;
}
