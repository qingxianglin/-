package com.sun.swingset3.demos.aaa;

import com.sun.swingset3.DemoModule;
import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.CarInBean;
import com.sun.swingset3.test.CarInSuccess;
import com.sun.swingset3.test.JAutoCompleteComboBox1;
import org.jb2011.lnf.beautyeye.utils.JVM;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRelation;
import javax.accessibility.AccessibleRelationSet;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class TableDemo1 extends DemoModule {
    JRadioButton chooseDayCard = new JRadioButton("<html>月卡/年卡<br> <font style='color:blue'>使用说明:会员使用该类型IC卡,可在一定期限内(单位:月/年)免费停放指定的次数</font></html>");

    JRadioButton chooseChargeCard = new JRadioButton("<html>IC储值卡<br> <font style='color:blue'>使用说明:会员使用该类型IC卡,可预先充值金额, 停车时可以直接刷卡结算，无需现金</font></html>");

    JRadioButton chooseTempCard = new JRadioButton("<html>临时停车卡<br> <font style='color:blue'>使用说明:该类型IC卡仅供非会员用户临时停车使用, 作为车辆出场的凭证, 出场需支付现金进行结算</font></html>");

    ButtonGroup buttonGroup = new ButtonGroup();

    //IC卡
    JLabel dayCard = new JLabel();

    JLabel chargeCard = new JLabel();

    JLabel tempCard = new JLabel();

    JLabel carInImage = new JLabel();

    JTable tableView;

    JScrollPane scrollpane;

    Dimension origin = new Dimension(0, 0);

    JCheckBox isColumnReorderingAllowedCheckBox;

    JCheckBox showHorizontalLinesCheckBox;

    JCheckBox showVerticalLinesCheckBox;

    JCheckBox isColumnSelectionAllowedCheckBox;

    JCheckBox isRowSelectionAllowedCheckBox;

    JLabel interCellSpacingLabel;

    JLabel rowHeightLabel;

    JSlider interCellSpacingSlider;

    JSlider rowHeightSlider;

    JComboBox selectionModeComboBox = null;

    JComboBox resizeModeComboBox = null;

    JLabel headerLabel;

    JLabel headerLabel1;

    JLabel footerLabel;

    JLabel footerLabel1;

    JLabel charger = new JLabel("收费员姓名:");

    JTextField chargerField = new JTextField(25);

    JLabel cardType = new JLabel("使用的IC卡种类:");

    JLabel cardNo = new JLabel("IC卡号:");

    JAutoCompleteComboBox1 cardNoField = new JAutoCompleteComboBox1();

    JComboBox cardTypeBox = new JComboBox();

    JTextField headerTextField;

    JTextField headerTextField1;

    JTextField footerTextField;

    JTextField footerTextField1;

    JCheckBox fitWidth;

    JButton printButton;

    JPanel controlPanel;

    JScrollPane tableAggregate;

    String path = "ImageClub/food/";

    final int INITIAL_ROWHEIGHT = 25;// 原代码是33，由jb2011改为25，好看些

    public static void main(String[] args) {
        //TableDemo1 demo = new TableDemo1();
        //demo.mainImpl();
    }

    @Override
    public String getName() {
        return "表格";
    }

    public TableDemo1(final JTabbedPane parentTabbedPane, final CarInBean carInBean) {
        getDemoPanel().setLayout(new BorderLayout());
        getDemoPanel().setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red,1,true)));
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.green,3,true)));
        //controlPanel.setBorder();
        JPanel cbPanel = new JPanel(new GridLayout(3, 2));
        JPanel labelPanel = new JPanel(new GridLayout(2, 1)) {
            public Dimension getMaximumSize() {
                return new Dimension(getPreferredSize().width, super.getMaximumSize().height);
            }
        };
        JPanel sliderPanel = new JPanel(new GridLayout(2, 1)) {
            public Dimension getMaximumSize() {
                return new Dimension(getPreferredSize().width, super.getMaximumSize().height);
            }
        };
        JPanel comboPanel = new JPanel(new GridLayout(3, 1));
        JPanel printPanel = new JPanel(new ColumnLayout());

        getDemoPanel().add(controlPanel, BorderLayout.CENTER);
        Vector relatedComponents = new Vector();


        // check box panel
        isColumnReorderingAllowedCheckBox = new JCheckBox(getString("TableDemo.reordering_allowed"), true);
        isColumnReorderingAllowedCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = ((JCheckBox) e.getSource()).isSelected();
                tableView.getTableHeader().setReorderingAllowed(flag);
                tableView.repaint();
            }
        });

        showHorizontalLinesCheckBox = new JCheckBox(getString("TableDemo.horz_lines"), true);
        showHorizontalLinesCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = ((JCheckBox) e.getSource()).isSelected();
                tableView.setShowHorizontalLines(flag);
                ;
                tableView.repaint();
            }
        });

        showVerticalLinesCheckBox = new JCheckBox(getString("TableDemo.vert_lines"), false);// 原代码默认check是true，现由jb2011改为false
        showVerticalLinesCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = ((JCheckBox) e.getSource()).isSelected();
                tableView.setShowVerticalLines(flag);
                ;
                tableView.repaint();
            }
        });

        // Show that showHorizontal/Vertical controls are related
        relatedComponents.removeAllElements();
        relatedComponents.add(showHorizontalLinesCheckBox);
        relatedComponents.add(showVerticalLinesCheckBox);
        buildAccessibleGroup(relatedComponents);

        isRowSelectionAllowedCheckBox = new JCheckBox(getString("TableDemo.row_selection"), true);
        isRowSelectionAllowedCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = ((JCheckBox) e.getSource()).isSelected();
                tableView.setRowSelectionAllowed(flag);
                tableView.repaint();
            }
        });

        isColumnSelectionAllowedCheckBox = new JCheckBox(getString("TableDemo.column_selection"), false);
        isColumnSelectionAllowedCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = ((JCheckBox) e.getSource()).isSelected();
                tableView.setColumnSelectionAllowed(flag);
                ;
                tableView.repaint();
            }
        });

        // Show that row/column selections are related
        relatedComponents.removeAllElements();
        relatedComponents.add(isColumnSelectionAllowedCheckBox);
        relatedComponents.add(isRowSelectionAllowedCheckBox);
        buildAccessibleGroup(relatedComponents);

        cbPanel.add(isColumnReorderingAllowedCheckBox);
        cbPanel.add(isRowSelectionAllowedCheckBox);
        cbPanel.add(showHorizontalLinesCheckBox);
        cbPanel.add(isColumnSelectionAllowedCheckBox);
        cbPanel.add(showVerticalLinesCheckBox);


        // label panel
        interCellSpacingLabel = new JLabel(getString("TableDemo.intercell_spacing_colon"));
        labelPanel.add(interCellSpacingLabel);

        rowHeightLabel = new JLabel(getString("TableDemo.row_height_colon"));
        labelPanel.add(rowHeightLabel);


        // slider panel
        interCellSpacingSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);// 原代码value是1，现由jb2011改为0
        interCellSpacingSlider.getAccessibleContext().setAccessibleName(getString("TableDemo.intercell_spacing"));
        interCellSpacingLabel.setLabelFor(interCellSpacingSlider);
        sliderPanel.add(interCellSpacingSlider);
        interCellSpacingSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int spacing = ((JSlider) e.getSource()).getValue();
                tableView.setIntercellSpacing(new Dimension(spacing, spacing));
                tableView.repaint();
            }
        });

        rowHeightSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, INITIAL_ROWHEIGHT);
        rowHeightSlider.getAccessibleContext().setAccessibleName(getString("TableDemo.row_height"));
        rowHeightLabel.setLabelFor(rowHeightSlider);
        sliderPanel.add(rowHeightSlider);
        rowHeightSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int height = ((JSlider) e.getSource()).getValue();
                tableView.setRowHeight(height);
                tableView.repaint();
            }
        });

        // Show that spacing controls are related
        relatedComponents.removeAllElements();
        relatedComponents.add(interCellSpacingSlider);
        relatedComponents.add(rowHeightSlider);
        buildAccessibleGroup(relatedComponents);

        // Create the table.
        /*tableAggregate = createTable();
        //由jb2011加的，目的是让表格的4周有多点空白好看点 START
        JPanel tttp = new JPanel(new BorderLayout());
        tttp.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        tttp.add(tableAggregate, BorderLayout.CENTER);
        // END
        getDemoPanel().add(tttp, BorderLayout.CENTER);*/

        dayCard.setSize(290, 240);
        chargeCard.setSize(280, 290);
        tempCard.setSize(290, 240);
        ImageIcon image1 = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\resources\\images\\储值卡.png");
        ImageIcon image2 = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\resources\\images\\年月卡.png");
        ImageIcon image3 = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\resources\\images\\临时卡.png");
        Image img1 = image1.getImage().getScaledInstance(290, 240, Image.SCALE_DEFAULT);
        image1.setImage(img1);
        Image img2 = image2.getImage().getScaledInstance(280, 290, Image.SCALE_DEFAULT);
        image2.setImage(img2);
        Image img3 = image3.getImage().getScaledInstance(290, 240, Image.SCALE_DEFAULT);
        image3.setImage(img3);
        dayCard.setIcon(image1);
        chargeCard.setIcon(image2);
        tempCard.setIcon(image3);


        // ComboBox for selection modes.
        JPanel selectMode = new JPanel();
        selectMode.setLayout(new BoxLayout(selectMode, BoxLayout.X_AXIS));
        selectMode.setBorder(new TitledBorder("月卡/年卡"));

        selectionModeComboBox = new JComboBox() {
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        selectionModeComboBox.addItem("月卡");
        selectionModeComboBox.addItem("年卡");
        //selectionModeComboBox.setSelectedIndex(tableView.getSelectionModel().getSelectionMode());
        selectionModeComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                //tableView.setSelectionMode(source.getSelectedIndex());
            }
        });

        //selectMode.add(Box.createHorizontalStrut(12));
        //selectMode.add(selectionModeComboBox);
        selectMode.add(chooseDayCard);
        selectMode.add(Box.createHorizontalGlue());
        selectMode.add(dayCard);
        comboPanel.add(selectMode);
        comboPanel.setBorder(new TitledBorder("请选择对应的IC卡种类"));

        // Combo box for table resize mode.
        JPanel chargeMode = new JPanel();
        chargeMode.setLayout(new BoxLayout(chargeMode, BoxLayout.X_AXIS));
        chargeMode.setBorder(new TitledBorder("IC储值卡"));
        chargeMode.add(chooseChargeCard);
        chargeMode.add(Box.createHorizontalGlue());
        chargeMode.add(chargeCard);
        comboPanel.add(chargeMode);
        resizeModeComboBox = new JComboBox() {
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        resizeModeComboBox.addItem("IC储值卡");
        //resizeModeComboBox.setSelectedIndex(tableView.getAutoResizeMode());
        resizeModeComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                tableView.setAutoResizeMode(source.getSelectedIndex());
            }
        });


        JPanel tempMode = new JPanel();
        tempMode.setLayout(new BoxLayout(tempMode, BoxLayout.X_AXIS));
        tempMode.setBorder(new TitledBorder("临时停车卡"));

        tempMode.add(Box.createHorizontalStrut(2));
        tempMode.add(chooseTempCard);
        tempMode.add(Box.createHorizontalGlue());
        tempMode.add(tempCard);
        comboPanel.add(tempMode);

        /*JPanel tempMode = new JPanel();
        tempMode.setLayout(new BoxLayout(resizeMode, BoxLayout.X_AXIS));
        tempMode.setBorder(new TitledBorder("临时停车卡"));
        comboPanel.add(tempMode);*/
        // print panel
        printPanel.setBorder(new TitledBorder("车辆停车明细结算单"));
        headerLabel = new JLabel("车辆入场时间:");
        headerLabel1 = new JLabel("车牌号码:");
        footerLabel = new JLabel("停车场地址:");
        footerLabel1 = new JLabel("停车位:");
        headerTextField = new JTextField(25);
        headerTextField1 = new JTextField(25);
        footerTextField = new JTextField(25);
        footerTextField1 = new JTextField(25);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        headerTextField.setText(sdf.format(carInBean.getCarInTime()));
        headerTextField1.setText(carInBean.getCarNo());
        footerTextField.setText(carInBean.getAddress());
        chargerField.setText(carInBean.getManagerName());
        footerTextField1.setText(carInBean.getStopNo()+"");
        fitWidth = new JCheckBox(getString("TableDemo.fitWidth"), true);
        printButton = new JButton("确认信息无误,下一步");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                carInBean.setCardType(cardTypeBox.getSelectedItem().toString());
                carInBean.setCardId(Integer.parseInt(cardNoField.getSelectedItem().toString()));

                ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();
                parkingLotDBUtils.createCarInLog(carInBean);

                parentTabbedPane.addTab("开闸放行",new CarInSuccess(parentTabbedPane,carInBean));
                parentTabbedPane.setSelectedIndex(3);
            }
        });

        cardTypeBox.addItem("月卡/年卡");
        cardTypeBox.addItem("IC储蓄卡");
        cardTypeBox.addItem("临时停车卡");

        printPanel.add(headerLabel);
        printPanel.add(headerTextField);
        headerTextField.setEditable(false);
        printPanel.add(headerLabel1);
        printPanel.add(headerTextField1);
        printPanel.add(footerLabel);
        printPanel.add(footerTextField);
        printPanel.add(footerLabel1);
        printPanel.add(footerTextField1);
        printPanel.add(charger);
        printPanel.add(chargerField);
        printPanel.add(cardType);
        printPanel.add(cardTypeBox);
        printPanel.add(cardNo);
        printPanel.add(cardNoField);


        JPanel buttons = new JPanel();
        //buttons.add(fitWidth);
        buttons.add(printButton);

        printPanel.add(buttons);

        // Show that printing controls are related
        relatedComponents.removeAllElements();
        relatedComponents.add(headerTextField);
        relatedComponents.add(footerTextField);
        relatedComponents.add(printButton);
        buildAccessibleGroup(relatedComponents);

        // wrap up the panels and add them
        JPanel sliderWrapper = new JPanel();
        sliderWrapper.setLayout(new BoxLayout(sliderWrapper, BoxLayout.X_AXIS));
        sliderWrapper.add(labelPanel);
        sliderWrapper.add(sliderPanel);
        sliderWrapper.add(Box.createHorizontalGlue());
        sliderWrapper.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));

        JPanel leftWrapper = new JPanel();
        leftWrapper.setLayout(new BoxLayout(leftWrapper, BoxLayout.Y_AXIS));
        leftWrapper.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.yellow,6,true),"实时车辆入场抓拍图片"));
        //leftWrapper.add(cbPanel);
        /*leftWrapper.add(sliderWrapper);*/
        carInImage.setSize(500, 380);
        carInImage.addMouseListener(new OpenActionListener());
        ImageIcon image = new ImageIcon("/Users/linqingxiang/Desktop/ParkingLotManager/src/com/sun/swingset3/resources/images/监视摄像头.png");
        Image img = image.getImage();
        //img = img.getScaledInstance(500, 380, Image.SCALE_DEFAULT);
        image.setImage(img);
        carInImage.setSize(500, 380);
        carInImage.setIcon(image);

        leftWrapper.add(carInImage);

        // add everything
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        //controlPanel.add(leftWrapper);
        controlPanel.add(comboPanel);
        controlPanel.add(printPanel);

        //setTableControllers(); // Set accessibility information

        getDemoPanel().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke("ctrl P"), "print");

        getDemoPanel().getActionMap().put("print", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                printTable();
            }
        });
        buttonGroup.add(chooseDayCard);
        chooseDayCard.setSelected(true);
        chooseDayCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardTypeBox.setSelectedIndex(0);
            }
        });
        buttonGroup.add(chooseChargeCard);
        chooseChargeCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardTypeBox.setSelectedIndex(1);
            }
        });
        buttonGroup.add(chooseTempCard);
        chooseTempCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardTypeBox.setSelectedIndex(2);
            }
        });
    }

    /**
     * Sets the Accessibility MEMBER_OF property to denote that
     * these components work together as a group. Each object
     * is set to be a MEMBER_OF an array that contains all of
     * the objects in the group, including itself.
     *
     * @param components The list of objects that are related
     */
    void buildAccessibleGroup(Vector components) {

        AccessibleContext context = null;
        int numComponents = components.size();
        Object[] group = components.toArray();
        Object object = null;
        for (int i = 0; i < numComponents; ++i) {
            object = components.elementAt(i);
            if (object instanceof Accessible) {
                context = ((Accessible) components.elementAt(i)).
                        getAccessibleContext();
                context.getAccessibleRelationSet().add(
                        new AccessibleRelation(
                                AccessibleRelation.MEMBER_OF, group));
            }
        }
    } // buildAccessibleGroup()

    /**
     * This sets CONTROLLER_FOR on the controls that manipulate the
     * table and CONTROLLED_BY relationships on the table to point
     * back to the controllers.
     */
    private void setTableControllers() {

        // Set up the relationships to show what controls the table
        setAccessibleController(isColumnReorderingAllowedCheckBox,
                tableAggregate);
        setAccessibleController(showHorizontalLinesCheckBox,
                tableAggregate);
        setAccessibleController(showVerticalLinesCheckBox,
                tableAggregate);
        setAccessibleController(isColumnSelectionAllowedCheckBox,
                tableAggregate);
        setAccessibleController(isRowSelectionAllowedCheckBox,
                tableAggregate);
        setAccessibleController(interCellSpacingSlider,
                tableAggregate);
        setAccessibleController(rowHeightSlider,
                tableAggregate);
        setAccessibleController(selectionModeComboBox,
                tableAggregate);
        setAccessibleController(resizeModeComboBox,
                tableAggregate);
    } // setTableControllers()

    /**
     * Sets up accessibility relationships to denote that one
     * object controls another. The CONTROLLER_FOR property is
     * set on the controller object, and the CONTROLLED_BY
     * property is set on the target object.
     *
     * @param controller the controller
     * @param target     the target
     */
    private void setAccessibleController(JComponent controller,
                                         JComponent target) {
        AccessibleRelationSet controllerRelations =
                controller.getAccessibleContext().getAccessibleRelationSet();
        AccessibleRelationSet targetRelations =
                target.getAccessibleContext().getAccessibleRelationSet();

        controllerRelations.add(
                new AccessibleRelation(
                        AccessibleRelation.CONTROLLER_FOR, target));
        targetRelations.add(
                new AccessibleRelation(
                        AccessibleRelation.CONTROLLED_BY, controller));
    } // setAccessibleController()


    /**
     * Prints the table.
     */
    private void printTable() {
        MessageFormat headerFmt;
        MessageFormat footerFmt;
        JTable.PrintMode printMode = fitWidth.isSelected() ?
                JTable.PrintMode.FIT_WIDTH :
                JTable.PrintMode.NORMAL;

        String text;
        text = headerTextField.getText();
        if (text != null && text.length() > 0) {
            headerFmt = new MessageFormat(text);
        } else {
            headerFmt = null;
        }

        text = footerTextField.getText();
        if (text != null && text.length() > 0) {
            footerFmt = new MessageFormat(text);
        } else {
            footerFmt = null;
        }

        try {
            boolean status = tableView.print(printMode, headerFmt, footerFmt);

            if (status) {
                JOptionPane.showMessageDialog(tableView.getParent(),
                        getString("TableDemo.printingComplete"),
                        getString("TableDemo.printingResult"),
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(tableView.getParent(),
                        getString("TableDemo.printingCancelled"),
                        getString("TableDemo.printingResult"),
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            String errorMessage = MessageFormat.format(getString("TableDemo.printingFailed"),
                    new Object[]{pe.getMessage()});
            JOptionPane.showMessageDialog(tableView.getParent(),
                    errorMessage,
                    getString("TableDemo.printingResult"),
                    JOptionPane.ERROR_MESSAGE);
        } catch (SecurityException se) {
            String errorMessage = MessageFormat.format(getString("TableDemo.printingFailed"),
                    new Object[]{se.getMessage()});
            JOptionPane.showMessageDialog(tableView.getParent(),
                    errorMessage,
                    getString("TableDemo.printingResult"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * The Class NamedColor.
     */
    class NamedColor extends Color {

        /**
         * The name.
         */
        String name;

        /**
         * Instantiates a new named color.
         *
         * @param color the color
         * @param name  the name
         */
        public NamedColor(Color color, String name) {
            super(color.getRGB());
            this.name = name;
        }

        /**
         * Gets the text color.
         *
         * @return the text color
         */
        public Color getTextColor() {
            int r = getRed();
            int g = getGreen();
            int b = getBlue();
            if (r > 240 || g > 240) {
                return Color.black;
            } else {
                return Color.white;
            }
        }

        /* (non-Javadoc)
         * @see java.awt.Color#toString()
         */
        public String toString() {
            return name;
        }
    }

    /**
     * The Class ColumnLayout.
     */
    class ColumnLayout implements LayoutManager {

        /**
         * The x inset.
         */
        int xInset = 5;

        /**
         * The y inset.
         */
        int yInset = 5;

        /**
         * The y gap.
         */
        int yGap = 2;

        /* (non-Javadoc)
         * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
         */
        public void addLayoutComponent(String s, Component c) {
        }

        /* (non-Javadoc)
         * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
         */
        public void layoutContainer(Container c) {
            Insets insets = c.getInsets();
            int height = yInset + insets.top;

            Component[] children = c.getComponents();
            Dimension compSize = null;
            for (int i = 0; i < children.length; i++) {
                compSize = children[i].getPreferredSize();
                children[i].setSize(compSize.width, compSize.height);
                children[i].setLocation(xInset + insets.left, height);
                height += compSize.height + yGap;
            }

        }

        /* (non-Javadoc)
         * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
         */
        public Dimension minimumLayoutSize(Container c) {
            Insets insets = c.getInsets();
            int height = yInset + insets.top;
            int width = 0 + insets.left + insets.right;

            Component[] children = c.getComponents();
            Dimension compSize = null;
            for (int i = 0; i < children.length; i++) {
                compSize = children[i].getPreferredSize();
                height += compSize.height + yGap;
                width = Math.max(width, compSize.width + insets.left + insets.right + xInset * 2);
            }
            height += insets.bottom;
            return new Dimension(width, height);
        }

        /* (non-Javadoc)
         * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
         */
        public Dimension preferredLayoutSize(Container c) {
            return minimumLayoutSize(c);
        }

        /* (non-Javadoc)
         * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
         */
        public void removeLayoutComponent(Component c) {
        }
    }

    /* (non-Javadoc)
     * @see DemoModule#updateDragEnabled(boolean)
     */
    void updateDragEnabled(boolean dragEnabled) {
        tableView.setDragEnabled(dragEnabled);
        headerTextField.setDragEnabled(dragEnabled);
        footerTextField.setDragEnabled(dragEnabled);
    }

    class OpenActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
            fileChooser.setFileFilter(filter);

            int n = fileChooser.showOpenDialog(fileChooser);
            if (n == fileChooser.APPROVE_OPTION) {
                ImageIcon image = new ImageIcon(fileChooser.getSelectedFile().getPath());
                Image img = image.getImage();
                img = img.getScaledInstance(500, 380, Image.SCALE_DEFAULT);
                image.setImage(img);
                carInImage.setIcon(image);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}

