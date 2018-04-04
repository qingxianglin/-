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
import com.sun.swingset3.sql.bean.AuthBean;
import com.sun.swingset3.sql.bean.CardBean;
import com.sun.swingset3.sql.bean.RoleBean;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

public  class   JAutoCompleteComboBox2   extends   JComboBox   {
        private   AutoCompleter2   completer;

        public   JAutoCompleteComboBox2()
        { 
                super(); 
                addCompleter(); 
        } 

        public   JAutoCompleteComboBox2(ComboBoxModel cm)   {
                super(cm); 
                addCompleter();
        } 
        
        public   JAutoCompleteComboBox2(Object[]   items)   {
                super(items); 
                addCompleter(); 
        } 
        
        public   JAutoCompleteComboBox2(List   v)   {
                super((Vector)   v); 
                addCompleter(); 
        } 

        private   void   addCompleter()   {
                setEditable(true); 
                completer = new AutoCompleter2(this);
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
}

/**   
  *   自动完成器。自动找到最匹配的项目，并排在列表的最前面。
  */ 

class   AutoCompleter2   implements   KeyListener,   ItemListener   {

        private   JComboBox   owner   =   null; 
        private   JTextField   editor   =   null;
        private   ComboBoxModel   model   =   null;

        ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

        public   AutoCompleter2(JComboBox comboBox)
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
                if(str==null || str.length()==0){
                        owner.hidePopup();
                        return;
                }
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
                        owner.hidePopup();
                }
        } 
        
        /**
          *  返回所有项目的列表。
          */ 
        protected Object[] getMatchingOptions(String keyword)
        {
                Vector list = new Vector();
                List<AuthBean> authBeans = parkingLotDBUtils.queryAuthBean(keyword);
                if(authBeans!=null && authBeans.size()>0){
                        for(int k = 0; k<authBeans.size();k++)   {
                                AuthBean authBean = authBeans.get(k);
                                list.add(authBean.getId()+"-"+authBean.getNameZh()+"-"+authBean.getNameEn());
                        }
                        return list.toArray();
                }
                return null;
        }
        public   void   itemStateChanged(ItemEvent   event) 
        { 

        } 
}   
