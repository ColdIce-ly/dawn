package com.xiaoyuan.poi.util.fileio;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
    /**
     * 2003- 版本的excel
     */
    public final static String EXCEL2003L = ".xls";

    /**
     * 2007+ 版本的excel
     */
    public final static String EXCEL2007U = ".xlsx";

    /**
     * @param sheetName 工作簿名称
     * @param clazz     数据源model类型
     * @param objs      excel标题列以及对应model字段名
     * @param map       标题列行数以及cell字体样式
     */
    private static XSSFWorkbook createExcelFile(Class<?> clazz, List<?> objs, Map<Integer, List<ExcelBean>> map, String sheetName) throws
            IllegalArgumentException, IllegalAccessException, InvocationTargetException,
            IntrospectionException {
        // 创建新的Excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
        //字体样式
        XSSFCellStyle xssfCellStyle = createTableHeadFont(workbook);
        //创建标题（头）
        createTableHeader(xssfCellStyle, sheet, map);

        XSSFCellStyle tableBodyFont = createTableBodyFont(workbook);
        //创建内容
        createTableRows(tableBodyFont, sheet, map, objs, clazz);

        return workbook;
    }

    /**
     * @param workbook
     * @description excel文件标题字体样式
     */
    private static XSSFCellStyle createTableHeadFont(XSSFWorkbook workbook) {

        // 表头
        XSSFCellStyle fontStyle = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        // 设置字体
        font1.setFontName("黑体");
        // 设置字体大小
        font1.setFontHeightInPoints((short) 14);
        fontStyle.setFont(font1);
        // 下边框
        fontStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        fontStyle.setBorderLeft(BorderStyle.THIN);
        // 上边框
        fontStyle.setBorderTop(BorderStyle.THIN);
        // 右边框
        fontStyle.setBorderRight(BorderStyle.THIN);
        // 居中
        fontStyle.setAlignment(HorizontalAlignment.CENTER);
        return fontStyle;
    }

    /**
     * @param workbook
     * @description excel文件内容字体样式
     */
    private static XSSFCellStyle createTableBodyFont(XSSFWorkbook workbook) {
        // 内容
        XSSFCellStyle fontStyle2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        // 设置字体大小
        font2.setFontHeightInPoints((short) 10);
        fontStyle2.setFont(font2);
        // 下边框
        fontStyle2.setBorderBottom(BorderStyle.THIN);
        // 左边框
        fontStyle2.setBorderLeft(BorderStyle.THIN);
        // 上边框
        fontStyle2.setBorderTop(BorderStyle.THIN);
        // 右边框
        fontStyle2.setBorderRight(BorderStyle.THIN);
        // 居中
        fontStyle2.setAlignment(HorizontalAlignment.CENTER);
        return fontStyle2;
    }

    /**
     * @param xssfCellStyle 表头格式
     * @param sheet         工作簿
     * @param map           每行每个单元格对应的列头信息
     * @description 根据ExcelMapping 生成列头（多行列头）
     */
    private static void createTableHeader(XSSFCellStyle xssfCellStyle, XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) {
        //cell起始位置
        int startIndex = 0;
        //cell终止位置
        int endIndex = 0;
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) {
                //合并单元格
                if (excels.get(x).getCols() > 1) {
                    if (x == 0) {
                        endIndex += excels.get(x).getCols() - 1;
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                    } else {
                        endIndex += excels.get(x).getCols();
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                    }
                    startIndex += excels.get(x).getCols();
                    XSSFCell cell = row.createCell(startIndex - excels.get(x).getCols());
                    // 设置内容
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) {
                        // 设置格式
                        cell.setCellStyle(excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle(xssfCellStyle);
                } else {
                    XSSFCell cell = row.createCell(x);
                    // 设置内容
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) {
                        // 设置格式
                        cell.setCellStyle(excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle(xssfCellStyle);
                }
            }
        }
    }

    /**
     * @param sheet
     * @param map
     * @param objs
     * @param clazz
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @description 创建内容
     */
    private static void createTableRows(XSSFCellStyle tableBodyFont, XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List<?> objs, Class<?> clazz) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems;
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        List<Integer> widths = new ArrayList<>(ems.size());
        for (Object obj : objs) {
            XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = ems.get(i);
                // 获得get方法
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // 如果是日期类型进行转换
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        value = new SimpleDateFormat("yyyy-MM-dd").format((Date) rtn);
                    } else if (rtn instanceof BigDecimal) {
                        NumberFormat nf = new DecimalFormat("#,##0.00");
                        value = nf.format(rtn);
                    } else if ((rtn instanceof Integer) && (Integer.parseInt(rtn.toString()) < 0)) {
                        value = "--";
                    } else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(CellType.STRING);
                cell.setCellStyle(tableBodyFont);
                // 获得最大列宽
                int width = value.getBytes().length * 300;
                // 还未设置，设置当前
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                }
                // 比原来大，更新数据
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            rowindex++;
        }
        // 设置列宽
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }

    /**
     * @param excelName     工作簿名称
     * @param clazz         数据源model类型
     * @param objs          excel标题列以及对应model字段名
     * @param excelBeanList 标题列行数以及cell字体样式
     * @param response
     * @description 数据导出excel
     */
    public static void dataOutPrintToExcel(Class<?> clazz, List<?> objs, List<ExcelBean> excelBeanList, String excelName, HttpServletResponse response) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
        map.put(0, excelBeanList);

        XSSFWorkbook xssfWorkbook = createExcelFile(clazz, objs, map, excelName);
        response.reset(); //清除buffer缓存
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((excelName + EXCEL2007U).getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream output = null;
        BufferedOutputStream bufferedOutPut = null;
        try {
            output = response.getOutputStream();
            bufferedOutPut = new BufferedOutputStream(output);
            xssfWorkbook.write(bufferedOutPut);
            bufferedOutPut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutPut != null) {
                try {
                    bufferedOutPut.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
