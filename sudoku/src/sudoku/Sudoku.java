
package sudoku;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sudoku.Gui.board;


public class Sudoku {

    private static final int size = 9;
    private static final boolean[] valid=new boolean[27];
    private static final int [][] board ={
            {1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
        };
    public static void main(String[] args) {
           Gui frame = new Gui();
           frame.setVisible(true);
   }
   public static boolean checkSolution(int [][] board){
       Runnable []threads;
       threads = new Runnable[27];
       int threadIndex =0;
       for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {						
	        if (i%3 == 0 && j%3 == 0) {
                    threads[threadIndex ++] = new ValidBox(board,i,j);
                }
                if (i == 0){
                    threads[threadIndex ++] = new ValidColumn(board,i,j);
                }
                
                if (j == 0){
                    threads[threadIndex ++] = new ValidRow(board,i,j);
                }
           }
       }
       startThreads(threads);
       for(int i = 0; i < valid.length; i++){
           if(!valid[i]){
              return false;
           }
       }
       for (int i = 0; i < 27; i++) {
           valid[i]=false;
       }
       return true;
   }
   public static boolean solve(int [][] board) {
       Runnable [] t = new Runnable[3];
       ExecutorService exe = Executors.newFixedThreadPool(3);
       for(int row =0; row < size ; row++){
           for(int column = 0; column <size ; column++){
                if (board [row][column] == 0){
                    for(int x = 1; x <= size ; x++){
                        t[0] = new Row(board,x,row);
                        t[1] = new Column(board,x,column);
                        t[2] = new Box(board,x,row, column);
                        startThreads (t);
                        if(!Row.c && !Column.c && !Box.c ){
                            board[row][column] = x;
                            if(solve(board)){
                                return true;}
                            else{
                                board[row][column] = 0;}
                        }
                    }
                    return false;
                }
           }
       }
        Gui.setBoard(board);
        return true;
    }
    public static void startThreads (Runnable [] threads){
        //        Thread[] threads_2 = new Thread[3];
//        for(int i = 0; i < threads.length; i++) {
//            threads_2[i] = new Thread(threads[i]);
//        }
//        for (int i = 0; i < threads.length; i++) {
//			threads_2[i].start();
//		}
//		
//		// Wait for all threads to finish
//		for (int i = 0; i < threads.length; i++) {
//			try {
//				threads_2[i].join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//        
        
       ExecutorService exe = Executors.newFixedThreadPool(27);
       for(int i = 0; i < threads.length; i++) {
           exe.execute(threads[i]);
       }
      exe.shutdown();
      
      try{
           exe.awaitTermination(10, TimeUnit.MILLISECONDS);
      }catch (InterruptedException ex) {
            Logger.getLogger(Sudoku.class.getName()).log(Level.SEVERE, null, ex);
      } 
   }
   public static void printBoard(int [][] board){
       for(int row = 0 ; row < size ; row++){
           if (row % 3 == 0 && row != 0){
               System.out.println("--------------");
           }
           for(int column = 0 ; column < size ; column++){
                if (column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
           }
           System.out.println();
       }
   }
   public static void setValid(int index , boolean val){
       
       valid[index] = val;
   }
}
