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
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

///////////////////////////////////////////////////////////////////// Viewer_Canvas
class Viewer_Canvas extends JPanel implements MouseListener, MouseMotionListener  {
    
  
    
   // For Restart game paint button  
   boolean is_rstrt_image_hvr, is_rstrt_image_pressed;
   
   // For no of player selection paint button
   int plyr_selection_val;
   boolean is_plyr_sln_pressed;
    
   // For Start Game paint button
   boolean is_start_image_hvr, is_start_image_pressed;
   
    int  init_graphics;
    double side;
    
    int mouseX, mouseY, drag_diff_x, drag_diff_y;
    
    boolean to_draw_polygon, piece_being_dragged;
    
    int draw_piece_no, start_x, start_y, start_x_piece, start_y_piece;
    
    
    // *****************  BOARD POLYGONS ****************************
     Polygon poly_brd_square[][]= new Polygon[14][14];

     Polygon poly_brd_up[][]= new Polygon[14][14];
     Polygon poly_brd_down[][]=new Polygon[14][14];
     Polygon poly_brd_left[][]=new Polygon[14][14];
     Polygon poly_brd_right[][]=new Polygon[14][14];

     Polygon poly_brd_config[][][]=new Polygon[4][14][14];
    
     
     
    // *********** HOVER BOARD VARIABLES STARTS  *************
    int hvr_brd_row, hvr_brd_col, start_x_hover, start_y_hover;
    boolean can_piece_placed_hover;
    boolean is_config_hvr;
    boolean drag_permission_hvr_draw;
       
    Polygon hv_poly_sqr[]= new Polygon[3];
    Polygon hv_poly_tri= new Polygon();
    
     int[] hv_pc_point_x_act= new int[9];
     int[] hv_pc_point_y_act= new int[9];
     
     // Storing Centre of 3 squares
     int hv_centre_x_sqr[]= new int[3];
     int hv_centre_y_sqr[]= new int[3];

     // Storing centre of trinagle
     int hv_centre_x_tri, hv_centre_y_tri;
     
     
     // *********** HOVER BOARD VARIABLES ENDS  *************
    
    
    int s=30;
    
    
     Polygon poly_sqr[]=new Polygon[3];
     Polygon poly_tri;

      // array to store Square polygon vertices
     int x_poly_sqr[]= new int[4];
     int y_poly_sqr[]= new int[4];

     // Array to store Triangle Polygon vertices
     int x_poly_tr[]= new int[3];
     int y_poly_tr[]= new int[3];

     // Storing Centre of 3 squares
     int centre_x_sqr[]= new int[3];
     int centre_y_sqr[]= new int[3];

     // Storing centre of trinagle
     int centre_x_tri, centre_y_tri;


     // TEMPORARY array for x y relative and actual co-ordinates
     int [] pc_point_x_r= new int[9];                  
     int [] pc_point_y_r=new int[9];
     
     int[] pc_point_x_act= new int[9];
     int[] pc_point_y_act= new int[9];
     
    
    
     
     // Array to store three colors
     
     int [] color_piece= new int[3];
    
    
     int piece_config;
    
    // Gradients for different colored hexagons in a piece
    GradientPaint[] grad = new GradientPaint[3];
     GradientPaint[] grad_light = new GradientPaint[3];
     
     GradientPaint grad_grey;
    
    
    
    // Counter determining no of pieces of penthex overlap on board pieces
    int ct_brd_pcs_overlapped;
    
    
    
    PuzzleController PD;
    
    // IMAGES FOR NO OF PLAYERS DISPLAY SELECTION
    BufferedImage  image_no_of_plyrs_bk;                           
    BufferedImage img_select_no_of_plyrs_text; // 270 * 60
    BufferedImage img_1_plyr_nm, img_1_plyr_hv ; // 180 * 30
    BufferedImage img_2_plyr_nm, img_2_plyr_hv ; // 180 * 30
    
    // IMAGES FOR START GAME DISPLAY 
    BufferedImage  image_start_game_bk; // 812 * 612
    BufferedImage  image_start_game_txt_nm; // 305 * 50
    BufferedImage  image_start_game_txt_hv;  // 305 * 50
    
    // IMAGES FOR MADE BY Display
    BufferedImage  image_made_by; // 812 * 35
    
    // IMAGES FOR Instructions
    BufferedImage  image_instruct_sel; // 812 * 35
    BufferedImage  image_instruct_drag; // 812 * 35
    
    // Images FOR TURN INO
    BufferedImage  image_turn[]= new BufferedImage[3]; 
    
    
    // IMAGES FOR NO OF STRIPS
    BufferedImage image_strip[]= new BufferedImage[3];
    
    // Images For Score
    BufferedImage image_score[]= new BufferedImage[3];
    
    
    // IMAGES FOR PLAYER WON
    BufferedImage image_won[]= new BufferedImage[3];
            
    
    
    static boolean is_game_over;
    
    static boolean rpt_first_time;
    
    // OBJECTS OF OTHER CLASSES
    ModelPieces MP= new ModelPieces();
    PuzzleController PC;
    
    ModelBoard MB= new ModelBoard();
    
    ModelGameInfo MGI= new ModelGameInfo();
     
    //============================================================== constructor
    public Viewer_Canvas(ModelPieces mp,ModelBoard mb,ModelGameInfo mgi, PuzzleController pc) {
        
        MP=mp;
        
        PC= new PuzzleController(-1);
        
        PC=pc;
        
        MB=mb;
        
        MGI=mgi;
        
        try 
        {
            // INITIALIZE IMAGES FOR NO OF PLAYERS SELECTION DISPLAY
            
            image_no_of_plyrs_bk = ImageIO.read(new File("Images/no_of_plyr_BK.png"));            
            img_select_no_of_plyrs_text= ImageIO.read(new File("Images/select_no_of_plyrs_text.png")); 
            img_1_plyr_nm= ImageIO.read(new File("Images/1_plyr_hv.png"));
            img_1_plyr_hv= ImageIO.read(new File("Images/1_plyr_nml.png"));
            img_2_plyr_nm= ImageIO.read(new File("Images/2_plyrs_hv.png"));            
            img_2_plyr_hv= ImageIO.read(new File("Images/2_plyrs_nml.png"));
            
            // INITIALIZE IMAGES FOR NO OF PLAYERS SELECTION DISPLAY
            
            image_start_game_bk = ImageIO.read(new File("Images/Start_game_bk.png"));            
            image_start_game_txt_nm= ImageIO.read(new File("Images/Start_game_txt_1.png")); 
            image_start_game_txt_hv= ImageIO.read(new File("Images/Start_game_txt_2.png"));
            
            // INITIALIZE IMAGES FOR NO OF PLAYERS SELECTION DISPLAY
            
            image_made_by = ImageIO.read(new File("Images/Made_by.png"));   
            
            // INITIALIZE IMAGES FOR TWO INSTRUCTIONS
            
            image_instruct_sel = ImageIO.read(new File("Images/Instructions_select.png")); 
            image_instruct_drag = ImageIO.read(new File("Images/Instructions_drag.png")); 
            
            // INITIALIZE IMAGES FOR PLAYER TURN
            
            image_turn[0] = ImageIO.read(new File("Images/turn_red.png"));
            image_turn[1] = ImageIO.read(new File("Images/turn_blue.png"));
            image_turn[2] = ImageIO.read(new File("Images/turn_yellow.png"));
            
            // INITIALIZE IMAGES FOR SCORES
            image_score[0] = ImageIO.read(new File("Images/Score_red.png"));
            image_score[1] = ImageIO.read(new File("Images/Score_blue.png"));
            image_score[2] = ImageIO.read(new File("Images/Score_yellow.png"));
            
            
            // INITIALIZE IMAGES FOR STRIP NOS
            image_strip[0]= ImageIO.read(new File("Images/Strips_red.png"));
            image_strip[1]= ImageIO.read(new File("Images/Strips_blue.png"));
            image_strip[2]= ImageIO.read(new File("Images/Strips_yellow.png"));
            
            // INITIALIZE IMAGES FOR WON PLAYER
            image_won[0]=ImageIO.read(new File("Images/won_red.png"));
            image_won[1]=ImageIO.read(new File("Images/won_blue.png"));
            image_won[2]=ImageIO.read(new File("Images/won_yellow.png"));
            


        }
        catch (IOException ie) 
        {
              
            System.out.println("Critical Error. Unable To load Images...");
        }
        
        
        is_game_over=false;
        
        ct_brd_pcs_overlapped=0;
        
        
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.white);
        
         init_graphics=1;
         side=20;
         
         to_draw_polygon=false;
         piece_being_dragged=false;
         
         draw_piece_no=-1;
         
         start_x_piece=700;
         start_y_piece=300;
         
         
         
         
          
         
        // ...... Create gradients .....
           
           grad[0]= new GradientPaint(75,75,new Color(255, 97, 97),115,115, new Color(218,37,37),true); // red
           grad[1]= new GradientPaint(75,75,new Color(111, 162, 213),115,115, new Color(51,102,153),true); // blue
           grad[2]= new GradientPaint(75,75,new Color(255, 255, 88),115,115, new Color(255,246,0),true); // yellow
           
           
           grad_light[0]= new GradientPaint(75,75,new Color(249, 155, 155),115,115, new Color(249,155,155),true); // red
           grad_light[1]= new GradientPaint(75,75,new Color(170, 213, 255),115,115, new Color(170, 213, 255),true); // blue
           grad_light[2]= new GradientPaint(75,75,new Color(255, 247, 152),115,115, new Color(255, 247, 15),true); // yellow
           
           grad_grey = new GradientPaint(75,75,new Color(128, 128, 128),115,115, new Color(128, 128, 128),true); // yellow
          
          
           
           
           
           plyr_selection_val=-1;
           
           is_plyr_sln_pressed=false;
           
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
     // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        rpt_first_time=false;
        
        is_game_over=false;
        
        ct_brd_pcs_overlapped=0;
        
         to_draw_polygon=false;
         piece_being_dragged=false;
         
         
           
           plyr_selection_val=-1;
           is_plyr_sln_pressed=false;
           
           repaint();
        
    }
    
    
    
  
    
  
    
 
   
             //  drawing on the space.
		public void paintComponent(Graphics g) 
		{

			// The object referenced by the 'g' parameter is actually		
			// an instance of the Graphics2D class.
			// If you need to use a method from the Graphics2D class,
			// you can't use this paintComponent parameter('g')
			// straight from the method. But you can cast it with
			// a new GraphicsED variable.
			// Graphics2D g2d = (Graphics2D) g;

			
                        // Initial Configurtion
                            Graphics2D g2d = (Graphics2D) g;
                            
                             super.paintComponent(g);  
                             g.setColor(new Color(255,174,95));
                             int margin = 1;
                             g.drawRect(margin,margin, getSize().width-2*margin,getSize().height-2*margin);
                        
                           
                            
                            
                            
                         
                             
                             // array to store square and triangle polygon co-ordinates
                             int x_brd_sqr[]= new int[4];
                             int y_brd_sqr[]= new int[4];
                             
                             int x_brd_tri[]= new int[3];
                             int y_brd_tri[]= new int[3];
                             
                             
                             Font ft = new Font("Arial", Font.PLAIN, 17);
                             g2d.setFont(ft);
                             
                             
                             
                                


                                       // DRAW TURN IMAGES
                                     g.drawImage(image_turn[MP.no_of_pieces_placed%3], 0, 50, null);

                                 // ###################  DRAWING BOARD GRID STARTS  ################################





                                    init_graphics=-1;
                                    //System.out.println("Board being generated");



                                    start_x=40;
                                    start_y=100;
                                    double x_0, y_0;


                                    g2d.setPaint(Color.BLACK);

                                   // g.fillRect((int)(start_x-10),(int)(start_y-20), (int)((a*11)+40),(int)((b*10)-(h*6)-8));


                                    // Loop for drawing board
                                    for(int i=0;i<14;i++)
                                    {
                                        for(int j=0;j<14;j++)
                                        {
                                            if(!rpt_first_time)
                                            {
                                                x_0=start_x+(j*s);
                                                y_0=start_y+(i*s);

                                                    // ******* Drawing Main Square Starts *******

                                                    x_brd_sqr[0]=(int)x_0;
                                                    y_brd_sqr[0]=(int)y_0;

                                                    x_brd_sqr[1]=(int)(x_0+s);
                                                    y_brd_sqr[1]=(int)y_0;

                                                    x_brd_sqr[2]=(int)(x_0+s);
                                                    y_brd_sqr[2]=(int)(y_0+s);

                                                    x_brd_sqr[3]=(int)x_0;
                                                    y_brd_sqr[3]=(int)(y_0+s);

                                                    poly_brd_square[i][j]= new Polygon(x_brd_sqr,y_brd_sqr,4);

                                                    g2d.setPaint(Color.BLACK);

                                                    //g.drawPolygon(poly_brd_square[i][j]);


                                                    // *******  Drawing Main square Ends ********



                                                    // ******* Drawing UP Triangle Starts *******

                                                    x_brd_tri[0]=(int)x_0;
                                                    y_brd_tri[0]=(int)y_0;

                                                    x_brd_tri[1]=(int)(x_0+s);
                                                    y_brd_tri[1]=(int)y_0;

                                                    x_brd_tri[2]=(int)(x_0+(s/2));
                                                    y_brd_tri[2]=(int)(y_0+(s/2));

                                                    poly_brd_up[i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(179,204,135));

                                                    g.drawPolygon(poly_brd_up[i][j]);


                                                    // *******   Drawing UP Triangle Ends ********


                                                    // ******* Drawing DOWN Triangle Starts *******

                                                    x_brd_tri[0]=(int)(x_0+(s/2));
                                                    y_brd_tri[0]=(int)(y_0+(s/2));

                                                    x_brd_tri[1]=(int)(x_0);
                                                    y_brd_tri[1]=(int)(y_0+s);

                                                    x_brd_tri[2]=(int)(x_0+s);
                                                    y_brd_tri[2]=(int)(y_0+s);

                                                    poly_brd_down[i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(255,174,95));

                                                    g.drawPolygon(poly_brd_down[i][j]);


                                                    // *******   Drawing DOWN Triangle Ends ********



                                                    // ******* Drawing LEFT Triangle Starts *******

                                                    x_brd_tri[0]=(int)(x_0);
                                                    y_brd_tri[0]=(int)(y_0);

                                                    x_brd_tri[1]=(int)(x_0);
                                                    y_brd_tri[1]=(int)(y_0+s);

                                                    x_brd_tri[2]=(int)(x_0+(s/2));
                                                    y_brd_tri[2]=(int)(y_0+(s/2));

                                                    poly_brd_left[i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(79,129,189));

                                                    g.drawPolygon(poly_brd_left[i][j]);


                                                    // *******   Drawing LEFT Triangle Ends ********



                                                     // ******* Drawing RIGHT Triangle Starts *******

                                                    x_brd_tri[0]=(int)(x_0+s);
                                                    y_brd_tri[0]=(int)(y_0);

                                                    x_brd_tri[1]=(int)(x_0+(s/2));
                                                    y_brd_tri[1]=(int)(y_0+(s/2));

                                                    x_brd_tri[2]=(int)(x_0+s);
                                                    y_brd_tri[2]=(int)(y_0+s);

                                                    poly_brd_right[i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(79,129,189));

                                                    g.drawPolygon(poly_brd_right[i][j]);


                                                    // *******   Drawing RIGHT Triangle Ends ********




                                                     // ******* Drawing CONFIG 0 Starts *******

                                                    x_brd_tri[0]=(int)(x_0);
                                                    y_brd_tri[0]=(int)(y_0);

                                                    x_brd_tri[1]=(int)(x_0);
                                                    y_brd_tri[1]=(int)(y_0+s);

                                                    x_brd_tri[2]=(int)(x_0+s);
                                                    y_brd_tri[2]=(int)(y_0+s);

                                                    poly_brd_config[0][i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(79,129,189));

                                                    g.drawPolygon(poly_brd_config[0][i][j]);


                                                    // *******   Drawing CONFIG 0 Ends ********



                                                    // ******* Drawing CONFIG 1 Starts *******

                                                    x_brd_tri[0]=(int)(x_0);
                                                    y_brd_tri[0]=(int)(y_0);

                                                    x_brd_tri[1]=(int)(x_0);
                                                    y_brd_tri[1]=(int)(y_0+s);

                                                    x_brd_tri[2]=(int)(x_0+s);
                                                    y_brd_tri[2]=(int)(y_0);

                                                    poly_brd_config[1][i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(79,129,189));

                                                    g.drawPolygon(poly_brd_config[1][i][j]);


                                                    // *******   Drawing CONFIG 1 Ends ********




                                                    // ******* Drawing CONFIG 2 Starts *******

                                                    x_brd_tri[0]=(int)(x_0);
                                                    y_brd_tri[0]=(int)(y_0);

                                                    x_brd_tri[1]=(int)(x_0+s);
                                                    y_brd_tri[1]=(int)(y_0);

                                                    x_brd_tri[2]=(int)(x_0+s);
                                                    y_brd_tri[2]=(int)(y_0+s);

                                                    poly_brd_config[2][i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(79,129,189));

                                                    g.drawPolygon(poly_brd_config[2][i][j]);


                                                    // *******   Drawing CONFIG 2 Ends ********




                                                     // ******* Drawing CONFIG 3 Starts *******

                                                    x_brd_tri[0]=(int)(x_0+s);
                                                    y_brd_tri[0]=(int)(y_0);

                                                    x_brd_tri[1]=(int)(x_0);
                                                    y_brd_tri[1]=(int)(y_0+s);

                                                    x_brd_tri[2]=(int)(x_0+s);
                                                    y_brd_tri[2]=(int)(y_0+s);

                                                    poly_brd_config[3][i][j]= new Polygon(x_brd_tri,y_brd_tri,3);

                                                    g2d.setPaint(new Color(79,129,189));

                                                    g.drawPolygon(poly_brd_config[3][i][j]);


                                                    // *******   Drawing CONFIG 3 Ends ********




                                            }

                                            // defining gradient
                                            GradientPaint gp = new GradientPaint(75, 75, new Color(249, 249, 249),105, 105, new Color(237, 237, 237), true);

                                             //g2d.setPaint( getColor() );
                                             g2d.setPaint(gp);






                                        } // for(int j=0;j<11;j++) 

                                    } // for(int i=0;i<10;i++)





                                    GradientPaint grad_orange= new GradientPaint(75,75,new Color(255, 175, 107),115,115, new Color(240,115,47),true); // orange


                                    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  DRAWING ALREADY PLACED PIECES STARTS    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

                                    for(int i=0;i<27;i++)
                                    {
                                        // if ith piece place
                                        if(MP.is_piece_placed[i])
                                        {
                                            // DRAW TRIANGLE
                                            g2d.setPaint(Color.BLACK);
                                            g.fillPolygon(MP.poly_piece_tri[i]);




                                             for(int k=0;k<3;k++)
                                             {
                                                 // DRAW SQUARES  
                                                     g2d.setPaint(grad[MP.color_piece_sqr[i][k]]);
                                                     g.fillPolygon(MP.poly_piece_sqr[i][k]);

                                                      g2d.setPaint(Color.BLACK);                        
                                                     g.drawPolygon(MP.poly_piece_sqr[i][k]);





                                             }
                                        }
                                    }




                                    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  DRAWING ALREADY PLACED PIECES ENDS    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  DRAWING PIECES AND HOVER EFFECTS STARTS    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%







                                 // ******* DRAW POLYGONS IF REQUIRED ********************
                                    if(to_draw_polygon)
                                    {



                                             //  ****  DRAWING HOVER PIECE ON BOARD  ******

                                            if(drag_permission_hvr_draw)
                                            {
                                                System.out.println("\nDrag hover true");

                                                // DRAW TRIANGLE
                                            g2d.setPaint(grad_grey);
                                            g.fillPolygon(hv_poly_tri);

                                                for(int i=0;i<3;i++)
                                                 {
                                                 // DRAW SQUARES  
                                                     g2d.setPaint(grad_light[color_piece[i]]);
                                                     g.fillPolygon(hv_poly_sqr[i]);

                                                      g2d.setPaint(Color.BLACK);                        
                                                     g.drawPolygon(hv_poly_sqr[i]);



                                                 }



                                            }
                                            else
                                                System.out.println("\nDrag hover false");







                                        // *****************   DRAWING ACTUAL PIECE ********************


                                            // DRAW TRIANGLE
                                            g2d.setPaint(Color.BLACK);
                                            g.fillPolygon(MP.poly_piece_tri[draw_piece_no]);





                                             for(int i=0;i<3;i++)
                                             {
                                                 // DRAW SQUARES  
                                                     g2d.setPaint(grad[color_piece[i]]);
                                                     g.fillPolygon(MP.poly_piece_sqr[draw_piece_no][i]);

                                                      g2d.setPaint(Color.BLACK);                        
                                                     g.drawPolygon(MP.poly_piece_sqr[draw_piece_no][i]);



                                             }




                                    }


                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  DRAWING PIECES AND HOVER EFFECTS ENDS    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                 // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


                                     g.drawImage(image_made_by, 0, 577, null);

                                    if(to_draw_polygon)
                                        g.drawImage(image_instruct_drag, 0,0, null);
                                    else
                                        g.drawImage(image_instruct_sel, 0,0, null);


                            if(MGI.get_is_game_over())
                             {
                                 int winner=PC.winner_color();
                                 
                                
                                 
                                  g.drawImage(image_won[winner], 0,0, null);
                             }
                            
                            
                       
                       
                       
		} // end of function paintComponent(Graphics g)   
    
                
    // ***********  FUNCTION TO DRAW PIECE STARTS***********************
    void drawPiece(int piece_no)
    {
        
        drag_permission_hvr_draw=false;
        
        System.out.println("\nInside Viewer. drawPiece fn called");
        
        start_x_piece=630;
        start_y_piece=290;
        
        
        to_draw_polygon= true;
        draw_piece_no= piece_no;
        
       draw_piece_settings(piece_no,start_x_piece,start_y_piece);

                         
                         
        repaint();
        
    }
       // ***********  FUNCTION TO DRAW PIECE ENDS ***********************
    
    
    
    
    
    // ********************************************************************************************************
    // ******************  FUNCTION TO CONFIGURE SETTINGS OF PIECE TO BE DRAWN STARTS *************************
    // ********************************************************************************************************
    
    public void draw_piece_settings(int piece_no, int start_x_piece, int start_y_piece)
    {
        
         // Storing 9 co-ordinates of piece
        int rel_coord[][]= new int[9][2];
        
        rel_coord=MP.get_piece_relative_Points(piece_no);
        
       
        for(int i=0;i<9;i++)
        {
            
            
            // Storing Relative cooordinates of 9 piece points
            pc_point_x_r[i]=rel_coord[i][0];
            pc_point_y_r[i]=rel_coord[i][1];
            
         // Storing Actual cooordinates of 9 piece points
            pc_point_x_act[i]=pc_point_x_r[i]+start_x_piece;
            pc_point_y_act[i]=pc_point_y_r[i]+start_y_piece;
        }
        
        
        // storing centre-coordinates of piece
         // first 3 index will store square centre co-ordinates
        // last index(3th) will store triangle centre co-ordinates
        int cent_coord[][]= new int[4][2];
        
        cent_coord=MP.get_piece_centre_coordinates(piece_no);
        
        // Storing centre coordinates to variables defined in this classs
        
        // Storing square centre co-ordinates
        for(int i=0;i<3;i++)
        {
            centre_x_sqr[i]=start_x_piece + cent_coord[i][0];
            centre_y_sqr[i]=start_y_piece + cent_coord[i][1];
            
            MP.centre_x_a_sqr[piece_no][i]=centre_x_sqr[i];
            MP.centre_y_a_sqr[piece_no][i]=centre_y_sqr[i];
            
        }
        
        
        // Storing triangle centre co-ordinates      
        centre_x_tri=start_x_piece + cent_coord[3][0];
        centre_y_tri=start_y_piece + cent_coord[3][1];
        
        
        MP.centre_x_a_tri[piece_no]=centre_x_tri;
        MP.centre_y_a_tri[piece_no]=centre_y_tri;
        
       
        color_piece= MP.get_piece_colors(piece_no);
        
        piece_config=MP.piece_config_no[piece_no];
        
        // **********************************************************
        // ********* NOW DEFINE POLYGONS TO BE DRAWN ****************
        // **********************************************************
        
        // ********* FOR Square SubPiece 0  **********
                         
                         
                         x_poly_sqr[0]=pc_point_x_act[1];
                         y_poly_sqr[0]=pc_point_y_act[1];
                         
                         x_poly_sqr[1]=pc_point_x_act[2];
                         y_poly_sqr[1]=pc_point_y_act[2];
                         
                         x_poly_sqr[2]=pc_point_x_act[4];
                         y_poly_sqr[2]=pc_point_y_act[4];
                         
                         x_poly_sqr[3]=pc_point_x_act[3];
                         y_poly_sqr[3]=pc_point_y_act[3];
                         
                        
                         
                         poly_sqr[0]=new Polygon(x_poly_sqr,y_poly_sqr,4);
                         
                         MP.poly_piece_sqr[piece_no][0]=poly_sqr[0];
                         
                         
                         
                          // ********* FOR Square SubPiece 1  **********
                         
                         x_poly_sqr[0]=pc_point_x_act[0];
                         y_poly_sqr[0]=pc_point_y_act[0];
                         
                         x_poly_sqr[1]=pc_point_x_act[2];
                         y_poly_sqr[1]=pc_point_y_act[2];
                         
                         x_poly_sqr[2]=pc_point_x_act[6];
                         y_poly_sqr[2]=pc_point_y_act[6];
                         
                         x_poly_sqr[3]=pc_point_x_act[5];
                         y_poly_sqr[3]=pc_point_y_act[5];
                         
                         
                         poly_sqr[1]=new Polygon(x_poly_sqr,y_poly_sqr,4);
                         
                         MP.poly_piece_sqr[piece_no][1]=poly_sqr[1];
                         
                         
                       
                         
                         
                         
                         
                          // ********* FOR Square SubPiece 2  **********
                         
                         x_poly_sqr[0]=pc_point_x_act[0];
                         y_poly_sqr[0]=pc_point_y_act[0];
                         
                         x_poly_sqr[1]=pc_point_x_act[7];
                         y_poly_sqr[1]=pc_point_y_act[7];
                         
                         x_poly_sqr[2]=pc_point_x_act[8];
                         y_poly_sqr[2]=pc_point_y_act[8];
                         
                         x_poly_sqr[3]=pc_point_x_act[1];
                         y_poly_sqr[3]=pc_point_y_act[1];
                         
                         
                         poly_sqr[2]=new Polygon(x_poly_sqr,y_poly_sqr,4);
                         
                         MP.poly_piece_sqr[piece_no][2]=poly_sqr[2];
                         
                         
                          // ********* FOR TRINAGLE SUBPIECE  **********
                         
                         x_poly_tr[0]=pc_point_x_act[0];
                         y_poly_tr[0]=pc_point_y_act[0];
                         
                         x_poly_tr[1]=pc_point_x_act[1];
                         y_poly_tr[1]=pc_point_y_act[1];
                         
                         x_poly_tr[2]=pc_point_x_act[2];
                         y_poly_tr[2]=pc_point_y_act[2];
                         
                         
                         
                         
                         poly_tri=new Polygon(x_poly_tr,y_poly_tr,3);
                         
                         MP.poly_piece_tri[piece_no]=poly_tri;
    }
    
    // ******************  FUNCTION TO CONFIGURE SETTINGS OF PIECE TO BE DRAWN STARTS *************************
    
    
    
    
    // ********************************************************************************************************
    // ******************  FUNCTION TO CONFIGURE SETTINGS OF   HOVER   PIECE TO BE DRAWN STARTS *************************
    // ********************************************************************************************************
    public void set_drawpiece_hover(int piece_no, int st_x, int st_y)
    {
        
       
        for(int i=0;i<9;i++)
        {
            
            
           
            
         // Storing Actual cooordinates of 9 piece points
            hv_pc_point_x_act[i]=pc_point_x_r[i]+st_x;
            hv_pc_point_y_act[i]=pc_point_y_r[i]+st_y;
        }
        
        
        // storing centre-coordinates of piece
         // first 3 index will store square centre co-ordinates
        // last index(3th) will store triangle centre co-ordinates
        int cent_coord[][]= new int[4][2];
        
        System.out.println("\n\n Hover piece no: "+ piece_no);
        
        cent_coord=MP.get_piece_centre_coordinates(piece_no);
        
        // Storing centre coordinates to variables defined in this classs
        
        // Storing square centre co-ordinates
        for(int i=0;i<3;i++)
        {
            hv_centre_x_sqr[i]=st_x + cent_coord[i][0];
            hv_centre_y_sqr[i]=st_y + cent_coord[i][1];
            
        }
        
        
        // Storing triangle centre co-ordinates      
        hv_centre_x_tri=st_x + cent_coord[3][0];
        hv_centre_y_tri=st_y + cent_coord[3][1];
        
       
        
        // **********************************************************
        // ********* NOW DEFINE POLYGONS TO BE DRAWN ****************
        // **********************************************************
        
        // ********* FOR Square SubPiece 0  **********
                         
                         
                         x_poly_sqr[0]=hv_pc_point_x_act[1];
                         y_poly_sqr[0]=hv_pc_point_y_act[1];
                         
                         x_poly_sqr[1]=hv_pc_point_x_act[2];
                         y_poly_sqr[1]=hv_pc_point_y_act[2];
                         
                         x_poly_sqr[2]=hv_pc_point_x_act[4];
                         y_poly_sqr[2]=hv_pc_point_y_act[4];
                         
                         x_poly_sqr[3]=hv_pc_point_x_act[3];
                         y_poly_sqr[3]=hv_pc_point_y_act[3];
                         
                        
                         
                         hv_poly_sqr[0]=new Polygon(x_poly_sqr,y_poly_sqr,4);
                         
                        
                         
                         
                         
                          // ********* FOR Square SubPiece 1  **********
                         
                         x_poly_sqr[0]=hv_pc_point_x_act[0];
                         y_poly_sqr[0]=hv_pc_point_y_act[0];
                         
                         x_poly_sqr[1]=hv_pc_point_x_act[2];
                         y_poly_sqr[1]=hv_pc_point_y_act[2];
                         
                         x_poly_sqr[2]=hv_pc_point_x_act[6];
                         y_poly_sqr[2]=hv_pc_point_y_act[6];
                         
                         x_poly_sqr[3]=hv_pc_point_x_act[5];
                         y_poly_sqr[3]=hv_pc_point_y_act[5];
                         
                         
                         hv_poly_sqr[1]=new Polygon(x_poly_sqr,y_poly_sqr,4);
                         
                       
                         
                         
                       
                         
                         
                         
                         
                          // ********* FOR Square SubPiece 2  **********
                         
                         x_poly_sqr[0]=hv_pc_point_x_act[0];
                         y_poly_sqr[0]=hv_pc_point_y_act[0];
                         
                         x_poly_sqr[1]=hv_pc_point_x_act[7];
                         y_poly_sqr[1]=hv_pc_point_y_act[7];
                         
                         x_poly_sqr[2]=hv_pc_point_x_act[8];
                         y_poly_sqr[2]=hv_pc_point_y_act[8];
                         
                         x_poly_sqr[3]=hv_pc_point_x_act[1];
                         y_poly_sqr[3]=hv_pc_point_y_act[1];
                         
                         
                         hv_poly_sqr[2]=new Polygon(x_poly_sqr,y_poly_sqr,4);
                         
                        
                         
                         
                          // ********* FOR TRINAGLE SUBPIECE  **********
                         
                         x_poly_tr[0]=hv_pc_point_x_act[0];
                         y_poly_tr[0]=hv_pc_point_y_act[0];
                         
                         x_poly_tr[1]=hv_pc_point_x_act[1];
                         y_poly_tr[1]=hv_pc_point_y_act[1];
                         
                         x_poly_tr[2]=hv_pc_point_x_act[2];
                         y_poly_tr[2]=hv_pc_point_y_act[2];
                         
                         
                         
                         
                         hv_poly_tri=new Polygon(x_poly_tr,y_poly_tr,3);
                         
                        
    }
    
    
    
    
    
    
    // ********* FUNCTION TO ROTATE PIECE ******************************
    public void rotate_piece(int val)
    {
        MP.rotate_piece(draw_piece_no,val);
        
        
        draw_piece_settings(draw_piece_no,start_x_piece,start_y_piece);
        repaint();
        
    }
    
    
    //============================================================= mousePressed
    public void mousePressed(MouseEvent e) {
        
        
        mouseX = e.getX();
	mouseY = e.getY();
        
        
        
        if(to_draw_polygon)
            {
                piece_being_dragged=false;
            
                for(int i=0;i<3;i++)
                {
                    if(MP.poly_piece_sqr[draw_piece_no][i].contains(mouseX,mouseY) )
                    {
                         piece_being_dragged=true;
                           break;
                    }
                }
                
                if(piece_being_dragged==false)
                {
                    if(MP.poly_piece_tri[draw_piece_no].contains(mouseX,mouseY) )
                         piece_being_dragged=true;
                }
                
                
                if(piece_being_dragged)
                {
                    drag_diff_x=start_x_piece-mouseX;
                    drag_diff_y=start_y_piece-mouseY;
                }
            }
        
        
        
        
    }
    
    // ***** Function to set start_x-hover and start_y_hover for hover board piece **********
    void set_start_hover_coord(int i, int j, int config)
    {
        if(config==0)
        {
            start_x_hover=start_x+ (j*s);
            start_y_hover=start_y+ ((i+1)*s);
            
        }
        
        
         if(config==1)
        {
            start_x_hover=start_x+ (j*s);
            start_y_hover=start_y+ ((i)*s);
            
        }
         
         
          if(config==2)
        {
            start_x_hover=start_x+ ((j+1)*s);
            start_y_hover=start_y+ ((i)*s);
            
        }
          
          
            if(config==3)
        {
            start_x_hover=start_x+ ((j+1)*s);
            start_y_hover=start_y+ ((i+1)*s);
            
        }
            
            
    }
    
    
   
    
    
    
    
    // ********* FUNCTION TO CHECK COLOR ADJACENCY WHILE PLACING PIECES ***********
    // Note: this function called in Mousedragg method
    boolean check_color_adjacency()
    {
        if(MP.no_of_pieces_placed==0)
            return true;
        
        for(int i=0;i<27;i++)
        {
            if(MP.is_piece_placed[i])
            {
                // ***** CHECKING COLOR ADJACENCY OF TWO SMALL SQUARES STARTS ***
                
                for(int m=1;m<=2; m++)
                {
                    for(int n=1;n<=2;n++)
                    {
                        System.out.println("\n\nCentre points:    hv_centre_x_sqr[m]: "+hv_centre_x_sqr[m]+ "   hv_centre_y_sqr[m]: "+ hv_centre_y_sqr[m]+ "   MP.centre_x_a_sqr[i][n]: "+ MP.centre_x_a_sqr[i][n]+ "   MP.centre_y_a_sqr[i][n]:  " + MP.centre_y_a_sqr[i][n]);
                        if((PC.is_distance_proper(hv_centre_x_sqr[m],hv_centre_y_sqr[m],MP.centre_x_a_sqr[i][n],MP.centre_y_a_sqr[i][n],s)) && color_piece[m]== MP.color_piece_sqr[i][n])
                            return true;
                        
                        
                    }// end of for(int n=1;n<=2;n++)
                    
                } // end of for(int m=1;m<=2; m++)
                
                
                // ***** CHECKING COLOR ADJACENCY OF TWO SMALL SQUARES STARTS ***
                
                
                 // ***** CHECKING COLOR ADJACENCY OF LARGE STARTS ***
                
                if(PC.is_distance_proper(hv_centre_x_sqr[0],hv_centre_y_sqr[0],MP.centre_x_a_sqr[i][0],MP.centre_y_a_sqr[i][0],(int)(Math.sqrt(2)*s)) && color_piece[0]== MP.color_piece_sqr[i][0])
                    return true;
                
                // ***** CHECKING COLOR ADJACENCY OF LARGE ENDS ***
                
                
                
                
                
                
            } // end of if(MP.is_piece_placed[i])
            
        } // end of for(int i=0;i<27;i++)
        
        
        return false;
        
    }// end of function boolean check_color_adjacency()
    
    
    
    
    
    
    //*******************  Function to get Board hover configuration block Row and column
    
    void get_brd_hvr_row_column()
    {
        is_config_hvr=false;
        
        for(int i=0;i<14;i++)
        {
            for(int j=0;j<14;j++)
            {
                if(poly_brd_square[i][j].contains(centre_x_tri, centre_y_tri))
                {
                    is_config_hvr=true;
                    hvr_brd_row=i;
                    hvr_brd_col=j;
                    
                    
                    break;
                }
            }
        }
    }
    
     // MouseMotionListener
    	public void mouseDragged(MouseEvent e) 
	{ 
           
            if(piece_being_dragged)
                    {
                        drag_permission_hvr_draw=false;
                        
                        start_x_piece=e.getX()+drag_diff_x;
                        start_y_piece=e.getY()+drag_diff_y;

                        draw_piece_settings(draw_piece_no,start_x_piece,start_y_piece);
                        
                        // *** BOARD HOVER CODE *******
                        
                        get_brd_hvr_row_column();
                        
                        // if some config block actually hovered
                        if(is_config_hvr)
                        {
                            // check whether piece can be placed
                            if(MB.check_can_piece_placed(hvr_brd_row, hvr_brd_col, piece_config))
                            {
                                set_start_hover_coord(hvr_brd_row, hvr_brd_col, piece_config);
                                
                                set_drawpiece_hover(draw_piece_no,start_x_hover, start_y_hover);
                                
                                if (check_color_adjacency())
                                    drag_permission_hvr_draw=true;
                                
                                
                            }
                        }

                        repaint();
                    }  
            
    	}
    
    //============================================================ mouseReleased
    public void mouseReleased(MouseEvent e) 
    {
        // if piece_being dragged
       if(piece_being_dragged)
       {
           // if piece left after releasing button at appropriate place
           if(drag_permission_hvr_draw)
           {
               // move the piece corresponding to position shown by hovered image of piece
               start_x_piece=start_x_hover;
               start_y_piece=start_y_hover;
               
               
               draw_piece_settings(draw_piece_no,start_x_piece,start_y_piece);
               
               MP.is_piece_placed[draw_piece_no]=true;
               
               piece_being_dragged=false;
               drag_permission_hvr_draw=false;
               
               MP.no_of_pieces_placed++;
               
               // Update Boatd after placing piece
               MB.update_brd_aftr_piece_placement(hvr_brd_row,hvr_brd_col,piece_config);
               
               
               // Update score
               PC.update_score(draw_piece_no,color_piece[0]);
               
               PC.Piece_button[draw_piece_no].setEnabled(false);
               PC.Piece_button[draw_piece_no].setDisabledIcon(new ImageIcon("Images/Piece_Over/P" + Integer.toString(draw_piece_no+1)+ ".png"));
               
               
               System.out.println("\n\nRED SCORE:   "+ MGI.score[0]);
               System.out.println("\nBLUE SCORE:   "+ MGI.score[1]);
               System.out.println("\nYELLOW SCORE:   "+ MGI.score[2]);
               PC.enable_buttons();
               
               to_draw_polygon=false;
               
               PC.Check_game_over();
               
               //piece_config=-1;
              // draw_piece_no=-1;
               
               
               repaint();
               
           }
           else // if piece left after releasing button at NOT an appropriate place
           {
               start_x_piece=630;
               start_y_piece=290;
               
               draw_piece_settings(draw_piece_no,start_x_piece,start_y_piece);
               
               repaint();
           }
       }
       
                       
        
    }
    
   
    public void mouseMoved  (MouseEvent e) {
        
     // System.out.println("\nMouseX:  "+ e.getX()+ "    MouseY: "+ e.getY());
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}