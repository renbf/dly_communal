package com.project.util;

import java.util.Random;

public class LatticeUtil {

    public static void main(String[] args) {
       ;
        // for形式遍历数组
        int []array= getlLatticePosition(14,5);
        for(int i=0;i<array.length;i++){
            System.out.println("-----------"+array[i]);
        }
    }
    public static int[] getlLatticePosition(int lattice,int prize){
        int []array=new int[lattice];
        for(int i=0;i<lattice;i++){
            array[i]=1;
        }
        Random random = new Random();
        for(int i=0;i<prize;i++){
//            int num=0;
//            int s = random.nextInt(lattice) % (lattice - 0 + 1) + 0;
//            if(num!=s){
//                array[s]=2;
//                num=s;
//            }
            int s = random.nextInt(lattice);
            if(array[s] == 1) {
            	array[s] = 2;
            }else {
            	i--;
            }
        }
        return array;
    }
}
