/**
 * @fileName: ExcelUtil.java
 * @author: pjf
 * @date: 2019年12月26日 上午11:56:57
 */

package com.wxhj.cloud.core.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.context.MessageSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @className ExcelUtil.java
 * @author pjf
 * @date 2019年12月26日 上午11:56:57
 */

/**
 * @author pjf
 * @className ExcelUtil.java
 * @date 2019年12月26日 上午11:56:57
 */

public class ExcelUtil {
    public static final String OFFICE_EXCEL_XLS = "xls";
    public static final String OFFICE_EXCEL_XLSX = "xlsx";
    private static Map<String, List<Pair<Field, ExcelColumnAnnotation>>> fieldAnnotationListMap = Maps.newHashMap();

    /**
     * 设置格式
     */
    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = Maps.newHashMap();

        // 标题样式
        XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平对齐
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
        titleStyle.setLocked(true); // 样式锁定
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleStyle.setFont(titleFont);
        styles.put("title", titleStyle);

        // 文件头样式
        XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // 前景色
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 颜色填充方式
        headerStyle.setWrapText(true);
        headerStyle.setBorderRight(BorderStyle.THIN); // 设置边界
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        titleFont.setFontName("微软雅黑");
        headerStyle.setFont(headerFont);
        styles.put("header", headerStyle);

        Font cellStyleFont = wb.createFont();
        cellStyleFont.setFontHeightInPoints((short) 12);
        cellStyleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
        cellStyleFont.setFontName("微软雅黑");

        // 正文样式A
        XSSFCellStyle cellStyleA = (XSSFCellStyle) wb.createCellStyle();
        cellStyleA.setAlignment(HorizontalAlignment.CENTER); // 居中设置
        cellStyleA.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleA.setWrapText(true);
        cellStyleA.setBorderRight(BorderStyle.THIN);
        cellStyleA.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderLeft(BorderStyle.THIN);
        cellStyleA.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderTop(BorderStyle.THIN);
        cellStyleA.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderBottom(BorderStyle.THIN);
        cellStyleA.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setFont(cellStyleFont);
        styles.put("cellA", cellStyleA);

        // 正文样式B:添加前景色为浅黄色
        XSSFCellStyle cellStyleB = (XSSFCellStyle) wb.createCellStyle();
        cellStyleB.setAlignment(HorizontalAlignment.CENTER);
        cellStyleB.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleB.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyleB.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleB.setWrapText(true);
        cellStyleB.setBorderRight(BorderStyle.THIN);
        cellStyleB.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderLeft(BorderStyle.THIN);
        cellStyleB.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderTop(BorderStyle.THIN);
        cellStyleB.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderBottom(BorderStyle.THIN);
        cellStyleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setFont(cellStyleFont);
        styles.put("cellB", cellStyleB);

        return styles;
    }

    private static List<Pair<Field, ExcelColumnAnnotation>> getFieldAnnotationList(Class<?> tClass) {
        String key = tClass.getName();
        List<Pair<Field, ExcelColumnAnnotation>> fieldAnnotationList = null;
        fieldAnnotationList = fieldAnnotationListMap.get(key);
        if (fieldAnnotationList != null) {
            return fieldAnnotationList;
        }
        fieldAnnotationList = new ArrayList<>();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            ExcelColumnAnnotation excelColumn = field.getAnnotation(ExcelColumnAnnotation.class);
            if (excelColumn != null) {
                Pair<Field, ExcelColumnAnnotation> fieldAnnotation = new ImmutablePair<>(field, excelColumn);
                fieldAnnotationList.add(fieldAnnotation);
            }
        }
        fieldAnnotationListMap.put(key, fieldAnnotationList);
        return fieldAnnotationList;
    }

    // 以上为标准部分
    // 以下为改进部分
    public static <T> byte[] writeExcel(List<T> tList, Class<T> tClass, Locale locale, MessageSource messageSource)
            throws IOException, IllegalArgumentException, IllegalAccessException {
        //
        List<Pair<Field, ExcelColumnAnnotation>> fieldAnnotationList = getFieldAnnotationList(tClass);
        Workbook workbook = new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet;
        sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成样式
        Map<String, CellStyle> styles = createStyles(workbook);
        // 创建标题行
        Row row = sheet.createRow(0);
        // 存储标题在Excel文件中的序号
        Map<String, Integer> titleOrder = Maps.newHashMap();
        for (int i = 0; i < fieldAnnotationList.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(styles.get("header"));
            String title = messageSource.getMessage(fieldAnnotationList.get(i).getRight().columnName(), null, locale);
            cell.setCellValue(title);
            titleOrder.put(title, i);
        }
        // 写入正文
        int index = 1;
        for (T tTemp : tList) {
            row = sheet.createRow(index);
            for (int i = 0; i < fieldAnnotationList.size(); i++) {
                // 在指定序号处创建cell
                Cell cell = row.createCell(i);
                // 设置cell的样式
                if (index % 2 == 1) {
                    cell.setCellStyle(styles.get("cellA"));
                } else {
                    cell.setCellStyle(styles.get("cellB"));
                }
                // 获取列的值
                fieldAnnotationList.get(i).getLeft().setAccessible(true);
                Object object = fieldAnnotationList.get(i).getLeft().get(tTemp);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (object instanceof Double) {
                    cell.setCellValue((Double) object);
                } else if (object instanceof Date) {
                    String time = simpleDateFormat.format((Date) object);
                    cell.setCellValue(time);
                } else if (object instanceof Calendar) {
                    Calendar calendar = (Calendar) object;
                    String time = simpleDateFormat.format(calendar.getTime());
                    cell.setCellValue(time);
                } else if (object instanceof Boolean) {
                    cell.setCellValue((Boolean) object);
                } else {
                    if (object != null) {
                        cell.setCellValue(object.toString());
                    }
                }
            }
            index++;
        }

        byte[] returnByte = null;
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            returnByte = outputStream.toByteArray();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (workbook != null) {
                workbook.close();
            }
        }
        return returnByte;
    }

    //	private static byte[] toByte(String url) throws IOException {
//		byte[] buffer;
//		File file = new File(url);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(fis.available());
//		byte[] bytes = new byte[fis.available()];
//		int temp;
//		while ((temp = fis.read(bytes)) != -1) {
//			baos.write(bytes, 0, temp);
//		}
//		fis.close();
//		baos.close();
//		buffer = baos.toByteArray();
//		return buffer;
//	}
//	
    public static List<String[]> readExcel(byte[] fileByte) throws IOException {
        List<String[]> dataList = new ArrayList<String[]>();
        XSSFWorkbook workbook = new XSSFWorkbook(new ByteArrayInputStream(fileByte));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        if (rowNum <= 1) {
            return dataList;
        }
        int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 1; i < rowNum; i++) {
            String[] dataTemp = new String[cellNum];
//			String[] dataTemp = new String[]();
            XSSFRow xssfRow = sheet.getRow(i);

            int cellNumTemp = xssfRow.getPhysicalNumberOfCells();
            for (int j = 0; j < cellNumTemp; j++) {
                String excelStr = formatCellType(xssfRow.getCell(j));
                if (!Strings.isNullOrEmpty(excelStr)) {
                    dataTemp[j] = formatCellType(xssfRow.getCell(j));
                }
            }
            if (dataTemp[0] != null) {
                dataList.add(dataTemp);
            }
        }
        return dataList;
    }

    private static String formatCellType(XSSFCell xssFCell) {
        switch (xssFCell.getCellTypeEnum()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(xssFCell)) {
                    DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", LocaleUtil.getUserLocale());
                    sdf.setTimeZone(LocaleUtil.getUserTimeZone());
                    return sdf.format(xssFCell.getDateCellValue());
                }
                return
                        String.valueOf(
                                Math.round(xssFCell.getNumericCellValue()));
            case STRING:
                return xssFCell.getRichStringCellValue().toString();
            case FORMULA:
                return xssFCell.getCellFormula();
            case BLANK:
                return "";
            case BOOLEAN:
                return xssFCell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case ERROR:
                return ErrorEval.getText(xssFCell.getErrorCellValue());
            default:
                return "Unknown Cell Type: " + xssFCell.getCellTypeEnum();
        }

    }


}
