import java.util.ArrayList;
import java.util.Random;

/**
 * is in charge of the logical rules of Conway's game of life. The Class can create the game, and
 * update the game within itself. It also holds the preset game values.
 * @author Demitri Maestas
 */
public class Conway {

    /*The grid which holds the game*/
    private Cell[][][] grid;

    private ArrayList<Cell> liveCells;

    /*Holds the chance of a grid point having a live cell at spawn*/
    private Random chance;

    /*The values from the R sliders as the game progresses*/
    int[] r;

    /*How do I document this? haha...*/
    {
        chance = new Random();
        liveCells = new ArrayList<Cell>();
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
     * @param presetIndex the index number of the preset grid requested.
     */
    public Conway(int presetIndex)
    {
        grid = new Cell[32][32][32];
        if(presetIndex == 0)
        {
            grid[15][15][15] = new Cell();
            grid[15][15][14] = new Cell();
            grid[15][15][16] = new Cell();
            r[0] = 3;
            r[1] = 3;
            r[2] = 4;
            r[3] = 2;
        }

    }

    /**
     * when called, itterates to the next version of the grid in the game.
     */
    public void step()
    {
        /*This is the grid that we will pass the pointer to to show on the screen*/
        Cell[][][] newGrid = new Cell[32][32][32];
        liveCells = new ArrayList<>();


        for(int i = 1;i<30;i++)
        {
            for(int j=1;j<30;j++)
            {
                for(int k = 1;k<30;k++)//These 3 loops just hit every cell in the new grid for checks
                {
                    if(grid[i][j][k]!=null)
                    {
                        newGrid[i][j][k] = grid[i][j][k];
                    }
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
                        liveCells.add(newGrid[i][j][k]);
                    }
                    else if(grid[i][j][k] != null && (neighbors>r[2] || neighbors <r[3]))
                    {

                        newGrid[i][j][k] = null;


                    }
                }
            }
        }
        grid = newGrid;
    }

    /**
     * @return the grid in its current state of the game.
     */
    public Cell[][][] getGrid()
    {
        return grid;
    }

    public ArrayList<Cell> liveCells()
    {
        return liveCells;
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
                    //There is currently a 1% chance of a cube spawning at any given location
                    if(chance.nextInt(100) == 0)
                    {
                        grid[i][j][k] = new Cell();
                        liveCells.add(grid[i][j][k]);
                    }
                }
            }
        }
    }

}
