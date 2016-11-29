import java.util.ArrayList;
import java.util.Random;

/**
 * holds all of the game logic for Game of life.
 *
 * @author Demitri Maestas
 */
public class Logic {


    private Cell[][][] grid;
    private int[] r;
    private int gridSize;

    //The cells that are to be marked alive this itteration of the game
    private ArrayList<Cell> aliveCells;

    //The cells to be marked as dead this itteration,
    //but need to display death animation
    private ArrayList<Cell> dyingCells;

    //The cells that are ready to be taken out of the GUI.
    private ArrayList<Cell> deadCells;

    //Cells in existance just to help with the edge cases of the game logic.
    private ArrayList<Cell> bufferCells;

    //The Number generator for a random game
    private Random chance = new Random();


    public Logic(int gridSize, int[] r, Cell[][][] grid)
    {
        this(gridSize,r,grid,new ArrayList<Cell>(), new ArrayList<Cell>(), new ArrayList<Cell>());
    }



    public Logic(int gridSize, int[] r, Cell[][][] grid,ArrayList<Cell> aliveCells,
                 ArrayList<Cell> deadCells, ArrayList<Cell> bufferCells)
    {
         this.gridSize = gridSize;
         this.r = r;
         this.grid = grid;
         this.aliveCells = aliveCells;
         this.dyingCells = deadCells;
         this.bufferCells = bufferCells;
    }

    /**
     * when called, itterates to the next version of the grid in the game.
     */
    public void step()
    {
        deadCells = dyingCells;
        dyingCells = aliveCells;
        aliveCells = new ArrayList<Cell>();
        for(Cell cell: dyingCells)
        {
            cell.setDead();
        }
        for(int i = 0; i < gridSize;i++)
        {
            for(int j = 0; j< gridSize;j++)
            {
                for(int k = 0; k < gridSize;k++)
                {
                    if(chance.nextInt(100) == 0)
                    {
                        int[] coords = new int[] {i,j,k};
                        int[] position = new int[] {-(gridSize/2)+i
                                ,-(gridSize/2)+j
                                ,-(gridSize/2)+k};
                        grid[i][j][k] = new Cell(position);
                        grid[i][j][k].setAlive();
                        aliveCells.add(grid[i][j][k]);
                    }
                }
            }
        }


    }

    /**
     * @return the grid in its current state of the game.
     */
    public Cell[][][] getGrid()
    {
        return grid;
    }

    public int getGridSize()
    {
        return this.gridSize;
    }



    public ArrayList<Cell> getAliveCells()
    {
        return aliveCells;
    }

    public ArrayList<Cell> getDyingCells()
    {
        return dyingCells;
    }

    public ArrayList<Cell> getDeadCells()
    {
        return dyingCells;
    }

}
