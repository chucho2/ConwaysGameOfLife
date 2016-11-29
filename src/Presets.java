

/**
 * Holds all of the pre-set values of the POC Game of life
 * Created by Demitri Maestas.
 */
public enum Presets {
    ONE(3,3,3,3,2,100,getGridOne());


    private final int gridSize;
    private final int r0;
    private final int r1;
    private final int r2;
    private final int r3;
    private final int speed;


    Presets(int gridSize, int r0, int r1,int r2, int r3, int speed,Cell[][][] test) {
        this.gridSize = gridSize;
        this.r0 = r0;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.speed = speed;
    }

    private double getGridSize()
    {
        return this.gridSize;
    }
    private int[] getRValues()
    {
        int[] rValues = {0,1};
        return rValues;
    }

    private static Cell[][][] getGridOne(){
        Cell[][][] Return = new Cell[3][3][3];
        int[] position = {0,0,0};
        Return[1][1][1] = new Cell(position);
        return Return;
    }


}


