/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Taneja
 */

import java.util.ArrayList;
        
public class ModelGameInfo {
    
    
    // Game state = 0
    //  Game Being started after compilation
    // show Logo and all
    
    // Game state 1
    // Game Restart state ( show how many players buttons on canvas)
    
    // Game state 2
    // Normal game progression
    
    // game state 3
    // Game is over and game over is displayed on screen
    
    
    
    
    int game_state;
    
    
    
    static boolean is_game_over;    
    
    int score[] = new int[3];
    
    int color_strip[] = new int [3];
    
    int color_strip_array[][][]= new int [3][20][30]; // color_no, no of strips, elements in each strip
    
    // no  of elements in each strip of each color
    int color_strip_num[][]= new int[3][20];
   
    // Constructor
    public ModelGameInfo()
    {
        game_state=2;
        is_game_over=false;
        
        for(int i=0;i<3;i++)
        {
            score[i]=0;
            for(int j=0;j<20;j++)
                color_strip_num[i][j]=-1;
            
        }
        
        get_color_strip_no();
    }
    
    // Function to get no of color strips of each type
    void get_color_strip_no()
    {
        
        
        System.out.println("\n Color Strip[0] : "+ color_strip[0]+ "   color_strip[1]: " +color_strip[1]+ "   color_strip[2]: "+ color_strip[2]);
    }
    
    
    // FUNCTION TO CALL WHEN GAME IS RESTARTED
    void restart_game_settings()
    {
        game_state=1;
        is_game_over=false;
    }
    
    void set_game_state(int a)
    {
        game_state=a;
    }
    
    int get_game_state()
    {
        return game_state;
    }
    
    
    
    
    
    void set_is_game_over(boolean a)
    {
        is_game_over=a;
    }
    
    
    
    boolean get_is_game_over()
    {
        return is_game_over;
    }
}
