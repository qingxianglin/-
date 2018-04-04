package com.sun.swingset3.demos.aaa;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.swingset3.DemoModule;
import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.AuthBean;
import com.sun.swingset3.sql.bean.RoleBean;
import com.sun.swingset3.sql.bean.UserBean;
import com.sun.swingset3.test.JAutoCompleteComboBox2;
import com.sun.swingset3.test.JAutoCompleteComboBox3;
import com.sun.swingset3.test.UserCreateRight;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class UserCreatePanel extends DemoModule {

    JTree tree;

    EditJTree editJTree;

    UserCreateRight userCreateRight = new UserCreateRight();

    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

    ActionListener createUserButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserBean userBean = new UserBean();
            try{
                userBean.setBirthday(new SimpleDateFormat("yyyy-mm-dd").parse(userCreateRight.textField1.getText()));
            }catch (Exception e1){

            }
            userBean.setPicturePath(userCreateRight.picturePath);
            userBean.setName(userCreateRight.textField7.getText());
            userBean.setIdCard(userCreateRight.comboBox1.getText());
            userBean.setPhonex(userCreateRight.textField3.getText());
            userBean.setAccount(userCreateRight.textArea1.getText());
            userBean.setPassword(userCreateRight.passwordField1.getText());
            userBean.setPath(userCreateRight.passwordField2.getText());
            userBean.setSex(userCreateRight.comboBox2.getSelectedItem().toString());
            userBean.setRoleBeanList(userCreateRight.roleBeanVector);
            parkingLotDBUtils.createUser(userBean);
            JOptionPane.showMessageDialog(
                    UserCreatePanel.this,
                    "创建管理员:"+userBean.getName()+"成功!",
                    "管理员创建成功",
                    JOptionPane.INFORMATION_MESSAGE
            );
            DefaultMutableTreeNode selectedNode
                    = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            selectedNode.setUserObject(userBean.getName());
            tree.updateUI();
            editJTree.treeNodes.put(userBean.getName(),selectedNode);
            editJTree.map.put(selectedNode,userBean);
        }
    };
    ActionListener editUserButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    class UserCreateRight extends JPanel{
        public UserCreateRight() {
            initComponents();
        }

        private void initComponents() {
            setLayout(new BorderLayout());
            setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red,1,true)));
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - hhh ddd
            label11 = new JLabel();
            label1 = new JLabel();
            textField1 = new JTextField();
            label4 = new JLabel();
            label4.addMouseListener(new OpenActionListener());
            label12 = new JLabel();
            textField7 = new JTextField();
            comboBox2 = new JComboBox<>();
            label9 = new JLabel();
            comboBox1 = new JTextField();
            label3 = new JLabel();
            textField3 = new JTextField();
            label13 = new JLabel();
            textArea1 = new JTextField();
            label6 = new JLabel();
            passwordField1 = new JPasswordField();
            label14 = new JLabel();
            passwordField2 = new JTextField();
            button1 = new JButton();
            label15 = new JLabel();
            textField8 = new JAutoCompleteComboBox3();
            label16 = new JLabel();
            jlist.setVisibleRowCount(5);
            jlist.setSelectedIndex(0);
            textField9 = new JScrollPane(jlist);
            button2 = new JButton();
            label17 = new JLabel();
            textField10 = new JTextField();
            label18 = new JLabel();
            comboBox3 = new JComboBox<>();

            //======== this ========
            setBackground(new Color(232, 232, 232));

            // JFormDesigner evaluation mark
            setBorder(new javax.swing.border.CompoundBorder(
                    new TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", TitledBorder.CENTER,
                            TitledBorder.BOTTOM, new Font("Dialog", Font.BOLD, 12),
                            Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            setLayout(new FormLayout(
                    "right:default, $lcgap, default:grow, $lcgap, center:[50dlu,default], $lcgap, default",
                    "pref, 27*($lgap, default)"));

            //---- label11 ----
            label11.setText("性别:");
            label11.setForeground(Color.black);
            add(label11, CC.xy(1, 5));

            //---- label1 ----
            label1.setText("出生日期:");
            label1.setForeground(Color.black);
            add(label1, CC.xy(1, 7));

            //---- textField1 ----
            textField1.setBackground(Color.white);
            add(textField1, CC.xy(3, 7));

            //---- label4 ----
            label4.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u5934\u50cf.png"));
            add(label4, CC.xywh(5, 2, 1, 10, CC.DEFAULT, CC.CENTER));

            //---- label12 ----
            label12.setText("姓名:");
            label12.setForeground(Color.black);
            add(label12, CC.xy(1, 3));

            //---- textField7 ----
            textField7.setBackground(Color.white);
            textField7.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    int index = passwordField2.getText().lastIndexOf("/");
                    passwordField2.setText(passwordField2.getText().substring(0,index+1)+textField7.getText());
                }
            });
            add(textField7, CC.xy(3, 3));

            //---- comboBox2 ----
            comboBox2.setBackground(Color.white);
            comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                    "男",
                    "女"
            }));
            comboBox2.setForeground(Color.black);
            add(comboBox2, CC.xy(3, 5));

            //---- label9 ----
            label9.setText("身份证号码:");
            label9.setForeground(Color.black);
            add(label9, CC.xy(1, 9));

            //---- comboBox1 ----
            comboBox1.setBackground(Color.white);
            add(comboBox1, CC.xy(3, 9));

            //---- label3 ----
            label3.setText("手机号码:");
            label3.setForeground(Color.black);
            add(label3, CC.xy(1, 11));

            //---- textField3 ----
            textField3.setBackground(Color.white);
            add(textField3, CC.xy(3, 11));

            //---- label13 ----
            label13.setText("员工号:");
            label13.setForeground(Color.black);
            add(label13, CC.xy(1, 13));

            //---- textArea1 ----
            textArea1.setBackground(Color.white);
            add(textArea1, CC.xy(3, 13));

            //---- label6 ----
            label6.setText("账号密码:");
            label6.setForeground(Color.black);
            add(label6, CC.xy(1, 15));

            //---- passwordField1 ----
            passwordField1.setBackground(Color.white);
            add(passwordField1, CC.xy(3, 15));

            //---- label14 ----
            label14.setText("所属层级:");
            label14.setForeground(Color.black);
            add(label14, CC.xy(1, 17));

            //---- passwordField2 ----
            passwordField2.setBackground(Color.white);
            add(passwordField2, CC.xy(3, 17));

            //---- button1 ----
            button1.setText("关联选中的角色");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] array = textField8.getText().split("-");
                    textField8.setText("");
                    RoleBean roleBean = new RoleBean();
                    roleBean.setId(Integer.parseInt(array[0]));
                    roleBean.setNameEn(array[2]);
                    roleBean.setNameZh(array[1]);
                    roleBeanVector.add(roleBean);
                    comboBox3.insertItemAt(roleBean.getId()+"-"+roleBean.getNameZh()+"-"+roleBean.getNameEn(),0);
                    comboBox3.setSelectedIndex(0);
                    comboBox3.updateUI();
                }
            });
            add(button1, CC.xywh(5, 19, 1, 1));

            //---- label15 ----
            label15.setText("关联新角色:");
            label15.setForeground(Color.black);
            add(label15, CC.xy(1, 19));

            //---- textField8 ----
            textField8.setBackground(Color.white);
            //add(textField8, CC.xy(3, 19));
            add(textField8,CC.xy(3,19));

            //---- label16 ----
            label16.setText("已关联角色:");
            label16.setBackground(Color.black);
            label16.setForeground(Color.black);
            add(label16, CC.xy(1, 21));


            add(comboBox3, CC.xy(3, 21));

            //---- button2 ----
            button2.setText("确认无误,点击创建");
            add(button2, CC.xy(5, 21));
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UserBean userBean = new UserBean();
                    try{
                        userBean.setBirthday(new SimpleDateFormat("yyyy-mm-dd").parse(userCreateRight.textField1.getText()));
                    }catch (Exception e1){

                    }
                    userBean.setPicturePath(userCreateRight.picturePath);
                    userBean.setName(userCreateRight.textField7.getText());
                    userBean.setIdCard(userCreateRight.comboBox1.getText());
                    userBean.setPhonex(userCreateRight.textField3.getText());
                    userBean.setAccount(userCreateRight.textArea1.getText());
                    userBean.setPassword(userCreateRight.passwordField1.getText());
                    userBean.setPath(userCreateRight.passwordField2.getText());
                    userBean.setSex(userCreateRight.comboBox2.getSelectedItem().toString());
                    userBean.setRoleBeanList(userCreateRight.roleBeanVector);
                    parkingLotDBUtils.createUser(userBean);
                    JOptionPane.showMessageDialog(
                            UserCreatePanel.this,
                            "创建管理员:"+userBean.getName()+"成功!",
                            "管理员创建成功",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    DefaultMutableTreeNode selectedNode
                            = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    selectedNode.setUserObject(userBean.getName());
                    tree.updateUI();
                    editJTree.treeNodes.put(userBean.getName(),selectedNode);
                    editJTree.map.put(selectedNode,userBean);
                }
            });
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - hhh ddd
        JLabel label11;
        JLabel label1;
        JTextField textField1;
        JLabel label4;
        JLabel label12;
        JTextField textField7;
        JComboBox<String> comboBox2;
        JLabel label9;
        JTextField comboBox1;
        JLabel label3;
        JTextField textField3;
        JLabel label13;
        JTextField textArea1;
        JLabel label6;
        JPasswordField passwordField1;
        JLabel label14;
        JTextField passwordField2;
        JButton button1;
        JLabel label15;
        JAutoCompleteComboBox3 textField8;
        JLabel label16;
        JScrollPane textField9;
        JList jlist = new JList();
        String picturePath;
        JButton button2;
        JLabel label17;
        JTextField textField10;
        JLabel label18;
        JComboBox<String> comboBox3;
        List<RoleBean> roleBeanVector = new ArrayList<RoleBean>();
        // JFormDesigner - End of variables declaration  //GEN-END:variables

        class OpenActionListener extends MouseAdapter {

            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                fileChooser.setFileFilter(filter);

                int n = fileChooser.showOpenDialog(fileChooser);
                if (n == fileChooser.APPROVE_OPTION) {
                    ImageIcon image = new ImageIcon(fileChooser.getSelectedFile().getPath());
                    Image img = image.getImage();
                    img = img.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                    image.setImage(img);
                    label4.setIcon(image);
                    picturePath = fileChooser.getSelectedFile().getPath();
                }
            }
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

        Map<DefaultMutableTreeNode, UserBean> map = new HashMap<DefaultMutableTreeNode, UserBean>();

        public JTree init(final UserCreateRight userCreateRight)
        {
            java.util.List<UserBean> userBeanList = parkingLotDBUtils.queryUserBean(null);
            treeNodes.put("路径1",root);
            if(userBeanList!=null && userBeanList.size()>0){
                for(UserBean userBean : userBeanList){
                    String[] pathArray = userBean.getPath().split("\\/");
                    for(int i = 1;i<pathArray.length;++i){
                        String currentpath = pathArray[i];
                        String parentPath = pathArray[i-1];
                        if(treeNodes.containsKey(currentpath)){

                        }else{
                            DefaultMutableTreeNode node = new DefaultMutableTreeNode(currentpath);
                            if(i==pathArray.length-1){
                                map.put(node,userBean);
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
            JMenuItem createAuth=new JMenuItem("新建管理员");
            JMenuItem deleteAuth=new JMenuItem("删除管理员");
            JMenuItem editAuth=new JMenuItem("编辑管理员");
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
                        UserBean selected = map.get(selectedNode);
                        if(selected!=null){
                            userCreateRight.comboBox3.setModel(new DefaultComboBoxModel<String>());
                            ImageIcon image = new ImageIcon(selected.getPicturePath());
                            Image img = image.getImage();
                            img = img.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                            image.setImage(img);
                            userCreateRight.label4.setIcon(image);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                            userCreateRight.textField1.setText(sdf.format(selected.getBirthday()));
                            userCreateRight.textField7.setText(selected.getName());
                            userCreateRight.comboBox1.setText(selected.getIdCard());
                            userCreateRight.textField3.setText(selected.getPhonex());
                            userCreateRight.textArea1.setText(selected.getAccount());
                            userCreateRight.passwordField1.setText(selected.getPassword());
                            userCreateRight.passwordField2.setText(selected.getPath());
                            /*userCreateRight.button2.removeActionListener(editUserButton);
                            userCreateRight.button2.removeActionListener(createUserButton);*/
                            userCreateRight.roleBeanVector = parkingLotDBUtils.queryUserRoleBean(selected.getId());
                            List<String> list2 = new LinkedList<String>();
                            if(userCreateRight.roleBeanVector!=null && userCreateRight.roleBeanVector.size()>0){
                                for(RoleBean roleBean1 : userCreateRight.roleBeanVector){
                                    list2.add(0,roleBean1.getId()+"-"+roleBean1.getNameZh()+"-"+roleBean1.getNameEn());
                                }
                                userCreateRight.comboBox3.setModel(new DefaultComboBoxModel(list2.toArray()));
                                userCreateRight.comboBox3.setSelectedIndex(0);
                            }
                        }else{
                            TreeNode[] treePath = selectedNode.getPath();
                            StringBuilder sb = new StringBuilder();
                            for(int i = 0;i<treePath.length;++i){
                                sb.append(treePath[i].toString());
                                if(i!=treePath.length-1){
                                    sb.append("/");
                                }
                            }
                            userCreateRight.label4.setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\resources\\images\\\u5934\u50cf.png"));
                            userCreateRight.textField1.setText("");
                            userCreateRight.textField7.setText("");
                            userCreateRight.comboBox1.setText("");
                            userCreateRight.textField3.setText("");
                            userCreateRight.textArea1.setText("");
                            userCreateRight.passwordField1.setText("");
                            userCreateRight.passwordField2.setText(sb.toString());
                            userCreateRight.roleBeanVector.clear();
                            userCreateRight.comboBox3.setModel(new DefaultComboBoxModel(new Object[]{}));
                            userCreateRight.comboBox3.updateUI();
                            /*userCreateRight.button2.removeActionListener(editUserButton);
                            userCreateRight.button2.addActionListener(createUserButton);*/
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

    public UserCreatePanel(final JTabbedPane parentTabbedPanel) {
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
        tree = editJTree.init(userCreateRight);
        comboPanel.add(new JScrollPane(tree));
        //comboPanel.add(new EditJTree().init());

        getDemoPanel().add(controlPanel, BorderLayout.CENTER);

        // label panel
        interCellSpacingLabel = new JLabel(getString("TableDemo.intercell_spacing_colon"));
        labelPanel.add(interCellSpacingLabel);

        rowHeightLabel = new JLabel(getString("TableDemo.row_height_colon"));
        labelPanel.add(rowHeightLabel);

        comboPanel.setBorder(new TitledBorder("管理员层级关系图"));

        comboPanel2.setBorder(new TitledBorder("管理员信息详情表单"));

        //comboPanel2.add(roleCreateRight,BorderLayout.WEST);
        comboPanel2.add(userCreateRight,BorderLayout.WEST);
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
