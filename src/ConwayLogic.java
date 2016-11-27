import java.util.ArrayList;
import java.util.Random;

/**
 * @author Demitri Maestas
 */
public class ConwayLogic {

    /*The grid which holds the game*/
    private Cell[][][] grid;

    private ArrayList<Cell> liveCells;
    private ArrayList<Cell> deadCells;
    private ArrayList<Cell> fixedCells;

    //The Number generator for a random game
    private Random chance = new Random();

    //This is the default value for r, as per the spec.
    int[] r = {3,3,3,2};

    //The default dimension for the grid. Sorry, not making this three. Haha.
    private int gridDimension = 30;

    /*How do I document this? haha...*/
    {
      liveCells = new ArrayList<Cell>();
      deadCells = new ArrayList<Cell>();
      fixedCells = new ArrayList<Cell>();

    }

    /**
     * Builds a blank 30x30x30 grid, then randomly populates it to start the game.
     */
    public ConwayLogic()
    {
        grid = new Cell[gridDimension+1][gridDimension+1][gridDimension+1];
        for(int x = 0;x<gridDimension+1; x++)
        {
            for(int y = 0;y<gridDimension+1; y++)
            {
                for(int z = 0;z<gridDimension+1; z++)
                {
                    int[] position = new int[] {x,y,z};


                    for(int i = 0; i<3;i++)
                    {
                        //Adding the buffer Cells
                        if(position[i] == 0 || position[i] == gridDimension)
                        {
                            grid[x][y][z] = new Cell(Cell.CellState.BUFFER, position);
                        }

                        //Adding the regular cells
                        else
                        {
                            grid[x][y][z] = new Cell(position);
                            if(chance.nextInt(100) == 0)
                            {
                                grid[x][y][z].setAlive();
                                liveCells.add(grid[x][y][z]);
                            }
                        }
                        }

                    }
                }
            }
        }


    /**
     * is to be used if a preset grid is already available or wanted to be created.
     * @param presetIndex the index number of the preset grid requested.
     */
    public ConwayLogic(int presetIndex)
    {

    }

    /**
     * when called, itterates to the next version of the grid in the game.
     */
    public void step()
    {
        liveCells = new ArrayList<Cell>();
    }

    /**
     * @return the grid in its current state of the game.
     */
    public Cell[][][] getGrid()
    {
        return grid;
    }

    public int getGridDimension()
    {
        return this.gridDimension;
    }


    public ArrayList<Cell> getliveCells()
    {
        return liveCells;
    }

    public ArrayList<Cell> getDeadCells()
    {
        return deadCells;
    }
    public ArrayList<Cell> getFixedCells()
    {
        return fixedCells;
    }


    /*Sets up a non-preset grid for us.*/
    private void createGrid()
    {

    }

}
