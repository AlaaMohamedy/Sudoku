
package sudoku;

public class ValidColumn implements Runnable{
   private int [][]board ;
   private int row;
   private int col;
   
   public ValidColumn(int [][]board,int row,int col) {
      this.board = board;
      this.row = row;
      this.col = col;
    }
    @Override
   public void run(){
       if (row != 0 || col > 8) {
           System.out.println("invalid row or column2");				
           return;
       }
       boolean[] validityArray = new boolean[9];
       int i;
       for(i = 0; i < 9; i++) {
           int num = board[i][col];
           if (num < 1 || num > 9 || validityArray[num - 1]) {
               return;
           }
           else if (!validityArray[num - 1]) {
            validityArray[num - 1] = true;
           }
       }
       int x= 18+col;
     Sudoku.setValid(x, true);
   }
}
