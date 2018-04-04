/*
 * Created by JFormDesigner on Wed Jan 24 16:48:23 CST 2018
 */

package com.sun.swingset3.test;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.swingset3.demos.aaa.TableDemo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 卡片发行界面的表单
 */
public class UserCreateRight extends JPanel{
    public UserCreateRight() {
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
        label4.addMouseListener(new OpenActionListener());
        label12 = new JLabel();
        textField7 = new JTextField();
        comboBox2 = new JComboBox<>();
        label9 = new JLabel();
        comboBox1 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label13 = new JLabel();
        textArea1 = new JTextField();
        label6 = new JLabel();
        passwordField1 = new JPasswordField();
        label14 = new JLabel();
        passwordField2 = new JTextField();
        button1 = new JButton();
        label15 = new JLabel();
        textField8 = new JAutoCompleteComboBox3();
        label16 = new JLabel();
        jlist.setVisibleRowCount(5);
        jlist.setSelectedIndex(0);
        textField9 = new JScrollPane(jlist);
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
        label11.setText("性别:");
        label11.setForeground(Color.black);
        add(label11, CC.xy(1, 5));

        //---- label1 ----
        label1.setText("出生日期:");
        label1.setForeground(Color.black);
        add(label1, CC.xy(1, 7));

        //---- textField1 ----
        textField1.setBackground(Color.white);
        add(textField1, CC.xy(3, 7));

        //---- label4 ----
        label4.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u5934\u50cf.png"));
        add(label4, CC.xywh(5, 2, 1, 10, CC.DEFAULT, CC.CENTER));

        //---- label12 ----
        label12.setText("姓名:");
        label12.setForeground(Color.black);
        add(label12, CC.xy(1, 3));

        //---- textField7 ----
        textField7.setBackground(Color.white);
        add(textField7, CC.xy(3, 3));

        //---- comboBox2 ----
        comboBox2.setBackground(Color.white);
        comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
            "男",
            "女"
        }));
        comboBox2.setForeground(Color.black);
        add(comboBox2, CC.xy(3, 5));

        //---- label9 ----
        label9.setText("身份证号码:");
        label9.setForeground(Color.black);
        add(label9, CC.xy(1, 9));

        //---- comboBox1 ----
        comboBox1.setBackground(Color.white);
        comboBox1.setForeground(Color.black);
        add(comboBox1, CC.xy(3, 9));

        //---- label3 ----
        label3.setText("手机号码:");
        label3.setForeground(Color.black);
        add(label3, CC.xy(1, 11));

        //---- textField3 ----
        textField3.setBackground(Color.white);
        add(textField3, CC.xy(3, 11));

        //---- label13 ----
        label13.setText("员工号:");
        label13.setForeground(Color.black);
        add(label13, CC.xy(1, 13));

        //---- textArea1 ----
        textArea1.setBackground(Color.white);
        add(textArea1, CC.xy(3, 13));

        //---- label6 ----
        label6.setText("账号密码:");
        label6.setForeground(Color.black);
        add(label6, CC.xy(1, 15));

        //---- passwordField1 ----
        passwordField1.setBackground(Color.white);
        add(passwordField1, CC.xy(3, 15));

        //---- label14 ----
        label14.setText("所属层级:");
        label14.setForeground(Color.black);
        add(label14, CC.xy(1, 17));

        //---- passwordField2 ----
        passwordField2.setBackground(Color.white);
        add(passwordField2, CC.xy(3, 17));

        //---- button1 ----
        button1.setText("关联选中的角色");
        add(button1, CC.xywh(5, 19, 1, 1));

        //---- label15 ----
        label15.setText("关联新角色:");
        label15.setForeground(Color.black);
        add(label15, CC.xy(1, 19));

        //---- textField8 ----
        textField8.setBackground(Color.white);
        //add(textField8, CC.xy(3, 19));
        add(textField8,CC.xy(3,19));

        //---- label16 ----
        label16.setText("已关联角色:");
        label16.setBackground(Color.black);
        label16.setForeground(Color.black);
        add(label16, CC.xy(1, 21));


        add(comboBox3, CC.xy(3, 21));

        //---- button2 ----
        button2.setText("确认无误,点击创建");
        add(button2, CC.xy(5, 21));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hhh ddd
     JLabel label11;
     JLabel label1;
     JTextField textField1;
     JLabel label4;
     JLabel label12;
     JTextField textField7;
     JComboBox<String> comboBox2;
     JLabel label9;
     JTextField comboBox1;
     JLabel label3;
     JTextField textField3;
     JLabel label13;
     JTextField textArea1;
     JLabel label6;
     JPasswordField passwordField1;
     JLabel label14;
     JTextField passwordField2;
     JButton button1;
     JLabel label15;
     JAutoCompleteComboBox3 textField8;
     JLabel label16;
    JScrollPane textField9;
    JList jlist = new JList();
    String picturePath;
     JButton button2;
     JLabel label17;
    JTextField textField10;
     JLabel label18;
     JComboBox<String> comboBox3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args){
        JFrame f = new JFrame();
        UserCreateRight test  = new UserCreateRight();
        f.getContentPane().add(test);
        // 再将滚动条组件添加到中间容器中
        f.setTitle("表格测试窗口");
        f.pack();
        f.setVisible(true);
    }

    class OpenActionListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
            fileChooser.setFileFilter(filter);

            int n = fileChooser.showOpenDialog(fileChooser);
            if (n == fileChooser.APPROVE_OPTION) {
                ImageIcon image = new ImageIcon(fileChooser.getSelectedFile().getPath());
                Image img = image.getImage();
                img = img.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                image.setImage(img);
                label4.setIcon(image);
                picturePath = fileChooser.getSelectedFile().getPath();
            }
        }
    }
}
