import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;

/**
 * holds all of the information about a specific state of
 * a Cell, its JavaFX Box, and it's position on the game Grid.
 * "BUFFER" cells exist to assist in game logic, removing the need
 * to account for edge cases in the game. "BUFFER" cells cannot be
 * alive or dead, but can exist in the Grid.
 * @author Demitri Maesetas
 */
public class Cell extends Xform{

    //The Type of Cell Object as per CellType Enum
    private CellState type;

    //The [x,y,z] position of this Cell Object in the Logic Grids
    private int[] logicPosition;

    //The [x,y,z] position of this Cell Object in the GUI grids
    private int[] guiPosition;

    //GUI Components
    private PhongMaterial cellColor;
    private Box displayCell;

    Color greenColor = new Color(0,1,0,1);

    //In charge of updating the cells animation
    Timeline grow = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> grow()));

    //In charge of updating the cells animation
    Timeline shrink = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> shrink()));

    private volatile boolean alive = false;





    /**
     * assumes the Cell Object stars at CellState.DEAD,
     * and that the Cell Object will be used in a GUI.
     * @param logicPosition of the Cell int the Logic Grids
     */
    public Cell(int[] logicPosition)
    {
        this(CellState.DEAD,logicPosition,null,true);
    }

    /**
     * assumes the Cell Object stars at CellState.DEAD,
     * and that the Cell Object will be used in a GUI.
     * @param logicPosition of the Cell int the Logic Grids
     * @param guiPosition of the Cell in the GUI Grids
     */
    public Cell(int[] logicPosition,int[] guiPosition)
    {
        this(CellState.DEAD,logicPosition,guiPosition,true);
    }


    /**
     * assumes that the Cell Object will be used in a GUI.
     * @param type of the Cell Object from CellState Enum
     * @param logicPosition of the Cell int the Logic Grids
     * @param guiPosition of the Cell in the GUI Grids
     */
    public Cell(CellState type,int[] logicPosition,int[] guiPosition)
    {
        this(type,logicPosition,guiPosition,true);
    }


    /**
     * @param type of the Cell Object from CellState Enum
     * @param logicPosition of the Cell int the Logic Grids
     * @param guiPosition of the Cell in the GUI Grids
     * @param generateGUIComponents true if Cell object will be used
     *                              in a GUI; false if program is used
     *                              without a GUI and requests improved
     *                              efficiency.
     */
    public Cell(CellState type, int[] logicPosition,int[] guiPosition, boolean generateGUIComponents)
    {
        grow.setCycleCount(100);
        shrink.setCycleCount(Timeline.INDEFINITE);

        if(logicPosition.length != 3)
        {
            String message = "Must give 3 Logic co-ords to Cell Object";
            throw new UnsupportedOperationException(message);
        }
        if(generateGUIComponents)
        {
            if(type != CellState.BUFFER)
            {
                cellColor = new PhongMaterial();
                cellColor.setDiffuseColor(greenColor);
                displayCell= new Box(0.1,0.1,0.1);
                this.getChildren().add(displayCell);
                displayCell.setMaterial(cellColor);

            }
        }
        this.type = type;
        this.logicPosition = logicPosition;
        this.guiPosition = guiPosition;
    }

    /**
     * sets the Cell Object and its potential JavaFX Box
     * to "Alive" per game rules.
     */
    public void setAlive()
    {
        if(this.type != CellState.BUFFER)
        {
            grow.play();
            this.type = CellState.ALIVE;
        }
    }

    /**
     * sets the Cell Object and its potential JavaFX Box
     * to "A" per game rules.
     */
    public void setDead()
    {
        if(this.type != CellState.BUFFER)
        {
            this.alive = false;
            this.type = CellState.DEAD;
            cellColor.setDiffuseColor(new Color(.5,0,0,1));
            shrink.play();
        }
    }

    /**
     * @return the logical position of the cell
     */
    public int[] getLogicPosition()
    {
        return this.logicPosition;
    }

    /**
     * @return the GUI Position of the cell
     */
    public int[] getGuiPosition()
    {
        return this.guiPosition;
    }

    /**
     * @return String Cell Object co-ords and state
     */
    @Override
    public String toString() {
        String Return = "Cell At["+logicPosition[0]+
                ","+logicPosition[1]+","+logicPosition[2]+"]: State ="+this.type;
        return Return;
    }


    private void grow()
    {
        displayCell.setDepth(displayCell.getDepth()+.01);
        displayCell.setWidth(displayCell.getWidth()+.01);
        displayCell.setHeight(displayCell.getHeight()+.01);
    }

    private void shrink()
    {
        displayCell.setDepth(displayCell.getDepth()-.01);
        displayCell.setWidth(displayCell.getWidth()-.01);
        displayCell.setHeight(displayCell.getHeight()-.01);
        cellColor.setDiffuseColor(
                new Color(cellColor.getDiffuseColor().getRed()-.000,
                        0,
                        0,1));
    }


    /**
     * the States the cell can exist in.
     * Buffered states are states that cannot be alive or dead
     * and simply exist for mathematical reasons.
     */
    public enum CellState {
        ALIVE, DEAD, BUFFER
    }


}

