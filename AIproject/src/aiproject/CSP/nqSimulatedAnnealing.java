/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aiproject.CSP;


import java.util.Random;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import java.util.Scanner;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.abs;



/**
 *
 * @author saeid
 */
public class nqSimulatedAnnealing {
    public int[][][] randStates;
    public int[]  rSHurestics;   

    public void nqueen(int numberOfQueen){
        randStates=new int[20][numberOfQueen][numberOfQueen];
        rSHurestics=new int[20];
       Random r=new Random();
        int[][] rt=new int[numberOfQueen][numberOfQueen];
        makeItZero(rt,numberOfQueen);
    
        //first state that is random 
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        int firstStateHurestic=NqueenHuresticCalculater(rt);
        
        for(int o=0;o<20;o++){
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        firstStateHurestic=NqueenHuresticCalculater(rt);    
        randStates[o]=rt;
        rSHurestics[o]=firstStateHurestic;
        
        }
         sort(randStates,rSHurestics);        
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
    private int NqueenHuresticCalculater(int[][] rt) {
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
   
    private void sort(int[][][] randStates, int[] rSHurestics) {
        int temp;
        int[][] temp1;
        for (int i = 0; i < 20 - 1; i++) {
            for (int j = 0; j < 20 - 1 - i; j++) {
                if (rSHurestics[j] > rSHurestics[j + 1]) {
                    temp = rSHurestics[j];
                    temp1 = randStates[j];
                    rSHurestics[j] = rSHurestics[j + 1];
                    randStates[j] = randStates[j + 1];
                    rSHurestics[j + 1] = temp;
                    randStates[j + 1] = temp1;
                }
            }
        }
        System.out.println("the 20 first random State is:");
        for (int i = 0; i < 20; i++) {
            statePrinter(randStates[i]);
            System.out.println("hurestic:"+rSHurestics[i]);
            
        }
        System.out.println("the state that selected with simulated Annealeing is:\n\n\n");
        statePrinter(this.randStates[19]);
        System.out.println("hurestic:"+rSHurestics[19]);
    }
    
    
     public static void main(String[] args) {
       nqSimulatedAnnealing h=new nqSimulatedAnnealing();
       Scanner s=new Scanner(System.in);
       System.out.println("please enter number of queens !");
       h.nqueen(s.nextInt());
    }
}




