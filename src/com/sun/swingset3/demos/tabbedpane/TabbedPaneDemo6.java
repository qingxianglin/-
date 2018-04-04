/*
 * Copyright 2007-2008 Sun Microsystems, Inc.  All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.sun.swingset3.demos.tabbedpane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.swingset3.Demo;
import com.sun.swingset3.DemoProperties;
import com.sun.swingset3.SwingSet3;
import com.sun.swingset3.demos.ResourceManager;
import com.sun.swingset3.demos.aaa.*;
import com.sun.swingset3.demos.list.ListDemo;
import com.sun.swingset3.demos.optionpane.OptionPaneDemo;
import com.sun.swingset3.demos.tree.TreeDemo;
import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.CardBean;
import com.sun.swingset3.test.CardQueryTop;
import com.sun.swingset3.test.CardRechargeBottom;
import com.sun.swingset3.test.CardRechargePanel;
import com.sun.swingset3.test.CardSuccess;

/**
 * IC卡充值
 *
 * @version 1.11 11/17/05
 * @author Jeff Dinkins
 */
@DemoProperties(
        value = "账户挂失/注销/解挂",
        category = "IC停车卡管理",
        description = "Demonstrates JTabbedPane, a container which allows tabbed navigation of components",
        sourceFiles = {
                /*"com/sun/swingset3/demos/tabbedpane/TabbedPaneDemo.java",
                "com/sun/swingset3/demos/ResourceManager.java",
                "com/sun/swingset3/demos/tabbedpane/resources/TabbedPaneDemo.properties",
                "com/sun/swingset3/demos/tabbedpane/resources/images/blake.gif",
                "com/sun/swingset3/demos/tabbedpane/resources/images/brooke.gif",
                "com/sun/swingset3/demos/tabbedpane/resources/images/camille.jpg",
                "com/sun/swingset3/demos/tabbedpane/resources/images/david.gif",
                "com/sun/swingset3/demos/tabbedpane/resources/images/ewan.gif",
                "com/sun/swingset3/demos/tabbedpane/resources/images/ewan.jpg",
                "com/sun/swingset3/demos/tabbedpane/resources/images/miranda.jpg",
                "com/sun/swingset3/demos/tabbedpane/resources/images/matthew.gif",
                "com/sun/swingset3/demos/tabbedpane/resources/images/stephen.gif",
                "com/sun/swingset3/demos/tabbedpane/resources/images/TabbedPaneDemo.gif"*/
        }
)
public class TabbedPaneDemo6 extends JPanel implements ActionListener {
    CardBean card;
    public Demo demo;
    private JButton jButton = new JButton("呵呵");
    private final ResourceManager resourceManager = new ResourceManager(this.getClass());

    private final HeadSpin spin;

    private final JTabbedPane tabbedpane;

    private final ButtonGroup group;

    private final JRadioButton top;
    private final JRadioButton bottom;
    private final JRadioButton left;
    private final JRadioButton right;

    private JLabel progressBar;
    private ImageIcon carIn1;
    private ImageIcon carIn2;
    private ImageIcon carIn3;
    private ImageIcon carIn4;
    CardQueryTop cardQueryTop = new CardQueryTop();
    CardRechargePanel cardRechargePanel = new CardRechargePanel();
    CardRechargeBottom cardRechargeBottom = new CardRechargeBottom();
    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();
    /**
     * TabbedPaneDemo Constructor
     */
    public TabbedPaneDemo6() {
        carIn1 = resourceManager.createImageIcon("进场进度1.png", "");
        carIn2 = resourceManager.createImageIcon("进场进度2.png", "");
        carIn3 = resourceManager.createImageIcon("进场进度3.png", "");
        carIn4 = resourceManager.createImageIcon("进场进度4.png", "");
        setLayout(new BorderLayout());
        // create tab position controls
        JPanel tabControls = new JPanel();
        tabControls.add(new JLabel(resourceManager.getString("TabbedPaneDemo.label")));
        top = (JRadioButton) tabControls.add(new JRadioButton(resourceManager.getString("TabbedPaneDemo.top")));
        left = (JRadioButton) tabControls.add(new JRadioButton(resourceManager.getString("TabbedPaneDemo.left")));
        bottom = (JRadioButton) tabControls.add(new JRadioButton(resourceManager.getString("TabbedPaneDemo.bottom")));
        right = (JRadioButton) tabControls.add(new JRadioButton(resourceManager.getString("TabbedPaneDemo.right")));
        //add(tabControls, BorderLayout.NORTH);
        progressBar = new JLabel(carIn1);
        add(cardQueryTop,BorderLayout.NORTH);
        group = new ButtonGroup();
        group.add(top);
        group.add(bottom);
        group.add(left);
        group.add(right);

        top.setSelected(true);

        top.addActionListener(this);
        bottom.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);

        // create tab
        tabbedpane = new JTabbedPane();
        add(tabbedpane, BorderLayout.CENTER);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        center.add(cardRechargePanel, BorderLayout.NORTH);
        center.add(cardRechargeBottom, BorderLayout.CENTER);

        tabbedpane.add("马上充值",center);

        //tabbedpane.add("历史充值记录", new CardSuccess(null));

        spin = new HeadSpin();

        tabbedpane.getModel().addChangeListener(
                new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        SingleSelectionModel model = (SingleSelectionModel) e.getSource();
                        if (model.getSelectedIndex() == 3) {
                            spin.go();
                        }
                        if(model.getSelectedIndex()==1){
                            progressBar.setIcon(carIn2);
                        }
                        if(model.getSelectedIndex()==2){
                            progressBar.setIcon(carIn3);
                        }
                        if(model.getSelectedIndex()==3){
                            progressBar.setIcon(carIn4);
                        }
                    }
                }
        );
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == top) {
            tabbedpane.setTabPlacement(JTabbedPane.TOP);
        } else if (e.getSource() == left) {
            tabbedpane.setTabPlacement(JTabbedPane.LEFT);
        } else if (e.getSource() == bottom) {
            tabbedpane.setTabPlacement(JTabbedPane.BOTTOM);
        } else if (e.getSource() == right) {
            tabbedpane.setTabPlacement(JTabbedPane.RIGHT);
        }
    }

    private class HeadSpin extends JComponent implements ActionListener {
        private javax.swing.Timer animator;

        private final ImageIcon[] icon = new ImageIcon[6];

        private final static int numImages = 6;

        private final double[] x = new double[numImages];
        private final double[] y = new double[numImages];

        private final int[] xh = new int[numImages];
        private final int[] yh = new int[numImages];

        private final double[] scale = new double[numImages];

        private final Random rand = new Random();

        public HeadSpin() {
            setBackground(Color.black);
            icon[0] = resourceManager.createImageIcon("ewan.gif", resourceManager.getString("TabbedPaneDemo.ewan"));
            icon[1] = resourceManager.createImageIcon("stephen.gif", resourceManager.getString("TabbedPaneDemo.stephen"));
            icon[2] = resourceManager.createImageIcon("david.gif", resourceManager.getString("TabbedPaneDemo.david"));
            icon[3] = resourceManager.createImageIcon("matthew.gif", resourceManager.getString("TabbedPaneDemo.matthew"));
            icon[4] = resourceManager.createImageIcon("blake.gif", resourceManager.getString("TabbedPaneDemo.blake"));
            icon[5] = resourceManager.createImageIcon("brooke.gif", resourceManager.getString("TabbedPaneDemo.brooke"));

            /*
             for(int i = 0; i < 6; i++) {
                 x[i] = (double) rand.nextInt(500);
                 y[i] = (double) rand.nextInt(500);
             }
             */
        }

        public void go() {
            //animator = new javax.swing.Timer(22 + 22 + 22, this);
            //animator.start();
            //tabbedpane.setSelectedIndex(0);
            //tabbedpane.add("hehe1",new TreeDemo());
            //tabbedpane.setSelectedIndex(tabbedpane.getTabCount()-1);
            //tabbedpane.setSelectedIndex(0);
            //for(int i = tabbedpane.getTabCount()-1;i>0;--i){
            //    tabbedpane.removeTabAt(i);
            //}
        }

        public void paint(Graphics g) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());

            for (int i = 0; i < numImages; i++) {
                if (x[i] > 3 * i) {
                    nudge(i);
                    squish(g, icon[i], xh[i], yh[i], scale[i]);
                } else {
                    x[i] += .05;
                    y[i] += .05;
                }
            }
        }

        public void nudge(int i) {
            x[i] += (double) rand.nextInt(1000) / 8756;
            y[i] += (double) rand.nextInt(1000) / 5432;
            int tmpScale = (int) (Math.abs(Math.sin(x[i])) * 10);
            scale[i] = (double) tmpScale / 10;
            int nudgeX = (int) (((double) getWidth() / 2) * .8);
            int nudgeY = (int) (((double) getHeight() / 2) * .60);
            xh[i] = (int) (Math.sin(x[i]) * nudgeX) + nudgeX;
            yh[i] = (int) (Math.sin(y[i]) * nudgeY) + nudgeY;
        }

        public void squish(Graphics g, ImageIcon icon, int x, int y, double scale) {
            if (isVisible()) {
                g.drawImage(icon.getImage(), x, y,
                        (int) (icon.getIconWidth() * scale),
                        (int) (icon.getIconHeight() * scale),
                        this);
            }
        }

        public void actionPerformed(ActionEvent e) {
            if (isVisible()) {
                repaint();
            } else {
                animator.stop();
            }
        }
    }

    class CardQueryTop extends JPanel {
        public CardQueryTop() {
            initComponents();
        }

        private void initComponents() {
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
            label7 = new JLabel();
            textField1 = new JTextField();
            label8 = new JLabel();
            comboBox2 = new JComboBox();
            comboBox2.addItem("月卡/年卡");
            comboBox2.addItem("储值卡");
            label9 = new JLabel();
            textField3 = new JTextField();
            label10 = new JLabel();
            comboBox1 = new JComboBox();
            comboBox1.addItem("男");
            comboBox1.addItem("女");
            label11 = new JLabel();
            textField4 = new JTextField();
            label12 = new JLabel();
            comboBox3 = new JComboBox();
            comboBox3.addItem("正常");
            comboBox3.addItem("已挂失");
            comboBox3.addItem("已注销");
            label13 = new JLabel();
            textField5 = new JTextField();
            button7 = new JButton();

            //======== this ========
            setBackground(new Color(204, 204, 204));

            // JFormDesigner evaluation mark
            setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            setLayout(new FormLayout(
                    "5*(default:grow, $lcgap), default:grow",
                    "6*(default, $lgap), default:grow"));

            //---- button1 ----
            button1.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u67e5\u770b\u5361\u4fe1\u606f.png"));
            add(button1, CC.xy(1, 1));

            //---- button2 ----
            button2.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u65b0\u589e\u5361\u7247.png"));
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
            label2.setText("\u65b0\u589eIC\u5361");
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
            add(separator1, CC.xywh(1, 5, 11, 1));

            //---- label7 ----
            label7.setText("\u6301\u5361\u4eba\u59d3\u540d:");
            label7.setForeground(Color.black);
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            add(label7, CC.xy(1, 7));
            add(textField1, CC.xy(3, 7));

            //---- label8 ----
            label8.setText("IC\u5361\u7c7b\u578b:");
            label8.setForeground(Color.black);
            label8.setHorizontalAlignment(SwingConstants.RIGHT);
            add(label8, CC.xy(5, 7));
            add(comboBox2, CC.xy(7, 7));

            //---- label9 ----
            label9.setText("IC\u5361\u53f7:");
            label9.setForeground(Color.black);
            label9.setHorizontalAlignment(SwingConstants.RIGHT);
            add(label9, CC.xy(9, 7));
            add(textField3, CC.xy(11, 7));

            //---- label10 ----
            label10.setText("\u6027\u522b:");
            label10.setForeground(Color.black);
            label10.setHorizontalAlignment(SwingConstants.RIGHT);
            add(label10, CC.xy(1, 9));
            add(comboBox1, CC.xy(3, 9));

            //---- label11 ----
            label11.setText("\u624b\u673a\u53f7\u7801:");
            label11.setForeground(Color.black);
            label11.setHorizontalAlignment(SwingConstants.RIGHT);
            add(label11, CC.xy(5, 9));
            add(textField4, CC.xy(7, 9));

            //---- label12 ----
            label12.setText("IC\u5361\u72b6\u6001");
            label12.setForeground(Color.black);
            label12.setHorizontalAlignment(SwingConstants.RIGHT);
            add(label12, CC.xy(9, 9));
            add(comboBox3, CC.xy(11, 9));

            //---- label13 ----
            label13.setText("\u59d3\u540d:");
            label13.setHorizontalAlignment(SwingConstants.RIGHT);
            label13.setForeground(Color.black);
            add(label13, CC.xy(1, 11));
            add(textField5, CC.xy(3, 11));

            //---- button7 ----
            button7.setText("\u67e5\u8be2");
            button7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardBean queryBean = new CardBean();
                    queryBean.setName(cardQueryTop.getTextField1().getText());
                    if(textField3.getText()!=null && !textField3.getText().equals("")){
                        queryBean.setCardId(Integer.parseInt(getTextField3().getText()));
                    }
                    queryBean.setCardType(comboBox2.getSelectedItem().toString());
                    queryBean.setPhone(textField4.getText());
                    queryBean.setGender(comboBox1.getSelectedItem().toString());
                    queryBean.setStatus(comboBox3.getSelectedItem().toString());
                    java.util.List<CardBean> list = parkingLotDBUtils.queryCard(queryBean);
                    if(list!=null&&list.size()>0){
                        card = list.get(0);
                        cardRechargePanel.textField1.setText(card.getCardId().toString());
                        cardRechargePanel.textField2.setText(card.getCardType());
                        cardRechargePanel.textField4.setText(card.getName());
                        cardRechargePanel.textField5.setText(card.getGender());
                        cardRechargePanel.textField7.setText(card.getPhone());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                        cardRechargePanel.textField8.setText(sdf.format(card.getExpireTime()));
                        cardRechargePanel.textField9.setText(card.getCardType());
                        cardRechargePanel.textField10.setText(card.getMoney().toString());
                        cardRechargeBottom.textField12.setText(card.getMoney().toString());
                    }
                }
            });
            add(button7, CC.xy(7, 11));
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
        private JLabel label7;
        private JTextField textField1;
        private JLabel label8;
        private JComboBox comboBox2;
        private JLabel label9;
        private JTextField textField3;
        private JLabel label10;
        private JComboBox comboBox1;
        private JLabel label11;
        private JTextField textField4;
        private JLabel label12;
        private JComboBox comboBox3;
        private JLabel label13;
        private JTextField textField5;
        private JButton button7;
        // JFormDesigner - End of variables declaration  //GEN-END:variables

        public JTextField getTextField1() {
            return textField1;
        }

        public void setTextField1(JTextField textField1) {
            this.textField1 = textField1;
        }

        public JComboBox getComboBox2() {
            return comboBox2;
        }

        public void setComboBox2(JComboBox comboBox2) {
            this.comboBox2 = comboBox2;
        }

        public JTextField getTextField3() {
            return textField3;
        }

        public void setTextField3(JTextField textField3) {
            this.textField3 = textField3;
        }

        public JComboBox getComboBox1() {
            return comboBox1;
        }

        public void setComboBox1(JComboBox comboBox1) {
            this.comboBox1 = comboBox1;
        }

        public JTextField getTextField4() {
            return textField4;
        }

        public void setTextField4(JTextField textField4) {
            this.textField4 = textField4;
        }

        public JComboBox getComboBox3() {
            return comboBox3;
        }

        public void setComboBox3(JComboBox comboBox3) {
            this.comboBox3 = comboBox3;
        }

        public JTextField getTextField5() {
            return textField5;
        }

        public void setTextField5(JTextField textField5) {
            this.textField5 = textField5;
        }
    }

    class CardRechargePanel extends JPanel {
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

    class CardRechargeBottom extends JPanel {
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
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card.setStatus(comboBox1.getSelectedItem().toString());
                    parkingLotDBUtils.updateCard(card);
                    JOptionPane.showMessageDialog(
                            CardRechargeBottom.this,
                            "IC卡号:"+card.getCardId()+",状态变更为:"+comboBox1.getSelectedItem().toString(),
                            "IC卡充值成功",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

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
                panel2.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "IC卡状态变更", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                panel2.setLayout(new FormLayout(
                        "default:grow, 2*($lcgap, default), 2*($lcgap, default:grow)",
                        "8*(default, $lgap), default"));

                //---- label14 ----
                label14.setText("IC\u5361\u7c7b\u578b:");
                label14.setForeground(Color.black);
                panel2.add(label14, CC.xy(3, 1));

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                        "正常",
                        "注销",
                        "解挂"
                }));
                panel2.add(comboBox1, CC.xy(5, 1));

                //---- label15 ----
                label15.setText("卡内余额:");
                label15.setForeground(Color.red);
                //panel2.add(label15, CC.xy(3, 3));

                //---- textField12 ----
                textField12.setForeground(new Color(255, 0, 51));
                //panel2.add(textField12, CC.xy(5, 3));

                //---- label16 ----
                label16.setText("充值金额:");
                label16.setForeground(Color.red);
                //panel2.add(label16, CC.xy(3, 5));
                //panel2.add(textField13, CC.xy(5, 5));

                //---- label17 ----
                label17.setText("合计金额:");
                label17.setForeground(new Color(255, 0, 51));
                //panel2.add(label17, CC.xy(3, 7));
                //panel2.add(textField14, CC.xy(5, 7));

                //---- label18 ----
                label18.setText("\u5145\u503c\u5907\u6ce8:");
                label18.setForeground(Color.black);
                //panel2.add(label18, CC.xy(3, 9));
                //panel2.add(textArea1, CC.xywh(5, 9, 4, 5));

                //---- button1 ----
                button1.setText("点击进行卡状态变更");
                button1.setForeground(Color.black);
                panel2.add(button1, CC.xy(5, 3));
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
}

