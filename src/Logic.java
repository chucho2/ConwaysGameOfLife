import java.util.ArrayList;

/**
 * @author Demitri Maestas
 */
public class Logic {

    /*The grid which holds the game*/
    private Cell[][][] grid;
    private int[] r;
    private int gridSize;

    private ArrayList<Cell> liveCells;
    private ArrayList<Cell> deadCells;
    private ArrayList<Cell> bufferCells;



    /*How do I document this? haha...*/
    {
      liveCells = new ArrayList<Cell>();
      deadCells = new ArrayList<Cell>();
      bufferCells = new ArrayList<Cell>();

    }


    public Logic(int size, int[] r, Cell[][][] grid)
    {
        this(size,r,grid,new ArrayList<Cell>(), new ArrayList<Cell>(), new ArrayList<Cell>());
    }



    public Logic(int size, int[] r, Cell[][][] grid,ArrayList<Cell> aliveCells,
                 ArrayList<Cell> deadCells, ArrayList<Cell> bufferCells)
    {
         this.gridSize = size;
         this.r = r;
         this.grid = grid;
         this.liveCells = aliveCells;
         this.deadCells = deadCells;
         this.bufferCells = bufferCells;
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



    public ArrayList<Cell> getAliveCells()
    {
        return liveCells;
    }

    public ArrayList<Cell> getDeadCells()
    {
        return deadCells;
    }
    public ArrayList<Cell> getBufferCells()
    {
        return bufferCells;
    }


}
