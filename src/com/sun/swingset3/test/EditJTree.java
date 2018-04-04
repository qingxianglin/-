package com.sun.swingset3.test;

import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.AuthBean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class EditJTree   
{   
    JFrame jf;   
  
    JTree tree;
    //上面JTree对象对应的model   
    DefaultTreeModel model;   

    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

    //定义几个初始节点   
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("路径1");

    Map<String, DefaultMutableTreeNode> treeNodes =
            new HashMap<String, DefaultMutableTreeNode>();

    public JTree init()
    {
        List<AuthBean> authBeanList = parkingLotDBUtils.queryAuthBean(null);
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
                        treeNodes.put(currentpath,node);
                        System.out.println(parentPath);
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