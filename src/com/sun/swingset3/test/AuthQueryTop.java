/*
 * Created by JFormDesigner on Thu Jan 25 17:20:30 CST 2018
 */

package com.sun.swingset3.test;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.swingset3.demos.aaa.AuthCreatePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author lin lego
 */
public class AuthQueryTop extends JPanel {
    public AuthQueryTop(JTabbedPane parentTabbedPanel) {
        initComponents(parentTabbedPanel);
    }

    private void initComponents(final JTabbedPane parentTabbedPanel) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hhh ddd
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label3 = new JLabel();
        separator1 = new JSeparator();


        //======== this ========
        setBackground(new Color(204, 204, 204));

        // JFormDesigner evaluation mark
       /* setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new Font("Dialog", Font.BOLD, 12),
                Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});*/

        setLayout(new FormLayout(
            "5*(default:grow, $lcgap), default:grow",
            "2*(default, $lgap), default:grow"));

        //---- button1 ----
        button1.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u67e5\u770b\u5361\u4fe1\u606f.png"));
        add(button1, CC.xy(1, 1));

        //---- button2 ----
        button2.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u65b0\u589e\u5361\u7247.png"));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentTabbedPanel.addTab("新增权限",new AuthCreatePanel(parentTabbedPanel).getDemoPanel());
                parentTabbedPanel.setSelectedIndex(1);
            }
        });
        add(button2, CC.xy(3, 1));

        //---- button3 ----
        button3.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u7f16\u8f91\u4fe1\u606f.png"));
        add(button3, CC.xy(5, 1));

        //---- button4 ----
        button4.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\TabbedPaneDemo6.png"));
        add(button4, CC.xy(7, 1));

        //---- button5 ----
        button5.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\TabbedPaneDemo5.png"));
        add(button5, CC.xy(9, 1));

        //---- button6 ----
        button6.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u4e0b\u8f7d.png"));
        add(button6, CC.xy(11, 1));

        //---- label1 ----
        label1.setText("\u67e5\u770b\u8d44\u6599");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.black);
        add(label1, CC.xy(1, 3));

        //---- label2 ----
        label2.setText("新增权限");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(Color.black);
        add(label2, CC.xy(3, 3));

        //---- label4 ----
        label4.setText("\u4fee\u6539\u8d44\u6599");
        label4.setForeground(Color.black);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        add(label4, CC.xy(5, 3));

        //---- label5 ----
        label5.setText("\u6302\u5931/\u6ce8\u9500");
        label5.setForeground(Color.black);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        add(label5, CC.xy(7, 3));

        //---- label6 ----
        label6.setText("\u5145\u503c/\u7eed\u8d39");
        label6.setForeground(Color.black);
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        add(label6, CC.xy(9, 3));

        //---- label3 ----
        label3.setText("\u6570\u636e\u5bfc\u51fa");
        label3.setForeground(Color.black);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        add(label3, CC.xy(11, 3));

        //---- separator1 ----
        separator1.setForeground(Color.black);
        //add(separator1, CC.xywh(1, 5, 11, 1));

        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hhh ddd
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label3;
    private JSeparator separator1;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
