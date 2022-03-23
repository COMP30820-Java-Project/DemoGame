package com.boshen.fan;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
//        int numOfAst = 5;
        int[] AstSizeArr = new int[1];
        ArrayList<Asteroid> astList = new ArrayList<>();
        ArrayList<Integer> Xposition = new ArrayList<>();
        ArrayList<Integer> Yposition=new ArrayList<>();
        ArrayList<Integer> removePosition = new ArrayList<>();
        ArrayList<Integer> removePositionX = new ArrayList<>();
//  路径数组（直线斜线）
//    自动分配路径的路径候选数组（1代表直线，2代表斜线1，3代表斜线2）
        ArrayList<Integer> eachAstPathRecord = new ArrayList<>();
        Random random = new Random();
        for (int i : AstSizeArr) {
            Asteroid astTemp= new Asteroid(i,25 + 25 * random.nextInt(34),0);
            astList.add(astTemp);
            Xposition.add(astTemp.getAsteroidX1());
            Yposition.add(astTemp.getAsteroidY1());
            removePosition.add(0);
            removePositionX.add(0);
            eachAstPathRecord.add(1+random.nextInt(3));
        }
        int i=0;
        while(i<5){
            for (int index = 0; index < Yposition.size(); index++) {
                int[] smallRemTemp = astList.get(index).getSmallRemoveArray(removePositionX.get(index),removePosition.get(index), eachAstPathRecord.get(index));
                System.out.println(smallRemTemp[0]+" "+smallRemTemp[1]);
                removePosition.set(index,smallRemTemp[1]);
                System.out.println(removePosition.get(index));
                Yposition.set(index,removePosition.get(index));
                System.out.println(Yposition.get(index));
                System.out.println("==============");
            }
            i++;

        }

//
//        ArrayList<Asteroid> astList = new ArrayList<>();
////    这个变量会产生三个值1，2，3这三个值分别代表小中大
////    int RandomFlagForAstSize = 1+randomSize.nextInt(3);
////    初始化的时候希望有5个小行星，那就把这5个小行星加到一个数组中
//        int[] AstSizeArr = new int[numOfAst];
//        for(int j = 0; j < AstSizeArr.length; j++){
//            AstSizeArr[j]=1+randomSize.nextInt(3);
//        }
//        for (int i : AstSizeArr) {
//            astList.add(new Asteroid(i));
//        }
//        for(int i = 0;i<astList.size();i++) {
//            System.out.println(astList.get(i).size);
//        }
//        int[] typeOfPath = new int[]{1,2,3};
//        for (int i : typeOfPath) {
//            System.out.println(i);
//        }

    }
}
