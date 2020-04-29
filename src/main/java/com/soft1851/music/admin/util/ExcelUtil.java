package com.soft1851.music.admin.util;

import com.soft1851.music.admin.annotation.ExcelVoAttribute;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CRQ
 */
public class ExcelUtil<T> {
    Class<T> clazz;

    /**
     * 表头字段列表
     */
    private List<Field> fields;

    /**
     * 数字单元格对象
     */
    private CellStyle decimalCellStyle = null;

    /**
     * 日期时间单元格对象
     */
    private CellStyle dateTimeCellStyle = null;

    /**
     * 构造方法
     *
     * @param clazz
     */
    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 添加一条数据
     *
     * @param vo:需要导出的vo对象
     * @param wb：工作簿
     * @param sheet：工作表
     * @param rowNum：当前行号
     */
    public void addRowData(T vo, SXSSFWorkbook wb, Sheet sheet, int rowNum) {
        //创建一行
        Row row = sheet.createRow(rowNum);
        Field field;
        Cell cell;
        ExcelVoAttribute attr;
        int fieldSize = fields.size();
        // 遍历入参对象的所有属性
        for (int j = 0; j < fieldSize; j++) {
            // 通过反射获得需要导出的入参对象的所有属性
            field = fields.get(j);
            // 设置实体类私有属性可访问
            field.setAccessible(true);
            // 获取所有添加了注解的属性
            attr = field.getAnnotation(ExcelVoAttribute.class);
            // 给每个属性创建一个单元格
            cell = row.createCell(j);
            try {
                this.setCellValue(attr, field.get(vo), wb, cell);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据注解类判断字段类型设置excel单元格数据格式方法
     *
     * @param attr
     * @param valueObject
     * @param workbook
     * @param cell
     */
    private void setCellValue(ExcelVoAttribute attr, Object valueObject, SXSSFWorkbook workbook, Cell cell) {
        String returnValue;
        if (attr.isNumber()) {
            cell.setCellStyle(getCellStyle(attr, workbook));
            returnValue = valueObject == null || "".equals(valueObject) ? "0" : valueObject.toString();
            BigDecimal num = new BigDecimal(returnValue);
            cell.setCellValue(num.doubleValue());
        } else if (attr.isDateTime()) {
            cell.setCellStyle(getCellStyle(attr, workbook));
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            returnValue = df.format((TemporalAccessor) valueObject);
            cell.setCellValue(returnValue);
        } else {
            returnValue = valueObject == null ? "" : valueObject.toString();
            cell.setCellValue(returnValue);
        }
    }

    /**
     * 根据注解类判断字段类型，返回excel单元格数据格式方法
     *
     * @param attr
     * @param workbook
     * @return CellStyle
     */
    private CellStyle getCellStyle(ExcelVoAttribute attr, SXSSFWorkbook workbook) {
        if (attr.isNumber()) {
            if (decimalCellStyle == null) {
                decimalCellStyle = workbook.createCellStyle();
                //此处设置数字单元格格式
                DataFormat df = workbook.createDataFormat();
                //千分位,保留1位小数
                decimalCellStyle.setDataFormat(df.getFormat("#,##0.0"));
            }
            return decimalCellStyle;
        }
        if (attr.isDateTime()) {
            if (dateTimeCellStyle == null) {
                dateTimeCellStyle = workbook.createCellStyle();
                //此处设置日期时间单元格格式
                DataFormat df = workbook.createDataFormat();
                dateTimeCellStyle.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
            }
            return dateTimeCellStyle;
        }
        return null;
    }

    /**
     * 创建工作页Sheet
     *
     * @param wb
     * @param sheetName
     * @return Sheet
     */
    public Sheet createSheet(SXSSFWorkbook wb, String sheetName) {

        return wb.createSheet(sheetName);
    }

    /**
     * 设置excel列头及格式
     *
     * @param sheet
     */
    public void setColumnTitle(Sheet sheet) {
        if (fields == null) {
            this.fields = this.getSortFields();
        }
        Row row;
        Cell cell;
        ExcelVoAttribute attr;
        Field field;
        int fieldSize = fields.size();
        row = sheet.createRow(0);
        for (int i = 0; i < fieldSize; i++) {
            field = fields.get(i);
            attr = field.getAnnotation(ExcelVoAttribute.class);
            cell = CellUtil.createCell(row, i, attr.name());
            // 设置列宽，根据相应的字段名的长度等比
            sheet.setColumnWidth(i, attr.name().getBytes().length * 400);
        }
    }

    /**
     * 获取输出对象字段列表，并根据注解进行字段排序
     *
     * @return
     */
    private List<Field> getSortFields() {
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(x -> x.isAnnotationPresent(ExcelVoAttribute.class)).collect(Collectors.toList());
        List<Field> sortList = new ArrayList<>(fields);
        //排序
        for (Field field : fields) {
            ExcelVoAttribute excelVoAttribute = field.getAnnotation(ExcelVoAttribute.class);
            int sortNo = excelVoAttribute.column();
            sortList.set(sortNo, field);
        }
        return sortList;
    }
}
