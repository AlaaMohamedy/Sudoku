
package sudoku;


public class ValidBox implements Runnable{
   private int [][]board ;
   private int row;
   private int col;
   
   public ValidBox(int [][]board,int row,int col) {
      this.board = board;
      this.row = row;
      this.col = col;
    }
    @Override
   public void run(){
       if (row > 6 || row % 3 != 0 || col > 6 || col % 3 != 0) {
           System.out.println("invalid row or column3");				
           return;
       }
       boolean[] validityArray = new boolean[9];
       int i;
       for(i = row; i < row + 3; i++) {
           for (int j = col; j < col + 3; j++) {
               int num = board[i][j];
               if (num < 1 || num > 9 || validityArray[num - 1]) {
                   return;
               }
               else if (!validityArray[num - 1]) {
                   validityArray[num - 1] = true;
               }
           }
       }
     int x= row + col/3;
     Sudoku.setValid(x, true);
   }  
}
