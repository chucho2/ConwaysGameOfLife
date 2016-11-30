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
        this.r0 = 3;
        this.r1 = 3;
        this.r2 = 3;
        this.r3 = 2;
        this.speed = 100;
        this.getGrid = getGridZero();
    }

    private ArrayList<Cell> getGridZero(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        int[] position = {0,0,0};
        Return.add(new Cell(position));
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


