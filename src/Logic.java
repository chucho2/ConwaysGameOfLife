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

    //The Number generator for a random game
    private Random chance = new Random();


    {
        dyingCells = new ArrayList<Cell>();
        deadCells = new ArrayList<Cell>();
    }

    public Logic(int gridSize, int[] r, Cell[][][] grid)
    {
        this(gridSize,r,grid,new ArrayList<Cell>());
    }



    public Logic(int gridSize, int[] r, Cell[][][] grid,ArrayList<Cell> aliveCells)
    {
         this.gridSize = gridSize;
         this.r = r;
         this.grid = grid;
         this.aliveCells = aliveCells;
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
                        int[] logicalPosition = new int[] {i,j,k};
                        int[] guiPosition = new int[] {-(gridSize/2)+i
                                ,-(gridSize/2)+j
                                ,-(gridSize/2)+k};
                        grid[i][j][k] = new Cell(logicalPosition, guiPosition);
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

    public ArrayList<Cell> getAliveCells()
    {
        return aliveCells;
    }

    public ArrayList<Cell> getDeadCells()
    {
        return deadCells;
    }

}
