package com.sun.swingset3.demos.aaa;


import com.sun.swingset3.DemoModule;
import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.AuthBean;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AuthCreatePanel extends DemoModule {

    JTree tree;

    EditJTree editJTree;

    AuthCreateRight authCreateRight = new AuthCreateRight();

    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

    ActionListener createAuthButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AuthBean authBean = new AuthBean();
            authBean.setPath(authCreateRight.textField1.getText());
            authBean.setNameZh(authCreateRight.textField2.getText());
            authBean.setNameEn(authCreateRight.textField3.getText());
            authBean.setRemark(authCreateRight.textField5.getText());
            parkingLotDBUtils.createAuth(authBean);
            JOptionPane.showMessageDialog(
                    AuthCreatePanel.this,
                    "创建权限:"+authBean.getNameZh()+"成功!",
                    "权限创建成功",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.out.println("搞什么啊");
            DefaultMutableTreeNode selectedNode
                    = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            selectedNode.setUserObject(authBean.getNameZh());
            tree.updateUI();
            editJTree.treeNodes.put(authBean.getNameZh(),selectedNode);
            editJTree.map.put(selectedNode,authBean);
        }
    };
    ActionListener editAuthButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    class AuthCreateRight extends JPanel {

        JLabel label1 = new JLabel("权限所属层级关系根路径:");
        JLabel label2 = new JLabel("权限中文名称:");
        JLabel label3 = new JLabel("权限英文标识符(必须保证不重复):");
        JLabel label4 = new JLabel("权限创建人:");
        JLabel label5 = new JLabel("权限简介:");
        JTextField textField1 = new JTextField(30);
        JTextField textField2 = new JTextField(30);
        JTextField textField3 = new JTextField(30);
        JTextField textField4 = new JTextField(30);
        JTextArea textField5 = new JTextArea(5,20);
        JButton button1 = new JButton("权限信息确认无误,点击创建");
        public AuthCreateRight() {
            textField2.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    int index = textField1.getText().lastIndexOf("/");
                    textField1.setText(textField1.getText().substring(0,index+1)+textField2.getText());
                }
            });
            setLayout(new ColumnLayout());
            add(label1);
            add(textField1);
            add(label2);
            add(textField2);
            add(label3);
            add(textField3);
            add(label4);
            add(textField4);
            add(label5);
            add(textField5);
            textField5.setBorder(new LineBorder(Color.BLACK));
            add(button1);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AuthBean authBean = new AuthBean();
                    authBean.setPath(textField1.getText());
                    authBean.setNameZh(textField2.getText());
                    authBean.setNameEn(textField3.getText());
                    authBean.setRemark(textField5.getText());
                    parkingLotDBUtils.createAuth(authBean);
                    JOptionPane.showMessageDialog(
                            AuthCreatePanel.this,
                            "创建权限:"+authBean.getNameZh()+"成功!",
                            "权限创建成功",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    System.out.println("搞什么啊");
                    DefaultMutableTreeNode selectedNode
                            = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    selectedNode.setUserObject(authBean.getNameZh());
                    tree.updateUI();
                    editJTree.treeNodes.put(authBean.getNameZh(),selectedNode);
                    editJTree.map.put(selectedNode,authBean);
                }
            });
        }
    }

    class EditJTree
    {
        JTree tree;
        //上面JTree对象对应的model
        DefaultTreeModel model;

        //定义几个初始节点
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("路径1");

        Map<String, DefaultMutableTreeNode> treeNodes =
                new HashMap<String, DefaultMutableTreeNode>();

        Map<DefaultMutableTreeNode, AuthBean> map = new HashMap<DefaultMutableTreeNode, AuthBean>();

        public JTree init(final AuthCreateRight authCreateRight)
        {
            java.util.List<AuthBean> authBeanList = parkingLotDBUtils.queryAuthBean2(null);
            treeNodes.put("路径1",root);
            if(authBeanList!=null && authBeanList.size()>0){
                for(AuthBean authBean : authBeanList){
                    String[] pathArray = authBean.getPath().split("\\/");
                    for(int i = 1;i<pathArray.length;++i){
                        String currentpath = pathArray[i];
                        String parentPath = pathArray[i-1];
                        if(treeNodes.containsKey(currentpath)){

                        }else{
                            DefaultMutableTreeNode node = new DefaultMutableTreeNode(currentpath);
                            if(i==pathArray.length-1){
                                map.put(node,authBean);
                            }
                            treeNodes.put(currentpath,node);
                            treeNodes.get(parentPath).add(node);
                        }
                    }
                }
            }
            tree = new JTree(root);
            //获取JTree对应的TreeModel对象
            model = (DefaultTreeModel)tree.getModel();
            //设置JTree可编辑
            tree.setEditable(true);
            final JPopupMenu popUpMenu=new JPopupMenu();
            JMenuItem createAuth=new JMenuItem("新建权限");
            JMenuItem deleteAuth=new JMenuItem("删除权限");
            JMenuItem editAuth=new JMenuItem("编辑权限");
            popUpMenu.add(editAuth);
            popUpMenu.add(createAuth);
            popUpMenu.add(deleteAuth);
            tree.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if(event.getButton()==MouseEvent.BUTTON3)//只响应鼠标右键单击事件
                    {
                        //获取选中节点
                        DefaultMutableTreeNode selectedNode
                                = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                        //如果节点为空，直接返回
                        if (selectedNode == null) return;
                        popUpMenu.show(tree,event.getX(),event.getY());//在鼠标位置显示弹出式菜单
                    }else if(event.getButton()==MouseEvent.BUTTON1){
                        //获取选中节点
                        DefaultMutableTreeNode selectedNode
                                = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                        //如果节点为空，直接返回
                        if (selectedNode == null || !selectedNode.isLeaf()) return;
                        AuthBean selected = map.get(selectedNode);
                        if(selected!=null){
                            authCreateRight.textField1.setText(selected.getPath());
                            authCreateRight.textField2.setText(selected.getNameZh());
                            authCreateRight.textField3.setText(selected.getNameEn());

                            authCreateRight.textField5.setText(selected.getRemark());
                            //authCreateRight.button1.removeActionListener(editAuthButton);
                            //authCreateRight.button1.removeActionListener(createAuthButton);
                        }else{
                            TreeNode[] treePath = selectedNode.getPath();
                            StringBuilder sb = new StringBuilder();
                            for(int i = 0;i<treePath.length;++i){
                                sb.append(treePath[i].toString());
                                if(i!=treePath.length-1){
                                    sb.append("/");
                                }
                            }
                            authCreateRight.textField1.setText(sb.toString());
                            authCreateRight.textField2.setText("");
                            authCreateRight.textField3.setText("");
                            authCreateRight.textField5.setText("");
                            //authCreateRight.button1.removeActionListener(editAuthButton);

                        }
                    }
                }
            });
            createAuth.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    //获取选中节点
                    DefaultMutableTreeNode selectedNode
                            = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    //如果节点为空，直接返回
                    if (selectedNode == null) return;
                    //创建一个新节点
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("新节点");
                    //直接通过model来添加新节点，则无需通过调用JTree的updateUI方法
                    //model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
                    //直接通过节点添加新节点，则需要调用tree的updateUI方法
                    selectedNode.add(newNode);
                    //--------下面代码实现显示新节点（自动展开父节点）-------
                    TreeNode[] nodes = model.getPathToRoot(newNode);
                    TreePath path = new TreePath(nodes);
                    tree.scrollPathToVisible(path);
                    tree.updateUI();
                }
            });

            deleteAuth.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    DefaultMutableTreeNode selectedNode
                            = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (selectedNode != null && selectedNode.getParent() != null)
                    {
                        //删除指定节点
                        model.removeNodeFromParent(selectedNode);
                    }
                }
            });

            editAuth.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    TreePath selectedPath = tree.getSelectionPath();
                    if (selectedPath != null)
                    {
                        //编辑选中节点
                        tree.startEditingAtPath(selectedPath);
                    }
                }
            });
            return tree;
        }
    }

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

    public AuthCreatePanel(final JTabbedPane parentTabbedPanel) {
        getDemoPanel().setLayout(new BorderLayout());
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,2));
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
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));
        //comboPanel.setLayout(new BorderLayout());
        //comboPanel.setLayout(new ColumnLayout());

        JPanel comboPanel2 = new JPanel(new BorderLayout());
        JPanel printPanel = new JPanel(new ColumnLayout());
        editJTree = new EditJTree();
        tree = editJTree.init(authCreateRight);
        comboPanel.add(new JScrollPane(tree));
        //comboPanel.add(new EditJTree().init());

        getDemoPanel().add(controlPanel, BorderLayout.CENTER);

        // label panel
        interCellSpacingLabel = new JLabel(getString("TableDemo.intercell_spacing_colon"));
        labelPanel.add(interCellSpacingLabel);

        rowHeightLabel = new JLabel(getString("TableDemo.row_height_colon"));
        labelPanel.add(rowHeightLabel);

        comboPanel.setBorder(new TitledBorder("权限层级关系图"));
        comboPanel2.setBorder(new TitledBorder("权限信息详情表单"));

        comboPanel2.add(authCreateRight,BorderLayout.WEST);
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
                parentTabbedPanel.addTab("停车费用结算",
                        new CarOutSecondPanel(parentTabbedPanel).getDemoPanel());
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

        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        //controlPanel.add(leftWrapper);
        controlPanel.add(comboPanel);
        controlPanel.add(comboPanel2);
    }
    void buildAccessibleGroup(Vector components) {
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
}
