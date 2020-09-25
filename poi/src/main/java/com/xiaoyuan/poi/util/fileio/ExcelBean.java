package com.xiaoyuan.poi.util.fileio;


import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.Objects;


/**
 * @author Administrator
 */
public class ExcelBean implements java.io.Serializable {

    /**
     * 列头（标题）名
     */
    private String headTextName;

    /**
     * 对应字段名
     */
    private String propertyName;

    /**
     * 合并单元格数
     */
    private Integer cols;

    private XSSFCellStyle cellStyle;


    public ExcelBean() {
    }

    public ExcelBean(String headTextName, String propertyName) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
    }

    public ExcelBean(String headTextName, String propertyName, Integer cols) {
        super();
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = cols;
    }


    public String getHeadTextName() {
        return headTextName;
    }

    public void setHeadTextName(String headTextName) {
        this.headTextName = headTextName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public XSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(XSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExcelBean excelBean = (ExcelBean) o;
        return Objects.equals(headTextName, excelBean.headTextName) &&
                Objects.equals(propertyName, excelBean.propertyName) &&
                Objects.equals(cols, excelBean.cols) &&
                Objects.equals(cellStyle, excelBean.cellStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headTextName, propertyName, cols, cellStyle);
    }

    @Override
    public String toString() {
        return "ExcelBean{" +
                "headTextName='" + headTextName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", cols=" + cols +
                ", cellStyle=" + cellStyle +
                '}';
    }
}
