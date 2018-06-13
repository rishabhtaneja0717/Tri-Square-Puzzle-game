/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh
 */

import java.awt.*;
public class ModelPieces {
    
     // Arrays to store co-ordinates of all pieces
    
     // Array to store Relative Co-ordinates
    int piece_points_x_r[][]= new int[27][9];
    int piece_points_y_r[][] = new int[27][9];
    
    // Array to store actual co-ordinates
    int piece_points_x_a[][]= new int[27][9];
    int piece_points_y_a[][] = new int[27][9];
    
    
    // Boolean to determine whether a particular piece is placed on board or not
    boolean is_piece_placed[]= new boolean[27];
    
    // Piece configuration no
    int piece_config_no[] = new int[27];
    
    // Array to store centre co-ordinates of three squares of each of the piece
    
    // Relative co-ordinates
        int centre_x_r_sqr[][]= new int[27][3]; 
        int centre_y_r_sqr[][]= new int[27][3];
        
    // Actual co-ordinates
        int centre_x_a_sqr[][]= new int[27][3]; 
        int centre_y_a_sqr[][]= new int[27][3];
    
    
    // Array to store centre of middle black triangle of each of the piece
        
     // Relative centres
        int centre_x_r_tri[] = new int[27];
        int centre_y_r_tri[] = new int[27];
        
     // Actual centres
        int centre_x_a_tri[] = new int[27];
        int centre_y_a_tri[] = new int[27];
        
    
    // Color values of three squares in each of the pieces
    int color_piece_sqr[][]= new int [27][3];
    
    
    
    // Polygons to store the polygon shapes of squares and triangles
    
    Polygon poly_piece_sqr[][]= new Polygon[27][3];
    Polygon poly_piece_tri[]= new Polygon[27];
    
    
    int s;
    
    int no_of_pieces_placed;
    
    // ================================================================================
    // =========================  CONSTRUCTOR ==========================================
    // ===================================================================================
    public ModelPieces()
    {
        s=30;
        
        no_of_pieces_placed=0;
      
        
        for(int i=0;i<27;i++)
        {
            is_piece_placed[i]=false;
            piece_config_no[i]=0;
            
            centre_x_a_tri[i]=-1;
            centre_y_a_tri[i]=-1;
            
             poly_piece_tri[i]=new Polygon();
            
            // Initializing actual co-ordinates to -1
            for(int j=0;j<9;j++)
            {
                // Setting actual co-0rdinates to -1  
                piece_points_x_a[i][j]=-1;
                piece_points_y_a[i][j]=-1;
                
            } // end of for(int j=0;j<9;j++)
            
            
            // Initialize Actual centre co-ordinates of square to be -1
            for(int k=0;k<3;k++)
            {
                centre_x_a_sqr[i][k]=-1;
                centre_y_a_sqr[i][k]=-1;
                
                 poly_piece_sqr[i][k]= new Polygon();
                
            }
            
            
            // Setting Relative co-ordinates in accordance with 0 CONFIGURATION STARTS
            
                // Point 0
                piece_points_x_r[i][0]=0;
                piece_points_y_r[i][0]=0;
                               
                // Point 1
                piece_points_x_r[i][1]=0;
                piece_points_y_r[i][1]=-s;
                
                 // Point 2
                piece_points_x_r[i][2]=s;
                piece_points_y_r[i][2]=0;
                
                // Point 3
                piece_points_x_r[i][3]=s;
                piece_points_y_r[i][3]=-(2*s);
                
                // Point 4
                piece_points_x_r[i][4]=2*s;
                piece_points_y_r[i][4]=-s;
                
                // Point 5
                piece_points_x_r[i][5]=0;
                piece_points_y_r[i][5]=s;
                
                // Point 6
                piece_points_x_r[i][6]=s;
                piece_points_y_r[i][6]=s;
                
                // Point 7
                piece_points_x_r[i][7]=-s;
                piece_points_y_r[i][7]=0;
                
                // Point 8
                piece_points_x_r[i][8]=-s;
                piece_points_y_r[i][8]=-s;
                
                
                // Setting Relative Centres of three squares
                
                // Square 0
                centre_x_r_sqr[i][0]=s;
                centre_y_r_sqr[i][0]=-s;
                
                //Square 1
                centre_x_r_sqr[i][1]=s/2;
                centre_y_r_sqr[i][1]=(s/2);
                
                //Square 2
                centre_x_r_sqr[i][2]=-(s/2);
                centre_y_r_sqr[i][2]=-(s/2);
                
                
                // Setting Relative Centres of middle black triangle
                centre_x_r_tri[i]=(int)(s/(2*(Math.sqrt(2))));
                centre_y_r_tri[i]=-(int)(s/(2*(Math.sqrt(2))));
                
                
                        
            // Setting Relative co-ordinates in accordance with 0 CONFIGURATION ENDS
            
            
            
        } // end of for(int i=0;i<27;i++)
    
       
        // Call Function to set color to the squares of all piecese
        
        set_color_pieces();
        
        
        
    } // end of constructor
    
    
    //********** FUNCTION TO DEFINE COLOR OF ALL PIECES **************************
    // 0 color: RED
    // 1 color: BLUE
    //2 color: YELLOW
    void set_color_pieces()
    {
        //************  ROW 1 ********************
        
        // FOR PIECE 0
        
            color_piece_sqr[0][0]=0;
            color_piece_sqr[0][1]=0;
            color_piece_sqr[0][2]=0;
        
            // FOR PIECE 1
        
            color_piece_sqr[1][0]=0;
            color_piece_sqr[1][1]=1;
            color_piece_sqr[1][2]=0;
            
            
            // FOR PIECE 2
        
            color_piece_sqr[2][0]=0;
            color_piece_sqr[2][1]=2;
            color_piece_sqr[2][2]=0;
            
            
             // FOR PIECE 3
        
            color_piece_sqr[3][0]=0;
            color_piece_sqr[3][1]=1;
            color_piece_sqr[3][2]=1;
            
            
            // FOR PIECE 4
        
            color_piece_sqr[4][0]=0;
            color_piece_sqr[4][1]=0;
            color_piece_sqr[4][2]=1;
            
            
            // FOR PIECE 5
        
            color_piece_sqr[5][0]=0;
            color_piece_sqr[5][1]=2;
            color_piece_sqr[5][2]=1;
            
            
            // FOR PIECE 6
        
            color_piece_sqr[6][0]=0;
            color_piece_sqr[6][1]=2;
            color_piece_sqr[6][2]=2;
            
            
            // FOR PIECE 7
        
            color_piece_sqr[7][0]=0;
            color_piece_sqr[7][1]=0;
            color_piece_sqr[7][2]=2;
            
            
            // FOR PIECE 8
        
            color_piece_sqr[8][0]=0;
            color_piece_sqr[8][1]=1;
            color_piece_sqr[8][2]=2;
            
        // ***********  ROW 2 *****************
            
            
            // FOR PIECE 9
        
            color_piece_sqr[9][0]=1;
            color_piece_sqr[9][1]=1;
            color_piece_sqr[9][2]=1;
            
            
            // FOR PIECE 10
        
            color_piece_sqr[10][0]=1;
            color_piece_sqr[10][1]=0;
            color_piece_sqr[10][2]=1;
            
            
            // FOR PIECE 11
        
            color_piece_sqr[11][0]=1;
            color_piece_sqr[11][1]=2;
            color_piece_sqr[11][2]=1;
            
            
            // FOR PIECE 12
        
            color_piece_sqr[12][0]=1;
            color_piece_sqr[12][1]=0;
            color_piece_sqr[12][2]=0;
            
            
            // FOR PIECE 13
        
            color_piece_sqr[13][0]=1;
            color_piece_sqr[13][1]=1;
            color_piece_sqr[13][2]=0;
            
            
            // FOR PIECE 14
        
            color_piece_sqr[14][0]=1;
            color_piece_sqr[14][1]=2;
            color_piece_sqr[14][2]=0;
            
            
            // FOR PIECE 15
        
            color_piece_sqr[15][0]=1;
            color_piece_sqr[15][1]=2;
            color_piece_sqr[15][2]=2;
            
            
            // FOR PIECE 16
        
            color_piece_sqr[16][0]=1;
            color_piece_sqr[16][1]=1;
            color_piece_sqr[16][2]=2;
            
            
            // FOR PIECE 17
        
            color_piece_sqr[17][0]=1;
            color_piece_sqr[17][1]=0;
            color_piece_sqr[17][2]=2;
            
        // **************  ROW 3 ******************
            
            // FOR PIECE 18
        
            color_piece_sqr[18][0]=2;
            color_piece_sqr[18][1]=2;
            color_piece_sqr[18][2]=2;
            
            
            // FOR PIECE 19
        
            color_piece_sqr[19][0]=2;
            color_piece_sqr[19][1]=0;
            color_piece_sqr[19][2]=2;
            
            
            // FOR PIECE 20
        
            color_piece_sqr[20][0]=2;
            color_piece_sqr[20][1]=1;
            color_piece_sqr[20][2]=2;
            
            
            // FOR PIECE 21
        
            color_piece_sqr[21][0]=2;
            color_piece_sqr[21][1]=0;
            color_piece_sqr[21][2]=0;
            
            
            // FOR PIECE 22
        
            color_piece_sqr[22][0]=2;
            color_piece_sqr[22][1]=2;
            color_piece_sqr[22][2]=0;
            
            
            // FOR PIECE 23
        
            color_piece_sqr[23][0]=2;
            color_piece_sqr[23][1]=1;
            color_piece_sqr[23][2]=0;
            
            
            
            // FOR PIECE 24
        
            color_piece_sqr[24][0]=2;
            color_piece_sqr[24][1]=1;
            color_piece_sqr[24][2]=1;
            
            
            
            // FOR PIECE 25
        
            color_piece_sqr[25][0]=2;
            color_piece_sqr[25][1]=2;
            color_piece_sqr[25][2]=1;
            
            
            // FOR PIECE 26
        
            color_piece_sqr[26][0]=2;
            color_piece_sqr[26][1]=0;
            color_piece_sqr[26][2]=1;
            
            
        
    }
    
    
    
    
    
    
    // **********  FUNCTION CALLED WHEN ROTATE BUTTON IS PRESSED *********************
    // val=1 rotate clockwise
    //val=-1 rotate anticlockwise
    void rotate_piece(int piece_no, int val)
    {
        int config=piece_config_no[piece_no];
        
        if(val==1)
        {
            config=(config+1)%4;
        }
        else// val=-1
        {
            config--;
            if(config==-1)
                config=3;
            
        }
        
        piece_config_no[piece_no]=config;
        
        // Call function to set relative co-ordinates in accordance with new configuration
        set_piece_relative_coord(piece_no);
        
        
    }
    
    
    
    
    // **********  FUNCTION TO SET RELATIVE CO-ORDINATES IN ACCORDANCE WITH NEW CONFIGURATION  ******
    void set_piece_relative_coord(int piece_no)
    {
        int config=piece_config_no[piece_no];
        
        // *********  FOR CONFIGURATION 0  ****************
        if(config==0)
        {
                // Point 0
                piece_points_x_r[piece_no][0]=0;
                piece_points_y_r[piece_no][0]=0;
                               
                // Point 1
                piece_points_x_r[piece_no][1]=0;
                piece_points_y_r[piece_no][1]=-s;
                
                 // Point 2
                piece_points_x_r[piece_no][2]=s;
                piece_points_y_r[piece_no][2]=0;
                
                // Point 3
                piece_points_x_r[piece_no][3]=s;
                piece_points_y_r[piece_no][3]=-(2*s);
                
                // Point 4
                piece_points_x_r[piece_no][4]=2*s;
                piece_points_y_r[piece_no][4]=-s;
                
                // Point 5
                piece_points_x_r[piece_no][5]=0;
                piece_points_y_r[piece_no][5]=s;
                
                // Point 6
                piece_points_x_r[piece_no][6]=s;
                piece_points_y_r[piece_no][6]=s;
                
                // Point 7
                piece_points_x_r[piece_no][7]=-s;
                piece_points_y_r[piece_no][7]=0;
                
                // Point 8
                piece_points_x_r[piece_no][8]=-s;
                piece_points_y_r[piece_no][8]=-s;
                
                
                // Setting Relative Centres of three squares
                
                // Square 0
                centre_x_r_sqr[piece_no][0]=s;
                centre_y_r_sqr[piece_no][0]=-s;
                
                //Square 1
                centre_x_r_sqr[piece_no][1]=s/2;
                centre_y_r_sqr[piece_no][1]=(s/2);
                
                //Square 2
                centre_x_r_sqr[piece_no][2]=-(s/2);
                centre_y_r_sqr[piece_no][2]=-(s/2);
                
                
                // Setting Relative Centres of middle black triangle
                centre_x_r_tri[piece_no]=(int)(s/(2*(Math.sqrt(2))));
                centre_y_r_tri[piece_no]=-(int)(s/(2*(Math.sqrt(2))));
        }
        else if(config==1)
        {
            
            // Point 0
                piece_points_x_r[piece_no][0]=0;
                piece_points_y_r[piece_no][0]=0;
                               
                // Point 1
                piece_points_x_r[piece_no][1]=s;
                piece_points_y_r[piece_no][1]=0;
                
                 // Point 2
                piece_points_x_r[piece_no][2]=0;
                piece_points_y_r[piece_no][2]=s;
                
                // Point 3
                piece_points_x_r[piece_no][3]=2*s;
                piece_points_y_r[piece_no][3]=s;
                
                // Point 4
                piece_points_x_r[piece_no][4]=s;
                piece_points_y_r[piece_no][4]=2*s;
                
                // Point 5
                piece_points_x_r[piece_no][5]=-s;
                piece_points_y_r[piece_no][5]=0;
                
                // Point 6
                piece_points_x_r[piece_no][6]=-s;
                piece_points_y_r[piece_no][6]=s;
                
                // Point 7
                piece_points_x_r[piece_no][7]=0;
                piece_points_y_r[piece_no][7]=-s;
                
                // Point 8
                piece_points_x_r[piece_no][8]=s;
                piece_points_y_r[piece_no][8]=-s;
                
                
                // Setting Relative Centres of three squares
                
                // Square 0
                centre_x_r_sqr[piece_no][0]=s;
                centre_y_r_sqr[piece_no][0]=s;
                
                //Square 1
                centre_x_r_sqr[piece_no][1]=-(s/2);
                centre_y_r_sqr[piece_no][1]=(s/2);
                
                //Square 2
                centre_x_r_sqr[piece_no][2]=(s/2);
                centre_y_r_sqr[piece_no][2]=-(s/2);
                
                
                // Setting Relative Centres of middle black triangle
                centre_x_r_tri[piece_no]=(int)(s/(2*(Math.sqrt(2))));
                centre_y_r_tri[piece_no]=(int)(s/(2*(Math.sqrt(2))));
                
                
        }
        else if(config==2)
        {
            
            // Point 0
                piece_points_x_r[piece_no][0]=0;
                piece_points_y_r[piece_no][0]=0;
                               
                // Point 1
                piece_points_x_r[piece_no][1]=0;
                piece_points_y_r[piece_no][1]=s;
                
                 // Point 2
                piece_points_x_r[piece_no][2]=-s;
                piece_points_y_r[piece_no][2]=0;
                
                // Point 3
                piece_points_x_r[piece_no][3]=-s;
                piece_points_y_r[piece_no][3]=2*s;
                
                // Point 4
                piece_points_x_r[piece_no][4]=-(2*s);
                piece_points_y_r[piece_no][4]=s;
                
                // Point 5
                piece_points_x_r[piece_no][5]=0;
                piece_points_y_r[piece_no][5]=-s;
                
                // Point 6
                piece_points_x_r[piece_no][6]=-s;
                piece_points_y_r[piece_no][6]=-s;
                
                // Point 7
                piece_points_x_r[piece_no][7]=s;
                piece_points_y_r[piece_no][7]=0;
                
                // Point 8
                piece_points_x_r[piece_no][8]=s;
                piece_points_y_r[piece_no][8]=s;
                
                
                // Setting Relative Centres of three squares
                
                // Square 0
                centre_x_r_sqr[piece_no][0]=-s;
                centre_y_r_sqr[piece_no][0]=s;
                
                //Square 1
                centre_x_r_sqr[piece_no][1]=-(s/2);
                centre_y_r_sqr[piece_no][1]=-(s/2);
                
                //Square 2
                centre_x_r_sqr[piece_no][2]=(s/2);
                centre_y_r_sqr[piece_no][2]=(s/2);
                
                
                // Setting Relative Centres of middle black triangle
                centre_x_r_tri[piece_no]=-(int)(s/(2*(Math.sqrt(2))));
                centre_y_r_tri[piece_no]=(int)(s/(2*(Math.sqrt(2))));
            
        }
        else // if config==3
        {
            // Point 0
                piece_points_x_r[piece_no][0]=0;
                piece_points_y_r[piece_no][0]=0;
                               
                // Point 1
                piece_points_x_r[piece_no][1]=-s;
                piece_points_y_r[piece_no][1]=0;
                
                 // Point 2
                piece_points_x_r[piece_no][2]=0;
                piece_points_y_r[piece_no][2]=-s;
                
                // Point 3
                piece_points_x_r[piece_no][3]=-(2*s);
                piece_points_y_r[piece_no][3]=-s;
                
                // Point 4
                piece_points_x_r[piece_no][4]=-(s);
                piece_points_y_r[piece_no][4]=-(2*s);
                
                // Point 5
                piece_points_x_r[piece_no][5]=s;
                piece_points_y_r[piece_no][5]=0;
                
                // Point 6
                piece_points_x_r[piece_no][6]=s;
                piece_points_y_r[piece_no][6]=-s;
                
                // Point 7
                piece_points_x_r[piece_no][7]=0;
                piece_points_y_r[piece_no][7]=s;
                
                // Point 8
                piece_points_x_r[piece_no][8]=-s;
                piece_points_y_r[piece_no][8]=s;
                
                
                // Setting Relative Centres of three squares
                
                // Square 0
                centre_x_r_sqr[piece_no][0]=-s;
                centre_y_r_sqr[piece_no][0]=-s;
                
                //Square 1
                centre_x_r_sqr[piece_no][1]=(s/2);
                centre_y_r_sqr[piece_no][1]=-(s/2);
                
                //Square 2
                centre_x_r_sqr[piece_no][2]=-(s/2);
                centre_y_r_sqr[piece_no][2]=(s/2);
                
                
                // Setting Relative Centres of middle black triangle
                centre_x_r_tri[piece_no]=-(int)(s/(2*(Math.sqrt(2))));
                centre_y_r_tri[piece_no]=-(int)(s/(2*(Math.sqrt(2))));
        }
        
        
        
    }
    
    
    
    // ********** Function to return relative  co-ordinates of 9 points *******************
    
    int[][] get_piece_relative_Points(int piece_no)
    {
        int rel_coord[][]= new int[9][2];
        
        for(int i=0;i<9;i++)
        {
            rel_coord[i][0]=piece_points_x_r[piece_no][i];
            rel_coord[i][1]=piece_points_y_r[piece_no][i];
            
        }
        return rel_coord;
        
    }
    
    
    // ********** Function to return relative  co-ordinates of piece centres *******************
    int [][] get_piece_centre_coordinates(int piece_no)
    {
        int cent_coord[][]= new int [4][2];
        
        // first 3 index will store square centre co-ordinates
        for(int i=0;i<3;i++)
        {
            cent_coord[i][0]=centre_x_r_sqr[piece_no][i];
            cent_coord[i][1]=centre_y_r_sqr[piece_no][i];
            
        }
              
        // last index(3th) will store triangle centre co-ordinates
        cent_coord[3][0]=centre_x_r_tri[piece_no];
        cent_coord[3][1]=centre_y_r_tri[piece_no];
        
        return(cent_coord);
        
    }
    
    // ********** Function to return colors of three squares *******************
    int[] get_piece_colors(int piece_no)
    {
        int piece_color[] = new int[3];
        for(int i=0;i<3;i++)
        {
            piece_color[i]=color_piece_sqr[piece_no][i];
        }
        
        return piece_color;
        
    }
    
}  // end of class
