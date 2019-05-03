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
public class localBeamMapColoring {
    /**
     * به صورت پیشفرض:
     * k=20;
     * الگوریتم پرتو محلی :
     * مراحل:
     * 1-ایجاد بیست حالت تصادفی اولیه
     * 2-ایجاد بیست حالت بعدی هر یک از بیست حال اولیه 
     * 3-انتخاب بیست تا از بهترین ها 
     * 4-در صورت پیدا نشدن جواب به مرحله دو میرود
     * 
     */
  int[] hurestics = new int[20];
  int[] hurestics2 = new int[20];
  int[][] randStates = new int[20][];
  int[][] nextStates = new int[20][];
  char[] vertexes;




    public void mapColoring(int[][] m, int numOfColor) {
        
        
        //an array for getting vertex titles
        vertexes = new char[m.length];
        ///initial titles
        char[] ch = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        //giving vertexes title
        for (int i = 0; i < m.length; i++) {
            vertexes[i] = ch[i];
        }
        //checking graph coloring possibality 
        ///if sum of one's of m matrix divide number of vertexes mines one equal or less than number of color there is solution else there is no one
        if (possibality(m, numOfColor)) {
            for (int i = 0; i < 20; i++) {
                int[] vAllocatedColor = randomColoring(numOfColor, vertexes);
                int h = huresticCalculator(vAllocatedColor, m);
                randStates[i] = vAllocatedColor;
                hurestics[i] = h;
            }
         localBeam();
            ///////introducing the next 20 states//////////
           while(goalFound(randStates,m,numOfColor,hurestics)==false){
                for (int i = 0; i < 20; i++) {
                int[] vAllocatedColor = randomColoring(numOfColor, vertexes);
                int h = huresticCalculator(vAllocatedColor, m);
                nextStates[i] = vAllocatedColor;
                hurestics2[i] = h;
            }
                //////saving 20 of bests///////
                for (int i = 0; i < 10; i++) {
               randStates[i]=randStates[i+10]; 
               hurestics[i]=hurestics[i+10];
            }
            for (int i = 11; i < 20; i++) {
               randStates[i]=nextStates[i+10]; 
               hurestics[i]=hurestics2[i+10];
            }
                
           }
           
           
        } else {
            System.out.println("there is no sulotion");
        }
    }


    private void localBeam() {
        ////////sorting all 20 states////////
        int temp;
        int[] temp1;
        for (int i = 0; i < 20 - 1; i++) {
            for (int j = 0; j < 20 - 1 - i; j++) {
                if (hurestics[j] > hurestics[j + 1]) {
                    temp = hurestics[j];
                    temp1 = randStates[j];
                    hurestics[j] = hurestics[j + 1];
                    randStates[j] = randStates[j + 1];
                    hurestics[j + 1] = temp;
                    randStates[j + 1] = temp1;
                }
            }
        
            for (i = 0; i < 20 - 1; i++) {
            for (int j = 0; j < 20 - 1 - i; j++) {
                if (hurestics[j] > hurestics[j + 1]) {
                    temp = hurestics[j];
                    temp1 = randStates[j];
                    hurestics[j] = hurestics[j + 1];
                    randStates[j] = randStates[j + 1];
                    hurestics[j + 1] = temp;
                    randStates[j + 1] = temp1;
                }
            }
        }

        /////printing 20 random first state////
        System.out.println("20 first state is:");
        for (int p = 0; p < 20; p++) {
            statePrinter(randStates[p], vertexes);
            System.out.println("hurestic is=" + hurestics[p]);
        }
       

    }

    }
    private int[] randomColoring(int numOfColor, char[] vertexes) {
        int[] vAllocatedColor = new int[vertexes.length];
        Random r = new Random();
        for (int i = 0; i < vertexes.length; i++) {
            vAllocatedColor[i] = Math.abs(r.nextInt() % numOfColor);
        }

        return vAllocatedColor;
    }


    private boolean possibality(int[][] m, int numOfColor) {
        int sum = 0;
        boolean ret = false;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
                    sum += 1;
                }
            }
        }
        if (sum / (m.length - 1) <= numOfColor) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }


    private int huresticCalculator(int[] vAllocatedColor, int[][] m) {
        int h = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
                    if (vAllocatedColor[i] == vAllocatedColor[j]) {
                        h--;
                    } else {
                        h++;
                    }
                }
            }
        }
        return h;
    }


    private void statePrinter(int[] vAllocatedColor, char[] vertexes) {
        for (int i = 0; i < vAllocatedColor.length; i++) {
            System.out.println(vertexes[i] + ":" + vAllocatedColor[i]);

        }
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
        
        for(int o=0;o<20;o++){
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
        localBeamMapColoring m=new localBeamMapColoring();
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

