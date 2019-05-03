/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aiproject.CSP;

import aiproject.GraphColoring;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author saeid
 */
public class gaMapColoring {
     int[] neighbersHu=new int[4];
     int[][] fourRandState;
     char[] vertexes;
     
    public void mapColoring(int[][] m,int numOfColor){
        fourRandState=new int[4][m.length];
        //an array for getting vertex titles
       vertexes =new char[m.length];
        ///initial titles
        char[] ch=new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        //giving vertexes title
        for (int i = 0; i < m.length; i++) {
            vertexes[i]=ch[i];
        }
        //checking graph coloring possibality 
        ///if sum of one's of m matrix divide number of vertexes mines one equal or less than number of color there is solution else there is no one
       if( possibality(m,numOfColor)){
         int[] vAllocatedColor0=randomColoring(numOfColor,vertexes);
         int h0= huresticCalculator(vAllocatedColor0,m);
         int[] vAllocatedColor1=randomColoring(numOfColor,vertexes);
         int[] vAllocatedColor2=randomColoring(numOfColor,vertexes);
         int[] vAllocatedColor3=randomColoring(numOfColor,vertexes);
         int h1= huresticCalculator(vAllocatedColor1,m);
         int h2= huresticCalculator(vAllocatedColor2,m);
         int h3= huresticCalculator(vAllocatedColor3,m);
         fourRandState[0]=vAllocatedColor0;
         fourRandState[1]=vAllocatedColor1;
         fourRandState[2]=vAllocatedColor2;
         fourRandState[3]=vAllocatedColor3;
         neighbersHu[0]=h0;
         neighbersHu[1]=h1;
         neighbersHu[2]=h2;
         neighbersHu[3]=h3;
         
         ///four first state that is maked ///
         statePrinter(vAllocatedColor0,vertexes) ;
         System.out.println("first state hurestic is:"+h0);
         statePrinter(vAllocatedColor1,vertexes) ;
         System.out.println("second state hurestic is:"+h1);
         statePrinter(vAllocatedColor2,vertexes) ;
         System.out.println("third state hurestic is:"+h2);
         statePrinter(vAllocatedColor3,vertexes) ;
         System.out.println("state number four hurestic is:"+h3);
         //implementing genetic algorithm//
         while(goalFound(fourRandState, m, numOfColor, neighbersHu)){
          GA(fourRandState,neighbersHu,m);
         }
       
       }else{
           System.out.println("there is no solution !!");
       }
    }

    private int[] randomColoring(int numOfColor, char[] vertexes) {
        int[] vAllocatedColor=new int[vertexes.length];
        Random r=new Random();
        for(int i=0;i<vertexes.length;i++){
           vAllocatedColor[i]=Math.abs(r.nextInt()%numOfColor);
        }
        
        return vAllocatedColor;
    }
    
    

    private boolean possibality(int[][] m, int numOfColor) {
        int sum=0;
        boolean ret=false;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if(m[i][j]==1){
                    sum+=1;
                }
            }
        }
        if(sum/(m.length-1)<=numOfColor){
          ret=true;  
        }else{
            ret=false;
        }
        return ret;
    }
    
    private int huresticCalculator(int[] vAllocatedColor, int[][] m) {
        int h=0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if(m[i][j]==1){
                    if(vAllocatedColor[i]==vAllocatedColor[j]){
                       h--; 
                    }else{
                        h++;
                    }
                }
            }
        }
        return h;
    }
    

    private void statePrinter(int[] vAllocatedColor,char[] vertexes) {
        for (int i = 0; i < vAllocatedColor.length; i++) {
        System.out.println(vertexes[i]+":"+vAllocatedColor[i]);

        }
    }



    private void GA(int[][] fourRandState, int[] neighbersHu, int[][] m) {
        int[] temp=new int[fourRandState.length/2];
        
        ///////////ترکیب کروموزوم ها به صورت پیشفرض کروموزوم ها از وسط با هم ترکیب میشوند////
          for (int i = 0; i < fourRandState.length/2; i++) {
            temp[i]=fourRandState[0][i];
            fourRandState[0][i]=fourRandState[1][i];
            fourRandState[1][i]=temp[i];
        }
          for (int i = 0; i < fourRandState.length/2; i++) {
            temp[i]=fourRandState[2][i];
            fourRandState[2][i]=fourRandState[3][i];
            fourRandState[3][i]=temp[i];
        }
          ///////تغییر در ژنها///////
          Random r=new Random();
          int r1,r2,r3,r4;
          r1=Math.abs(r.nextInt()%m.length);
          r2=Math.abs(r.nextInt()%m.length);
          r3=Math.abs(r.nextInt()%m.length);
          r4=Math.abs(r.nextInt()%m.length);
          
          if(fourRandState[0][r1]==0){
             fourRandState[0][r1]=1; 
          }else{
              fourRandState[0][r1]=0;
          }
          if(fourRandState[1][r2]==0){
             fourRandState[0][r2]=1; 
          }else{
              fourRandState[0][r2]=0;
          }
          if(fourRandState[0][r3]==0){
             fourRandState[0][r3]=1; 
          }else{
              fourRandState[0][r3]=0;
          }
          if(fourRandState[0][r4]==0){
             fourRandState[0][r4]=1; 
          }else{
              fourRandState[0][r4]=0;
          }
          
          neighbersHu[0]=huresticCalculator(fourRandState[0],m);
          neighbersHu[1]=huresticCalculator(fourRandState[1],m);
          neighbersHu[2]=huresticCalculator(fourRandState[2],m);
          neighbersHu[3]=huresticCalculator(fourRandState[3],m);
        
    }
    private boolean goalFound(int[][] randStates, int[][] m, int numOfColor, int[] hurestics1) {
        boolean ret=false;
        int ghurestic=0;
        GraphColoring gc=new GraphColoring();
        gc.graphColor(m, numOfColor);
        System.out.println("\n\n");
        
        for (int i = 0; i < gc.color.length; i++){
            gc.color[i]-=1;    
            }
        ghurestic=huresticCalculator(gc.color, m);
        
        for(int o=0;o<4;o++){
            if(hurestics1[o]==ghurestic){
              ret=true;
                System.out.println("goal is found !");
                statePrinter(randStates[o], vertexes);
                System.out.println("and the goal hurestic is:"+hurestics1[o]);
            }else{
                ret=false;
                
            }
        }
       
     return  ret;
    }
    
     public static void main(String[] args) {
         gaMapColoring m=new gaMapColoring();
        System.out.println("please enter number of vertexes :");
        Scanner d=new Scanner(System.in);
        
        int vert=d.nextInt();
        System.out.println("please enter number of colors");
        int noc=d.nextInt();
        int[][] vb=new int[vert][vert];
        System.out.println("please enter neighberhood graph 1 for neighbers and 0 for others");
        for (int i = 0; i < vert; i++) {
            for (int j = 0; j < vert; j++) {
                vb[i][j]=d.nextInt();
            }
        }
        m.mapColoring(vb, noc);
    }
}

