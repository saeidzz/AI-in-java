/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aiproject.CSP;


import java.util.Random;
import static java.lang.Math.abs;
import java.util.Scanner;



/**
 *
 * @author saeid
 */
public class localBeamNqueen {
     public int[][][] randStates;
     public int[][][] nextrands;
     
    public int[]  rSHurestics;   
    public int[]  nextHurestics;   

    public void nqueen(int numberOfQueen){
        randStates=new int[20][numberOfQueen][numberOfQueen];
        nextrands=new int[20][numberOfQueen][numberOfQueen];
        rSHurestics=new int[20];
        nextHurestics=new int[20];
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
        System.out.println("first 20 random state :");
        sort(randStates, rSHurestics);
        randsPrinter(randStates, rSHurestics);
        while(goalIsFound(randStates,rSHurestics)==false){
        for(int o=0;o<20;o++){     
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        firstStateHurestic=NqueenHuresticCalculater(rt); 
        nextrands[o]=rt;
        nextHurestics[o]=firstStateHurestic;
        rt=randomStateIntroducer( numberOfQueen, r, rt);
        firstStateHurestic=NqueenHuresticCalculater(rt);    
        randStates[o]=rt;
        rSHurestics[o]=firstStateHurestic;
     
        }
        sort(nextrands, nextHurestics);
        sort(randStates,rSHurestics);
        ////saving 20 of best states/////
            for (int i = 0; i < 10; i++) {
               randStates[i]=randStates[i+10]; 
               rSHurestics[i]=rSHurestics[i+10];
            }
            for (int i = 11; i < 20; i++) {
               randStates[i]=nextrands[i]; 
               rSHurestics[i]=nextHurestics[i];
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
    private static void statePrinter(int[][] rt) {
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
    }

    private void randsPrinter(int[][][] randStates1, int[] rSHurestics1) {
        for (int i = 0; i < 20; i++) {
            statePrinter(randStates1[i]);
            System.out.println("hurestic:" + rSHurestics1[i]);
        }
       
        
    }
    
    
   

    private boolean goalIsFound(int[][][] randStates, int[] rSHurestics) {
     boolean ret=false;
     int goalStateHurestic=0;
     NQueenProblem k= new NQueenProblem(randStates.length);
        k.solveNQ();
     goalStateHurestic=NqueenHuresticCalculater(k.board);
        for (int i = 0; i < 20; i++) {
            if(rSHurestics[i]==goalStateHurestic){
                ret=true;
                System.out.println("goal is found:");
                statePrinter(k.board);
                System.out.println("and the goal state huresticis:"+goalStateHurestic);
                break;
            }else{
                ret=false;
                System.out.println("goal yet not found !!");
            }
        }
     return ret;
            
    }
    
    
      public static void main(String[] args) {
       localBeamNqueen h=new localBeamNqueen();
       Scanner s=new Scanner(System.in);
       System.out.println("please enter number of queens !");
       h.nqueen(s.nextInt());
    }


}
 class NQueenProblem

{   
     public int[][] board;
     public int N;
 public NQueenProblem(int N){
     this.N=N;
 }
 
  boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
 
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
 
        for (i=row, j=col; i>=0 && j>=0; i--, j--)
            if (board[i][j] == 1)
                return false;
 
        for (i=row, j=col; j>=0 && i<N; i++, j--)
            if (board[i][j] == 1)
                return false;
 
        return true;
    }
 
    
    boolean solveNQUtil(int board[][], int col)
    {
        
        if (col >= N)
            return true;
 
        for (int i = 0; i < N; i++)
        {
           
            if (isSafe(board, i, col))
            {
               board[i][col] = 1;
 
             if (solveNQUtil(board, col + 1) == true)
                    return true;
 
          
                board[i][col] = 0; // 
            }
        }
 
        return false;
    }
 

  public  boolean solveNQ()
    {
        board=new int[N][N];
        for(int i=0;i<N;i++){
            for (int j = 0; j < N; j++) {
                board[i][j]=0;
            }
 
        }
 
        if (solveNQUtil(board, 0) == false)
        {
            System.out.print("Solution does not exist");
            return false;
        }
        return true;
    }
}