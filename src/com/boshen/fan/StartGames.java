package com.boshen.fan;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
//        绘制静态窗口（JFrame）
        JFrame jFrame = new JFrame("javaProject");
//        设置界面大小
        jFrame.setBounds(10,10,900,720);
//        固定大小
        jFrame.setResizable(false);
//        设置关闭事件
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        展示窗口
        jFrame.setVisible(true);

//        面板JPanel加入到窗体中
        jFrame.add(new GamePanel());


    }
}
