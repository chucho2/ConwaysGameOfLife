import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
        Cell tempGrid[][][] = new Cell[gridSize+2][gridSize+2][gridSize+2];
        System.out.println("c:"+aliveCells.size());
        deadCells = dyingCells;
        dyingCells = new ArrayList<Cell>();

        for(int i = 1; i < gridSize+1;i++)
        {
            for(int j = 1; j< gridSize+1;j++)
            {
                for(int k = 1; k < gridSize+1;k++)
                {
                    int neighbors = checkForNeighbors(new int[]{i,j,k});

                   tempGrid[i][j][k] = grid[i][j][k];


                    //Copying the current state of the grid to the new grid
                    //in the case that nothing changed.
                    if(grid[i][j][k] == null && (neighbors>=r[0] && neighbors<=r[1]))
                    {

                        //Fancy footwork for making the center of the visible grid
                        // at 0,0 in the Xform axis.
                        int[] guiPosition = new int[] {-((gridSize+2)/2)+i
                                ,-((gridSize+2)/2)+j
                                ,-((gridSize+2)/2)+k};
                        tempGrid[i][j][k] = new Cell(new int[]{i,j,k},guiPosition);
                        tempGrid[i][j][k].setAlive();
                        aliveCells.add(tempGrid[i][j][k]);
                    }

                    //If the grid has an alive cell and meets the dead params,
                    // kill the cell.
                    if(grid[i][j][k] != null && (neighbors > 3 || neighbors < r[3]))
                    {
                        grid[i][j][k].setDead();
                        aliveCells.remove(grid[i][j][k]);
                        dyingCells.add(grid[i][j][k]);
                        //System.out.println("Removing Cell:"+grid[i][j][k]);
                    }


                }
            }
        }
        this.grid = tempGrid;
    }

    private int checkForNeighbors(int[] currentPosition)
    {
        int neighbors = -1;

        int x = currentPosition[0];
        int y = currentPosition[1];
        int z = currentPosition[2];
        //System.out.println("Current Position:"+ Arrays.toString(currentPosition));

        for(int i = -1;i<2;i++)
        {
            for(int j = -1;j<2;j++)
            {
                for(int k = -1; k<2;k++)
                {
                     if(grid[x+i][y+j][z+k] != null)
                     {
                         neighbors++;
                     }
                }
            }
        }
       // System.out.println(neighbors);
        return neighbors;
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
