import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

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

    //The [x,y,z] position of this Cell Object on the grid.
    private int[] position;

    //GUI Components
    private PhongMaterial cellColor;
    private Box displayCell;


    /**
     * assumes the Cell Object stars at CellState.DEAD,
     * and that the Cell Object will be used in a GUI.
     * @param position of the Cell at construction
     */
    public Cell(int[] position)
    {
        this(CellState.DEAD,position,true);
    }


    /**
     * assumes that the Cell Object will be used in a GUI.
     * @param type of the Cell Object from CellState Enum
     * @param position of the Cell at construction
     */
    public Cell(CellState type, int[] position)
    {
        this(type,position,true);
    }


    /**
     *
     * @param type of the Cell Object from CellState Enum
     * @param position of the Cell at construction
     * @param generateGUIComponents true if Cell object will be used
     *                              in a GUI; false if program is used
     *                              without a GUI and requests improved
     *                              efficiency.
     */
    public Cell(CellState type, int[] position, boolean generateGUIComponents)
    {
        if(position.length != 3)
        {
            String message = "Must give 3 co-ords to Cell Object";
            throw new UnsupportedOperationException(message);
        }
        for(int i = 0; i < 3;i++)
        {
            if((position[i] < 0 ||  position[i] > 30) && type != CellState.BUFFER)
            {
                String message = "Non-Buffered Cell Must have \"0 < X,Y,Z position <30\"";
                throw new UnsupportedOperationException(message);
            }
        }
        if(generateGUIComponents)
        {
            if(type != CellState.BUFFER)
            {
                cellColor = new PhongMaterial();
                displayCell= new Box(0.1,0.1,0.1);
                this.getChildren().add(displayCell);
                displayCell.setMaterial(cellColor);
            }
            else
            {
                cellColor = new PhongMaterial();
                cellColor.setDiffuseColor(Color.BEIGE);
                displayCell= new Box(1,1,1);
                this.getChildren().add(displayCell);
                displayCell.setMaterial(cellColor);
            }

        }
        this.type = type;
        this.position = position;
    }


    /**
     * sets the Cell Object and its potential JavaFX Box
     * to "Dead" per game rules.
     */
    public void setDead()
    {
        if(this.type != CellState.BUFFER)
        {
            this.type = CellState.DEAD;
        }
    }

    /**
     * sets the Cell Object and its potential JavaFX Box
     * to "Alive" per game rules.
     */
    public void setAlive()
    {
        if(this.type != CellState.BUFFER)
        {
            this.type = CellState.ALIVE;
        }
    }

    /**
     * @return true if CellState is "Alive"; else false.
     */
    public boolean isAlive()
    {
        if(this.type == CellState.ALIVE)return true;
        return false;
    }


    public int[] getPosition()
    {
        return this.position;
    }

    /**
     * @return String Cell Object co-ords and state
     */
    @Override
    public String toString() {
        String Return = "Cell At["+position[0]+
                ","+position[1]+","+position[2]+"]: State ="+this.type;
        return Return;
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

