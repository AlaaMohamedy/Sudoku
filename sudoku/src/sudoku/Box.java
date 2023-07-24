
package sudoku;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Box implements Runnable{
    private int [][]board ;
   private int num;
   private int col;
   private int row;
   public static boolean c ;
   public Box(int [][] board , int num ,int row, int col){
       this.board = board;
       this.num = num;
       this.col = col;
       this.row = row;

   }
   @Override
   public void run(){
        c = check_box();
   }
   private  boolean check_box(){
       int boxRow = row - row % 3;
       int boxColumn = col - col % 3;
       for(int i= boxRow ;i< boxRow +3 ;i++){
            for(int j= boxColumn ;j< boxColumn +3 ;j++){
                if(board[i][j] == num ){
                   return true;
                }
           }
       }
       return false;
   }
    
}
