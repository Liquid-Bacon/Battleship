//@aleks

package Backend;
public class Battleship
{

    
}
/*    private static final int MAX_COL = 10;
    private static final char MAX_ROW = 'J';

    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;


    public void run()
    {
        System.out.println("=======================\nWelcome to Battle Ship\n=======================");
        Player human = new Player();
        Player computer = new Player();
        setUpShips(human, computer);
        human.printMyShips();
        boolean gameOver = false;
        while(!gameOver)
        {
            gameOver = playRound(human, computer);
        }
        if(human.hasWon())
        {
            System.out.println("You won!");
        }
        else
        {
            System.out.println("You lost!");
        }
        System.out.println("Thanks for playing!");
    }
    /**
     * Method to do the following:
     *   Call a method to place the human ships onto the grid
     *   Call a method to place the computer ships on the grid randomly
     */ 
    /*private void setUpShips(Player human, Player computer)
    {
        initializeShipsFromInput(human);
        initializeShipsRandomly(computer);
    }
    /**
     * Method should loop through 5 times to place all ships, each time showing 
     * the current ship placement.  Each loop the user will choose a location of
     * the ship to be placed.  If the location is invalid, it will prompt them
     * to enter another location
     */ 

    /*public void initializeShipsFromInput(Player player)
    {
        ArrayList<Integer> ships = new ArrayList<Integer>();
        
        for(int j = 0; j < SHIP_LENGTHS.length; j++)
            {
                  
                ships.add(SHIP_LENGTHS[j]);
                    
            }
                
        for(int i = 0; i < Player.NUM_SHIPS; i++)
        {
                player.printMyShips();
                int chosenLength = 0;
                int r = 0;
                while(r == 0)
                {
                    chosenLength = readInt("Choose a ship to place. You have " + ships + " available: ");
                    for(int h = 0; h < ships.size(); h++)
                    {
                        if(ships.get(h) == chosenLength)
                        {
                            r++;
                        }
                    }
                }
                int curLength = chosenLength;
                System.out.println("You are now placing your " + curLength + " length ship");
                int dir = readDirection();
                int row = readRow();
                int col = readCol();
                Ship s = new Ship(curLength);
                s.setDirection(dir);
                s.setLocation(row, col);
                if(player.canPlaceShip(s))
                {
                    player.addShip(s);
                    for(int m = 0; m < ships.size(); m++)
                    {
                        if(ships.get(m) == chosenLength)
                        {
                            ships.remove(m);
                        }
                    }
                }
                else
                {
                    readLine("Please enter valid locations(Enter to continue)");
                    i = i - 1;
                }
        }
    }
    
    public void initializeShipsRandomly(Player computer)
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
    
    /**
     * Method to take in user input (in many formats) and convert it to the 
     * direction of the ship, as long as the direction entered was valid.
     */ 
    /*private int readDirection()
    {
        String hor = "horizontal";
        String ver = "vertical";
        String v = "v";
        String h = "h";
        String input = readLine("Enter a direction (horizontal or vertical)");
        if(input.equals(hor) || input.equals(h))
        {
            return 0;
        }
        if(input.equals(ver) || input.equals(v))
        {
            return 1;
        }
        else
        {
            System.out.println("Try again");
            readDirection();
        }
        return 0;
    }
    /**
     * Method to get user input as to which column the wish to place their ship
     * This method should verify the user input is valid (ie. within numerical range)
     */
    /*private int readCol()
    {
        int input = readInt("Enter a column number(1-10)");
        if(input < 0 || input > 10)
        {
            System.out.println("Try again");
            readCol();
        }
        return input - 1;
    }
    /**
     * Method to get user input as to which row the wish to place their ship
     * This method should verify the user input is valid (ie. within alphabet range)
     */
    /*private int readRow()
    {
        int index = -1;
        String letters ="ABCDEFGHIJ";
        while(index == -1)
        {
            String input = readLine("Enter a row letter(A-J)");
            if(input.equals(""))
            {
                System.out.println("Try again");
            }
            else
            {
                index = letters.indexOf(Character.toUpperCase(input.charAt(0)));
            }
        }
        return index;
    }
    // Plays a round of battle ship, returns true if the game
    // is over, false otherwise.
    private boolean playRound(Player human, Player computer)
    {
        humanTurn(human, computer);
        if(human.hasWon() || human.hasLost())
        {
            return true;
        }
        computerTurn(human, computer);
        return false;
    }
    /**
     * Randomly get a computer guess and make guess, then mark as hit or miss.
     * Make sure to print to the screen what happened.
     */ 
    /*private void computerTurn(Player human, Player computer)
    {
        int count  = 0;
        String letters = "ABCDEFGHIJ";
        human.printMyShips();
        readLine("Enter to see computer's guess");
        while(count == 0)
        {
            
            int row = human.getRandomRowGuess();
            int col = human.getRandomColGuess();
            
            boolean guessedalready = computer.beenGuessed(row, col);
            if(guessedalready == false)
            {
                System.out.println("Computer guess at " + letters.charAt(row) + ", " + (col + 1));
                if(human.recordOpponentGuess(row, col) == true)
                {
                    System.out.println("It was a hit!");
                    human.printOpponentGuesses();
                }
                else
                {
                    System.out.println("It was a miss!");
                    human.printOpponentGuesses();
                }
                count++;
            }
        }
    }
    /**
     * Allow user to guess a row and column, then make guess and 
     * mark as hit or miss.
      
    
/* private void humanTurn(Player human, Player computer)
    {
        int alreadyguessed = 0;
        readLine("Enter to play your turn");
        while(alreadyguessed == 0)
        {
            human.printMyGuesses();
            human.printHitsDelivered();
        
            int roww = readRow();
            int coll = readCol();
            boolean guessedalready = human.beenGuessed(roww, coll);
            if(guessedalready == false)
            {
                boolean aguess = human.makeGuess(roww, coll, computer);
                human.markGuess(roww, coll, aguess);
                human.printMyGuesses();
                if(aguess == true)
                {
                    System.out.println("It was a hit!");
                }
                else
                {
                    System.out.println("It was a miss!");
                }
                alreadyguessed++;
            }
            if(guessedalready == true)
            {
                System.out.println("========================");
                System.out.println("Location already guessed");
            }
        }
    }
*/
