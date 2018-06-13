/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Taneja 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

////////////////////////////////////////////////////////////////////// PuzzleController
public class PuzzleController extends JApplet {
    //=================================================================== fields
    Viewer_Canvas _canvas;
   
    ModelPieces MP= new ModelPieces();
    
    ModelBoard MB= new ModelBoard();
    
    ModelGameInfo MGI= new ModelGameInfo();
    
    JPanel jpanel_button, jpanel_rotate,jpanel_de, jpanel_combined;
    
    static JButton Piece_button[]=new JButton[27];
    
    // Rotation Buttons
    JButton Rot_1_button= new JButton();
    JButton Rot_2_button= new JButton();
    
    
    // Deselect Piece Button
    JButton Deselect_button= new JButton();
    
    
     int butt_press_no;
    
    // Set this to true when a piece button is pressed
    boolean is_piece_selected;
    
    boolean tmp_chk_brd_matrix[][]= new boolean[10][11];
    
    //===================================================================== main
    public static void main(String[] args) {
        //... Create and initialize the applet.
        JApplet theApplet = new PuzzleController(1);
        
        //... Create a window (JFrame) and make applet the content pane.
        JFrame window = new JFrame();
        window.setContentPane(theApplet);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Penta-Hex Puzzle");
        window.pack();
        window.setLocationRelativeTo(null); // Center window.
        window.setResizable(false);
        // System.out.println(theApplet.getSize()); // to get applet size.
        window.setVisible(true);            // Make the window visible.
    }
    
    //============================================================== constructor
     
     
    public PuzzleController(int val)
    {
        if(val==1)
            _canvas = new Viewer_Canvas(MP,MB,MGI,this);
       
        
       
       is_piece_selected=false;
       
       Deselect_button.setEnabled(false);
       
        //... ARRAY OF 22 Buttons ...................
       
        
        for(int i=0;i<27;i++)
        {
            butt_press_no=i;
            
            Piece_button[i] = new JButton();
            Piece_button[i].setPreferredSize(new Dimension(67,70));
            //Piece_button[i].setBorder(null);
            
           
            
            // Setting Buttons normal Icon
            Piece_button[i].setIcon(new ImageIcon("Images/Piece_normal/P" + Integer.toString(i+1)+ ".png"));      


            // Button Rollovr effects
            Piece_button[i].setRolloverIcon(new ImageIcon("Images/Piece_hover/P" + Integer.toString(i+1)+ ".png"));      

            // Button Pressed effects
            Piece_button[i].setPressedIcon(new ImageIcon("Images/Piece_selected/P" + Integer.toString(i+1)+ ".png")); 
            
            // Button Disabled Effects
            Piece_button[i].setDisabledIcon(new ImageIcon("Images/Piece_deselect/P" + Integer.toString(i+1)+ ".png"));
            
            // Piece Buttons Action Listener
            Piece_button[i].setActionCommand("PRESSED_BUTTON_"+i);
            
            // Disable all Piece Buttons initially
            Piece_button[i].setEnabled(true);
            
            
            Piece_button[i].addActionListener(new ActionListener() {
 
             public void actionPerformed(ActionEvent e)
                        {
                           String action = e.getActionCommand();
                        String prefix = "PRESSED_BUTTON_";

                        if(action.startsWith(prefix)) 
                        {
                           String buttonNumberAsString = action.substring(prefix.length());
                           int buttonIndex = new Integer(buttonNumberAsString).intValue();
                           butt_press_no=buttonIndex;
                           
                            if(is_piece_selected==false)
                            {
                                    
                                    disable_other_buttons(butt_press_no);
                                    
                                    System.out.println("\nInside Controller. Piece button pressed");

                                    _canvas.drawPiece(butt_press_no);

                                    // Enable Rotation Buttons
                                    Rot_1_button.setEnabled(true);
                                    Rot_2_button.setEnabled(true);
                                    
                                    Deselect_button.setEnabled(true);
                                    
                                    is_piece_selected=true;
                            }
                           
                           
                        }  

                    }// end of public void actionPerformed(ActionEvent e)
            });
        
        }// end of for(int i=0;i<22;i++)
        
            
        
        //... Create Button Jpanel ............................        
        jpanel_button = new JPanel();
        
        
        // .... Set Button Jpanel Border
        jpanel_button.setBorder(BorderFactory.createTitledBorder("Pieces"));
        
        
        // .... Set Button Jpanel Size
        jpanel_button.setPreferredSize(new Dimension(67*9, 245));
        
         // .... Set Button Jpanel Layout as GridBagLayout
        jpanel_button.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        
        //c.gridwidth = GridBagConstraints.REMAINDER;
        
        
        // ADD BUTTONS ROW WISE
       // First Row Of buttons
        for(int k=0;k<9;k++)
        {   
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridy = 0;
            c.gridx =k;
            jpanel_button.add(Piece_button[k], c);
            
        }
        
        
        // Second Row Of buttons
        for(int k=9;k<18;k++)
        {   
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridy = 1;
            c.gridx =k-9;
            jpanel_button.add(Piece_button[k], c);
            
        }
        
         // Third Row Of buttons
        for(int k=18;k<27;k++)
        {   
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridy = 2;
            c.gridx =k-18;
            jpanel_button.add(Piece_button[k], c);
            
        }
        
        
        
        // ......  Rotate Buttons .....
        
        Rot_1_button.setPreferredSize(new Dimension(90, 180));
        Rot_1_button.setIcon(new ImageIcon("Images/Rotate1.png"));
        Rot_1_button.setRolloverIcon(new ImageIcon("Images/Rotate1_2.png"));
        Rot_1_button.setDisabledIcon(new ImageIcon("Images/Rotate1_des.png"));
        Rot_1_button.setBorder(null);
        Rot_1_button.setBorderPainted(false);
        Rot_1_button.setMargin(new Insets(0, 0, 0, 0));
        Rot_1_button.setFocusPainted(false);
        Rot_1_button.setContentAreaFilled(false);
        Rot_1_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Rot_1_button.setEnabled(false);
        
        // Adding Acion Listener To Rotate Button
        Rot_1_button.addActionListener(new ActionListener() {
 
             public void actionPerformed(ActionEvent e)
                        {
                         
                            _canvas.rotate_piece(1);
                           
                        }
        });
        
        
        
        
        Rot_2_button.setIcon(new ImageIcon("Images/Rotate2.png"));
        Rot_2_button.setRolloverIcon(new ImageIcon("Images/Rotate2_2.png"));
        Rot_2_button.setDisabledIcon(new ImageIcon("Images/Rotate2_des.png"));
        Rot_2_button.setPreferredSize(new Dimension(90, 80));
        Rot_2_button.setBorder(null);
        Rot_2_button.setBorderPainted(false);
        Rot_2_button.setMargin(new Insets(0, 0, 0, 0));
        Rot_2_button.setFocusPainted(false);
        Rot_2_button.setContentAreaFilled(false);
        Rot_2_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Rot_2_button.setEnabled(false);
        
        
         // Adding Acion Listener To Rotate Button
        Rot_2_button.addActionListener(new ActionListener() {
 
             public void actionPerformed(ActionEvent e)
                        {
                           
                            _canvas.rotate_piece(-1);
                        }
        });
        
        //... Create Rotate Buttons Jpanel ............................        
        jpanel_rotate = new JPanel();
        
        
        // .... Set Button Jpanel Border
        jpanel_rotate.setBorder(BorderFactory.createTitledBorder("Rotate"));
        
        
        // .... Set Button Jpanel Size
        jpanel_rotate.setPreferredSize(new Dimension(150, 110));
        
         // .... Set Button Jpanel Layout as GridBagLayout
        jpanel_rotate.setLayout(new GridBagLayout());
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
       
        c.gridy = 0;
        c.gridx =0;
        //c.insets=new Insets(0,0,0,10);
        jpanel_rotate.add(Rot_2_button, c);
        
        c.gridy = 0;
        c.gridx =1;
        c.insets=new Insets(0,10,0,0);
        jpanel_rotate.add(Rot_1_button, c);
         
        
        Deselect_button.setText("Deselect Piece");
        //Deselect_button.setIcon(new ImageIcon("Images/butt_bk.png"));
        Deselect_button.setPreferredSize(new Dimension(50, 50));
        //Deselect_button.setBorder(null);
        //Deselect_button.setBorderPainted(false);
        Deselect_button.setMargin(new Insets(0, 0, 0, 0));
        Deselect_button.setFocusPainted(false);
        //Deselect_button.setContentAreaFilled(false);
        Deselect_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        // Adding ActionListener to Deselect_button
        Deselect_button.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                enable_buttons();
                _canvas.to_draw_polygon=false;
                _canvas.repaint();
            }
        }); 
        
        
        
        
        
         //... Create Deselect Buttons Jpanel ............................        
        jpanel_de = new JPanel();
        
        
        // .... Set Button Jpanel Border
        jpanel_de.setBorder(BorderFactory.createTitledBorder("Deselect Piece"));
        
        
        // .... Set Button Jpanel Size
        jpanel_de.setPreferredSize(new Dimension(150, 110));
        
         // .... Set Button Jpanel Layout as GridBagLayout
        jpanel_de.setLayout(new GridBagLayout());
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
       
        c.gridy = 0;
        c.gridx =0;
        c.insets=new Insets(5,10,0,10);
        jpanel_de.add(Deselect_button, c);
        
        
        
          
        // .. Panel For Combining Pieces Jpanel, Rotation Buttons JPanel, Deselect Button JPanel
       jpanel_combined = new JPanel();
       jpanel_combined.setLayout(new GridBagLayout());
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        
        c.insets=new Insets(0,5,0,5);
        c.weightx = 0.5;
        c.gridx =0;
        c.gridy = 0;
        c.gridheight=2;
        jpanel_combined.add(jpanel_button, c);
        
        
        c.insets=new Insets(0,5,0,5);
        c.weightx = 0.5;
        c.gridx =1;
        c.gridy = 0;
        jpanel_combined.add(jpanel_rotate, c);
        
        
        c.insets=new Insets(120,0,0,0);
        c.weightx = 0.5;
        c.gridx =1;
        c.gridy = 1;
        jpanel_combined.add(jpanel_de, c);
       
        if(val==1)
        {
            //... layout the applet ...........................................
            setLayout(new BorderLayout());
            add(jpanel_combined, BorderLayout.NORTH);
            add(_canvas    , BorderLayout.CENTER);
        }
    }// END OF CLASS CONSTRUCTOR
    
    
    // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        
        
        _canvas.restart_game_settings();
        
        
        
        is_piece_selected=false;
        
        Deselect_button.setEnabled(false);
       
        //... ARRAY OF 22 Buttons ...................
       
        
        for(int i=0;i<27;i++)
        {
            butt_press_no=i;
            
            
            // Button Disabled Effects
            Piece_button[i].setDisabledIcon(new ImageIcon("Images/Piece_deselect/P" + Integer.toString(i+1)+ ".png"));
            
            
        
        }// end of for(int i=0;i<22;i++)
    }
 
    
  // Disable all other buttons when a piece button is pressed 
    void disable_other_buttons(int b) 
    {
        
            for(int i=0;i<27;i++)
            {
                if(i!=b)
                    Piece_button[i].setEnabled(false);
            }
           
        
    }
    
    
    
    
    // Enable buttons after piece is placed on board or deselect piece button is pressed
    void enable_buttons() 
    {
        Deselect_button.setEnabled(false);
        
        for(int i=0;i<27;i++)
        {
            if(MP.is_piece_placed[i]==false)
                Piece_button[i].setEnabled(true);
        }
        is_piece_selected=false;
        butt_press_no=-1;
        
        // Disble Rotation Buttons
                Rot_1_button.setEnabled(false);
                Rot_2_button.setEnabled(false);
    }
    
    
    
    
    
    
    
    // Enable All Buttons
    void Enable_ALL_Buttons()
    {
        for(int i=0;i<22;i++)
        {
            
                Piece_button[i].setEnabled(true);
        }
        
    }
    
    
    
     // ********* FUNCTION TO CHECK DISTANCE BETWEEN TWO POINTS ***********
    // Note: this function useful for checking color adjacency while placing pieces
    boolean is_distance_proper(int x1, int y1, int x2, int y2, int dist)
    {
        int x_sqr=(x2-x1)*(x2-x1);
        int y_sqr=(y2-y1)*(y2-y1);
        

        
        int d = (int)(Math.sqrt(x_sqr+y_sqr));
        
       System.out.println("\nDistance proper:   d:"+d+ "   dist: "+ dist);
        if(d<=(dist+2) && d>= (dist-2))
            return true;
        else 
            return false;
        
        
    }
    
    
    // ******* FUNCTION TO COMPUTE WINNER ******************
    int winner_color()
    {
        boolean is_tie=true;
        
        int winner=-1;
        int large_2=-1;
        
        for(int i=0;i<3;i++)
        {
            if(is_tie==false || winner!=-1)
                break;
            
            if(MGI.score[i]>=MGI.score[(i+1)%3]  && MGI.score[i]>=MGI.score[(i+2)%3])
            {
                winner=i;
                
                if(MGI.score[(i+1)%3]>=MGI.score[(i+2)%3])
                {
                    if(MGI.score[i]>(MGI.score[(i+1)%3]))
                            {
                                is_tie=false;
                            }
                }
                
                
                if(MGI.score[(i+1)%3]>MGI.score[(i+2)%3])
                {
                    if(MGI.score[i]>(MGI.score[(i+1)%3]))
                            {
                                is_tie=false;
                            }
                }
                
                
                if(MGI.score[(i+1)%3]<MGI.score[(i+2)%3])
                {
                    if(MGI.score[i]>(MGI.score[(i+2)%3]))
                            {
                                is_tie=false;
                            }
                }
                
                
            }
        } // end of for(int i=0;i<3;i++)
        
        
        if(is_tie)
        {
            if(MGI.score[(winner+1)%3]>=MGI.score[(winner+2)%3])
                large_2=(winner+1)%3;
            else
                large_2=(winner+2)%3;
       
        
            if(MGI.color_strip[winner]<=MGI.color_strip[large_2])
            {
                System.out.println("\n Winner: "+ winner);
                return winner;
            }

            else 
            {
                System.out.println("\n Winner: "+ large_2);
                return large_2;
            }
        
        
        }
        else
        {
             System.out.println("\n Winner: "+ winner);
            return winner;
        }
        
              
    }
    
    // ************** FUNCTION TO CHECK GAME OVER ***************
    void Check_game_over()
    {
        boolean is_game_over=true;
        
        if(MP.no_of_pieces_placed<22)
        {  
        
            for(int cfg=0;cfg<4;cfg++)
            {
                for(int i=1;i<13;i++)
                {
                    for(int j=1;j<13;j++)
                    {
                        if(MB.check_can_piece_placed(i,j,cfg))
                        {
                            is_game_over=false;
                            break;

                        }
                    }
                }
            }
        }
        if(is_game_over)
        {
            MGI.set_is_game_over(true);
            MGI.set_game_state(3);
            
             for(int i=0;i<27;i++)
            {
                butt_press_no=i;


                // Button Disabled Effects
                Piece_button[i].setEnabled(false);



            }// end of for(int i=0;i<27;i++)
        }
        
    }
    
    
    
    // ***************  FUNCTION TO UPDATE SCORE ***************
    void update_score(int piece_no, int color_no)
    {
        MGI.score[0]=0;
        MGI.score[1]=0;
        MGI.score[2]=0;
        
        
        for(int i=0;i<27; i++)
        {
            if(MP.is_piece_placed[i])
            {
                for(int j=0;j<27;j++)
                {
                    if(MP.is_piece_placed[j])
                    {
                        if(is_distance_proper(MP.centre_x_a_sqr[i][0],MP.centre_y_a_sqr[i][0],MP.centre_x_a_sqr[j][0],MP.centre_y_a_sqr[j][0],(int)(Math.sqrt(2)*_canvas.s))  && MP.color_piece_sqr[j][0]==MP.color_piece_sqr[j][0])
                        {
                            MGI.score[MP.color_piece_sqr[j][0]]++;
                        }
                    }
                }
                // if distance are sqrt(2) *s and color equal update scoe
                if(is_distance_proper(MP.centre_x_a_sqr[piece_no][0],MP.centre_y_a_sqr[piece_no][0],MP.centre_x_a_sqr[i][0],MP.centre_y_a_sqr[i][0],(int)(Math.sqrt(2)*_canvas.s))  && MP.color_piece_sqr[piece_no][0]==MP.color_piece_sqr[i][0])
                {
                   
                    
                    boolean b=false;
                    // ******** LOGIC FOR NO OF STRIPS  *******
                    for(int k=0;k<=MGI.color_strip[color_no];k++)
                    {
                        for(int m=0;m<=MGI.color_strip_num[color_no][k];m++)
                        {
                            if(MGI.color_strip_array[color_no][k][m]==i)
                            {
                                MGI.color_strip_num[color_no][k]++;
                                MGI.color_strip_array[color_no][k][MGI.color_strip_num[color_no][k]]=piece_no;
                                
                                
                                b= true;
                                
                                break;
                            }
                        }
                    }// end of for(int k=0;k<MGI.color_strip[color_no];k++)
                    
                    if(b==false)
                    {
                        
                            MGI.color_strip[color_no]++;
                            
                        
                        MGI.color_strip_array[color_no][MGI.color_strip[color_no]][0]= piece_no;
                        MGI.color_strip_num[color_no][MGI.color_strip[color_no]]=1;
                    }
                        
                    
                }
            }
        }
        
      MGI.score[0]=(MGI.score[0]/2);
      MGI.score[1]=(MGI.score[1]/2);
      MGI.score[2]=(MGI.score[2]/2);
      
        
        
    }
   
 
    
}