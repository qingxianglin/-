/*
 * Created by JFormDesigner on Wed Jan 24 16:48:23 CST 2018
 */

package com.sun.swingset3.test;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 卡片发行界面的表单
 */
public class RoleCreateRight extends JPanel{
    public RoleCreateRight() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red,1,true)));
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hhh ddd
        label11 = new JLabel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label4 = new JLabel();
        label12 = new JLabel();
        textField7 = new JTextField();
        comboBox2 = new JComboBox<>();
        label9 = new JLabel();
        comboBox1 = new JComboBox<>();
        label3 = new JLabel();
        textField3 = new JTextField();
        label13 = new JLabel();
        textArea1 = new JTextArea();
        label6 = new JLabel();
        passwordField1 = new JPasswordField();
        label14 = new JLabel();
        passwordField2 = new JPasswordField();
        button1 = new JButton();
        label15 = new JLabel();
        textField8 = new JTextField();
        label16 = new JLabel();
        textField9 = new JTextField();
        button2 = new JButton();
        label17 = new JLabel();
        textField10 = new JTextField();
        label18 = new JLabel();
        comboBox3 = new JComboBox<>();

        //======== this ========
        setBackground(new Color(232, 232, 232));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", TitledBorder.CENTER,
                TitledBorder.BOTTOM, new Font("Dialog", Font.BOLD, 12),
                Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "right:default, $lcgap, default:grow, $lcgap, center:[50dlu,default], $lcgap, default",
            "pref, 27*($lgap, default)"));

        //---- label11 ----
        label11.setText("IC\u5361\u7c7b\u578b:");
        label11.setForeground(Color.black);
        add(label11, CC.xy(1, 5));

        //---- label1 ----
        label1.setText("\u59d3\u540d:");
        label1.setForeground(Color.black);
        add(label1, CC.xy(1, 7));

        //---- textField1 ----
        textField1.setBackground(Color.white);
        add(textField1, CC.xy(3, 7));

        //---- label4 ----
        label4.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u5934\u50cf.png"));
        add(label4, CC.xywh(5, 2, 1, 10, CC.DEFAULT, CC.CENTER));

        //---- label12 ----
        label12.setText("IC\u5361\u53f7");
        label12.setForeground(Color.black);
        add(label12, CC.xy(1, 3));

        //---- textField7 ----
        textField7.setBackground(Color.white);
        add(textField7, CC.xy(3, 3));

        //---- comboBox2 ----
        comboBox2.setBackground(Color.white);
        comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u6708\u5361/\u5e74\u5361",
            "\u50a8\u503c\u5361"
        }));
        comboBox2.setForeground(Color.black);
        add(comboBox2, CC.xy(3, 5));

        //---- label9 ----
        label9.setText("\u6027\u522b:");
        label9.setForeground(Color.black);
        add(label9, CC.xy(1, 9));

        //---- comboBox1 ----
        comboBox1.setBackground(Color.white);
        comboBox1.setForeground(Color.black);
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7537",
            "\u5973"
        }));
        add(comboBox1, CC.xy(3, 9));

        //---- label3 ----
        label3.setText("\u624b\u673a\u53f7:");
        label3.setForeground(Color.black);
        add(label3, CC.xy(1, 11));

        //---- textField3 ----
        textField3.setBackground(Color.white);
        add(textField3, CC.xy(3, 11));

        //---- label13 ----
        label13.setText("\u5907\u6ce8:");
        label13.setForeground(Color.black);
        add(label13, CC.xy(1, 13));

        //---- textArea1 ----
        textArea1.setBackground(Color.white);
        add(textArea1, CC.xywh(3, 13, 3, 5));

        //---- label6 ----
        label6.setText("\u5bc6\u7801:");
        label6.setForeground(Color.black);
        add(label6, CC.xy(1, 19));

        //---- passwordField1 ----
        passwordField1.setBackground(Color.white);
        add(passwordField1, CC.xy(3, 19));

        //---- label14 ----
        label14.setText("\u518d\u8f93\u4e00\u6b21:");
        label14.setForeground(Color.black);
        add(label14, CC.xy(1, 21));

        //---- passwordField2 ----
        passwordField2.setBackground(Color.white);
        add(passwordField2, CC.xy(3, 21));

        //---- button1 ----
        button1.setText("\u6e05\u7a7a\u8868\u5355\u6570\u636e");
        add(button1, CC.xywh(5, 21, 1, 3));

        //---- label15 ----
        label15.setText("\u5230\u671f\u65f6\u95f4:");
        label15.setForeground(Color.black);
        add(label15, CC.xy(1, 23));

        //---- textField8 ----
        textField8.setBackground(Color.white);
        add(textField8, CC.xy(3, 23));

        //---- label16 ----
        label16.setText("\u53ef\u505c\u8f66\u603b\u6b21\u6570:");
        label16.setBackground(Color.black);
        label16.setForeground(Color.black);
        add(label16, CC.xy(1, 25));

        //---- textField9 ----
        textField9.setBackground(Color.white);
        add(textField9, CC.xy(3, 25));

        //---- button2 ----
        button2.setText("\u786e\u8ba4\u4fe1\u606f\u65e0\u8bef");
        add(button2, CC.xywh(5, 25, 1, 3));

        //---- label17 ----
        label17.setText("\u91d1\u989d\u603b\u6570:");
        label17.setForeground(Color.black);
        add(label17, CC.xy(1, 27));

        //---- textField10 ----
        textField10.setBackground(Color.white);
        add(textField10, CC.xy(3, 27));

        //---- label18 ----
        label18.setText("IC\u5361\u72b6\u6001:");
        label18.setForeground(Color.black);
        add(label18, CC.xy(1, 29));

        //---- comboBox3 ----
        comboBox3.setBackground(Color.white);
        comboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u6b63\u5e38",
            "\u5df2\u6302\u5931",
            "\u5df2\u6ce8\u9500"
        }));
        add(comboBox3, CC.xy(3, 29));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hhh ddd
    private JLabel label11;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label4;
    private JLabel label12;
    private JTextField textField7;
    private JComboBox<String> comboBox2;
    private JLabel label9;
    private JComboBox<String> comboBox1;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label13;
    private JTextArea textArea1;
    private JLabel label6;
    private JPasswordField passwordField1;
    private JLabel label14;
    private JPasswordField passwordField2;
    private JButton button1;
    private JLabel label15;
    private JTextField textField8;
    private JLabel label16;
    private JTextField textField9;
    private JButton button2;
    private JLabel label17;
    private JTextField textField10;
    private JLabel label18;
    private JComboBox<String> comboBox3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args){
        JFrame f = new JFrame();
        RoleCreateRight test  = new RoleCreateRight();
        f.getContentPane().add(test);
        // 再将滚动条组件添加到中间容器中
        f.setTitle("表格测试窗口");
        f.pack();
        f.setVisible(true);
    }
}
