package com.sun.swingset3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class DataAnalysis extends JFrame {
    JPanel image = null;
    public DataAnalysis(){
        //构造DataSet
        DefaultCategoryDataset DataSet = new DefaultCategoryDataset();
        DataSet.addValue(7, "number", "上午7点");
        DataSet.addValue(8, "number", "上午8点");
        DataSet.addValue(9, "number", "上午9点");
        DataSet.addValue(10, "number", "上午10点");
        DataSet.addValue(11, "number", "上午11点");
        DataSet.addValue(12, "number", "上午12点");
        DataSet.addValue(13, "number", "下午13点");
        DataSet.addValue(14, "number", "下午14点");
        DataSet.addValue(15, "number", "下午15点");
        DataSet.addValue(16, "number", "下午16点");
        DataSet.addValue(17, "number", "下午17点");
        DataSet.addValue(18, "number", "晚上18点");
        DataSet.addValue(19, "number", "晚上19点");
        DataSet.addValue(20, "number", "晚上20点");
        DataSet.addValue(21, "number", "晚上21点");
        DataSet.addValue(22, "number", "晚上22点");
        //创建柱形图
        JFreeChart chart = ChartFactory.createBarChart3D("停车场收入柱形图",
                "停车场名称", "当天收入", DataSet, PlotOrientation.VERTICAL,
                false, false, false);
        //处理中文显示乱码问题
        handleFont(chart);
        //用来放置图表
        ChartPanel panel = new ChartPanel(chart);
        this.image = panel;
    }

    public void updateData(Integer[] data){
        //构造DataSet
        DefaultCategoryDataset DataSet = new DefaultCategoryDataset();
        for(int i = 7;i<=22;++i){
            DataSet.addValue(data[i-7], "number", "上午"+i+"点");
        }
        //创建柱形图
        JFreeChart chart = ChartFactory.createBarChart3D("停车场收入柱形图",
                "停车场名称", "当天收入", DataSet, PlotOrientation.VERTICAL,
                false, false, false);
        //处理中文显示乱码问题
        handleFont(chart);
        //用来放置图表
        ChartPanel panel = new ChartPanel(chart);
        this.image = panel;
    }

    /**
     * 处理中文显示乱码问题
     */
    public void handleFont(JFreeChart chart){
        //1. 图形标题文字设置
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("微软雅黑",Font.BOLD,20));
        //2. 图形X轴坐标文字的设置
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis axis = plot.getDomainAxis();
        //3. 横轴上的标签45度倾斜
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        axis.setLabelFont(new Font("微软雅黑",Font.BOLD,22));  //设置X轴坐标上标题的文字
        axis.setTickLabelFont(new Font("微软雅黑",Font.BOLD,15));  //设置X轴坐标上的文字
        //4. 图形Y轴坐标文字的设置
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,15));  //设置Y轴坐标上标题的文字
        valueAxis.setTickLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置Y轴坐标上的文字
    }

    public static void main(String [] args){
        new DataAnalysis();
        System.out.println(System.getProperty("java.library.path"));
    }

    public JPanel getImage() {
        return image;
    }

    public void setImage(JPanel image) {
        this.image = image;
    }
}
