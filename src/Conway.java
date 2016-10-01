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

    /*The values from the R sliders as the game progresses*/
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
        /*This is the grid that we will pass the pointer to to show on the screen*/
        Cell[][][] newGrid = new Cell[32][32][32];


        for(int i = 1;i<30;i++)
        {
            for(int j=1;j<30;j++)
            {
                for(int k = 1;k<30;k++)//These 3 loops just hit every cell in the new grid for checks
                {
                    if(grid[i][j][k]!=null) newGrid[i][j][k] = grid[i][j][k];
                    int neighbors=0;
                    /* This will check all 9 squares on the level below, then the level current, then the level on top.*/
                    for(int l = -1; l<2;l++)//Moving Y levels
                    {
                        for(int m=-1;m<2;m++)
                        {
                            for(int n=-1;n<2;n++)
                            {
                                if(grid[i+l][j+m][k+n] != null)neighbors++;
                            }
                        }
                    }
                    if(grid[i][j][k] == null &&neighbors >= r[0] && neighbors<=r[1])
                    {
                        newGrid[i][j][k] = new Cell();
                    }
                    else if(grid[i][j][k] != null && (neighbors>r[2] || neighbors <r[3]))newGrid[i][j][k] = null;
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
                    if(chance.nextInt(100) == 0)
                    {
                        grid[i][j][k] = new Cell();
                        grid[i][j][k].setAlive();
                    }
                }
            }
        }
    }

}
