package com.sun.swingset3.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageApplication {

 JFrame frame = new JFrame();
 JPanel panel = new JPanel();
 Container content = frame.getContentPane();
 JToolBar toolbar = new JToolBar();
 // ButtonGroup buttonGroup = new ButtonGroup();
 JButton OpenButton = new JButton("打开");
 JButton ExitButton = new JButton("退出");

 public ImageApplication() {
  frame.pack();
  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
  frame.setBounds(dim.width / 5, dim.height / 5, dim.width / 2, dim.height / 2);

  OpenButton.addActionListener(new OpenActionListener());
  ExitButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    System.exit(0);
   }
  });
//  buttonGroup.add(OpenButton);
//  buttonGroup.add(ExitButton);
  toolbar.add(OpenButton);
  toolbar.addSeparator();
  toolbar.add(ExitButton);
  content.add(toolbar, BorderLayout.NORTH);
  content.add(panel, BorderLayout.CENTER);
  frame.setVisible(true);
 }

 class OpenActionListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
   JFileChooser fileChooser = new JFileChooser("C://");
   FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
   fileChooser.setFileFilter(filter);

   JLabel label = new JLabel();
   int n = fileChooser.showOpenDialog(fileChooser);
   if (n == fileChooser.APPROVE_OPTION) {
    label.setText("显示图片");
    label.setVerticalTextPosition(JLabel.BOTTOM);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setToolTipText(fileChooser.getSelectedFile().getPath());
    label.setIcon(new ImageIcon(fileChooser.getSelectedFile().getPath()));
   } else
    label.setText("未选择");
   panel.removeAll();
   panel.add(label);
   content.add(panel);
   panel.updateUI();
   frame.repaint();
  }
 }

 public static void main(String[] args){
  ImageApplication test = new ImageApplication();
 }
}
