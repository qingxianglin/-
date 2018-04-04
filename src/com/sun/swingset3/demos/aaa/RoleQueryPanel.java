package com.sun.swingset3.demos.aaa;


import com.sun.swingset3.DemoModule;
import com.sun.swingset3.sql.bean.AuthBean;
import com.sun.swingset3.sql.bean.RoleBean;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Vector;

public class RoleQueryPanel extends DemoModule {
    TableDemo12 tableDemo12 = new TableDemo12(null);
    //车辆入场抓拍图像
    JLabel carInImage = new JLabel();

    JLabel carInImage2 = new JLabel();

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

    public static void main(String[] args) {
        //TableDemo demo = new TableDemo();
        //demo.mainImpl();
    }

    @Override
    public String getName() {
        return "表格";
    }

    public RoleQueryPanel(final JTabbedPane parentTabbedPanel) {
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
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.X_AXIS));

        JPanel comboPanel2 = new JPanel(new BorderLayout());
        JPanel printPanel = new JPanel(new ColumnLayout());
        comboPanel.add(printPanel);

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
                ;
                tableView.repaint();
            }
        });

        isColumnSelectionAllowedCheckBox = new JCheckBox(getString("TableDemo.column_selection"), false);
        isColumnSelectionAllowedCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = ((JCheckBox) e.getSource()).isSelected();
                tableView.setColumnSelectionAllowed(flag);
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
        //由jb2011加的，目的是让表格的4周有多点空白好看点 START
        JPanel tttp = new JPanel(new BorderLayout());
        tttp.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        tttp.add(tableAggregate, BorderLayout.CENTER);
        // END
        //getDemoPanel().add(tttp, BorderLayout.CENTER);

        comboPanel.setBorder(new TitledBorder("角色信息查询条件"));
        comboPanel2.setBorder(new TitledBorder("角色信息列表"));
        comboPanel2.add(tableDemo12.getDemoPanel(),BorderLayout.CENTER);
        // print panel
        printPanel.setBorder(new TitledBorder("请输入对应的查询条件:"));
        headerLabel = new JLabel("角色ID:");
        headerLabel1 = new JLabel("角色中文名称(模糊匹配):");
        footerLabel = new JLabel("角色英文名称(模糊匹配):");
        footerLabel1 = new JLabel("车牌号码:");
        headerTextField = new JTextField(25);
        headerTextField1 = new JTextField(25);
        footerTextField = new JTextField(25);
        footerTextField1 = new JTextField(25);
        fitWidth = new JCheckBox(getString("TableDemo.fitWidth"), true);
        printButton = new JButton("确认信息无误,下一步");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                RoleBean queryBean = new RoleBean();
                String roleId = headerTextField.getText();
                if(roleId!=null && !roleId.equals("")){
                    queryBean.setId(Integer.parseInt(roleId));
                }
                String roleNameZh = headerTextField1.getText();
                queryBean.setNameZh(roleNameZh);
                String roleNameEn = footerTextField.getText();
                queryBean.setNameEn(roleNameEn);
                tableDemo12.query(queryBean);
            }
        });

        printPanel.add(headerLabel);
        printPanel.add(headerTextField);
        printPanel.add(headerLabel1);
        printPanel.add(headerTextField1);
        printPanel.add(footerLabel);
        printPanel.add(footerTextField);
        //printPanel.add(footerLabel1);
        //printPanel.add(footerTextField1);

        JPanel buttons = new JPanel();
        //buttons.add(fitWidth);
        buttons.add(printButton);

        printPanel.add(buttons);

        // Show that printing controls are related
        relatedComponents.removeAllElements();
        relatedComponents.add(headerTextField);
        relatedComponents.add(footerTextField);
        relatedComponents.add(printButton);
        //buildAccessibleGroup(relatedComponents);


        // add everything
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        //controlPanel.add(leftWrapper);
        controlPanel.add(comboPanel);
        controlPanel.add(comboPanel2);
        //controlPanel.add(printPanel);

        //setTableControllers(); // Set accessibility information

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
     * Creates the table.
     *
     * @return the j scroll pane
     */
    public JScrollPane createTable() {

        // final
        final String[] names = {
                "车辆入场时间",
                "车牌号码",
                getString("TableDemo.favorite_color"),
                getString("TableDemo.favorite_movie"),
                getString("TableDemo.favorite_number"),
                getString("TableDemo.favorite_food")
        };

        ImageIcon apple = createImageIcon("ImageClub/food/apple.jpg", getString("TableDemo.apple"));
        ImageIcon asparagus = createImageIcon("ImageClub/food/asparagus.jpg", getString("TableDemo.asparagus"));
        ImageIcon banana = createImageIcon("ImageClub/food/banana.jpg", getString("TableDemo.banana"));
        ImageIcon broccoli = createImageIcon("ImageClub/food/broccoli.jpg", getString("TableDemo.broccoli"));
        ImageIcon cantaloupe = createImageIcon("ImageClub/food/cantaloupe.jpg", getString("TableDemo.cantaloupe"));
        ImageIcon carrot = createImageIcon("ImageClub/food/carrot.jpg", getString("TableDemo.carrot"));
        ImageIcon corn = createImageIcon("ImageClub/food/corn.jpg", getString("TableDemo.corn"));
        ImageIcon grapes = createImageIcon("ImageClub/food/grapes.jpg", getString("TableDemo.grapes"));
        ImageIcon grapefruit = createImageIcon("ImageClub/food/grapefruit.jpg", getString("TableDemo.grapefruit"));
        ImageIcon kiwi = createImageIcon("ImageClub/food/kiwi.jpg", getString("TableDemo.kiwi"));
        ImageIcon onion = createImageIcon("ImageClub/food/onion.jpg", getString("TableDemo.onion"));
        ImageIcon pear = createImageIcon("ImageClub/food/pear.jpg", getString("TableDemo.pear"));
        ImageIcon peach = createImageIcon("ImageClub/food/peach.jpg", getString("TableDemo.peach"));
        ImageIcon pepper = createImageIcon("ImageClub/food/pepper.jpg", getString("TableDemo.pepper"));
        ImageIcon pickle = createImageIcon("ImageClub/food/pickle.jpg", getString("TableDemo.pickle"));
        ImageIcon pineapple = createImageIcon("ImageClub/food/pineapple.jpg", getString("TableDemo.pineapple"));
        ImageIcon raspberry = createImageIcon("ImageClub/food/raspberry.jpg", getString("TableDemo.raspberry"));
        ImageIcon sparegrass = createImageIcon("ImageClub/food/asparagus.jpg", getString("TableDemo.sparegrass"));
        ImageIcon strawberry = createImageIcon("ImageClub/food/strawberry.jpg", getString("TableDemo.strawberry"));
        ImageIcon tomato = createImageIcon("ImageClub/food/tomato.jpg", getString("TableDemo.tomato"));
        ImageIcon watermelon = createImageIcon("ImageClub/food/watermelon.jpg", getString("TableDemo.watermelon"));

        NamedColor aqua = new NamedColor(new Color(127, 255, 212), getString("TableDemo.aqua"));
        NamedColor beige = new NamedColor(new Color(245, 245, 220), getString("TableDemo.beige"));
        NamedColor black = new NamedColor(Color.black, getString("TableDemo.black"));
        NamedColor blue = new NamedColor(new Color(0, 0, 222), getString("TableDemo.blue"));
        NamedColor eblue = new NamedColor(Color.blue, getString("TableDemo.eblue"));
        NamedColor jfcblue = new NamedColor(new Color(204, 204, 255), getString("TableDemo.jfcblue"));
        NamedColor jfcblue2 = new NamedColor(new Color(153, 153, 204), getString("TableDemo.jfcblue2"));
        NamedColor cybergreen = new NamedColor(Color.green.darker().brighter(), getString("TableDemo.cybergreen"));
        NamedColor darkgreen = new NamedColor(new Color(0, 100, 75), getString("TableDemo.darkgreen"));
        NamedColor forestgreen = new NamedColor(Color.green.darker(), getString("TableDemo.forestgreen"));
        NamedColor gray = new NamedColor(Color.gray, getString("TableDemo.gray"));
        NamedColor green = new NamedColor(Color.green, getString("TableDemo.green"));
        NamedColor orange = new NamedColor(new Color(255, 165, 0), getString("TableDemo.orange"));
        NamedColor purple = new NamedColor(new Color(160, 32, 240), getString("TableDemo.purple"));
        NamedColor red = new NamedColor(Color.red, getString("TableDemo.red"));
        NamedColor rustred = new NamedColor(Color.red.darker(), getString("TableDemo.rustred"));
        NamedColor sunpurple = new NamedColor(new Color(100, 100, 255), getString("TableDemo.sunpurple"));
        NamedColor suspectpink = new NamedColor(new Color(255, 105, 180), getString("TableDemo.suspectpink"));
        NamedColor turquoise = new NamedColor(new Color(0, 255, 255), getString("TableDemo.turquoise"));
        NamedColor violet = new NamedColor(new Color(238, 130, 238), getString("TableDemo.violet"));
        NamedColor yellow = new NamedColor(Color.yellow, getString("TableDemo.yellow"));

        // Create the dummy data (a few rows of names)
        final Object[][] data = {
                {"Mike", "Albers", green, getString("TableDemo.brazil"), new Double(44.0), strawberry},
                {"Mark", "Andrews", blue, getString("TableDemo.curse"), new Double(3), grapes},
                {"Brian", "Beck", black, getString("TableDemo.bluesbros"), new Double(2.7182818285), raspberry},
                {"Lara", "Bunni", red, getString("TableDemo.airplane"), new Double(15), strawberry},
                {"Roger", "Brinkley", blue, getString("TableDemo.man"), new Double(13), peach},
                {"Brent", "Christian", black, getString("TableDemo.bladerunner"), new Double(23), broccoli},
                {"Mark", "Davidson", darkgreen, getString("TableDemo.brazil"), new Double(27), asparagus},
                {"Jeff", "Dinkins", blue, getString("TableDemo.ladyvanishes"), new Double(8), kiwi},
                {"Ewan", "Dinkins", yellow, getString("TableDemo.bugs"), new Double(2), strawberry},
                {"Amy", "Fowler", violet, getString("TableDemo.reservoir"), new Double(3), raspberry},
                {"Hania", "Gajewska", purple, getString("TableDemo.jules"), new Double(5), raspberry},
                {"David", "Geary", blue, getString("TableDemo.pulpfiction"), new Double(3), watermelon},
//	  {"James", "Gosling",    pink,        getString("TableDemo.tennis"), new Double(21), donut},
                {"Eric", "Hawkes", blue, getString("TableDemo.bladerunner"), new Double(.693), pickle},
                {"Shannon", "Hickey", green, getString("TableDemo.shawshank"), new Double(2), grapes},
                {"Earl", "Johnson", green, getString("TableDemo.pulpfiction"), new Double(8), carrot},
                {"Robi", "Khan", green, getString("TableDemo.goodfellas"), new Double(89), apple},
                {"Robert", "Kim", blue, getString("TableDemo.mohicans"), new Double(655321), strawberry},
                {"Janet", "Koenig", turquoise, getString("TableDemo.lonestar"), new Double(7), peach},
                {"Jeff", "Kesselman", blue, getString("TableDemo.stuntman"), new Double(17), pineapple},
                {"Onno", "Kluyt", orange, getString("TableDemo.oncewest"), new Double(8), broccoli},
                {"Peter", "Korn", sunpurple, getString("TableDemo.musicman"), new Double(12), sparegrass},

                {"Rick", "Levenson", black, getString("TableDemo.harold"), new Double(1327), raspberry},
                {"Brian", "Lichtenwalter", jfcblue, getString("TableDemo.fifthelement"), new Double(22), pear},
                {"Malini", "Minasandram", beige, getString("TableDemo.joyluck"), new Double(9), corn},
                {"Michael", "Martak", green, getString("TableDemo.city"), new Double(3), strawberry},
                {"David", "Mendenhall", forestgreen, getString("TableDemo.schindlerslist"), new Double(7), peach},
                {"Phil", "Milne", suspectpink, getString("TableDemo.withnail"), new Double(3), banana},
                {"Lynn", "Monsanto", cybergreen, getString("TableDemo.dasboot"), new Double(52), peach},
                {"Hans", "Muller", rustred, getString("TableDemo.eraserhead"), new Double(0), pineapple},
                {"Joshua", "Outwater", blue, getString("TableDemo.labyrinth"), new Double(3), pineapple},
                {"Tim", "Prinzing", blue, getString("TableDemo.firstsight"), new Double(69), pepper},
                {"Raj", "Premkumar", jfcblue2, getString("TableDemo.none"), new Double(7), broccoli},
                {"Howard", "Rosen", green, getString("TableDemo.defending"), new Double(7), strawberry},
                {"Ray", "Ryan", black, getString("TableDemo.buckaroo"),
                        new Double(3.141592653589793238462643383279502884197169399375105820974944), banana},
                {"Georges", "Saab", aqua, getString("TableDemo.bicycle"), new Double(290), cantaloupe},
                {"Tom", "Santos", blue, getString("TableDemo.spinaltap"), new Double(241), pepper},
                {"Rich", "Schiavi", blue, getString("TableDemo.repoman"), new Double(0xFF), pepper},
                {"Nancy", "Schorr", green, getString("TableDemo.fifthelement"), new Double(47), watermelon},
                {"Keith", "Sprochi", darkgreen, getString("TableDemo.2001"), new Double(13), watermelon},
                {"Matt", "Tucker", eblue, getString("TableDemo.starwars"), new Double(2), broccoli},
                {"Dmitri", "Trembovetski", red, getString("TableDemo.aliens"), new Double(222), tomato},
                {"Scott", "Violet", violet, getString("TableDemo.raiders"), new Double(-97), banana},
                {"Kathy", "Walrath", darkgreen, getString("TableDemo.thinman"), new Double(8), pear},
                {"Nathan", "Walrath", black, getString("TableDemo.chusingura"), new Double(3), grapefruit},
                {"Steve", "Wilson", green, getString("TableDemo.raiders"), new Double(7), onion},
                {"Kathleen", "Zelony", gray, getString("TableDemo.dog"), new Double(13), grapes}
        };

        // Create a model of the data.
        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() {
                return names.length;
            }

            public int getRowCount() {
                return data.length;
            }

            public Object getValueAt(int row, int col) {
                return data[row][col];
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
                data[row][column] = aValue;
            }
        };

        // Create the table
        tableView = new JTable(dataModel);
        //* modified by jb2011：为了兼容1.5甚至更老版本.
        //* java1.5及以前老版本没有表格排序功能，以下代码作用就是为了
        //* 在jdk1.5及更老版本上可以运行而不致于因没有1.6的表格排序代码而出错.
        //* 以下代码主要完成版本的判断及在1.6及以上版本时才设置表格排序支持
        if (JVM.current().isOrLater(JVM.JDK1_6)) {
            //java1.6及以后版本直接可以用以下代码
//          TableRowSorter sorter = new TableRowSorter(dataModel);
//          tableView.setRowSorter(sorter);

            //java1.5及以前老版本没有表格排序功能，所当动行在1.6及更高版本时可以通过反射来设置排序支持
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
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(aqua);
        comboBox.addItem(beige);
        comboBox.addItem(black);
        comboBox.addItem(blue);
        comboBox.addItem(eblue);
        comboBox.addItem(jfcblue);
        comboBox.addItem(jfcblue2);
        comboBox.addItem(cybergreen);
        comboBox.addItem(darkgreen);
        comboBox.addItem(forestgreen);
        comboBox.addItem(gray);
        comboBox.addItem(green);
        comboBox.addItem(orange);
        comboBox.addItem(purple);
        comboBox.addItem(red);
        comboBox.addItem(rustred);
        comboBox.addItem(sunpurple);
        comboBox.addItem(suspectpink);
        comboBox.addItem(turquoise);
        comboBox.addItem(violet);
        comboBox.addItem(yellow);

        TableColumn colorColumn = tableView.getColumn(getString("TableDemo.favorite_color"));
        // Use the combo box as the editor in the "Favorite Color" column.
        colorColumn.setCellEditor(new DefaultCellEditor(comboBox));

        colorRenderer.setHorizontalAlignment(JLabel.CENTER);
        colorColumn.setCellRenderer(colorRenderer);

        tableView.setRowHeight(INITIAL_ROWHEIGHT);

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
                img = img.getScaledInstance(400, 330, Image.SCALE_DEFAULT);
                image.setImage(img);
                carInImage.setIcon(image);
            }
            ImageIcon image = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\加载中.gif");
            Image img = image.getImage();
            img = img.getScaledInstance(232, 248, Image.SCALE_DEFAULT);
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

    class OpenActionListener2 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
            fileChooser.setFileFilter(filter);

            int n = fileChooser.showOpenDialog(fileChooser);
            if (n == fileChooser.APPROVE_OPTION) {
                ImageIcon image = new ImageIcon(fileChooser.getSelectedFile().getPath());
                Image img = image.getImage();
                img = img.getScaledInstance(400, 330, Image.SCALE_DEFAULT);
                image.setImage(img);
                carInImage2.setIcon(image);
            }
            ImageIcon image = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\加载中.gif");
            Image img = image.getImage();
            img = img.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
            image.setImage(img);
            carInImage.setIcon(image);
            carInImage.setHorizontalTextPosition(JLabel.CENTER);
            carInImage.setVerticalTextPosition(JLabel.CENTER);
            /*java.util.Timer t=new java.util.Timer();
            t.schedule(new RecognizeTask(), 3000);*/
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
        }
    }
}
