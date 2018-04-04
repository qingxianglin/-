package com.sun.swingset3.utilities;

import java.awt.*;
import java.awt.event.*;
public class TestDialog {
    public TestDialog() {
        final Frame f1 = new Frame("1");
        f1.setSize(500, 500);
        f1.setVisible(true);
        Button b = new Button("Exit");
        TextArea jt = new TextArea(3, 0);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        Dialog dlinf1 = new Dialog(f1, "这是为了100分特地做的一段代码");
        dlinf1.setSize(300, 300);
        dlinf1.add(b, "North");
        dlinf1.add(jt,"Center");
        dlinf1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        dlinf1.setModal(true);
        dlinf1.setVisible(true);
    }
    public static void main(String[] args) {
        new TestDialog();
    }
}