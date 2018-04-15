package com.sun.swingset3;

import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D; 
import org.jfree.data.general.DefaultPieDataset; 
import org.jfree.chart.ChartUtilities;

import javax.swing.*;

public class PieChart3D
{
   JPanel image;
   public PieChart3D(){
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "第一天" , new Double( 20 ) );
      dataset.setValue( "第二天" , new Double( 20 ) );
      dataset.setValue( "第三天" , new Double( 40 ) );
      dataset.setValue( "第四天" , new Double( 10 ) );

      JFreeChart chart = ChartFactory.createPieChart3D(
              "停车场收入饼图" ,  // chart title
              dataset ,         // data
              true ,            // include legend
              true,
              false);

      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );
      plot.setStartAngle( 270 );
      plot.setForegroundAlpha( 0.60f );
      plot.setInteriorGap( 0.02 );
      ChartPanel panel = new ChartPanel(chart);
      this.image = panel;
   }

   public void updateData(Integer[] data){
      DefaultPieDataset dataset = new DefaultPieDataset( );
      for(int i = 7;i<=22;++i){
         dataset.setValue(i+"点",data[i-7]);
      }
      JFreeChart chart = ChartFactory.createPieChart3D(
              "停车场收入饼图" ,  // chart title
              dataset ,         // data
              true ,            // include legend
              true,
              false);

      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );
      plot.setStartAngle( 270 );
      plot.setForegroundAlpha( 0.60f );
      plot.setInteriorGap( 0.02 );
      ChartPanel panel = new ChartPanel(chart);
      this.image = panel;
   }

   public JPanel getImage(){
      return this.image;
   }
}