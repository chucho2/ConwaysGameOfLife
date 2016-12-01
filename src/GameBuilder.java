import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * generates the starting Cell[][][] of a new Conway 3D game,
 * and stores the initial game values.
 * @author Demitri Maestas
 */
public class GameBuilder {

    //The cells with their initial state after creation.
    private ArrayList<Cell> aliveCells;

    //The cells being held, so we can display a boarder
    private ArrayList<Cell> bufferCells;

    //The grid with the cells after generation
    private Cell[][][] grid;

    //The Number generator for a random game
    private Random chance = new Random();

    //This is the default value for r, as per the spec.
    int[] r = {2,3,3,3};

    //the cube has a 1 out of initialSpawnChance of spawning in that cell
    int initialSpawnChance = 100;

    //The default dimension for the grid. Sorry, not making this three. Haha.
    private int gridSize;

    {
        aliveCells = new ArrayList<Cell>();
        bufferCells = new ArrayList<Cell>();
    }

    /**
     * Generates a new Conway Game with:
     * - Grid size of 30x30x30
     * - R values of {3,3,3,2}
     * - A 1 in 100 chance that any given Cell will be
     *   set alive at game creation.
     */
    public  GameBuilder()
    {
        this(30);
    }

    /**
     * Generates a new Conway Game with:
     * - R values of {3,3,3,2}
     * - A 1 in 100 chance that any given Cell will be
     *   set alive at game creation.
     * @param gridSize the size that each side of the grid will be.
     */
    public GameBuilder(int gridSize)
    {
        this(gridSize,null);
    }

    /**
     * Generates a new Conway Game with:
     * - A 1 in 100 chance that any given Cell will be
     *   set alive at game creation.
     * @param gridSize the size that each side of the grid will be.
     * @param RValues the R Values that the game should be played with.
     */
    public GameBuilder(int gridSize, int[] RValues)
    {

        this(gridSize,RValues,null);
    }

    /**
     * builds a new Conway 3D Game with default r values,
     * a 30x30 grid, and a one in 100 chance of any given
     * cell spawning with a live value.
     */
    public GameBuilder(int gridSize, int[] RValues, ArrayList<Cell> aliveCells)
    {
        //for some fact checking later on.
        boolean givenAliveCells = true;

        //Should never be null
        this.gridSize = gridSize;

        //In case we got an earlier constructor
        if(RValues != null)this.r = RValues;

        //In case we got an earlier constructor
        if(aliveCells == null)
        {
            givenAliveCells = false;
            aliveCells = new ArrayList<Cell>();
        }
        this.aliveCells = aliveCells;

        grid = new Cell[gridSize+2][gridSize+2][gridSize+2];


        for(int x = 1; x < gridSize+1;x++)
        {
            for(int y = 1; y < gridSize+1;y++)
            {
                for(int z = 1; z < gridSize+1;z++)
                {
                    //Telling the Cell where it is in the Game grid for logic
                    int[] logicPosition = new int[] {x,y,z};
                    System.out.println(Arrays.toString(logicPosition));


                    //If we were NOT given the alive cells and the current cell is not a Buffer,
                    //Give it a 1 in initialSpawnChance shot of being alive.
                    if(!givenAliveCells && chance.nextInt(initialSpawnChance) == 0)
                    {
                        //Fancy footwork for making the center of the visible grid
                        // at 0,0 in the Xform axis.
                        int[] guiPosition = new int[] {-((gridSize+2)/2)+x
                                ,-((gridSize+2)/2)+y
                                ,-((gridSize+2)/2)+z};
                        grid[x][y][z] = new Cell(logicPosition,guiPosition);
                        grid[x][y][z].setAlive();
                        aliveCells.add(grid[x][y][z]);
                        System.out.println("Cells are alive");
                    }
                }
            }
        }

        if(givenAliveCells)
        {
            for(Cell cell: aliveCells)
            {
                int[] logicalPosition = cell.getLogicPosition();

                int x = logicalPosition[0];
                int y = logicalPosition[1];
                int z = logicalPosition[2];

                //Fancy footwork for making the center of the visible grid
                // at 0,0 in the Xform axis.
                int[] guiPosition = new int[] {-((gridSize+2)/2)+x
                        ,-((gridSize+2)/2)+y
                        ,-((gridSize+2)/2)+z};

                grid[x][y][z] = cell;
                cell.setGuiPosition(guiPosition);
                grid[x][y][z].setAlive();
            }
        }
    }


    /**
     * @return the initial cell grid.
     */
    public Cell[][][] getGrid()
    {
        return this.grid;
    }

    /**
     * @return the R values of the specific game
     */
    public int[] getRValues()
    {
        return this.r;
    }

    /**
     * @return the Size of the sides of the Grid
     */
    public int getGridSize()
    {
        return this.gridSize;
    }

    /**
     * @return which cells are alive at the initial start of the game
     */
    public ArrayList<Cell> getAliveCells()
    {
        return this.aliveCells;
    }

    /**
     * @return which cells are buffer cells throughout the game.
     */
    public ArrayList<Cell> getBufferCells()
    {
        return this.bufferCells;
    }
}
