package com.boshen.fan;

public class Asteroid {
    int size;
    int asteroidX1;
    int asteroidY1;

    public Asteroid(int size, int asteroidX1, int asteroidY1) {
        this.size = size;
        this.asteroidX1 = asteroidX1;
        this.asteroidY1 = asteroidY1;
    }

    public Asteroid(int size) {
        this.size = size;
    }

    public Asteroid() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAsteroidX1() {
        return asteroidX1;
    }

    public void setAsteroidX1(int asteroidX1) {
        this.asteroidX1 = asteroidX1;
    }

    public int getAsteroidY1() {
        return asteroidY1;
    }

    public void setAsteroidY1(int asteroidY1) {
        this.asteroidY1 = asteroidY1;
    }

    public int[] getLargeRemoveArray(int x,int y,int typeOfPath){
        /*
        * 这个方法返回下一次更新的x坐标值和y坐标值
        * 接收本次x坐标值和y坐标值，接收移动曲线方程的index
        * */
        if(typeOfPath==1){
//            走直线
            x=x-5;
            y=y+1;
        }else if(typeOfPath==2){
//            走斜线1方式
            x=x+1;
            y=y+6;
        }else{
            x=x+5;
            y=y+10;
        }
        return new int[]{x,y};
    }
    public int[] getMiddleRemoveArray(int x,int y,int typeOfPath){
        if(typeOfPath==1){
//            走直线
            x=x-10;
            y=y+3;
        }else if(typeOfPath==2){
//            走斜线1方式
            x=x+5;
            y=y+10;
        }else{
            x=x+8;
            y=y+10;
        }
        return new int[]{x,y};
    }
    public int[] getSmallRemoveArray(int x,int y,int typeOfPath){
        if(typeOfPath==1){
//            走直线
            x=x-15;
            y=y+6;
        }else if(typeOfPath==2){
//            走斜线1方式
            x=x+8;
            y=y+14;
        }else{
            x=x+10;
            y=y+14;
        }
        return new int[]{x,y};
    }

}



