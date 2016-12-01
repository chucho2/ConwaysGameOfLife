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
        if(preset == 1)this.createOne();
        if(preset == 2)this.createTwo();
        if(preset == 4)this.createFour();
        if(preset == 5)this.createFive();
    }




    private void createZero() {
        this.gridSize = 9;
        this.r0 = 4;
        this.r1 = 4;
        this.r2 = 3;
        this.r3 = 4;
        this.speed = 100;
        this.getGrid = getGridZero();
    }

    private ArrayList<Cell> getGridZero(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        int[][] startingPos = new int[][]{
                {4,4,4},{4,5,4},{6,4,4},{6,5,4}, //Center Row
                {4,4,5},{4,5,5},{6,4,5},{6,5,5}};
        for(int i =0; i<startingPos.length;i++)
        {
            Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }

    private void createOne() {
        this.gridSize = 9;
        this.r0 = 4;
        this.r1 = 4;
        this.r2 = 4;
        this.r3 = 3;
        this.speed = 100;
        this.getGrid = getGridOne();
    }

    private ArrayList<Cell> getGridOne(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        int[][] startingPos = new int[][]{
                {4,4,4},{4,5,4},{6,4,4},{6,5,4}, //Center Row
                {4,4,5},{4,5,5},{6,4,5},{6,5,5}};
        for(int i =0; i<startingPos.length;i++)
        {
            Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }

    private void createTwo() {
        this.gridSize = 9;
        this.r0 = 4;
        this.r1 = 5;
        this.r2 = 3;
        this.r3 = 4;
        this.speed = 100;
        this.getGrid = getGridTwo();
    }

    private ArrayList<Cell> getGridTwo(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        int[][] startingPos = new int[][]{
                {4,4,4},{4,5,4},{6,4,4},{6,5,4}, //Center Row
                {4,4,5},{4,5,5},{6,4,5},{6,5,5}};
        for(int i =0; i<startingPos.length;i++)
        {
            Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }

    private void createFive() {
        this.gridSize = 11;
        this.r0 = 2;
        this.r1 = 4;
        this.r2 = 5;
        this.r3 = 2;
        this.speed = 100;
        this.getGrid = getGridFive();
    }

    private ArrayList<Cell> getGridFive(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        for(int i =1; i<11;i+=2)
        {
            for(int j =1; j<11;j+=2)
            {
                for(int k =1; k<11;k+=2)
                {
                    Return.add(new Cell(new int[]{i,j,k}));
                }
            }
        }
        return Return;
    }

    private void createFour() {
        this.gridSize = 3;
        this.r0 = 3;
        this.r1 = 3;
        this.r2 = 3;
        this.r3 = 2;
        this.speed = 100;
        this.getGrid = getGridFour();
    }

    private ArrayList<Cell> getGridFour(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        int[][] startingPos = new int[][]{
                {1,1,2},{1,2,3},{1,3,2},//Bottom row
                {2,2,2}, //Center Row
                {3,1,2},{3,2,1},{3,3,2}};
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


    //Real time updateble R values
}


