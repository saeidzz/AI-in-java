/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aiproject.CSP;


import com.sun.xml.internal.stream.Entity;
import java.util.Random;
import java.util.Scanner;



/**
 *
 * @author saeid
 */
public class mapColoringHill {
    int[] neighbersHu=new int[3];
    public void mapColoring(int[][] m,int numOfColor){
        //an array for getting vertex titles
        char[] vertexes=new char[m.length];
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
         
         statePrinter(vAllocatedColor0,vertexes) ;
         System.out.println("first state hurestic is:"+h0);
         while(true){
          int[] vAllocatedColor1=randomColoring(numOfColor,vertexes);
          int[] vAllocatedColor2=randomColoring(numOfColor,vertexes);
          int[] vAllocatedColor3=randomColoring(numOfColor,vertexes);
          int h1= huresticCalculator(vAllocatedColor1,m);
          int h2= huresticCalculator(vAllocatedColor2,m);
          int h3= huresticCalculator(vAllocatedColor3,m); 
          if(h0>h1){
            statePrinter(vAllocatedColor0,vertexes) ;
              System.out.println("this state hurestic:"+h0);
              System.out.println("the next state hurestic:"+h1);
              break;
          }else{
             if(h1>h2){
                 statePrinter(vAllocatedColor1,vertexes) ;
                 System.out.println("this state hurestic:"+h1);
                 System.out.println("the next state hurestic:"+h2);

              break;
             }else{
                 if(h2>h3){
                    statePrinter(vAllocatedColor2,vertexes) ;
                    System.out.println("this state hurestic:"+h2);
                    System.out.println("the next state hurestic:"+h3);
              break;

                 }
                 else{
                     vAllocatedColor0=vAllocatedColor3;
                     h0=h3;
                     continue;
                 }
             }
              
          }
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

 public static void main(String[] args) {
        mapColoringHill m=new mapColoringHill();
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

