/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh
 */
public class ModelBoard {
    
   // ***** Boolean structures required for various shapes in board structure  ****
        
        boolean matrix_brd_sqr[][]= new boolean[14][14];
        
        boolean matrix_brd_left[][]= new boolean[14][14];
        boolean matrix_brd_right[][]= new boolean[14][14];
        boolean matrix_brd_up[][]= new boolean[14][14];
        boolean matrix_brd_down[][]= new boolean[14][14];
        
        boolean matrix_brd_config[][][]= new boolean[14][14][4];
        
        
        
        
    // ==========================   CLASS  CONSTRUCTOR  ==============================    
    public ModelBoard()
    {
        for(int i=0;i<14;i++)
        {
            for(int j=0;j<14;j++)
            {
                matrix_brd_sqr[i][j]=true;
                
                matrix_brd_left[i][j]=true;
                matrix_brd_right[i][j]=true;
                matrix_brd_up[i][j]=true;
                matrix_brd_down[i][j]=true;
                
                
                for(int k=0;k<4;k++)
                {
                    matrix_brd_config[i][j][k]=true;
                }
                
                
            }
            
        }
        
    }// end of constructor
    
    
    // Function to set value of Matrix square
    void set_brd_sqr(int i, int j, boolean b)
    {
        matrix_brd_sqr[i][j]=b;
    }
    
    
    // Function to set value of Matrix left
    void set_brd_left(int i, int j, boolean b)
    {
        matrix_brd_left[i][j]=b;
    }
    
    
    // Function to set value of Matrix right
    void set_brd_right(int i, int j, boolean b)
    {
        matrix_brd_right[i][j]=b;
    }
    
    
    
    // Function to set value of Matrix up
    void set_brd_up(int i, int j, boolean b)
    {
        matrix_brd_up[i][j]=b;
    }
    
    
    
    // Function to set value of Matrix down
    void set_brd_down(int i, int j, boolean b)
    {
        matrix_brd_down[i][j]=b;
    }
    
    
    // Function to set value of Matrix config
    void set_brd_config(int i, int j,int k, boolean b)
    {
        matrix_brd_config[i][j][k]=b;
    }
    
    
    // function to return value of Matrix square
    boolean get_brd_sqr(int i, int j)
    {
        return matrix_brd_sqr[i][j];
    }
    
    
    // function to return value of Matrix left
    boolean get_brd_left(int i, int j)
    {
        return matrix_brd_left[i][j];
    }
    
    
    // function to return value of Matrix right
    boolean get_brd_right(int i, int j)
    {
        return matrix_brd_right[i][j];
    }
    
    // function to return value of Matrix up
    boolean get_brd_up(int i, int j)
    {
        return matrix_brd_up[i][j];
    }
    
    
    // function to return value of Matrix down
    boolean get_brd_down(int i, int j)
    {
        return matrix_brd_down[i][j];
    }
    
     // function to return value of Matrix config
    boolean get_brd_down(int i, int j,int k)
    {
        return matrix_brd_config[i][j][k];
    }
    
    
    
    
    // Function to check left board matrix
    boolean check_brd_left(int i, int j,int config)
    {
        int k=0;
        
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            if(get_brd_left(i,j) && get_brd_left(i, j-1) && get_brd_left(i,j+1))
                k++;
            
            // Check above row
            if(get_brd_left(i-1,j+1))
                k++;
            
            //Check below row
            if(get_brd_left(i+1,j))
                k++;
            
            if(k==3)
                return true;
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13)// if piece configuration is 1
        {
            
            // Check same row
            if(get_brd_left(i,j) && get_brd_left(i, j-1) && get_brd_left(i,j+1))
                k++;
            
            // Check above row
            if(get_brd_left(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_left(i+1,j+1))
                k++;
            
            if(k==3)
                return true;
            
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            if(get_brd_left(i,j) && get_brd_left(i, j+1))
                k++;
            
            // Check above row
            if(get_brd_left(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_left(i+1,j))
                k++;
            
            if(k==3)
                return true;
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 3
        {
            
            // Check same row
            if(get_brd_left(i,j) && get_brd_left(i, j+1))
                k++;
            
            // Check above row
            if(get_brd_left(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_left(i+1,j))
                k++;
            
            if(k==3)
                return true;
            
        }
        
        return false;
        
    } // end of  function
    
    
    
    
    
    
    // Function to update left board matrix
    void update_brd_left(int i, int j,int config)
    {
        
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            set_brd_left(i,j,false);
            set_brd_left(i, j-1,false);
            set_brd_left(i,j+1,false);
               
            
            // Check above row
            set_brd_left(i-1,j+1,false);
                
            
            //Check below row
            set_brd_left(i+1,j,false);
               
            
            
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13)// if piece configuration is 1
        {
            
            // Check same row
            set_brd_left(i,j,false);
            set_brd_left(i, j-1,false);
            set_brd_left(i,j+1, false);
                
            
            // Check above row
            set_brd_left(i-1,j,false);
               
            
            //Check below row
            set_brd_left(i+1,j+1,false);
                
            
           
            
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            set_brd_left(i,j,false);
            set_brd_left(i, j+1,false);
               
            
            // Check above row
            set_brd_left(i-1,j,false);
              
            
            //Check below row
            set_brd_left(i+1,j,false);
               
            
           
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 3
        {
            
            // Check same row
            set_brd_left(i,j,false);
            set_brd_left(i, j+1,false);
               
            
            // Check above row
            set_brd_left(i-1,j,false);
                
            
            //Check below row
            set_brd_left(i+1,j,false);
             
            
            
            
        }
        
       
        
    } // end of  function
    
    
    
    
    
    
    
    
     // Function to check right board matrix
    boolean check_brd_right(int i, int j,int config)
    {
        int k=0;
        
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            if(get_brd_right(i,j) && get_brd_right(i, j-1))
                k++;
            
            // Check above row
            if(get_brd_right(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_right(i+1,j))
                k++;
            
            if(k==3)
                return true;
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 1
        {
            
            // Check same row
            if(get_brd_right(i,j) && get_brd_right(i, j-1))
                k++;
            
            // Check above row
            if(get_brd_right(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_right(i+1,j))
                k++;
            
            if(k==3)
                return true;
            
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            if(get_brd_right(i,j) && get_brd_right(i, j-1)&& get_brd_right(i,j+1))
                k++;
            
            // Check above row
            if(get_brd_right(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_right(i+1,j-1))
                k++;
            
            if(k==3)
                return true;
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 3
        {
            
            // Check same row
            if(get_brd_right(i,j) && get_brd_right(i, j-1) && get_brd_right(i,j+1))
                k++;
            
            // Check above row
            if(get_brd_right(i-1,j-1))
                k++;
            
            //Check below row
            if(get_brd_right(i+1,j))
                k++;
            
            if(k==3)
                return true;
            
        }
        
        return false;
        
    } // end of  function
    
    
    
    
     // Function to update right board matrix
    void update_brd_right(int i, int j,int config)
    {
        
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            set_brd_right(i,j,false);
            set_brd_right(i, j-1,false);
                
            
            // Check above row
            set_brd_right(i-1,j,false);
                
            
            //Check below row
            set_brd_right(i+1,j,false);
               
            
            
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 1
        {
            
            // Check same row
            set_brd_right(i,j,false);
            set_brd_right(i, j-1,false);
                
            
            // Check above row
            set_brd_right(i-1,j,false);
               
            
            //Check below row
            set_brd_right(i+1,j,false);
               
            
           
            
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            set_brd_right(i,j,false);
            set_brd_right(i, j-1,false);
            set_brd_right(i,j+1,false);
               
            
            // Check above row
            set_brd_right(i-1,j,false);
               
            
            //Check below row
            set_brd_right(i+1,j-1,false);
                
            
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 3
        {
            
            // Check same row
            set_brd_right(i,j,false);
            set_brd_right(i, j-1,false);
            set_brd_right(i,j+1,false);
               
            
            // Check above row
            set_brd_right(i-1,j-1,false);
               
            
            //Check below row
            set_brd_right(i+1,j,false);
               
            
           
            
        }
        
       
        
    } // end of  function
    
    
    
    
     // Function to check up board matrix
    boolean check_brd_up(int i, int j,int config)
    {
        int k=0;
        
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            if(get_brd_up(i,j) && get_brd_up(i, j-1) && get_brd_up(i,j+1))
                k++;
            
            
            //Check below row
            if(get_brd_up(i+1,j))
                k++;
            
            if(k==2)
                return true;
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 1
        {
            
            // Check same row
            if(get_brd_up(i,j) && get_brd_up(i, j-1))
                k++;
            
            // Check above row
            if(get_brd_up(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_up(i+1,j) && get_brd_up(i+1,j+1))
                k++;
            
            if(k==3)
                return true;
            
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            if(get_brd_up(i,j) && get_brd_up(i,j+1))
                k++;
            
            // Check above row
            if(get_brd_up(i-1,j))
                k++;
            
            //Check below row
            if(get_brd_up(i+1,j) && get_brd_up(i+1,j-1))
                k++;
            
            if(k==3)
                return true;
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 3
        {
            
            // Check same row
            if(get_brd_up(i,j) && get_brd_up(i, j-1) && get_brd_up(i,j+1))
                k++;
            
            //Check below row
            if(get_brd_up(i+1,j))
                k++;
            
            if(k==2)
                return true;
            
        }
        
        return false;
        
    } // end of  function
    
    
    
    
    
    // Function to check up board matrix
    void update_brd_up(int i, int j,int config)
    {
        
        
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            set_brd_up(i,j,false);
            set_brd_up(i, j-1,false);
            set_brd_up(i,j+1,false);
               
            
            
            //Check below row
            set_brd_up(i+1,j,false);
               
           
               
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 1
        {
            
            // Check same row
            set_brd_up(i,j,false);
            set_brd_up(i, j-1,false);
               
            
            // Check above row
            set_brd_up(i-1,j,false);
               
            
            //Check below row
            set_brd_up(i+1,j,false);
            set_brd_up(i+1,j+1,false);
                
            
           
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            set_brd_up(i,j,false);
            set_brd_up(i,j+1,false);
               
            
            // Check above row
            set_brd_up(i-1,j,false);
               
            
            //Check below row
            set_brd_up(i+1,j,false);
            set_brd_up(i+1,j-1,false);
               
            
           
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 3
        {
            
            // Check same row
            set_brd_up(i,j,false);
            set_brd_up(i, j-1,false);
            set_brd_up(i,j+1,false);
                
            
            //Check below row
            set_brd_up(i+1,j,false);
               
            
        }
        
        
        
    } // end of  function
    
    
    
    
    
    
     // Function to check down board matrix
    boolean check_brd_down(int i, int j,int config)
    {
        int k=0;
        
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            if(get_brd_down(i,j) && get_brd_down(i, j-1))
                k++;
            
            // Check above row
            if(get_brd_down(i-1,j) && get_brd_down(i-1,j+1))
                k++;
            
            //Check below row
            if(get_brd_down(i+1,j))
                k++;
            
            if(k==3)
                return true;
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 1
        {
            
            // Check same row
            if(get_brd_down(i,j) && get_brd_down(i, j-1) && get_brd_down(i,j+1))
                k++;
            
            // Check above row
            if(get_brd_down(i-1,j))
                k++;
           
            
            if(k==2)
                return true;
            
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            if(get_brd_down(i,j) && get_brd_down(i, j-1) && get_brd_down(i, j+1))
                k++;
            
            // Check above row
            if(get_brd_down(i-1,j))
                k++;
            
            
            
            if(k==2)
                return true;
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13)// if piece configuration is 3
        {
            
            // Check same row
            if(get_brd_down(i,j) && get_brd_down(i, j+1))
                k++;
            
            // Check above row
            if(get_brd_down(i-1,j)&& get_brd_down(i-1,j-1))
                k++;
            
            //Check below row
            if(get_brd_down(i+1,j))
                k++;
            
            if(k==3)
                return true;
            
        }
        
        return false;
        
    } // end of  function
    
    
    
    
    
      // Function to check down board matrix
    void update_brd_down(int i, int j,int config)
    {
       
        // if piece configuration is 0
        if(config==0 && i>0 && i<13 && j>0 && j<13)
        {
            // Check same row
            set_brd_down(i,j,false);
            set_brd_down(i, j-1,false);
              
            
            // Check above row
            set_brd_down(i-1,j,false);
            set_brd_down(i-1,j+1,false);
               
            
            //Check below row
            set_brd_down(i+1,j,false);
                
            
        }
        else if(config==1 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 1
        {
            
            // Check same row
            set_brd_down(i,j,false);
            set_brd_down(i, j-1,false);
            set_brd_down(i,j+1,false);
               
            
            // Check above row
            set_brd_down(i-1,j,false);
               
           
            
            
            
        }
        else if(config==2 && i>0 && i<13 && j>0 && j<13) // if piece configuration is 2
        {
            
            // Check same row
            set_brd_down(i,j,false);
            set_brd_down(i, j-1,false);
            set_brd_down(i, j+1,false);
                
            
            // Check above row
            set_brd_down(i-1,j,false);
                
            
            
            
            
            
        }
        
        else if(config==3 && i>0 && i<13 && j>0 && j<13)// if piece configuration is 3
        {
            
            // Check same row
            set_brd_down(i,j,false);
            set_brd_down(i, j+1,false);
               
            
            // Check above row
            set_brd_down(i-1,j,false);
            set_brd_down(i-1,j-1,false);
               
            
            //Check below row
            set_brd_down(i+1,j,false);
               
            
            
            
        }
        
       
        
    } // end of  function
    
    
    
    
    
    
    // ************** FUNCTION TO CHECK WHETHER PIECE CAN BE PLACED OR NOT  ******************
    boolean check_can_piece_placed(int hc_i, int hc_j, int config)
    {
        if(check_brd_left(hc_i,hc_j,config) &&  check_brd_right(hc_i,hc_j,config) && check_brd_up(hc_i,hc_j,config) && check_brd_down(hc_i,hc_j,config) && hc_i>0 && hc_i<14 && hc_j> 0 && hc_j<14)
            return true;
        else
            return false;
    }
    
    
    // ************** FUNCTION TO UPDATE BOARD MATRICES AFTER PLACING OF A PIECE  ******************
    void update_brd_aftr_piece_placement(int hc_i, int hc_j, int config)
    {
        update_brd_left(hc_i,hc_j,config);
        update_brd_right(hc_i,hc_j,config);
        update_brd_up(hc_i,hc_j,config);
        update_brd_down(hc_i,hc_j,config);
        
    }
    
    
    
    
    
    
    
}
