/*
 * Created by JFormDesigner on Thu Jan 25 11:11:32 CST 2018
 */

package com.sun.swingset3.test;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.swingset3.sql.bean.CarInBean;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

/**
 * @author lin lego
 */
public class CarInSuccess extends JPanel {
    public CarInSuccess(final JTabbedPane parentTabbedPane, CarInBean carInBean) {
        initComponents(carInBean);
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                parentTabbedPane.setSelectedIndex(0);
                parentTabbedPane.removeTabAt(3);
                parentTabbedPane.removeTabAt(2);
                parentTabbedPane.removeTabAt(1);
            }
        },3000);
    }

    private void initComponents(CarInBean carInBean) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - lin lego
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        label1 = new JLabel();
        separator1 = compFactory.createSeparator("本次车辆入场信息明细");
        table1 = new JTable();

        //======== this ========
        setBackground(new Color(204, 255, 204));

        // JFormDesigner evaluation mark
        setBorder(new CompoundBorder(
            new TitledBorder(new EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", TitledBorder.CENTER,
                TitledBorder.BOTTOM, new Font("Dialog", Font.BOLD, 12),
                Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "default:grow, $lcgap, default, 2*($lcgap, default:grow)",
            "3*(default, $lgap), 12*(default:grow, $lgap), default"));

        //---- label1 ----
        label1.setText(" 开闸放行成功!3秒后自动返回首页...");
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
                {"车辆入场时间:", sdf.format(carInBean.getCarInTime())},
                {"车牌号码:", carInBean.getCarNo()},
                {"停车场地址:", carInBean.getAddress()},
                {"停车位:", carInBean.getStopNo()},
                {"收费员姓名:", carInBean.getManagerName()},
                {"使用的IC卡种类:", carInBean.getCardType()},
                {"IC卡号:", carInBean.getCardId()}
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
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        // tcr.setHorizontalAlignment(JLabel.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        table1.setDefaultRenderer(Object.class, tcr);
        add(table1, CC.xywh(3, 7, 3, 14));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - lin lego
    private JLabel label1;
    private JComponent separator1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args){
        JFrame frame = new JFrame();

        /*frame.setSize(300,500);
        frame.getContentPane().add(new CarInSuccess());
        frame.setVisible(true);*/
    }

}
