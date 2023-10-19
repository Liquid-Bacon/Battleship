//@aleks

package Backend;

public class Grid
{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    private static final String HEADER_COLS = "  1 2 3 4 5 6 7 8 9 10";
    private static final String HEADER_ROWS = "ABCDEFGHIJ";

    private static final String[] STATUS_STRINGS = {"-", "X", "O"};

    // Direction constants
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    
    public Location[][] grid;

    // Create a new Grid. Initialize each Location in the grid
    // to be a new Location object.
    public Grid()
    {
        grid = new Location[NUM_ROWS][NUM_COLS];
        initGridValues();
    }

    // Initialize each Location in the grid to be a new Location object
    private void initGridValues()
    {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                grid[i][j] = new Location();
            }
        }
    }

    /**
     * This method can be called on your own grid. To add a ship
     * we will go at the ships location and mark a true value
     * in every location that the ship takes up.
     */
    public void addShip(Ship s)
    {
        int length = s.getLength();
        int direction = s.getDirection();
        int row = s.getRow();
        int col = s.getCol();
        
        if(direction == 1) 
        {
            for(int i = row; i < (s.getLength() + row); i++) 
            {
                setShip(i, col, true);
            }
        }
        else 
        {
            for(int j = col; j < (s.getLength() + col); j++) 
            {
                setShip(row, j, true);
            }
        }
    }



    public boolean checkHit(int row, int col)
    {
        if(grid[row][col].checkHit())
        {
            return true;
        }
        return false;
    }
    


    /**
     * Code to determine if ship is able to be placed at given location
     * Must check two things:
     *   if it goes off the grid
     *   if there is already a ship in the location
     */
   


    // Mark a hit in this location by calling the markHit method
    // on the Location object.
    public void markHit(int row, int col)
    {
        grid[row][col].markHit();
    }


    // Mark a miss on this location.
    public void markMiss(int row, int col)
    {
        grid[row][col].markMiss();
    }


    // Set the status of this location object.
    public void setStatus(int row, int col, int status)
    {
        grid[row][col].setStatus(status);
    }


    // Get the status of this location in the grid
    public int getStatus(int row, int col)
    {
        return grid[row][col].getStatus();
    }


    // Return whether or not this Location has already been guessed.
    public boolean alreadyGuessed(int row, int col)
    {
        if(grid[row][col].isUnguessed())
        {
            return false;
          
        }
        return true;
    }


    // Set whether or not there is a ship at this location to the val
    public void setShip(int row, int col, boolean val)
    {
        grid[row][col].setShip(val);
    }


    // Return whether or not there is a ship here
    public boolean hasShip(int row, int col)
    {
        if(grid[row][col].hasShip())
        {
            return true;
        }
        return false;
    }


    // Get the Location object at this row and column position
    public Location get(int row, int col)
    {
        return grid[row][col];
    }


    // Return the number of rows in the Grid
    public int numRows()
    {
        return NUM_ROWS;
    }


    // Return the number of columns in the grid
    public int numCols()
    {
        return NUM_COLS;
    }


    // Print the Grid status including a header at the top
    // that shows the columns 1-10 as well as letters across
    // the side for A-J
    // If there is no guess print a -
    // If it was a miss print a O
    // If it was a hit, print an X
    // A sample print out would look something like this:
    //
    //   1 2 3 4 5 6 7 8 9 10
    // A - - - - - - - - - -
    // B - - - - - - - - - -
    // C - - - O - - - - - -
    // D - O - - - - - - - -
    // E - X - - - - - - - -
    // F - X - - - - - - - -
    // G - X - - - - - - - -
    // H - O - - - - - - - -
    // I - - - - - - - - - -
    // J - - - - - - - - - -
    public String[][] getGridStatus()
    {
        String[][] blah = new String[10][11];
        
        for(int q = 0; q < 10; q++)
        {
            blah[q][0] = HEADER_ROWS.substring(q, q + 1);
        }
        
        
        for(int i = 0; i < NUM_ROWS; i++)
        { 
                for(int j = 1; j < NUM_COLS + 1; j++)
                {
                
                    if(grid[i][j - 1].getStatus() == 1)
                    {
                        blah[i][j] = "✖";
                    }
                    if(grid[i][j - 1].getStatus() == 2)
                    {
                        blah[i][j] = "○";
                    }
                    else if(grid[i][j - 1].getStatus() == 0)
                    {
                        blah[i][j] = "■";
                    }
                }
              
        }
        return blah;
    }
    
   


    // Print the grid and whether there is a ship at each location.
    // If there is no ship, you will print a - and if there is a
    // ship you will print a X. You can find out if there was a ship
    // by calling the hasShip method.
    //
    //   1 2 3 4 5 6 7 8 9 10
    // A - - - - - - - - - -
    // B - X - - - - - - - -
    // C - X - - - - - - - -
    // D - - - - - - - - - -
    // E X X X - - - - - - -
    // F - - - - - - - - - -
    // G - - - - - - - - - -
    // H - - - X X X X - X -
    // I - - - - - - - - X -
    // J - - - - - - - - X -
    public String[][] getGridShips()
    {
       
        String[][] blah = new String[10][11];
        for(int q = 0; q < 10; q++)
        {
            blah[q][0] = HEADER_ROWS.substring(q, q + 1);
        }
        
        
        
        
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 1; j < NUM_COLS + 1; j++)
            {
                
                if(getStatus(i, j - 1) == Location.HIT)
                {
                    blah[i][j] = "✖";
                }
                if(getStatus(i, j - 1) == Location.MISSED)
                {
                    blah[i][j] = "○";
                }
                if(getStatus(i, j - 1) == Location.UNGUESSED)
                {
                    if(hasShip(i, j - 1))
                    {
                        blah[i][j] = "𝕊";
                    }
                    else
                    {
                        blah[i][j] = "■";
                    }
                }
                
                
                
            }
            
        }
        return blah;
    }
}
