/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aiproject.CSP;

import static java.lang.Math.abs;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.abs;


/**
 *
 * @author saeid
 */
public class gaNqueen {
    int[] neighbersHu=new int[4];
     int[][][] fourRandState;
    
   public void nqueen(int numberOfQueen){
       Random r=new Random();
      fourRandState=new int[4][numberOfQueen][numberOfQueen];

       /////neiber states/////
        int[][] rt=new int[numberOfQueen][numberOfQueen];
        makeItZero(rt,numberOfQueen);
        //first state that is random 
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        int firstStateHurestic=HuresticCaculator(rt);
        fourRandState[0]=rt;
        neighbersHu[0]=firstStateHurestic;
        
        makeItZero(rt,numberOfQueen);
         rt=randomStateIntroducer( numberOfQueen, r, rt);
        firstStateHurestic=HuresticCaculator(rt);
        fourRandState[1]=rt;
        neighbersHu[1]=firstStateHurestic;
        
        makeItZero(rt,numberOfQueen);
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        firstStateHurestic=HuresticCaculator(rt);
        fourRandState[2]=rt;
        neighbersHu[2]=firstStateHurestic;makeItZero(rt,numberOfQueen);
        
        makeItZero(rt,numberOfQueen);
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        firstStateHurestic=HuresticCaculator(rt);
        fourRandState[3]=rt;
        neighbersHu[3]=firstStateHurestic;
       
        ///Printing first 4 random state = first population//
        System.out.println("4 first population is:");
        for (int i = 0; i < 4; i++) {
            statePrinter(fourRandState[i]);
            System.out.println("and the hurestic is:"+neighbersHu[i]);
       }
        
        while(goalIsFound(fourRandState,neighbersHu)==false){
        GA(fourRandState,neighbersHu);
        }

   }
    private void makeItZero(int[][] rt,int numberOfQueen) {
        for (int i = 0; i < numberOfQueen; i++) {
            for (int j = 0; j < numberOfQueen; j++) {
                rt[i][j]=0;
            }
        }
        
    }


    private int[][] randomStateIntroducer( int numberOfQueen, Random r, int[][] rt) {
        makeItZero(rt, numberOfQueen);
        int n=0;
        while(n<numberOfQueen){
            int x=abs(r.nextInt()%numberOfQueen);
            int y=abs(r.nextInt()%numberOfQueen);
            
            if(rt[x][y]==0){
                rt[x][y]=1;
                n++;
            }
        }
        return rt;
    }

    /////////a method for printing matrixes
    private void statePrinter(int[][] rt) {
        for (int[] bs : rt) {
            for (int b : bs) {
                System.out.print(b+"\t");
            }
            System.out.println("");
        }
    }
       

   

   //////a method for calculating hurestics //////////////
    private int HuresticCaculator(int[][] rt) {
        int h=0;
         //////checking index Out out of bound//////
        int p1=0;
        int m1=0;
        int p2=0;
        int m2=0;
        ///////hurestic calculate
        for(int i=0;i<rt.length;i++){
            for (int j = 0; j < rt.length; j++) {
                ////////////////////
                if((i+1)>=rt.length){
                    p1=1;
                }
                if((i-1)<0){
                    m1=1;
                }
                if((j+1)>=rt.length){
                    p2=1;
                }
                if((i-1)<0){
                    m2=1;
                }
                ////////////////////
                if(rt[i][j]==1){
                if(p2==0){
                if(rt[i][j+1]==1){
                  h--;  
                }else{
                    h++;
                }
                }
                if(m1==0&&m2==0){
                if(rt[i-1][j-1]==1){
                 h--;  
                }else{
                    h++;
                }
                }
                if(m1==0){
                if(rt[i-1][j]==1){
                 h--;  
                }else{
                    h++;
                }
                }
                if(m1==0&&p2==0){
                if(rt[i-1][j+1]==1){
                  h--;  
                }else{
                    h++;
                }
                }
                if(m2==0){
                if(rt[i][j-1]==1){
                  h--;  
                }else{
                    h++;
                }
                }
                if(p2==0){
                if(rt[i][j+1]==1){
                  h--;  
                }else{
                    h++;
                }
                }
                if(p1==0&&m2==0){
                if(rt[i+1][j-1]==1){
                  h--;  
                }else{
                    h++;
                }
                }
                if(p1==0){
                if(rt[i+1][j] == 1){
                  h--;  
                }else{
                    h++;
                }
                }
            }
        }
               
            
        }
   return h;

    }
   

     private boolean goalIsFound(int[][][] randStates, int[] rSHurestics) {
     boolean ret=false;
     int goalStateHurestic=0;
     NQueenProblem k= new NQueenProblem(randStates.length);
        k.solveNQ();
     goalStateHurestic=HuresticCaculator(k.board);
        for (int i = 0; i < randStates.length; i++) {
            if(rSHurestics[i]==goalStateHurestic){
                ret=true;
                System.out.println("goal is found:");
                statePrinter(k.board);
                System.out.println("and the goal state huresticis:"+goalStateHurestic);
                break;
            }else{
                ret=false;
                System.out.println("goal yet not found !! genetic is running !");
            }
        }
     return ret;
            
    }

    private void GA(int[][][] fourRandState, int[] neighbersHu) {
        int[][] temp=new int[fourRandState.length][fourRandState.length];
        
            for (int j = 0; j < fourRandState.length/2; j++) {
                for (int k = 0; k < fourRandState.length/2; k++) {
                    temp[j][k]=fourRandState[0][j][k];
                    fourRandState[0][j][k]=fourRandState[1][j][k];
                    fourRandState[1][j][k]=temp[j][k];
                }
            }
            
            for (int j = 0; j < fourRandState.length/2; j++) {
                for (int k = 0; k < fourRandState.length/2; k++) {
                    temp[j][k]=fourRandState[2][j][k];
                    fourRandState[2][j][k]=fourRandState[3][j][k];
                    fourRandState[3][j][k]=temp[j][k];
                }
            }
            
            Random r=new Random();
          int r1,r2,r3,r4;
          r1=Math.abs(r.nextInt()%fourRandState.length);
          r2=Math.abs(r.nextInt()%fourRandState.length);
          r3=Math.abs(r.nextInt()%fourRandState.length);
          r4=Math.abs(r.nextInt()%fourRandState.length);
          
          if(fourRandState[0][r2][r1]==0){
             fourRandState[0][r2][r1]=1; 
          }else{
              fourRandState[0][r2][r1]=0;
          }
           if(fourRandState[1][r3][r4]==0){
             fourRandState[1][r3][r4]=1; 
          }else{
              fourRandState[1][r3][r4]=0;
          }
          
            if(fourRandState[2][r2][r4]==0){
             fourRandState[2][r2][r4]=1; 
          }else{
              fourRandState[2][r2][r4]=0;
          }
          
             if(fourRandState[3][r1][r4]==0){
             fourRandState[3][r1][r4]=1; 
          }else{
              fourRandState[3][r1][r4]=0;
          }
          
        neighbersHu[0]=HuresticCaculator(fourRandState[0]);
        neighbersHu[1]=HuresticCaculator(fourRandState[1]);
        neighbersHu[2]=HuresticCaculator(fourRandState[2]); 
        neighbersHu[3]=HuresticCaculator(fourRandState[3]);
        
    }
    
     public static void main(String[] args) {
        gaNqueen h=new gaNqueen();
       Scanner s=new Scanner(System.in);
       System.out.println("please enter number of queens !");
       h.nqueen(s.nextInt());
    } 
}

