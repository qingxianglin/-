package com.sun.swingset3.test;

/*
改装思路如下：   
1.先继承一个JComboBox类，将其setEditable为true.这样的话，用户才可以在combobox上输入文字。
2.我们知道combobox的输入框是一个JTextField,   可以通过comboBox.getEditor().getEditorComponent()取得这个文本框。   
3.为这个文本框加上一个KeyListener.   
4.当用户在文本框中按键时，会解发keyReleased事件，我们在这个事件里写主要的实现自动查找和完成的代码。   
思想就是这么简单，而自动查找的算法，任何一个对编程不陌生的人都可以写出。以下我列出完整的程序代码：   
*/ 

import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.ManagerBean;

import java.awt.*;
import   java.awt.event.ItemEvent;
import   java.awt.event.ItemListener;   
import   java.awt.event.KeyEvent;   
import   java.awt.event.KeyListener;
import java.util.ArrayList;
import   java.util.List;
import   java.util.Vector;   
import   javax.swing.ComboBoxModel;   
import   javax.swing.DefaultComboBoxModel;   
import   javax.swing.JComboBox;   
import   javax.swing.JFrame;   
import   javax.swing.JTextField;   

public   class   JAutoCompleteComboBox   extends   JComboBox   {   
        private   AutoCompleter   completer;

        public   JAutoCompleteComboBox() 
        { 
                super(); 
                addCompleter(); 
        } 

        public   JAutoCompleteComboBox(ComboBoxModel cm)   {
                super(cm); 
                addCompleter();
        } 
        
        public   JAutoCompleteComboBox(Object[]   items)   { 
                super(items); 
                addCompleter(); 
        } 
        
        public   JAutoCompleteComboBox(List   v)   { 
                super((Vector)   v); 
                addCompleter(); 
        } 

        private   void   addCompleter()   {
                setEditable(true); 
                completer = new AutoCompleter(this);
        } 

        public   void   autoComplete(String   str)   { 
                this.completer.autoComplete(str);
        } 

        public   String   getText()   { 
                return   ((JTextField)   getEditor().getEditorComponent()).getText(); 
        } 

        public   void   setText(String   text)   { 
                ((JTextField)   getEditor().getEditorComponent()).setText(text); 
        }
        
        public   static   void   main(String[]   args) 
        { 
                JFrame   frame   =   new   JFrame();
                DefaultComboBoxModel   model   =   new   DefaultComboBoxModel(); 
                JComboBox   cmb   =   new   JAutoCompleteComboBox(model); 

                frame.setLayout(new BorderLayout());
                frame.add(cmb,BorderLayout.NORTH);
                frame.setSize(400,   80); 
                frame.setVisible(true); 
        } 
}   
  
/**   
  *   自动完成器。自动找到最匹配的项目，并排在列表的最前面。
  */ 

class   AutoCompleter   implements   KeyListener,   ItemListener   {   

        private   JComboBox   owner   =   null; 
        private   JTextField   editor   =   null;
        private   ComboBoxModel   model   =   null;

        ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

        public   AutoCompleter(JComboBox comboBox)
        { 
                owner = comboBox;
                editor = (JTextField)comboBox.getEditor().getEditorComponent();
                editor.addKeyListener(this);
                model = comboBox.getModel();
                owner.addItemListener(this); 
        } 

        public void keyTyped(KeyEvent e){}

        public void keyPressed(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e)
        {
                String str = editor.getText();
                if(str==null || str.length()==0) return;
                autoComplete(str);
        } 
        
        /** 
          *   自动完成。根据输入的内容，在列表中找到相似的项目. 
          */ 
        protected   void   autoComplete(String keyword)
        { 
                Object[] opts;
                opts = getMatchingOptions(keyword);
                if(opts!=null && opts.length>0)   {
                    model = new DefaultComboBoxModel(opts);
                    owner.setModel(model);
                        try   {
                                owner.showPopup();
                        }   catch   (Exception   ex)   {
                                ex.printStackTrace();
                        }
                }else{
                        owner.showPopup();
                }
        } 
        
        /**
          *  返回所有项目的列表。
          */ 
        protected Object[] getMatchingOptions(String keyword)
        {
                Vector list = new Vector();
                List<ManagerBean> managerBeans = parkingLotDBUtils.queryManagerBean(keyword);
                if(managerBeans!=null && managerBeans.size()>0){
                        for(int k = 0; k<managerBeans.size();k++)   {
                                ManagerBean managerBean = managerBeans.get(k);
                                list.add(managerBean.getManagerName()+"-"+managerBean.getManagerId());
                        }
                        return list.toArray();
                }
                return null;
        }
        public   void   itemStateChanged(ItemEvent   event) 
        { 

        } 
}   
