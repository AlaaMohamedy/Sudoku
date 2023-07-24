
package sudoku;

import static sudoku.Row.c;

public class ValidRow  implements Runnable{
   private int [][]board ;
   private int row;
   private int col;
   
   public ValidRow(int [][]board,int row,int col) {
      this.board = board;
      this.row = row;
      this.col = col;
    }
    @Override
   public void run(){
       if (col != 0 || row > 8) {
           System.out.println("invalid row or column1");				
           return;
       }
       boolean[] validityArray = new boolean[9];
       int i;
       for(i = 0; i < 9; i++) {
           int num = board[row][i];
           if (num < 1 || num > 9 || validityArray[num - 1]) {
               return;
           }
           else if (!validityArray[num - 1]) {
            validityArray[num - 1] = true;
           }
       }
       int x= 9+row;
       Sudoku.setValid(x, true);
 
   }
}
