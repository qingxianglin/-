package com.sun.swingset3.demos.aaa;


import com.sun.swingset3.DemoModule;
import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.AuthReqBean;
import com.sun.swingset3.test.CarStopQueryTop;
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
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.Vector;

public class TableDemo10 extends DemoModule {
    Object[][] data= null;
    Object[][] currentData= null;
    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();
    class PageList{
        private int currentPage=1;
        private  int pageSize=20;
        private int lastPage;
        List list,list1;
        JButton firstPageButton = new JButton("首页");
        JButton prePageButton = new JButton("上一页");
        JButton nextPageButton = new JButton("下一页");
        JButton lastPageButton = new JButton("尾页");

        public Object[][] findData(int currentPage, int pageSize){
            int totalRowCount = parkingLotDBUtils.queryAuthReqCount();
            if(currentPage<1){
                currentPage=1;
            }
            int startIndex=(currentPage-1)*pageSize;
            int endIndex=startIndex+pageSize;

            if(endIndex>=totalRowCount){
                endIndex=totalRowCount;
            }
            List<AuthReqBean> temp = parkingLotDBUtils.queryAuthReqBean2(null,startIndex,endIndex);
            Object[][] tempList = new Object[endIndex-startIndex][];
            int index = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY年-MM月-DD日 HH时:MM分:SS秒");
            for(int i = startIndex;i<endIndex;++i){
                AuthReqBean tempbean = temp.get(index);
                tempList[index] = new Object[]{tempbean.getRequestId(),tempbean.getUserName(),tempbean.getUserId(),tempbean.getRoleNameZh(),
                        tempbean.getRoleNameEn(),tempbean.getRoleId(),tempbean.getStatus()};
                ++index;
            }
            System.out.println("开始"+startIndex+",结束"+endIndex+",长度"+tempList.length);
            return tempList;
        }

        public PageList(){
            firstPageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showTable(1);
                }
            });

            prePageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(getCurrentPage()<=1){
                        setCurrentPage(2);
                    }
                    showTable(getCurrentPage()-1);
                }
            });

            nextPageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(getCurrentPage()<getLastPage()){
                        showTable(getCurrentPage()+1);
                    }else{
                        showTable(getLastPage());
                    }
                }
            });

            lastPageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showTable(getLastPage());
                }
            });
            int totalRowCount = parkingLotDBUtils.queryAuthReqCount();
            if(totalRowCount%pageSize==0){
                setLastPage(totalRowCount/getPageSize());
            }else{
                setLastPage(totalRowCount/getPageSize()+1);
            }
            showTable(currentPage);
        }

        public void showTable(int currentPage){
            setCurrentPage(currentPage);
            currentData=findData(currentPage, pageSize);
            tableView.repaint();
            scrollpane.updateUI();
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }
    }

    PageList pageList ;

    //车辆入场抓拍图像
    JLabel carInImage = new JLabel();

    //加载中图片
    JLabel loading = new JLabel();

    //识别出的车牌号码
    JTextField carPlateNo = new JTextField();

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

    @Override
    public String getName() {
        return "表格";
    }

    public TableDemo10(final JTabbedPane parentTabbedPanel) {
        getDemoPanel().setLayout(new BorderLayout());
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
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
        JPanel comboPanel = new JPanel(new GridLayout(2, 1));
        JPanel printPanel = new JPanel(new ColumnLayout());

        getDemoPanel().add(new CarStopQueryTop(parentTabbedPanel), BorderLayout.NORTH);
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
                ;
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
        tableAggregate = createTable();
        pageList = new PageList();
        //由jb2011加的，目的是让表格的4周有多点空白好看点 START
        JPanel tttp = new JPanel(new BorderLayout());
        tttp.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        tttp.add(tableAggregate, BorderLayout.CENTER);
        // END
        getDemoPanel().add(tttp, BorderLayout.CENTER);
        JPanel pagePanel = new JPanel();
        pagePanel.setLayout(new BoxLayout(pagePanel,BoxLayout.X_AXIS));
        pagePanel.add(pageList.firstPageButton);
        pagePanel.add(pageList.prePageButton);
        pagePanel.add(pageList.nextPageButton);
        pagePanel.add(pageList.lastPageButton);
        getDemoPanel().add(pagePanel, BorderLayout.NORTH);

        // ComboBox for selection modes.
        JPanel selectMode = new JPanel();
        selectMode.setLayout(new BoxLayout(selectMode, BoxLayout.X_AXIS));
        selectMode.setBorder(new TitledBorder("号码识别进度"));


        selectionModeComboBox = new JComboBox() {
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        selectionModeComboBox.addItem(getString("TableDemo.single"));
        selectionModeComboBox.addItem(getString("TableDemo.one_range"));
        selectionModeComboBox.addItem(getString("TableDemo.multiple_ranges"));
        selectionModeComboBox.setSelectedIndex(tableView.getSelectionModel().getSelectionMode());
        selectionModeComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                tableView.setSelectionMode(source.getSelectedIndex());
            }
        });

        selectMode.add(Box.createHorizontalStrut(26));
        loading.setSize(60,60);
        selectMode.add(loading);
        //selectMode.add(selectionModeComboBox);
        selectMode.add(Box.createHorizontalGlue());
        comboPanel.add(selectMode);
        comboPanel.setBorder(new TitledBorder("车牌号码自动识别"));

        // Combo box for table resize mode.
        JPanel resizeMode = new JPanel();
        resizeMode.setLayout(new BoxLayout(resizeMode, BoxLayout.Y_AXIS));
        resizeMode.setBorder(new TitledBorder("识别结果(可手动校正)"));


        resizeModeComboBox = new JComboBox() {
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        resizeModeComboBox.addItem(getString("TableDemo.off"));
        resizeModeComboBox.addItem(getString("TableDemo.column_boundaries"));
        resizeModeComboBox.addItem(getString("TableDemo.subsequent_columns"));
        resizeModeComboBox.addItem(getString("TableDemo.last_column"));
        resizeModeComboBox.addItem(getString("TableDemo.all_columns"));
        resizeModeComboBox.setSelectedIndex(tableView.getAutoResizeMode());
        resizeModeComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                tableView.setAutoResizeMode(source.getSelectedIndex());
            }
        });

        resizeMode.add(Box.createVerticalStrut(50));
        //resizeMode.add(resizeModeComboBox);
        resizeMode.add(carPlateNo);
        carPlateNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                footerTextField1.setText(carPlateNo.getText());
            }
        });
        resizeMode.add(Box.createVerticalStrut(50));
        comboPanel.add(resizeMode);

        // print panel
        printPanel.setBorder(new TitledBorder("车辆入场基本信息"));
        headerLabel = new JLabel("车辆入场时间:");
        headerLabel1 = new JLabel("停车场地址:");
        footerLabel = new JLabel("收费员姓名:");
        footerLabel1 = new JLabel("车牌号码:");
        headerTextField = new JTextField(25);
        headerTextField1 = new JTextField(25);
        footerTextField = new JTextField(25);
        footerTextField1 = new JTextField(25);
        fitWidth = new JCheckBox(getString("TableDemo.fitWidth"), true);
        printButton = new JButton("确认信息无误,下一步");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //printTable();
                parentTabbedPanel.addTab("停车位动态调度",
                        new TableDemo2(parentTabbedPanel,null).getDemoPanel());
                parentTabbedPanel.setSelectedIndex(1);
            }
        });

        printPanel.add(headerLabel);
        printPanel.add(headerTextField);
        headerTextField.setEditable(false);
        printPanel.add(headerLabel1);
        printPanel.add(headerTextField1);
        printPanel.add(footerLabel);
        printPanel.add(footerTextField);
        printPanel.add(footerLabel1);
        printPanel.add(footerTextField1);

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
        leftWrapper.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue,6,true),"实时车辆入场抓拍图片"));



        //leftWrapper.add(cbPanel);
        /*leftWrapper.add(sliderWrapper);*/
        carInImage.setSize(500, 380);
        carInImage.addMouseListener(new OpenActionListener());
        ImageIcon image = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\resources\\images\\监视摄像头.png");
        Image img = image.getImage();
        //img = img.getScaledInstance(500, 380, Image.SCALE_DEFAULT);
        image.setImage(img);
        carInImage.setSize(500, 380);
        carInImage.setIcon(image);

        leftWrapper.add(carInImage);

        // add everything
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        controlPanel.add(leftWrapper);
        controlPanel.add(comboPanel);
        controlPanel.add(printPanel);

        setTableControllers(); // Set accessibility information

        getDemoPanel().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke("ctrl P"), "print");

        getDemoPanel().getActionMap().put("print", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                printTable();
            }
        });

    } // TableDemo()

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
     * Creates the table.
     *
     * @return the j scroll pane
     */
    public JScrollPane createTable() {
        final String[] names = {
                "角色申请编号",
                "申请人姓名",
                "申请人工号",
                "申请的角色中文名称",
                "申请的角色英文名称",
                "申请的角色ID",
                "审批结果"
        };
        data = new Object[][]{{1,2,3,4,5,6,7}};
        currentData = data;
        // Create a model of the data.
        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() {
                return names.length;
            }

            public int getRowCount() {
                return currentData.length;
            }

            public Object getValueAt(int row, int col) {
                return currentData[row][col];
            }

            public String getColumnName(int column) {
                return names[column];
            }

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            public boolean isCellEditable(int row, int col) {
                return col != 5;
            }

            public void setValueAt(Object aValue, int row, int column) {
                currentData[row][column] = aValue;
            }
        };
        // Create the table
        tableView = new JTable(dataModel);
        if (JVM.current().isOrLater(JVM.JDK1_6)) {

            try {
                //以下代码完成：TableRowSorter sorter = new TableRowSorter(dataModel);
                Class c = Class.forName("javax.swing.table.TableRowSorter");
                Constructor constructor = c.getConstructor(TableModel.class); //构造函数参数列表的class类型
                Object trs = constructor.newInstance(dataModel); //传参

                //以下代码完成：tableView.setRowSorter(sorter);
                Method m2 = JTable.class.getMethod("setRowSorter", Class.forName("javax.swing.RowSorter"));//注意反身时，参数类只能本类本身，子类是不行的（比如不能直接用c）
                m2.invoke(tableView, trs);
            } catch (Exception e) {
                System.err.println("错误：为1.6及更高版本设置表格排序支持失败," + e.getMessage());
            }
        }

        // Show colors by rendering them in their own color.
        DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer() {
            public void setValue(Object value) {
                if (value instanceof NamedColor) {
                    NamedColor c = (NamedColor) value;
                    setBackground(c);
                    setForeground(c.getTextColor());
                    setText(c.toString());
                } else {
                    super.setValue(value);
                }
            }
        };

        // Create a combo box to show that you can use one in a table.
       /*
        TableColumn colorColumn = tableView.getColumn("请选择审批意见");
        // Use the combo box as the editor in the "Favorite Color" column.
        colorColumn.setCellEditor(new DefaultCellEditor(comboBox));*/
/*
        colorRenderer.setHorizontalAlignment(JLabel.CENTER);
        colorColumn.setCellRenderer(colorRenderer);*/

        tableView.setRowHeight(25);
        tableView.setShowHorizontalLines(true);
        tableView.setShowVerticalLines(true);
        tableView.setGridColor(Color.gray);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        // tcr.setHorizontalAlignment(JLabel.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        tableView.setDefaultRenderer(Object.class, tcr);
        scrollpane = new JScrollPane(tableView);
        return scrollpane;
    }

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
                img = img.getScaledInstance(450, 330, Image.SCALE_DEFAULT);
                image.setImage(img);
                carInImage.setIcon(image);
            }
            ImageIcon image = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\加载中.gif");
            Image img = image.getImage();
            img = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
            image.setImage(img);
            loading.setIcon(image);
            java.util.Timer t=new java.util.Timer();
            t.schedule(new RecognizeTask(), 3000);
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

    /**
     * 延迟3秒显示识别成功
     */
    class RecognizeTask extends TimerTask {

        @Override
        public void run() {
            ImageIcon image = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\识别成功.png");
            Image img = image.getImage();
            img = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
            image.setImage(img);
            loading.setIcon(image);
            carPlateNo.setText("闽A23333");
            footerTextField1.setText("闽A23333");
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            headerTextField.setText(sdf.format(d));
            System.out.println("哈哈哈哈哈哈");
        }
    }
}
