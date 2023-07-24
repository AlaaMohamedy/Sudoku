
package sudoku;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Row implements Runnable {
   private int [][]board ;
   private int num;
   private int row;
   public static boolean c ;
   public Row(int [][] board , int num , int row){
       this.board = board;
       this.num = num;
       this.row = row;
   }
   @Override
   public void run(){
        c = check_row();
   }
   private  boolean check_row(){
       for(int i=0 ;i<9 ;i++){
           if(board[row][i] == num ){
               return true;
           }
       }
       return false;
   }
}
