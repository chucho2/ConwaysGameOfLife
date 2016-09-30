import java.util.Random;

/**
 * is in charge of the logical rules of Conways game of life. The Class can create the game, and
 * update the game within itself.
 */
public class Conway {

    /*The grid which holds the game*/
    private Cell[][][] grid;
    /*Holds the chance of a grid point having a live cell at spawn*/
    private Random chance;

    int[] r;

    /*How do I document this? haha...*/
    {
        chance = new Random();
    }

    /**
     * Builds a blank 30x30x30 grid, then randomly populates it to start the game.
     */
    public Conway(int[] r)
    {
        this.r = r;
        if(r.length != 4)throw new UnsupportedOperationException("r should have 4 parameters");
        grid = new Cell[32][32][32];
        chance = new Random();
        createGrid();

    }

    /**
     * is to be used if a preset grid is already available or wanted to be created.
     * @param preset a grid with values located at the (x,y,z) co-ords to make cool preset grids.
     */
    public Conway(Cell[][][] preset)
    {
        grid = preset;
    }

    /**
     * when called, itterates to the next version of the grid in the game.
     */
    public void step()
    {
        Cell[][][] newGrid = new Cell[32][32][32];
        for(int i = 1;i<30;i++)
        {
            for(int j=1;j<30;j++)
            {
                for(int k = 1;k<30;k++)
                {
                    int neighbors = 0;
                    if(grid[i][j][k]!= null)
                    {
                        newGrid[i][j][k] = new Cell();
                        newGrid[i][j][k].setAlive();
                    }


                    if(grid[i+1][j][k] != null)neighbors++;
                    if(grid[i-1][j][k] != null)neighbors++;
                    if(grid[i][j+1][k] != null)neighbors++;
                    if(grid[i][j-1][k] != null)neighbors++;
                    if(grid[i][j][k+1] != null)neighbors++;
                    if(grid[i][j][k-1] != null)neighbors++;
                    if(neighbors>= 2 && neighbors<= 6)
                    {
                        newGrid[i][j][k] = new Cell();
                        newGrid[i][j][k].setAlive();
                        System.out.println("This is a bullshit test");
                    }
                    if(neighbors<= 2 || neighbors >=6)
                    {
                        newGrid[i][j][k] = null;
                    }
                }
            }
        }
        grid = newGrid;
    }

    /**
     * @return the grid in its current state.
     */
    public Cell[][][] getGrid()
    {
        return grid;
    }

    /*Sets up a non-preset grid for us.*/
    private void createGrid()
    {
        for(int i = 1;i<30;i++)
        {
            for(int j=1;j<30;j++)
            {
                for(int k = 1;k<30;k++)
                {
                    //There is currently a 5% chance of a cube spawning at any given location
                    if(chance.nextInt(10) == 0)
                    {
                        grid[i][j][k] = new Cell();
                        grid[i][j][k].setAlive();
                    }
                }
            }
        }
    }

}
