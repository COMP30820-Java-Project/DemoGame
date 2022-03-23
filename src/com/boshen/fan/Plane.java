package com.boshen.fan;

import javax.swing.*;
import java.util.ArrayList;

public class Plane {

    //    飞机指向图片数组
    static ArrayList<ImageIcon> planeDirArr = new ArrayList<>();

    public static void initPosition(){
        planeDirArr.add(Data.up);
        planeDirArr.add(Data.right);
        planeDirArr.add(Data.down);
        planeDirArr.add(Data.left);
    }
}
