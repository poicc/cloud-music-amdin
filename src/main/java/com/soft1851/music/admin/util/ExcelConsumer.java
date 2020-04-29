package com.soft1851.music.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.concurrent.CountDownLatch;

/**
 * @author CRQ
 */
@Slf4j
public class ExcelConsumer<T> implements Runnable {
    /**
     * 一张表可以容纳的最大行数
     */
    private static Integer SHEET_SIZE = 100000;

    /**
     * 导出的VO的数据类型
     */
    private Class<T> clazz;

    /**
     * 工作薄
     */
    private SXSSFWorkbook wb;

    /**
     * 工作表名称
     */
    private String sheetName;

    /**
     * 数据缓冲区对象
     */
    private ExportDataAdapter<T> exportDataAdapter;

    /**
     * 线程同步
     */
    private CountDownLatch latch;

    public ExcelConsumer(Class<T> clazz,ExportDataAdapter<T> exportDataAdapter,SXSSFWorkbook wb,CountDownLatch latch,String sheetName){
        if (clazz == null || wb == null || exportDataAdapter == null || latch == null) {
            log.error("ExcelConsumer::初始化对象参数不能为空");
            return;
        }
        this.clazz = clazz;
        this.exportDataAdapter = exportDataAdapter;
        this.wb = wb;
        this.latch = latch;
        this.sheetName = sheetName == null ? "UnNamedSheet" : sheetName;
    }


    @Override
    public void run() {
        //初始化excel导出工具类
        ExcelUtil<T> excelUtil = new ExcelUtil<>(this.clazz);
        Sheet sheet = null;
        int sheetNo = 0;
        int rowNum = 1;
        T vo;
        //生产者还在生产数据
        while (latch.getCount() > 1) {
            //生成sheetName
            if (rowNum == 1) {
                sheetNo++;
                sheet = excelUtil.createSheet(wb, sheetName.concat(Integer.toString(sheetNo)));
                excelUtil.setColumnTitle(sheet);
            }
            //获取数据
            vo = exportDataAdapter.getData();
            //往excel添加一行数据
            excelUtil.addRowData(vo, wb, sheet, rowNum);
            rowNum++;
            //准备生成下一个sheetName
            if (rowNum == SHEET_SIZE + 1) {
                rowNum = 1;
            }
        }
        //生产者不再生产数据，取剩余数据，并将数据写入excel
        Integer reminderDataSize = exportDataAdapter.getDataSize();
        T reminderData;
        if (reminderDataSize > 0) {
            for (int i = 0; i < reminderDataSize; i++) {
                reminderData = exportDataAdapter.getData();
                if (rowNum == 1) {
                    sheetNo++;
                    sheet = excelUtil.createSheet(wb, sheetName.concat(Integer.toString(sheetNo)));
                    excelUtil.setColumnTitle(sheet);
                }
                excelUtil.addRowData(reminderData, wb, sheet, rowNum);
                rowNum++;
                if (rowNum == SHEET_SIZE + 1) {
                    rowNum = 1;
                }
            }
        }
        log.info("数据导出完成");
        latch.countDown();

    }
}
