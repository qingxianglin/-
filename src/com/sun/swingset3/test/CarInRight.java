/*
 * Created by JFormDesigner on Wed Jan 24 16:48:23 CST 2018
 */

package com.sun.swingset3.test;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.swingset3.demos.aaa.TableDemo1;
import com.sun.swingset3.demos.aaa.TableDemo3;
import com.sun.swingset3.sql.bean.CarInBean;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 卡片发行界面的表单
 */
public class CarInRight extends JPanel{
    CarInBean carInBean = null;
    public CarInRight(JTabbedPane parentTabbedPanel) {
        initComponents(parentTabbedPanel);
    }

    public void setCarInfo(CarInBean carInBean){
        comboBox2.setText(carInBean.getCarNo());
        textField1.setText(carInBean.getAddress());
        comboBox1.setText(carInBean.getStopNo()+"");
        textField3.setText(carInBean.getManagerName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        textField7.setText(sdf.format(carInBean.getCarOutTime()));
        this.carInBean = carInBean;
    }

    private void initComponents(final JTabbedPane parentTabbedPanel) {
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
        comboBox2 = new JTextField();
        label9 = new JLabel();
        comboBox1 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label13 = new JLabel();
        textArea1 = new JTextField();
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
        label11.setText("车牌号码:");
        label11.setForeground(Color.black);
        add(label11, CC.xy(1, 5));

        //---- label1 ----
        label1.setText("停车场地址:");
        label1.setForeground(Color.black);
        add(label1, CC.xy(1, 7));

        //---- textField1 ----
        textField1.setBackground(Color.white);
        add(textField1, CC.xy(3, 7));

        //---- label4 ----
        label4.setIcon(new ImageIcon("/Users/linqingxiang/Desktop/\u672a\u547d\u540d\u6587\u4ef6\u5939/\u5934\u50cf.png"));
        add(label4, CC.xywh(5, 2, 1, 10, CC.DEFAULT, CC.CENTER));

        //---- label12 ----
        label12.setText("车辆出场时间:");
        label12.setForeground(Color.black);
        add(label12, CC.xy(1, 3));

        //---- textField7 ----
        textField7.setBackground(Color.white);
        add(textField7, CC.xy(3, 3));

        //---- comboBox2 ----
        comboBox2.setBackground(Color.white);
        /*comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u6708\u5361/\u5e74\u5361",
            "\u50a8\u503c\u5361"
        }));
        comboBox2.setForeground(Color.black);*/
        add(comboBox2, CC.xy(3, 5));

        //---- label9 ----
        label9.setText("停车位:");
        label9.setForeground(Color.black);
        add(label9, CC.xy(1, 9));

        //---- comboBox1 ----
        comboBox1.setBackground(Color.white);
        comboBox1.setForeground(Color.black);
       /* comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7537",
            "\u5973"
        }));*/
        add(comboBox1, CC.xy(3, 9));

        //---- label3 ----
        label3.setText("收费员姓名:");
        label3.setForeground(Color.black);
        add(label3, CC.xy(1, 11));

        //---- textField3 ----
        textField3.setBackground(Color.white);
        add(textField3, CC.xy(3, 11));

        //---- label13 ----
        label13.setText("使用的IC卡种类:");
        label13.setForeground(Color.black);
        //add(label13, CC.xy(1, 13));

        //---- textArea1 ----
        textArea1.setBackground(Color.white);
        //add(textArea1, CC.xy(3, 13));

        //---- label6 ----
        label6.setText("IC卡号:");
        label6.setForeground(Color.black);
        //add(label6, CC.xy(1, 15));

        //---- passwordField1 ----
        passwordField1.setBackground(Color.white);
        //add(passwordField1, CC.xy(3, 15));

        //---- label14 ----
        label14.setText("8");
        label14.setForeground(Color.black);
        //add(label14, CC.xy(1, 21));

        //---- passwordField2 ----
        passwordField2.setBackground(Color.white);
        //add(passwordField2, CC.xy(3, 21));

        //---- button1 ----
        button1.setText("确认信息无误，下一步");
        button1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                parentTabbedPanel.add("停车费用结算",new TableDemo3(parentTabbedPanel,carInBean).getDemoPanel());
                parentTabbedPanel.setSelectedIndex(1);
            }
        });
        add(button1, CC.xywh(3, 13, 1, 18));

        //---- label15 ----
        label15.setText("10");
        label15.setForeground(Color.black);
        //add(label15, CC.xy(1, 23));

        //---- textField8 ----
        textField8.setBackground(Color.white);
        //add(textField8, CC.xy(3, 23));

        //---- label16 ----
        label16.setText("11");
        label16.setBackground(Color.black);
        label16.setForeground(Color.black);
        //add(label16, CC.xy(1, 25));

        //---- textField9 ----
        textField9.setBackground(Color.white);
        //add(textField9, CC.xy(3, 25));

        //---- button2 ----
        button2.setText("12");
        //add(button2, CC.xywh(5, 25, 1, 3));

        //---- label17 ----
        label17.setText("13");
        label17.setForeground(Color.black);
        //add(label17, CC.xy(1, 27));

        //---- textField10 ----
        textField10.setBackground(Color.white);
        //add(textField10, CC.xy(3, 27));

        //---- label18 ----
        label18.setText("14");
        label18.setForeground(Color.black);
        //add(label18, CC.xy(1, 29));

        //---- comboBox3 ----
        comboBox3.setBackground(Color.white);
        comboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u6b63\u5e38",
            "\u5df2\u6302\u5931",
            "\u5df2\u6ce8\u9500"
        }));
        //add(comboBox3, CC.xy(3, 29));
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
    private JTextField comboBox2;
    private JLabel label9;
    private JTextField comboBox1;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label13;
    private JTextField textArea1;
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
        /*JFrame f = new JFrame();
        CarInRight test  = new CarInRight();
        f.getContentPane().add(test);
        // 再将滚动条组件添加到中间容器中
        f.setTitle("表格测试窗口");
        f.pack();
        f.setVisible(true);*/
    }
}
