//@aleks

package Backend;

public class Player
{
    // Static constants for the Player class
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public static final int NUM_SHIPS = 5;
    public static final int MAX_HITS = computeMaxHits();
    public int curShipIndex = 0;

    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    // Direction constants
    private static final int UNSET = -1;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;


    private Grid myGrid;
    private Grid opponentGrid;
    private Ship[] myShips;
    private int numShips;
    private int totalHitsTaken;
    private int totalHitsDelivered;


    public Player()
    {
        myGrid = new Grid();
        opponentGrid = new Grid();
        myShips = new Ship[NUM_SHIPS];
        numShips = 0;
        totalHitsTaken = 0;
        totalHitsDelivered = 0;
    }

    public int getShipLength()
    {
        return SHIP_LENGTHS[curShipIndex];
    }
    
    public static int computeMaxHits()
    {
        int b = 0;
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            b = b + SHIP_LENGTHS[i];
        }
        return b;
    }

    public Grid getGrid()
    {
        return myGrid;
    }
    
    public int numShips()
    {
        return numShips;
    }
    
    public int opponentHitsRemaining()
    {
        return totalHitsTaken;
    }
    public int winn()
    {
        return totalHitsDelivered;
    }
    
    /**
     * Method to check if location is within the grid
     */
    public boolean inBounds(int row, int col)
    {
        if(col < NUM_COLS && row < NUM_ROWS)
        {
            return true;
        }
        return false;
    }


    public boolean canPlaceShip(Ship s)
    {
        int length = s.getLength();
        int direction = s.getDirection();
        int row = s.getRow();
        int col = s.getCol();
        
        if(direction == 1) 
        {
            for(int i = row; i < (s.getLength() + row); i++) 
            {
                if(!inBounds(i, col) || myGrid.hasShip(i, col))
                {
                    return false;
                }
            }
        }
        else 
        {
            for(int j = col; j < (s.getLength() + col); j++) 
            {
                if(!inBounds(row, j) || myGrid.hasShip(row, j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    
    /*public void initializeShipsRandomly()
    {
        for(int i = 0; i < Player.NUM_SHIPS; i++)
        {
                int curLength = Player.SHIP_LENGTHS[i];
                int dire = Randomizer.nextInt(2);
                int rowe = Randomizer.nextInt(10);
                int cole = Randomizer.nextInt(10);
                Ship s = new Ship(curLength);
                s.setDirection(dire);
                s.setLocation(rowe, cole);
                if(computer.canPlaceShip(s))
                {
                    computer.addShip(s);
                }
                else
                {
                    i = i - 1;
                }
        } 
    }
    */
    
    
    
    // Adds a ship if it's a legal placement
    // Returns whether the ship was successfully added
    public boolean addShip(int row, int col, int dir)
    {
        
        Ship ship = new Ship(SHIP_LENGTHS[curShipIndex]);
        ship.setLocation(row, col);
        ship.setDirection(dir);
        if(numShips < 5)
        {
            
            if(canPlaceShip(ship) == true)
            {
                myGrid.addShip(ship);
                myShips[numShips] = ship;
                numShips++;
                curShipIndex++;
                return true;
            }
        }
        return false;
    }

    public void setOpponentGrid(Grid grid)
    {
        opponentGrid = grid;
    }

    public int getRandomRowGuess()
    {
        int rowGuess = Randomizer.nextInt(10);
        return rowGuess;
    }


    public int getRandomColGuess()
    {
        int colGuess = Randomizer.nextInt(10);
        return colGuess;
    }
    
    

    public boolean beenGuessed(int row, int col)
    {
        if(opponentGrid.alreadyGuessed(row, col))
        {
            return true;
        }
        return false;
    }

    public boolean makeGuess(int row, int col, Player other)
    {
        if(other.myGrid.hasShip(row, col))
        {
            return true;
        }
        return false;
    }
    
    


    public void markGuess(int row, int col, boolean val)
    {
        opponentGrid.setShip(row, col, val);
        if(val == true)
        {
            opponentGrid.markHit(row, col);
            totalHitsDelivered++;
        }
        else
        {
            opponentGrid.markMiss(row, col);
        }
    }
    
    
    public boolean computerMakeGuess(int row, int col, Player other)
    {
        if(other.myGrid.hasShip(row, col))
        {
            return true;
        }
        return false;
    }
    
    


    public void computerMarkGuess(int row, int col, boolean val)
    {
        opponentGrid.setShip(row, col, val);
        if(val == true)
        {
            opponentGrid.markHit(row, col);
            totalHitsTaken++;
        }
        else
        {
            opponentGrid.markMiss(row, col);
        }
    }

    
    
    

    /*
     * Takes in an opponent guess for a row, col location.
     * Records the guess, and returns a boolean indicating
     * whether the guess was a hit.
     */
     
    

    



    


   


    public boolean hasWon()
    {
        if(totalHitsDelivered == computeMaxHits())
        {
            return true;
        }
        return false;
    }


    public boolean hasLost()
    {
        if(totalHitsTaken == computeMaxHits())
        {
            return true;
        }
        return false;
    }

    
    /*public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        
        s.setLocation(row, col);
        s.setDirection(direction);
        addShip(s);
    }
    */
}
