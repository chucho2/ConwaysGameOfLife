import java.util.ArrayList;
import java.util.Random;

/**
 * Created by chucho2 on 11/27/16.
 */
public class GameBuilder {

    //The cells with their initial state after creation.
    private ArrayList<Cell> aliveCells;
    private ArrayList<Cell> deadCells;
    private ArrayList<Cell> bufferCells;

    //The grid with the cells after generation
    private Cell[][][] grid;

    //The Number generator for a random game
    private Random chance = new Random();

    //This is the default value for r, as per the spec.
    int[] r = {3,3,3,2};

    //The default dimension for the grid. Sorry, not making this three. Haha.
    private int gridSize = 20;


    {
        aliveCells = new ArrayList<Cell>();
        deadCells = new ArrayList<Cell>();
        bufferCells = new ArrayList<Cell>();
    }


    /**
     * builds a new Conway 3D Game with default r values,
     * a 30x30 grid, and a one in 100 chance of any given
     * cell spawning with a live value.
     */
    public GameBuilder()
    {
        grid = new Cell[gridSize+1][gridSize+1][gridSize+1];
        for(int x = 0;x<gridSize+1; x++)
        {
            for(int y = 0;y<gridSize+1; y++)
            {
                for(int z = 0;z<gridSize+1; z++)
                {
                    int[] coords = new int[] {x,y,z};
                    int[] position = new int[] {-(gridSize/2)+x
                            ,-(gridSize/2)+y
                            ,-(gridSize/2)+z};


                    for(int i = 0; i<3;i++)
                    {
                        //Adding the buffer Cells
                        if(coords[i] == 0 || coords[i] == gridSize)
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
                                aliveCells.add(grid[x][y][z]);
                            }
                        }
                    }

                }
            }
        }
    }


    public Cell[][][] getGrid()
    {
        return this.grid;
    }


    public int[] getRValues()
    {
        return this.r;
    }


    public int getGridSize()
    {
        return this.gridSize;
    }

    public ArrayList<Cell> getAliveCells()
    {
        return this.aliveCells;
    }

    public ArrayList<Cell> getDeadCells()
    {
        return this.deadCells;
    }

    public ArrayList<Cell> getBufferCells()
    {
        return this.bufferCells;
    }



}
