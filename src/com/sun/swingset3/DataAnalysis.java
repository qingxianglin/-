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
        DataSet.addValue(300, "number", "测试停车场1");
        DataSet.addValue(400, "number", "测试停车场2");
        DataSet.addValue(250, "number", "测试停车场3");
        DataSet.addValue(330, "number", "测试停车场4");
        DataSet.addValue(420, "number", "测试停车场5");
        DataSet.addValue(300, "number", "测试停车场6");
        DataSet.addValue(400, "number", "测试停车场7");
        DataSet.addValue(250, "number", "测试停车场8");
        DataSet.addValue(330, "number", "测试停车场9");
        DataSet.addValue(400, "number", "测试停车场12");
        DataSet.addValue(250, "number", "测试停车场23");
        DataSet.addValue(330, "number", "测试停车场34");
        DataSet.addValue(420, "number", "测试停车场45");
        DataSet.addValue(300, "number", "测试停车场56");
        DataSet.addValue(400, "number", "测试停车场67");
        DataSet.addValue(250, "number", "测试停车场68");
        DataSet.addValue(330, "number", "测试停车场59");
        DataSet.addValue(300, "number", "测试停车场226");
        DataSet.addValue(400, "number", "测试停车场227");
        DataSet.addValue(250, "number", "测试停车场338");
        DataSet.addValue(330, "number", "测试停车场1119");
        DataSet.addValue(400, "number", "测试停车场1222");
        DataSet.addValue(250, "number", "测试停车场2ww3");
        DataSet.addValue(330, "number", "测试停车场33w4");
        DataSet.addValue(420, "number", "测试停车场4ww5");
        DataSet.addValue(300, "number", "测试停车场56");
        DataSet.addValue(400, "number", "测试停车场67");
        DataSet.addValue(250, "number", "测试停车场68");
        DataSet.addValue(330, "number", "测试停车场59");
        //创建柱形图
        JFreeChart chart = ChartFactory.createBarChart3D("停车场收入柱形图",
                "停车场名称", "当天收入", DataSet, PlotOrientation.VERTICAL,
                false, false, false);
        //处理中文显示乱码问题
        handleFont(chart);
        //用来放置图表
        ChartPanel panel = new ChartPanel(chart);
        this.image = panel;
        /*JPanel jp = new JPanel();
        jp.add(panel, BorderLayout.CENTER);
        this.add(jp);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 700, 500);
        this.setVisible(true);*/
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
