package com.sun.swingset3.demos.aaa;
import com.sun.swingset3.DemoModule;
import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.AuthBean;
import com.sun.swingset3.sql.bean.RoleBean;
import com.sun.swingset3.test.JAutoCompleteComboBox1;
import com.sun.swingset3.test.JAutoCompleteComboBox2;

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
import java.util.List;

public class RoleCreatePanel extends DemoModule {

    JTree tree;

    EditJTree editJTree;

    RoleCreateRight roleCreateRight = new RoleCreateRight();

    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

    ActionListener createRoleButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            RoleBean roleBean = new RoleBean();
            roleBean.setPath(roleCreateRight.textField1.getText());
            roleBean.setNameZh(roleCreateRight.textField2.getText());
            roleBean.setNameEn(roleCreateRight.textField3.getText());
            roleBean.setRemark(roleCreateRight.textField5.getText());
            roleBean.setAuthBeanList(roleCreateRight.authBeanVector);
            parkingLotDBUtils.createRole(roleBean);
            JOptionPane.showMessageDialog(
                    RoleCreatePanel.this,
                    "创建角色:"+roleBean.getNameZh()+"成功!",
                    "角色创建成功",
                    JOptionPane.INFORMATION_MESSAGE
            );
            DefaultMutableTreeNode selectedNode
                    = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            selectedNode.setUserObject(roleBean.getNameZh());
            tree.updateUI();
            editJTree.treeNodes.put(roleBean.getNameZh(),selectedNode);
            editJTree.map.put(selectedNode,roleBean);
        }
    };
    ActionListener editRoleButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    class RoleCreateRight extends JPanel {

        JLabel label1 = new JLabel("角色所属层级关系根路径:");
        JLabel label2 = new JLabel("角色中文名称:");
        JLabel label3 = new JLabel("角色英文标识符(必须保证不重复):");
        JLabel label4 = new JLabel("角色创建人:");
        JLabel label5 = new JLabel("角色简介:");
        JTextField textField1 = new JTextField(30);
        JTextField textField2 = new JTextField(30);
        JTextField textField3 = new JTextField(30);
        JTextField textField4 = new JTextField(30);
        JTextArea textField5 = new JTextArea(5,20);
        JButton button1 = new JButton("角色信息确认无误,点击创建");
        Vector<AuthBean> authBeanVector = new Vector<>();
        JList list = new JList();
        JScrollPane jScrollPane = new JScrollPane(list);
        public RoleCreateRight() {
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

            list.setVisibleRowCount(5);
            list.setSelectedIndex(0);
            add(new JLabel("请选择新关联的权限:"));
            final JAutoCompleteComboBox2 jAutoCompleteComboBox2 = new JAutoCompleteComboBox2();
            add(jAutoCompleteComboBox2);


            JButton button = new JButton("关联选中的权限");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = jAutoCompleteComboBox2.getSelectedItem().toString();
                    String[] array = text.split("-");
                    AuthBean authBean = new AuthBean();
                    authBean.setId(Integer.parseInt(array[0]));
                    authBean.setNameZh(array[1]);
                    authBean.setNameEn(array[2]);
                    authBeanVector.add(authBean);
                    List<String> list2 = new LinkedList<String>();
                    for(AuthBean authBean1 : authBeanVector){
                        list2.add(0,authBean1.getId()+"-"+authBean1.getNameZh()+"-"+authBean1.getNameEn());
                    }
                    list.setListData(list2.toArray());
                    list.setSelectedIndex(0);
                    jScrollPane.updateUI();
                }
            });
            add(button);
            add(new JLabel("该角色已关联的权限列表:"));
            add(jScrollPane);
            add(label5);
            add(textField5);
            textField5.setBorder(new LineBorder(Color.BLACK));
            add(button1);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RoleBean roleBean = new RoleBean();
                    roleBean.setPath(textField1.getText());
                    roleBean.setNameZh(textField2.getText());
                    roleBean.setNameEn(textField3.getText());
                    roleBean.setRemark(textField5.getText());
                    roleBean.setAuthBeanList(authBeanVector);
                    parkingLotDBUtils.createRole(roleBean);
                    JOptionPane.showMessageDialog(
                            RoleCreatePanel.this,
                            "创建角色:"+roleBean.getNameZh()+"成功!",
                            "角色创建成功",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    DefaultMutableTreeNode selectedNode
                            = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    selectedNode.setUserObject(roleBean.getNameZh());
                    tree.updateUI();
                    editJTree.treeNodes.put(roleBean.getNameZh(),selectedNode);
                    editJTree.map.put(selectedNode,roleBean);
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

        Map<DefaultMutableTreeNode, RoleBean> map = new HashMap<DefaultMutableTreeNode, RoleBean>();

        public JTree init(final RoleCreateRight roleCreateRight)
        {
            java.util.List<RoleBean> roleBeanList = parkingLotDBUtils.queryRoleBean2();
            treeNodes.put("路径1",root);
            if(roleBeanList!=null && roleBeanList.size()>0){
                for(RoleBean roleBean : roleBeanList){
                    String[] pathArray = roleBean.getPath().split("\\/");
                    for(int i = 1;i<pathArray.length;++i){
                        String currentpath = pathArray[i];
                        String parentPath = pathArray[i-1];
                        if(treeNodes.containsKey(currentpath)){

                        }else{
                            DefaultMutableTreeNode node = new DefaultMutableTreeNode(currentpath);
                            if(i==pathArray.length-1){
                                map.put(node,roleBean);
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
            JMenuItem createAuth=new JMenuItem("新建角色");
            JMenuItem deleteAuth=new JMenuItem("删除角色");
            JMenuItem editAuth=new JMenuItem("编辑角色");
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
                        RoleBean selected = map.get(selectedNode);
                        if(selected!=null){
                            roleCreateRight.textField1.setText(selected.getPath());
                            roleCreateRight.textField2.setText(selected.getNameZh());
                            roleCreateRight.textField3.setText(selected.getNameEn());

                            roleCreateRight.textField5.setText(selected.getRemark());
                            /*roleCreateRight.button1.removeActionListener(editRoleButton);
                            roleCreateRight.button1.removeActionListener(createRoleButton);*/
                            roleCreateRight.authBeanVector = parkingLotDBUtils.queryRoleAuthBean(selected.getId());
                            List<String> list2 = new LinkedList<String>();
                            if(roleCreateRight.authBeanVector!=null && roleCreateRight.authBeanVector.size()>0){
                                for(AuthBean authBean1 : roleCreateRight.authBeanVector){
                                    list2.add(0,authBean1.getId()+"-"+authBean1.getNameZh()+"-"+authBean1.getNameEn());
                                }
                            }
                            roleCreateRight.list.setListData(list2.toArray());
                            roleCreateRight.list.setSelectedIndex(0);
                            roleCreateRight.jScrollPane.updateUI();
                        }else{
                            TreeNode[] treePath = selectedNode.getPath();
                            StringBuilder sb = new StringBuilder();
                            for(int i = 0;i<treePath.length;++i){
                                sb.append(treePath[i].toString());
                                if(i!=treePath.length-1){
                                    sb.append("/");
                                }
                            }
                            roleCreateRight.textField1.setText(sb.toString());
                            roleCreateRight.textField2.setText("");
                            roleCreateRight.textField3.setText("");
                            roleCreateRight.textField5.setText("");
                            roleCreateRight.authBeanVector.clear();
                            roleCreateRight.list.setListData(new Object[]{});
                            roleCreateRight.jScrollPane.updateUI();
                            /*roleCreateRight.button1.removeActionListener(editRoleButton);
                            roleCreateRight.button1.addActionListener(createRoleButton);*/
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

    public RoleCreatePanel(final JTabbedPane parentTabbedPanel) {
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
        JPanel comboPanel2 = new JPanel(new BorderLayout());
        JPanel printPanel = new JPanel(new ColumnLayout());
        editJTree = new EditJTree();
        tree = editJTree.init(roleCreateRight);
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

        comboPanel2.add(roleCreateRight,BorderLayout.WEST);
        printPanel.setBorder(new TitledBorder("车辆入场基本信息"));

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
        JPanel buttons = new JPanel();
        //buttons.add(fitWidth);
        buttons.add(printButton);
        printPanel.add(buttons);

        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        //controlPanel.add(leftWrapper);
        controlPanel.add(comboPanel);
        JScrollPane jScrollPane = new JScrollPane(comboPanel2);
        controlPanel.add(jScrollPane);
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
