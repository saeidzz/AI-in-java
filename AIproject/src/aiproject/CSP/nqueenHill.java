/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aiproject.CSP;


import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.abs;



/**
 *
 * @author saeid
 */
public class nqueenHill {
    private int[]  nqHurestics=new int[3]; 
    

    public void nqueen(int numberOfQueen){
       Random r=new Random();
       /////neiber states/////
        int[][] rt=new int[numberOfQueen][numberOfQueen];
        int[][] rt1=new int[numberOfQueen][numberOfQueen];
        int[][] rt2=new int[numberOfQueen][numberOfQueen];
        int[][] rt3=new int[numberOfQueen][numberOfQueen];
        makeItZero(rt,numberOfQueen);
        makeItZero(rt1,numberOfQueen);
        makeItZero(rt2,numberOfQueen);
        makeItZero(rt3,numberOfQueen);
        //first state that is random 
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        int firstStateHurestic=hillClimpNqueenHuresticCalculater(rt);
       
        //printing random first state
        statePrinter(rt);
        System.out.println("first state hurestic is:"+firstStateHurestic);
        //////////cheking neighbers and implementing hill climping///////////
        while(true){
        rt1=randomStateIntroducer(numberOfQueen, r, rt);
        nqHurestics[0]= hillClimpNqueenHuresticCalculater(rt);
        if(nqHurestics[0]>firstStateHurestic){
            
        rt2=randomStateIntroducer( numberOfQueen, r, rt1);
        nqHurestics[1]= hillClimpNqueenHuresticCalculater(rt1);
        if(nqHurestics[1]>nqHurestics[0]){
        rt3=randomStateIntroducer(numberOfQueen, r, rt2);
        nqHurestics[2]= hillClimpNqueenHuresticCalculater(rt3);
        if(nqHurestics[2]>nqHurestics[1]){
          continue;
        }else{
             System.out.println("//////the best state ////////");
            statePrinter(rt2);
            System.out.println("hurestic is:"+nqHurestics[1]);
            System.out.println("next Stete hurestic:"+nqHurestics[2]);
            break;
        }
        }else{
            System.out.println("//////the best state ////////");
            statePrinter(rt1);
            System.out.println("hurestic is:"+nqHurestics[0]);
            System.out.println("next Stete hurestic:"+nqHurestics[1]);

            break;
        }
        }else{
            
            System.out.println("//////the best state ////////");
            statePrinter(rt);
            System.out.println("hurestic is:"+firstStateHurestic);
            System.out.println("next Stete hurestic:"+nqHurestics[0]);

            break;
        }
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
    private int hillClimpNqueenHuresticCalculater(int[][] rt) {
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
    public static void main(String[] args) {
        nqueenHill h=new nqueenHill();
       Scanner s=new Scanner(System.in);
       System.out.println("please enter number of queens !");
       h.nqueen(s.nextInt());
    }
   
}


