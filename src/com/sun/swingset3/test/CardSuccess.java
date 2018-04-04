/*
 * Created by JFormDesigner on Thu Jan 25 11:11:32 CST 2018
 */

package com.sun.swingset3.test;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.sun.swingset3.sql.bean.CardBean;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.border.*;

/**
 * @author lin lego
 */
public class CardSuccess extends JPanel {
    public CardSuccess(CardBean cardBean) {
        initComponents(cardBean);
    }

    private void initComponents(CardBean cardBean) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - lin lego
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        label1 = new JLabel();
        separator1 = compFactory.createSeparator("\u672c\u6b21\u53d1\u884c\u7684IC\u5361\u8be6\u60c5");
        table1 = new JTable();
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        // tcr.setHorizontalAlignment(JLabel.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        table1.setDefaultRenderer(Object.class, tcr);
        //======== this ========
        setBackground(new Color(204, 255, 204));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "default:grow, $lcgap, default, 2*($lcgap, default:grow)",
            "3*(default, $lgap), 12*(default:grow, $lgap), default"));

        //---- label1 ----
        label1.setText(" IC\u505c\u8f66\u5361\u53d1\u884c\u6210\u529f!");
        label1.setIcon(new ImageIcon(getClass().getResource("/resources/images/\u6210\u529f.png")));
        label1.setForeground(Color.black);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
        add(label1, CC.xy(3, 3, CC.RIGHT, CC.DEFAULT));
        add(separator1, CC.xywh(3, 5, 3, 1));

        //---- table1 ----
        table1.setRowHeight(35);
        table1.setBackground(Color.white);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        table1.setModel(new DefaultTableModel(
            new Object[][] {
                {"IC卡号:", cardBean.getCardId()},
                {"IC卡类型:", cardBean.getCardType()},
                {"持卡人姓名:", cardBean.getName()},
                {"性别:", cardBean.getGender()},
                {"手机号:", cardBean.getPhone()},
                {"到期时间:", sdf.format(cardBean.getExpireTime())},
                {"可停车次数:", cardBean.getAvailable()},
                {"总金额:", cardBean.getMoney()},
                {"IC卡状态:", cardBean.getStatus()},
                {"备注:", null}
            },
            new String[] {
                null, null
            }
        ) {
            boolean[] columnEditable = new boolean[] {
                false, false
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnEditable[columnIndex];
            }
        });
        table1.setForeground(Color.black);
        table1.setBorder(new LineBorder(Color.black, 1, true));
        table1.setCellSelectionEnabled(true);
        table1.setShowHorizontalLines(true);
        table1.setShowVerticalLines(true);
        add(table1, CC.xywh(3, 7, 3, 23));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - lin lego
    private JLabel label1;
    private JComponent separator1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
