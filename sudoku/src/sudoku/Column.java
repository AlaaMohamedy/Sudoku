
package sudoku;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Column implements Runnable {
    private int [][]board ;
   private int num;
   private int col;
   public static boolean c ;
   public Column(int [][] board , int num , int col){
       this.board = board;
       this.num = num;
       this.col = col;
   }
   @Override
   public void run(){
        c = check_column();
   }
   private  boolean check_column(){
       
       for(int i=0 ;i<9 ;i++){
           if(board[i][col] == num ){
               return true;
           }
       }
       return false;
   }
}
