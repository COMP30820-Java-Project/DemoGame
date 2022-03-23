package com.boshen.fan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


//定义一个数据初始化，监听它
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    //这里面的g就是一个画笔
    //    画板功能（实现父类的方法即可做到）

    //    描述飞机位置的数组（x坐标）
    int planeX;
    //    描述飞机位置的数组（Y坐标）
    int planeY;

    //    这就是一个index，用来取飞机指向的图片
    int countPressRightKeyBoard = 0;
    //    判断游戏是否开始
    boolean isStart = false;
    //    添加定时器，让其按帧移动
    int speedOne = 10;
    Timer timer = new Timer(300,this);


    int countPressUp = 1;


//小行星相关数据
//    第一个小行星


//    随机初始位置
    Random randomPosition = new Random();
//    随机漂移方向


//    第二个小行星


//第一个大行星测试


//    小行星的数量
//    初始化5个小行星
    int numOfAst = 6;
    Random randomSize = new Random();
    Random random = new Random();
//    这个变量会产生三个值1，2，3这三个值分别代表小中大
//    int RandomFlagForAstSize = 1+randomSize.nextInt(3);
//    初始化的时候希望有5个小行星，那就把这5个小行星加到一个数组中
    int[] AstSizeArr = new int[numOfAst];
    ArrayList<Asteroid> astList = new ArrayList<>();
    ArrayList<Integer> Xposition = new ArrayList<>();
    ArrayList<Integer> Yposition=new ArrayList<>();
    ArrayList<Integer> removePosition = new ArrayList<>();
    ArrayList<Integer> removePositionX = new ArrayList<>();
//  路径数组（直线斜线）
//    自动分配路径的路径候选数组（1代表直线，2代表斜线1，3代表斜线2）
    int[] typeOfPath = new int[]{1,2,3};
    ArrayList<Integer> eachAstPathRecord = new ArrayList<>();


    public GamePanel(){
        init();
//        获取键盘的监听事件
//        timer.start();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void init(){
        planeX = 100;
        planeY = 100;


        for(int j = 0; j < AstSizeArr.length; j++){
            AstSizeArr[j]=1+randomSize.nextInt(3);
        }
        for (int i : AstSizeArr) {
            Asteroid astTemp= new Asteroid(i,25 + 25 * randomSize.nextInt(34),0);
            astList.add(astTemp);
            Xposition.add(astTemp.getAsteroidX1());
            Yposition.add(astTemp.getAsteroidY1());
            removePosition.add(0);
            removePositionX.add(0);
            eachAstPathRecord.add(1+random.nextInt(3));
        }
    }

//    键盘按下弹起
    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP) {
            if(countPressUp>1){
                countPressUp--;
            }
        }
    }

//    键盘按下未释放
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            isStart = !isStart;
//            刷新界面
            repaint();
        }
//        键盘控制方向（左顺时针，右逆时针）
        if(keyCode == KeyEvent.VK_LEFT) {
            if(countPressRightKeyBoard>0){
                countPressRightKeyBoard--;
            }else{
                countPressRightKeyBoard=3;
            }
        }

        if(keyCode == KeyEvent.VK_RIGHT) {
            if(countPressRightKeyBoard<4){
                countPressRightKeyBoard++;
            }else{
                countPressRightKeyBoard=0;
            }
        }

//        当按倒加速箭头的时候
        if(keyCode == KeyEvent.VK_UP) {
//            if(planeY<630&&planeX<850){
                if(countPressRightKeyBoard==0){
                    if(countPressUp==1){
                        planeY-=speedOne;
                    }
                }else if(countPressRightKeyBoard==1){
                    if(countPressUp==1){
                        planeX+=speedOne;
                    }
                }else if(countPressRightKeyBoard==2){
                    if(countPressUp==1){
                        planeY+=speedOne;
                    }
                }else if(countPressRightKeyBoard==3){
                    if(countPressUp==1){
                        planeX-=speedOne;
                    }
                }
//            }else{
                if(planeX>850){
                    planeX=0;
//                    repaint();
                }
                if(planeX<0){
                    planeX=850;
    //                    repaint();
                }
                if(planeY>630){
                    planeY=0;
//                    repaint();
                }
                if(planeY<0){
                    planeY=630;
    //                    repaint();
                }

//                timer.start();
//            }

        }
    }

//    释放某个键的时候
    @Override
    public void keyReleased(KeyEvent e) {

        }

    @Override
    protected void paintComponent(Graphics g) {
//        这是一个清屏的作用
        super.paintComponent(g);
//        测试是否成功将画布放到Frame里面去了
        this.setBackground(Color.black);
//        绘制游戏区域
        g.fillRect(25, 25,850,630);
//        加载图片到这其中（Data类放入静态资源）
//        这就是拿到up这个图片，之后用paintIcon这个方法将其画上去
        Plane.initPosition();
        if(countPressRightKeyBoard<4){
//            Data.up.paintIcon(this, g,planeX,planeY);
            Plane.planeDirArr.get(countPressRightKeyBoard).paintIcon(this, g,planeX,planeY);
        }else{
            countPressRightKeyBoard = 0;
        }

//        提示游戏是否开始
        if(!isStart){
            g.setColor(Color.pink);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }
//画小行星
//        Data.right.paintIcon(this,g,a1.getAPositionX(),a1.getApositionY());
//        Data.left.paintIcon(this,g,asteroidX2,asteroidY2);
//        Data.food.paintIcon(this, g,asteroidX3,asteroidY3);
        for (int i = 0; i < astList.size(); i++) {
            if(astList.get(i).getSize()==1){
                Data.right.paintIcon(this,g,Xposition.get(i),Yposition.get(i));
            }else if(astList.get(i).getSize()==2){
                Data.left.paintIcon(this,g,Xposition.get(i),Yposition.get(i));
            }else if(astList.get(i).getSize()==3){
                Data.food.paintIcon(this, g,Xposition.get(i),Yposition.get(i));
            }
        }

    }

//    执行定时操作（当创建一个定时器的时候，继承了ActionEvent）的时候会实现这个方法
    @Override
    public void actionPerformed(ActionEvent e) {

         if(isStart){
             for (int index = 0; index < Yposition.size(); index++) {
                 if(astList.get(index).getSize()==1){
                     int[] smallRemTemp = new int[2];
                     smallRemTemp = astList.get(index).getSmallRemoveArray(removePositionX.get(index),removePosition.get(index),eachAstPathRecord.get(index));
                     removePosition.set(index,smallRemTemp[1]);
                     Yposition.set(index,removePosition.get(index));
                     removePositionX.set(index,smallRemTemp[0]);
                     Xposition.set(index,astList.get(index).getAsteroidX1()+removePositionX.get(index));
                     if(Yposition.get(index)>100){
                         astList.get(index).setAsteroidX1(25 + 25 * random.nextInt(34));
                         astList.get(index).setAsteroidY1(0);
                         Xposition.set(index,astList.get(index).getAsteroidX1());
                         Yposition.set(index,0);
                         removePosition.set(index,0);
                         removePositionX.set(index,0);
                         smallRemTemp[0]=0;
                         smallRemTemp[1]=0;
                     }
                 }else if(astList.get(index).getSize()==2){
                     int[] RemTemp = new int[2];
                     RemTemp = astList.get(index).getMiddleRemoveArray(removePositionX.get(index),removePosition.get(index),eachAstPathRecord.get(index));
                     removePosition.set(index,RemTemp[1]);
                     Yposition.set(index,removePosition.get(index));
                     removePositionX.set(index,RemTemp[0]);
                     Xposition.set(index,astList.get(index).getAsteroidX1()+removePositionX.get(index));
                     if(Yposition.get(index)>300){
                         astList.get(index).setAsteroidX1(25 + 25 * random.nextInt(34));
                         astList.get(index).setAsteroidY1(0);
                         Xposition.set(index,astList.get(index).getAsteroidX1());
                         Yposition.set(index,0);
                         removePosition.set(index,0);
                         removePositionX.set(index,0);
                         RemTemp[0]=0;
                         RemTemp[1]=0;
                     }
                 }else {
                     int[] RemTemp2 = new int[2];
                     RemTemp2 = astList.get(index).getLargeRemoveArray(removePositionX.get(index),removePosition.get(index),eachAstPathRecord.get(index));
                     removePosition.set(index,RemTemp2[1]);
                     Yposition.set(index,removePosition.get(index));
                     removePositionX.set(index,RemTemp2[0]);
                     Xposition.set(index,astList.get(index).getAsteroidX1()+removePositionX.get(index));
                     if(Yposition.get(index)>300){
                         astList.get(index).setAsteroidX1(25 + 25 * random.nextInt(34));
                         astList.get(index).setAsteroidY1(0);
                         Xposition.set(index,astList.get(index).getAsteroidX1());
                         Yposition.set(index,0);
                         removePosition.set(index,0);
                         removePositionX.set(index,0);
                         RemTemp2[0]=0;
                         RemTemp2[1]=0;
                     }
                 }



//                  if(astList.get(index).size==1){
//                     int[] smallRemTemp = astList.get(index).getSmallRemoveArray(removePosition.get(index), removePositionX.get(index), eachAstPathRecord.get(index));
//                     removePosition.set(index,smallRemTemp[1]);
//                     Yposition.set(index,astList.get(index).getAsteroidY1()+smallRemTemp[1]);
//                     removePositionX.set(index,smallRemTemp[0]);
//                     Xposition.set(index,astList.get(index).getAsteroidX1()+removePositionX.get(index));
////                     if(removePosition.get(index)>100){
////                        astList.get(index).setAsteroidX1(25 + 25 * random.nextInt(34));
////                        Xposition.set(index,astList.get(index).getAsteroidX1());
////                        Yposition.set(index,0);
////                        removePosition.set(index,0);
////                     }
//                 }else if(astList.get(index).size==2){
////                     int[] smallRemTemp = astList.get(index).getSmallRemoveArray(removePosition.get(index), removePositionX.get(index), eachAstPathRecord.get(index));
////                     removePosition.set(index,smallRemTemp[0]);
////                     Yposition.set(index,smallRemTemp[0]);
////                     removePosition.set(index,removePosition.get(index)+2);
////                     Yposition.set(index,removePosition.get(index));
////                     if(removePosition.get(index)>100){
////                         astList.get(index).setAsteroidX1(25 + 25 * random.nextInt(34));
////                         Xposition.set(index,astList.get(index).getAsteroidX1());
////                         Yposition.set(index,0);
////                         removePosition.set(index,0);
////                     }
//                     int[] smallRemTemp = astList.get(index).getSmallRemoveArray(removePosition.get(index), removePositionX.get(index), eachAstPathRecord.get(index));
//                     removePosition.set(index,smallRemTemp[0]);
//                     Yposition.set(index,smallRemTemp[0]);
//                     removePositionX.set(index,smallRemTemp[1]);
//                     Xposition.set(index,astList.get(index).getAsteroidX1()+removePositionX.get(index));
//                 }else if(astList.get(index).size==3){
////                     int[] smallRemTemp = astList.get(index).getSmallRemoveArray(removePosition.get(index), removePositionX.get(index), eachAstPathRecord.get(index));
////                     removePosition.set(index,smallRemTemp[0]);
////                     Yposition.set(index,smallRemTemp[0]);
////                     removePosition.set(index,removePosition.get(index)+1);
////                     Yposition.set(index,removePosition.get(index));
////                     if(removePosition.get(index)>100){
////                         astList.get(index).setAsteroidX1(25 + 25 * random.nextInt(34));
////                         Xposition.set(index,astList.get(index).getAsteroidX1());
////                         Yposition.set(index,0);
////                         removePosition.set(index,0);
////                     }
//                     int[] smallRemTemp = astList.get(index).getSmallRemoveArray(removePosition.get(index), removePositionX.get(index), eachAstPathRecord.get(index));
//                     removePosition.set(index,smallRemTemp[0]);
//                     Yposition.set(index,smallRemTemp[0]);
//                     removePositionX.set(index,smallRemTemp[1]);
//                     Xposition.set(index,astList.get(index).getAsteroidX1()+removePositionX.get(index));
//                 }
             }
             repaint();
        }

//        设置一下，让时间动起来，当时间动起来的时候每隔自己设定的时间这个函数就会执行一次
        timer.start();

    }

//    监听键盘的输入

}



