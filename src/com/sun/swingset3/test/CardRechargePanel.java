/*
 * Created by JFormDesigner on Mon Jan 29 16:40:31 CST 2018
 */

package com.sun.swingset3.test;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.sun.swingset3.sql.bean.CardBean;

/**
 * @author lin lego
 */
public class CardRechargePanel extends JPanel {
    public CardRechargePanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - lin lego
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label13 = new JLabel();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        label7 = new JLabel();
        textField7 = new JTextField();
        label8 = new JLabel();
        textField8 = new JTextField();
        label9 = new JLabel();
        textField9 = new JTextField();
        label10 = new JLabel();
        textField10 = new JTextField();
        label12 = new JLabel();
        textField11 = new JTextField();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "IC\u5361\u57fa\u672c\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
            panel1.setBackground(new Color(204, 204, 204));
            panel1.setLayout(new FormLayout(
                "default:grow, $lcgap, default, 2*($lcgap, default:grow), $lcgap, default, 4*($lcgap, default:grow), 4*($lcgap, default)",
                "4*(default, $lgap), default"));

            //---- label1 ----
            label1.setText("IC\u5361\u53f7:");
            label1.setForeground(Color.black);
            panel1.add(label1, CC.xy(3, 1));

            //---- textField1 ----
            textField1.setEditable(false);
            textField1.setEnabled(false);
            panel1.add(textField1, CC.xy(5, 1));

            //---- label2 ----
            label2.setText("IC\u5361\u7c7b\u578b:");
            label2.setForeground(Color.black);
            panel1.add(label2, CC.xy(9, 1));

            //---- textField2 ----
            textField2.setEditable(false);
            textField2.setEnabled(false);
            panel1.add(textField2, CC.xy(11, 1));

            //---- label13 ----
            label13.setIcon(new ImageIcon(getClass().getResource("/resources/images/\u5934\u50cf.png")));
            panel1.add(label13, CC.xywh(13, 1, 11, 9));

            //---- label4 ----
            label4.setText("\u6301\u5361\u4eba\u59d3\u540d:");
            label4.setForeground(Color.black);
            panel1.add(label4, CC.xy(3, 3));

            //---- textField4 ----
            textField4.setEditable(false);
            textField4.setEnabled(false);
            panel1.add(textField4, CC.xy(5, 3));

            //---- label5 ----
            label5.setText("\u6301\u5361\u4eba\u6027\u522b:");
            label5.setForeground(Color.black);
            panel1.add(label5, CC.xy(9, 3));

            //---- textField5 ----
            textField5.setEditable(false);
            textField5.setEnabled(false);
            panel1.add(textField5, CC.xy(11, 3));

            //---- label7 ----
            label7.setText("\u6301\u5361\u4eba\u624b\u673a:");
            label7.setForeground(Color.black);
            panel1.add(label7, CC.xy(3, 5));

            //---- textField7 ----
            textField7.setEditable(false);
            textField7.setEnabled(false);
            panel1.add(textField7, CC.xy(5, 5));

            //---- label8 ----
            label8.setText("\u5230\u671f\u65f6\u95f4:");
            label8.setForeground(Color.black);
            panel1.add(label8, CC.xy(9, 5));

            //---- textField8 ----
            textField8.setEditable(false);
            textField8.setEnabled(false);
            panel1.add(textField8, CC.xy(11, 5));

            //---- label9 ----
            label9.setText("IC\u5361\u72b6\u6001:");
            label9.setForeground(Color.black);
            panel1.add(label9, CC.xy(3, 7));

            //---- textField9 ----
            textField9.setEditable(false);
            textField9.setEnabled(false);
            panel1.add(textField9, CC.xy(5, 7));

            //---- label10 ----
            label10.setText("\u91d1\u989d\u603b\u6570:");
            label10.setForeground(Color.black);
            panel1.add(label10, CC.xy(9, 7));

            //---- textField10 ----
            textField10.setEditable(false);
            textField10.setEnabled(false);
            panel1.add(textField10, CC.xy(11, 7));

            //---- label12 ----
            label12.setText("\u5907\u6ce8:");
            label12.setForeground(Color.black);
            panel1.add(label12, CC.xy(3, 9));

            //---- textField11 ----
            textField11.setEnabled(false);
            panel1.add(textField11, CC.xywh(5, 9, 7, 1));
        }
        add(panel1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - lin lego
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label13;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label7;
    private JTextField textField7;
    private JLabel label8;
    private JTextField textField8;
    private JLabel label9;
    private JTextField textField9;
    private JLabel label10;
    private JTextField textField10;
    private JLabel label12;
    private JTextField textField11;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
