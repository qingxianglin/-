/*
 * Created by JFormDesigner on Mon Jan 29 20:09:44 CST 2018
 */

package com.sun.swingset3.test;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author lin lego
 */
public class CardRechargeBottom extends JPanel {
    public CardRechargeBottom() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - lin lego
        panel2 = new JPanel();
        label14 = new JLabel();
        comboBox1 = new JComboBox<>();
        label15 = new JLabel();
        textField12 = new JTextField();
        label16 = new JLabel();
        textField13 = new JTextField();
        label17 = new JLabel();
        textField14 = new JTextField();
        label18 = new JLabel();
        textArea1 = new JTextArea();
        button1 = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "default:grow",
            "default:grow"));

        //======== panel2 ========
        {
            panel2.setBackground(new Color(204, 204, 204));
            panel2.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "\u50a8\u503c\u5361\u5145\u503c", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
            panel2.setLayout(new FormLayout(
                "default:grow, 2*($lcgap, default), 2*($lcgap, default:grow)",
                "8*(default, $lgap), default"));

            //---- label14 ----
            label14.setText("IC\u5361\u7c7b\u578b:");
            label14.setForeground(Color.black);
            panel2.add(label14, CC.xy(3, 1));

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u6708\u5361/\u5e74\u5361",
                "\u50a8\u503c\u5361"
            }));
            panel2.add(comboBox1, CC.xy(5, 1));

            //---- label15 ----
            label15.setText("\u5145\u503c\u91d1\u989d:");
            label15.setForeground(Color.red);
            panel2.add(label15, CC.xy(3, 3));

            //---- textField12 ----
            textField12.setForeground(new Color(255, 0, 51));
            panel2.add(textField12, CC.xy(5, 3));

            //---- label16 ----
            label16.setText("\u8d60\u9001\u91d1\u989d:");
            label16.setForeground(Color.red);
            panel2.add(label16, CC.xy(3, 5));
            panel2.add(textField13, CC.xy(5, 5));

            //---- label17 ----
            label17.setText("\u5408\u8ba1\u5145\u503c:");
            label17.setForeground(new Color(255, 0, 51));
            panel2.add(label17, CC.xy(3, 7));
            panel2.add(textField14, CC.xy(5, 7));

            //---- label18 ----
            label18.setText("\u5145\u503c\u5907\u6ce8:");
            label18.setForeground(Color.black);
            panel2.add(label18, CC.xy(3, 9));
            panel2.add(textArea1, CC.xywh(5, 9, 4, 5));

            //---- button1 ----
            button1.setText("\u9a6c\u4e0a\u5145\u503c");
            button1.setForeground(Color.black);
            panel2.add(button1, CC.xy(5, 15));
        }
        add(panel2, CC.xy(1, 1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - lin lego
    private JPanel panel2;
    private JLabel label14;
    private JComboBox<String> comboBox1;
    private JLabel label15;
    private JTextField textField12;
    private JLabel label16;
    private JTextField textField13;
    private JLabel label17;
    private JTextField textField14;
    private JLabel label18;
    private JTextArea textArea1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
