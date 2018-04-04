package com.sun.swingset3.demos.aaa;

import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.CarInBean;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class CreateMazeProblem extends JPanel{
    private static Random random = new Random();
    private static int startX = 2;
    private static int startY = 0;
    private static int endX = 7;
    private static int endY = 14;
    Color selected = new Color(137, 207, 240);
    Color unselected = Color.white;
    JLabel rightSelected = null;
    List<Integer> selectedParkingSpace = new LinkedList<Integer>();
    List<Integer> selectedEntry = new LinkedList<Integer>();
    List<Integer> selectedExit = new LinkedList<Integer>();
    //迷宫地图
    private static int[][] map = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,0,0,1,1,1,0,0,0,1},
            {5,0,0,0,0,0,0,0,1,1,1,0,0,0,1},
            {1,0,0,0,1,1,1,0,0,1,0,0,0,0,1},
            {1,0,0,0,1,1,1,0,0,0,0,0,1,0,1},
            {1,1,0,0,1,1,1,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,1,0,0,0,0,1,1,1,0,1},
            {1,0,1,1,0,0,0,1,0,0,0,0,0,0,8},
            {1,0,1,1,0,0,0,1,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    private static int MAP_WIDTH = 15;
    private static int MAP_HEIGHT = 10;
    public List<JLabel> labels = new ArrayList<>();

    private int start = -1,end = -1;
    private boolean left = true;

    CarInBean carInBean = null;

    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

    public CreateMazeProblem(CarInBean carInBean){
        this.carInBean = carInBean;
        setLayout(new GridLayout(MAP_HEIGHT,MAP_WIDTH));
        /*setBounds(10, 10, MAP_WIDTH*40, MAP_HEIGHT*40);*/
        int count = 1;
        final JPopupMenu popUpMenu=new JPopupMenu();
        JMenuItem entryPoint=new JMenuItem("将停车场入口设在此处");
        entryPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image1 = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\起点.png");
                //Image img1 = image1.getImage().getScaledInstance(290, 240, Image.SCALE_DEFAULT);
                rightSelected.setIcon(image1);
                selectedEntry.add(Integer.parseInt(rightSelected.getText()));
                rightSelected.setText(null);
                System.out.println(selectedEntry+"|"+selectedExit);
            }
        });
        JMenuItem exitPoint =new JMenuItem("将停车场出口设在此处");
        exitPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image1 = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\终点.png");
                //Image img1 = image1.getImage().getScaledInstance(290, 240, Image.SCALE_DEFAULT);
                rightSelected.setIcon(image1);
                selectedExit.add(Integer.parseInt(rightSelected.getText()));
                rightSelected.setText(null);
                System.out.println(selectedEntry+"|"+selectedExit);
            }
        });
        JMenuItem cancel=new JMenuItem("取消选中状态");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightSelected.setIcon(null);
                rightSelected.setBackground(unselected);
                rightSelected.setText((labels.indexOf(rightSelected)+1)+"");
                if(selectedEntry.indexOf(Integer.parseInt(rightSelected.getText()))>=0){
                    selectedEntry.remove(selectedEntry.indexOf(Integer.parseInt(rightSelected.getText())));
                }
                if(selectedExit.indexOf(Integer.parseInt(rightSelected.getText()))>=0){
                    selectedExit.remove(selectedExit.indexOf(Integer.parseInt(rightSelected.getText())));
                }
                System.out.println(selectedEntry+"|"+selectedExit);
            }
        });
        popUpMenu.add(entryPoint);
        popUpMenu.add(exitPoint);
        popUpMenu.add(cancel);
        for(int i=0;i<MAP_HEIGHT;i++){
            for(int j=0;j<MAP_WIDTH;j++){
                final JLabel label = new JLabel();
                Color color = null;
                label.setText(String.valueOf(count));
                label.setBackground(unselected);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                count++;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent event) {
                        System.out.println("第"+label.getText());
                        if(event.getButton()==MouseEvent.BUTTON3)//只响应鼠标右键单击事件
                        {
                            rightSelected = label;
                            popUpMenu.show(label,event.getX(),event.getY());//在鼠标位置显示弹出式菜单
                            return;
                        }
                        if(label.getBackground()==selected){
                            label.setBackground(unselected);
                            selectedParkingSpace.remove(selectedParkingSpace.indexOf(Integer.parseInt(label.getText())));
                        }else{
                            label.setBackground(selected);
                            selectedParkingSpace.add(Integer.parseInt(label.getText()));
                        }
                        System.out.println(selectedParkingSpace);
                    }
                });
                //label.setBackground(color);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                add(label);
                labels.add(label);
            }
        }
    }

    //根据车位号获取车位的坐标
    int[] getCoord(int number){
        int[] coords = new int[2];
        coords[0] = number % MAP_WIDTH - 1;
        coords[1] = number / MAP_WIDTH;
        return coords;
    }

    public static void main(String[] args){
        CreateMazeProblem mazeProblem = new CreateMazeProblem(null);
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(mazeProblem);
    }
}

