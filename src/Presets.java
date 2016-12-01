import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Holds all of the pre-set values of the POC Game of life
 * Created by Demitri Maestas.
 */
public class Presets {

    private int gridSize;
    private int r0;
    private int r1;
    private int r2;
    private int r3;
    private int speed;
    private ArrayList<Cell> getGrid;


    Presets(int preset)
    {
        if(preset == 0)this.createZero();
    }
    private void createZero() {
        this.gridSize = 3;
        this.r0 = 2;
        this.r1 = 3;
        this.r2 = 3;
        this.r3 = 3;
        this.speed = 100;
        this.getGrid = getGridZero();
    }

    private ArrayList<Cell> getGridZero(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        int[][] startingPos = new int[][]{
                {1,1,1},{1,1,2},{1,2,1},{1,2,3},{1,3,2},{1,3,3},//Bottom row
                {2,2,2}, //Center Row
                {3,1,2},{3,1,3},{3,2,1},{3,2,3},{3,3,1},{3,3,2}};
        for(int i =0; i<startingPos.length;i++)
        {
                    Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }

    public int getGridSize()
    {
        return gridSize;
    }

    public int[] getRValues()
    {
        int[] RVales = {r0,r1,r2,r3};
        return  RVales;
    }
    public int getSpeed()
    {
        return speed;
    }

    public ArrayList<Cell> getGetGrid()
    {
        return getGrid;
    }
}


