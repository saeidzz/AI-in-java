/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aiproject.CSP;


import java.util.Random;


/**
 *
 * @author saeid
 */
public class mapColoringSimulatedAnnealing {


    /**
     * روش کار اینگونه است که ابتدا بیست حالت اولیه رندنم تولید شده و هیورستیک
     * های آنها نیز محاسبه میشود و هیورستیک ها در یک آرایه نگه داری میشود و سپس
     * تعریف الگوریتم شبیه سازی حرارت روی این آین آرایه اندیس ها پیاده سازی
     * میشود یعنی ابتدا آرایه هیورستیک به صورت سعودی مرتب میشود و تابع رندم
     * اندیس ها با فاصله تولید میکند و آرام آرام فاصله اندیس ها کم میشود تا
     * زمانی که در بهترین حالت متوقف شود توجه شود بهترین حالت در این جا پاسخ
     * الگوریتم است.
     *
     */
    
    int[] hurestics = new int[20];


    int[][] randStates = new int[20][];


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
            simulatedAnnealing();
        } else {
            System.out.println("there is no sulotion");
        }
    }


    private void simulatedAnnealing() {
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
        }

        /////printing 20 random first state////
        System.out.println("20 first state is:");
        for (int i = 0; i < 20; i++) {
            statePrinter(randStates[i], vertexes);
            System.out.println("hurestic is=" + hurestics[i]);
        }
        System.out.println("the state that selected by simulated annealing is :");
        System.out.println("\n\n\n\n");
        
        statePrinter(randStates[19], vertexes);
        System.out.println("hurestic is=" + hurestics[19]);
        
        

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


    public static void main(String[] args) {
        mapColoringSimulatedAnnealing m = new mapColoringSimulatedAnnealing();
        int[][] p = new int[][]{
            {0, 1, 1, 1},
            {1, 0, 1, 1}, 
            {1, 1, 0, 1}, 
            {1, 1, 1, 0}};
        m.mapColoring(p, 4);
    }


}

