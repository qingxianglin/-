package com.sun.swingset3;

import java.io.*;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PiePlot3D; 
import org.jfree.data.general.DefaultPieDataset; 
import org.jfree.chart.ChartUtilities;

public class PieChart3D 
{
   public static void main( String[ ] args )throws Exception 
   {
      DefaultPieDataset dataset = new DefaultPieDataset( );             
      dataset.setValue( "IPhone 5s" , new Double( 20 ) );             
      dataset.setValue( "SamSung Grand" , new Double( 20 ) );             
      dataset.setValue( "MotoG" , new Double( 40 ) );             
      dataset.setValue( "Nokia Lumia" , new Double( 10 ) ); 

      JFreeChart chart = ChartFactory.createPieChart3D( 
         "Mobile Sales" ,  // chart title                   
         dataset ,         // data 
         true ,            // include legend                   
         true, 
         false);

      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );             
      plot.setStartAngle( 270 );             
      plot.setForegroundAlpha( 0.60f );             
      plot.setInteriorGap( 0.02 );             
      int width = 640; /* Width of the image */             
      int height = 480; /* Height of the image */                             
      File pieChart3D = new File( "pie_Chart3D.jpeg" );                           
      ChartUtilities.saveChartAsJPEG( pieChart3D , chart , width , height );   
   }
}