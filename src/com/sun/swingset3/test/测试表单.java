/*
 * Created by JFormDesigner on Wed Jan 24 17:23:33 CST 2018
 */

package com.sun.swingset3.test;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author lin lego
 */
public class 测试表单 extends JPanel {
    public 测试表单() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - lin lego
        table1 = new JTable();

        //======== this ========
        setBackground(Color.white);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "2*(default:grow, $lcgap), default:grow",
            "2*(default:grow, $lgap), default:grow"));

        //---- table1 ----
        table1.setBackground(Color.lightGray);
        table1.setModel(new DefaultTableModel(
            new Object[][] {
                {"\u7b49\u4e30\u5bcc\u7684", "\u7684 v \u53d1\u7684 v \u5206", null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
            },
            new String[] {
                null, null, null, null
            }
        ));
        add(table1, CC.xywh(1, 1, 5, 5));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - lin lego
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
