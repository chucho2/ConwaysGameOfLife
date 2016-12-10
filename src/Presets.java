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


    /**
     *  used to pass the desried pre-set to game of life.
     * @param preset the preset which the game should load.
     */
    Presets(int preset)
    {
        if(preset == 0)this.createZero();
        if(preset == 1)this.createOne();
        if(preset == 2)this.createTwo();
        if(preset == 3)this.createThree();
        if(preset == 4)this.createFour();
        if(preset == 5)this.createFive();
        if(preset == 6)this.createSix();
    }


    /**
     * In all of the following methods, the createX() method will hold game
     * conditions and the getGridX() will hold the inital conditions of the
     * cells to spawn in.
     */
    private void createZero() {
        this.gridSize = 15;
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
                {6,6,6},{6,7,6},{8,6,6},{8,7,6}, //Center Row
                {6,6,7},{6,7,7},{8,6,7},{8,7,7}};
        for(int i =0; i<startingPos.length;i++)
        {
            Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }

    private void createOne() {
        this.gridSize = 15;
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
                {6,6,6},{6,7,6},{8,6,6},{8,7,6}, //Center Row
                {6,6,7},{6,7,7},{8,6,7},{8,7,7}};
        for(int i =0; i<startingPos.length;i++)
        {
            Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }

    private void createTwo() {
        this.gridSize = 15;
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
                {6,6,6},{6,7,6},{8,6,6},{8,7,6}, //Center Row
                {6,6,7},{6,7,7},{8,6,7},{8,7,7}};
        for(int i =0; i<startingPos.length;i++)
        {
            Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }

    private void createThree() {
        this.gridSize = 15;
        this.r0 = 6;
        this.r1 = 6;
        this.r2 = 7;
        this.r3 = 5;
        this.speed = 100;
        this.getGrid = getGridThree();
    }

    private ArrayList<Cell> getGridThree(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        int[][] startingPos = new int[][]{
                {6,6,6},{6,7,6},{7,6,6},{5,7,6},{4,6,6},
                {6,6,7},{6,7,7},{7,6,7},{5,7,7},{4,6,7}};
        for(int i =0; i<startingPos.length;i++)
        {
            Return.add(new Cell(startingPos[i]));
        }
        return Return;
    }



    private void createFour() {
        this.gridSize = 9;
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

    private void createSix() {
        this.gridSize = 15;
        this.r0 = 2;
        this.r1 = 4;
        this.r2 = 5;
        this.r3 = 2;
        this.speed = 100;
        this.getGrid = getGridSix();
    }

    private ArrayList<Cell> getGridSix(){
        ArrayList<Cell> Return = new ArrayList<Cell>();
        for(int i =1; i<11;i++)
        {
                    Return.add(new Cell(new int[]{i,1,1}));

        }
        return Return;
    }

    /**
     * @return the size of the grid.
     */
    public int getGridSize()
    {
        return gridSize;
    }

    /**
     * @return the R values of the current game
     */
    public int[] getRValues()
    {
        int[] RVales = {r0,r1,r2,r3};
        return  RVales;
    }

    /**
     * @return the game grid that was previously generated
     */
    public ArrayList<Cell> getGetGrid()
    {
        return getGrid;
    }
}


