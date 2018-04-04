package com.sun.swingset3.demos.aaa;

import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.CarInBean;
import com.sun.swingset3.sql.bean.ParkingSpaceBean;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

@SuppressWarnings("serial")
class Point{
    int x,y;
    Point previous;
    Point(int x,int y){
        this.x = x;
        this.y = y;
    }
}
public class MazeProblem extends JPanel{
    private static Random random = new Random();
    private static int startX = 2;
    private static int startY = 0;
    private static int endX = 7;
    private static int endY = 14;

    //迷宫地图
    private static int[][] map = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    private static int MAP_WIDTH = 15;
    private static int MAP_HEIGHT = 10;
    public List<JLabel> labels = new ArrayList<>();

    private int entryX = -1,entryY = -1;
    private int exitX = -1,exitY = -1;

    CarInBean carInBean = null;

    ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

    public MazeProblem(CarInBean carInBean,JTextField stopSpaceField){
        this.carInBean = carInBean;
        setLayout(new GridLayout(MAP_HEIGHT,MAP_WIDTH));
        ParkingSpaceBean parkingSpaceBean = parkingLotDBUtils.queryParkingSpaceBean(carInBean.getParkingLotId());
        List<Integer>parkingSpaceCoords = parkingSpaceBean.getParkingSpaceCoords();
        List<Integer>usedSpaceCoords = parkingSpaceBean.getUsedSpaceCoords();
        List<Integer>entryCoords = parkingSpaceBean.getEntryCoords();
        List<Integer>exitCoords = parkingSpaceBean.getExitCoords();
        for(Integer index : parkingSpaceCoords){
            int[] coords = getCoord(index);
            map[coords[1]][coords[0]] = 1;
        }
        for(Integer index : usedSpaceCoords){
            int[] coords = getCoord(index);
            map[coords[1]][coords[0]] = 2;
        }
        for(Integer index : entryCoords){
            int[] coords = getCoord(index);
            map[coords[1]][coords[0]] = 5;
        }
        for(Integer index : exitCoords){
            int[] coords = getCoord(index);
            map[coords[1]][coords[0]] = 8;
        }
        int count = 1;
        for(int i=0;i<MAP_HEIGHT;i++){
            for(int j=0;j<MAP_WIDTH;j++){
                final JLabel label = new JLabel();
                Color color = null;
                if(map[i][j] != 5 && map[i][j] !=8){
                    label.setText(String.valueOf(count));
                }
                count++;
                if(map[i][j] == 2){
                    color = new Color(122, 122, 222);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setVerticalAlignment(SwingConstants.CENTER);
                }
                if(map[i][j] == 1){
                    color = new Color(137, 207, 240);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setVerticalAlignment(SwingConstants.CENTER);
                }
                if(map[i][j] == 0){
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setVerticalAlignment(SwingConstants.CENTER);
                    color = Color.white;
                }
                if(map[i][j] == 5 || map[i][j] ==8){
                    color = Color.white;
                    if(map[i][j]==5){
                        entryX = j;
                        entryY = i;
                        ImageIcon image1 = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\起点.png");
                        //Image img1 = image1.getImage().getScaledInstance(290, 240, Image.SCALE_DEFAULT);
                        label.setIcon(image1);
                    }else{
                        exitX = j;
                        exitY = i;
                        ImageIcon image1 = new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\终点.png");
                        //Image img1 = image1.getImage().getScaledInstance(290, 240, Image.SCALE_DEFAULT);
                        label.setIcon(image1);
                    }
                }
                label.setBackground(color);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                add(label);
                labels.add(label);
            }
        }
        //利用BFS算法寻找最短路径
        int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        java.util.Queue <Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[MAP_WIDTH][MAP_HEIGHT];
        q.offer(new Point(entryX,entryY));
        labels.get(entryY*MAP_WIDTH+entryX).setBackground(Color.GREEN);
        visited[entryX][entryY] = true;
        Point cur = null;
        while(!q.isEmpty()){
            cur = q.poll();
            if(map[cur.y][cur.x]==1){
                carInBean.setStopNo(cur.y*MAP_WIDTH+(cur.x+1));
                labels.get(cur.y*MAP_WIDTH+cur.x).setIcon(new ImageIcon("C:\\Users\\franklin\\Desktop\\ParkingLotManager\\ParkingLotManager\\src\\com\\sun\\swingset3\\demos\\tabbedpane\\resources\\images\\终点.png"));
                labels.get(cur.y*MAP_WIDTH+cur.x).setText(null);
                stopSpaceField.setText(carInBean.getStopNo()+"");
                break;
            }
            for(int i = 0;i<4;++i){
                int x = cur.x + dir[i][0], y = cur.y + dir[i][1];
                if(x>=0 && x<MAP_WIDTH && y>=0 && y<MAP_HEIGHT && !visited[x][y] && map[y][x] != 2){
                    visited[x][y] = true;
                    Point next = new Point(x,y);
                    next.previous = cur;
                    q.offer(next);
                }
            }
        }
        while(cur.previous!=null){
            int index = cur.y*MAP_WIDTH+(cur.x+1);
            labels.get(index-1).setBackground(Color.GREEN);
            cur = cur.previous;
        }
        System.out.println(carInBean.getStopNo());
    }

    //根据车位号获取车位的坐标
    int[] getCoord(int number){
        int[] coords = new int[2];
        coords[0] = number % MAP_WIDTH - 1;
        if(coords[0]==-1){
            coords[0] = 14;
        }
        coords[1] = number / MAP_WIDTH;
        if(number % MAP_WIDTH==0){
            coords[1]-=1;
        }
        return coords;
    }

    public static void main(String[] args){

    }
}

