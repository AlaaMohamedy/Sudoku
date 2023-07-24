
package sudoku;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Gui extends JFrame implements ActionListener {
    public JPanel panel;
    public  static JTextField [][] fields = new JTextField [9][9];
    public JButton button1 , button2 ,button3;
    public Sudoku sudoku;
    public static int [][] board = new int[9][9];
    int matrix [][]= new int [9][9];
    int a=0,val,i,j,x,y;
    public  static boolean c1 =true;
    
    public Gui(){
        setSize(600,650);
        setTitle("sudoku");
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       componentes();
    }
    public void componentes(){
        editPanel();
        panelTitle();
        textArea();
        button();
    }
    public void editPanel(){
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        this.getContentPane().add(panel);
    }
    public void panelTitle(){
        panel.setLayout(null);
        ImageIcon image = new ImageIcon("New folder/ICON.PNG");
        JLabel label = new JLabel();
        label.setBounds(100,50,400,75);
        label.setIcon(new ImageIcon(image.getImage().getScaledInstance(400, 75, Image.SCALE_AREA_AVERAGING)));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        panel.add(label);
    }
    public void textArea(){
        y=100;
        for(i=0;i<9;i++){
            x=125;
            if(i == 3 || i==6){
                y+=50;
            }else{
                y+=35;
            }
            for(j=0;j<9;j++){
               fields[i][j] = new JTextField(null);
               if(((i==0)||(i==1)||(i==2)||(i==6)||(i==7)||(i==8))&&((j==3)||(j==4)||(j==5))||((j==0)||(j==1)||(j==2)||(j==6)||((j==7)||(j==8)))){
                  fields[i][j].setBounds(x,y,30,30);
                  fields[i][j].setEditable(true);
                  fields[i][j].setCaretColor(Color.WHITE);
                  //limit();
                  fields[i][j].setForeground(Color.WHITE);
                  fields[i][j].setBackground(Color.GRAY);
                  panel.add(fields[i][j]);
               }else{
                   fields[i][j].setBounds(x,y,30,30);
                   fields[i][j].setEditable(true);
                   panel.add(fields[i][j]);
               }
               if( j == 2 || j == 5){
                   x+=50;
               }else{
                   x+=35;
               }
            }
        }
    }
    public void button(){
        button1 = new JButton("Check");
        button1.setBounds(40,520,150,20);
        panel.add(button1);
        button2 = new JButton("Solve");
        button2.setBounds(225,520,150,20);
        panel.add(button2);
        button3 = new JButton("Clear");
        button3.setBounds(410,520,150,20);
        panel.add(button3);
        actions();
    }
    public static int [][] guiToSudokuBoard(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (!fields[i][j].getText().equals("")){
                    board[i][j] = Integer.parseInt(fields[i][j].getText());
                }
                else{
                    board[i][j] =0;
                }
            }
        }
        return board;
    }
    public  static void clearBoard(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                fields[i][j].setText("");
                board[i][j]=0;
            }
        }
    }
    public static void setBoard(int [][] board){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                fields[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }
    
    public void actions(){
        ActionListener solve = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
                   //checkTextField();
                   Sudoku.solve(guiToSudokuBoard());
           } 
        };
        button2.addActionListener(solve);
        ActionListener check = new ActionListener(){
            @Override
           public void actionPerformed(ActionEvent e){
               if(Sudoku.checkSolution(guiToSudokuBoard()))
                   JOptionPane.showMessageDialog(null,"sudoku solutiion is Valid");
               else
                  JOptionPane.showMessageDialog(null,"sudoku solutiion is Invalid");
           } 
        };
        button1.addActionListener(check);
        ActionListener clear = new ActionListener(){
            @Override
           public void actionPerformed(ActionEvent e){
               guiToSudokuBoard();
               clearBoard();
           } 
        };
        button3.addActionListener(clear);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
