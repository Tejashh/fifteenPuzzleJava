//Name: Tejash Contractor
//CS 342
//Project 2: Create Puzzle
//Decription: Fiften Puzzle which has Menu,Help,Reset,Mix,Undo,UndoAll,
//Auto menu on bar. You have to set up puzzle so that it would be slove.
//you can use undo and undo ALl button in which undo all is not working properly.
//Reset is when the puzzle is come with its initial solve position.

// Different Library uses
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
//import java.util.Queue;

public class Grid extends JFrame implements ActionListener{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    protected static final JButton[][] Buttons = null;
    
    //JButton button array which is created to store array
    public JButton buttons[];
    
    //Declare newNameOfButton and solution1
    public String[] newNameOfButton,solution1;
    
    //String array which is the array of string used to name buttons
    public final String nameOfButton[] = {"1","2","3","4","5","6","7","8","9",
    "10","11","12","13","14","15"};
    
    //inside array to help impliment the code
    public int[] arr,arr1,arr2,arr3;
    
    //solution array to compare the array to check if it solve
    public String[] solution = {"1","2","3","4","5","6","7","8","9",
    "10","11","12","13","14","15"};
    //container
    private Container container;
    
    //Grid layout
    private GridLayout grid1;
    
    //counter
    public int counter = 0;
    
    //to see if its soluable
    public boolean isTrue = true;
    
    //Menu initialization
    public JMenu menu, help,reset,mix,undo,undoALL,auto;
    
    //button x
    public JButton x;
    
    //counters
    public int count = 0, count1 = 0;
    
    //flags
    public int buttonPos = -1;
    public int blankPos = -1;
    
    //initialization of stack
    public Stack<Integer> s1,s2;
    
    //Timer
    public Timer timer;
    
    //Queue
    //private Queue<Pair> myQueue;
    
    //start of Grid Constructor
    public Grid()
    {
        //header for the container
        super("Fifteen Puzzle");
        
        /*************************** Menu **********************************/
        menu = new JMenu("Menu");    //Setting up file menu
        menu.setMnemonic('M');
        
        JMenuItem about = new JMenuItem("About");    //Setting up about menu
        about.setMnemonic('A');
        
        menu.add(about);    //adding about into file menu
        about.addActionListener(
                                
                                new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // writing dialouges for about menu which will show up when user click on about menu
                JOptionPane.showMessageDialog( Grid.this,
                                              "Author: Tejash Contractor \n"
                                              + "Starting Date: sepember 26, 2017\n"+
                                              "The 2nd programming assignment for CS 342",
                                              "About", JOptionPane.PLAIN_MESSAGE );
        				}
        }
        );
        
        
        JMenuItem exit = new JMenuItem("Quit");
        exit.setMnemonic('x');
        menu.add(exit);
        
        
        exit.addActionListener(
                               
                               new ActionListener() {
            
            @Override
            //display quit into the file menu
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);      // To exit the puzzle
            }
        }      
        );
        
        
        JMenuBar bar = new JMenuBar();    //Creating menu bar
        
        //attaching menu bar to MenuTest window
        setJMenuBar(bar);
        bar.add(menu);	//add menu to bar
        
        /************************************************************************************************************************/
        /******************************* Help ***********************************************************************************/
        help = new JMenu("Help");
        help.setMnemonic('H');
        
        JMenuItem info = new JMenuItem("Help Info");    //Setting up about menu
        info.setMnemonic('i');
        
        help.add(info);    //adding about into file menu
        
        info.addActionListener(
                               
                               new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // writing dialouges for about menu which will show up when user click on about menu
                JOptionPane.showMessageDialog( Grid.this,
                                              "Basics or Puzzle:\n"
                                              + "1. its Fifteen Puzzles which have 1 to 15 numbers initially"
                                              + "placed smallest to largest along with blank box\n"
                                              + "2. Click or randomize to randomly initialize soluable puzzle\n"
                                              + "3. Click on about in menu file to know information about author\n"
                                              + "4. Use undo to undo the move\n"
                                              + "5. Click on quit to exit the puzzle\n", "Help Info", JOptionPane.PLAIN_MESSAGE );
            }
        }
                               );
        
        bar.add(help);	//add help to bar
        
        /******************************************************************************************************************/
        //create 4x4 grids
        grid1 = new GridLayout(4,4);
        
        container = getContentPane();
        container.setLayout(grid1);
        
        //create and add button
        buttons = new JButton[nameOfButton.length+1];
        
        //user of newArray Function which shuffle the nameOfButton array's first 15 element.
        newNameOfButton = newArray( buttons,  arr,  arr1,  nameOfButton);
        
        // Memory Allocation for array 2 using the length of the neewNameOfButton array
        arr2 = new int[newNameOfButton.length];
        
        //Count how many number of total inversions are there
        counter = countTheNumberOfPermutationInversions(newNameOfButton, arr2);
        
        // Memory Allocation for arr3 using the length of the newNameOfButton+1
        arr3 = new int[newNameOfButton.length+1];
        
        //add the integer of array which has parseInt of newNameOfButton array
        for(int i = 0; i < newNameOfButton.length; i++)
        {
            arr3[i] = Integer.parseInt(newNameOfButton[i]);
            //System.out.println(arr3[i]);
        }
        
        // while loop to check if the inversion count is odd or even.
        // If inversion count is even then thats it thats your random grid
        // If not then it will repeat the process until it found the even count of inversion
        
        while(isTrue == true)
        {
            if(counter%2 == 0)	//if even
            {
                for(int i = 0; i < buttons.length-1; i++)
                {
                    
                    buttons[i] = new JButton(newNameOfButton[i]);    //crating 16 buttons
                    buttons[i].addActionListener(this);
                    container.add(buttons[i]);	//add buttons to container
                }
                
                //added 16th element as blank space.
                buttons[15] =new JButton(" ");	//create blank button
                buttons[15].addActionListener(this);
                container.add(buttons[15]);	//add blank button to the 15 positons
                isTrue = false;
            }
            else
            {
                newNameOfButton = newArray( buttons,  arr,  arr1,  nameOfButton);
                counter = countTheNumberOfPermutationInversions(newNameOfButton, arr2);
            }
            
        }
        
        /****************************************************** Reset******************************************************************/
        reset = new JMenu("Reset");
        reset.setMnemonic('r');
        JMenuItem rb = new JMenuItem("Reset button");    //Setting up about menu
        rb.setMnemonic('r');
        reset.add(rb);    //adding about into file menu
        rb.addActionListener(
                             new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                s1 = new Stack<Integer>();
                s2 = new Stack<Integer>();
                count = 0;
                for ( int count = 0; count < nameOfButton.length; count++ )
                {
                    buttons[count].setText(nameOfButton[count]);
                }
                buttons[15] .setText(" ");
            } //end of the actionPerformed
        }//end of the ActionListener
                             );
        bar.add(reset);	//add reset to bar
        
        /**************************************************************************************************************************************/
        
        /******************************************************* Mix ***************************************************************************/
        mix = new JMenu("Mix");
        mix.setMnemonic('z');
        JMenuItem mb = new JMenuItem("Mix button");    //Setting up about menu
        rb.setMnemonic('y');
        mix.add(mb);    //adding about into file menu
        mb.addActionListener(
                             new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1 = new Stack<Integer>();
                s2 = new Stack<Integer>();
                count = 0;
                for ( int count = 0; count < nameOfButton.length; count++ )
                {
                    buttons[count].setText(nameOfButton[count]);
                }
                buttons[15] .setText(" ");
            }//end of the actionPerformed
        }//end of the action listener
                             );
        bar.add(mix);	//add mix to bar
        
        /****************************************************************************************************************************************/
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
        
        /****************************************************** Undo ****************************************************************************/
        undo = new JMenu("Undo");
        undo.setMnemonic('U');
        
        JMenuItem ub = new JMenuItem("Undo button");    //Setting up about menu
        ub.setMnemonic('u');
        
        undo.add(ub);    //adding about into file menu
        
        ub.addActionListener(
                             new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(s2.elementAt(0) == 15 && s2.size() == 1)
                    return;
                
                buttons[s2.pop()].setText(buttons[s2.peek()].getText());
                buttons[s2.peek()].setText(" ");
                System.out.println(s2);
                count--;
                
                
            } //end of the actionPerformed
        }//end of the actionlistener
                             );
        
        
        
        bar.add(undo);	//add undo to bar
        
        /**************************************************************************************************************************************/
        
       
        
        setSize(400,400);
        setVisible(true);
    } //end of the Grid Constructor
    
    
    //Start of the main method
    public static void main(String[] args) {
        Grid application = new Grid();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }//emd of the main method
    
    
    
    
    //start of the RandomizeArray method to randomize array
    public static int[] RandomizeArray(int[] arr2)
    {
        Random rand = new Random(); //Random
        
        for(int i = 0; i < arr2.length; i++)
        {
            int randomPosition = rand.nextInt(arr2.length);
            int temp = arr2[i];
            arr2[i] = arr2[randomPosition];
            arr2[randomPosition] = temp;
        }
        
        return arr2;
    }   //end of the randomizeArray method
    
    //Start of the newArray method
    public static String[] newArray( JButton[] buttons2, int[] arr, int[] arr1, String[] nameOfButton)
    {
        
        // Memory allocation for the string array name newNameOfArray
        String[] newNameOfButton = new String[buttons2.length-1];
        //System.out.println(random);
        
        arr = new int[buttons2.length-1];
        for(int i = 0 ; i <buttons2.length-1; i++)
        {
            arr[i] = i;
        }
        
        //Use of RandomizeArray method to randomize the integer array
        arr1 = RandomizeArray(arr);
        
        
        newNameOfButton = new String[buttons2.length-1];
        
        for(int i = 0; i < buttons2.length-1; i++)
        {
            newNameOfButton[i] = nameOfButton[arr1[i]];
            //System.out.println(newNameOfButton[i]);
        }
        
        return newNameOfButton;
    }   //End of the newArray method
    
    //Start of countTHeNumberPermutationInversions method to count the invertion to make sure its solable or not
    public static int countTheNumberOfPermutationInversions(String[] newNameOfButton, int[] arr2)
    {
        int counter = 0;
        for(int i = 0; i < newNameOfButton.length; i++)
        {
            arr2[i] = Integer.parseInt(newNameOfButton[i]);
        }
        
        for(int i = 0; i < arr2.length; i++)
        {
            for(int j = i+1; j < arr2.length; j++)
            {
                if(arr2[i] > arr2[j])
                {
                    counter++;
                    //System.out.println("Inversion count: " + counter);
                }
            }
        }
        
        System.out.println("->COUNTER" + counter);
        return counter;
    }   //end of the countTheNumberOfPermutationInversions method
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        // TODO Auto-generated method stub
        //JOptionPane.showMessageDialog(null, "You pressed: " + e.getActionCommand());
        //System.out.println(e.getActionCommand());
        
        x = (JButton) e.getSource();
        
        //System.out.println(x);
        for(int i = 0; i < buttons.length; i++)
        {
            if(x.equals(buttons[i]))
            {
                buttonPos = i;
            }
        }
        
        for(int i = 0; i < buttons.length; i++)
        {
            if(buttons[i].getText() == " ")
            {
                blankPos = i;
            }
        }
        
        //System.out.println("1. buttonPos : " + buttonPos);
        //System.out.println("1. blankPos: " + blankPos);
        
        
        if((buttonPos == blankPos - 4  || buttonPos == blankPos - 1) &&
           blankPos == 15)
        {
            s1.push(15);
            s2.push(15);
        }
        if((buttonPos == blankPos - 4 || buttonPos == blankPos + 4 || buttonPos == blankPos + 1
            || buttonPos == blankPos - 1) &&
           !((blankPos == buttonPos + 1) && buttonPos == 11) &&
           !((blankPos == buttonPos + 1) && buttonPos == 7)&&
           !((blankPos == buttonPos + 1) && buttonPos == 3) &&
           
           !((blankPos == buttonPos - 1) && blankPos == 11) &&
           !((blankPos == buttonPos - 1) && blankPos == 7) &&
           !((blankPos == buttonPos - 1) && blankPos == 3))
        {
            
            buttons[blankPos].setText(buttons[buttonPos].getText());
            buttons[buttonPos].setText(" ");
            s1.push(buttonPos);
            s2.push(buttonPos);
            
            //s.push(blankPos);
            count++;
            
        }
        
        
        //System.out.println("2. Position : " + buttons[buttonPos].getText());
        ////System.out.println("2. Blank Position: " + buttons[blankPos].getText());
        if(count % 5 == 0 )
        {
            JOptionPane.showConfirmDialog(null, "Inversion Count: " + count);
        }
        
        //System.out.print("Button: " );
        
        solution1 = new String[16];
        for(int i = 0; i < solution.length+1; i++)
        {
            solution1[i] = buttons[i].getText();
        }
        
        //Checking that f it is solved or not
        if(solution[0].equals(solution1[0]) && solution[1].equals(solution1[1]) &&
           solution[2].equals(solution1[2]) && solution[3].equals(solution1[3])&&
           solution[4].equals(solution1[4]) && solution[5].equals(solution1[5])&&
           solution[6].equals(solution1[6]) && solution[7].equals(solution1[7])&&
           solution[8].equals(solution1[8]) && solution[9].equals(solution1[9])&&
           solution[10].equals(solution1[10]) && solution[11].equals(solution1[11])&&
           solution[12].equals(solution1[12]) && solution[13].equals(solution1[13])&&
           solution[14].equals(solution1[14]))
        {
            System.out.println("Solved!!");
            JOptionPane.showConfirmDialog(null, "Solved!!!\n" + "Inversion Count: " + count);
            count = 0;
            s1 = new Stack<Integer>();
            s2 = new Stack<Integer>();
            
        }
        
        //System.out.print(s1);
        //System.out.print(s2);
    }
}



