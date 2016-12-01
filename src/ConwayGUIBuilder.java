import javafx.scene.control.ToolBar;

/**
 * Gathers the three main components of the GUI portion of the game:
 * Top toolbar - which is the controls on the top of the screen
 * Grid GUI - shows the actual game board and the cells
 * Bottom toolbar - shows the update messages and the bottom controls.
 * @author Demitri Maestas
 */
public class ConwayGUIBuilder {


    //the Main method, used to access everything else.
    private Main gameControler;

    private GridGUI gridGUI;
    private ConwayToolBar toolBars;
    private ToolBar topToolbar;
    private ToolBar bottomToolBar;


    /**
     * @param main Class so GUI can manipulate game
     */
    public ConwayGUIBuilder(Main main)
    {
        this.gameControler = main;
        toolBars = new ConwayToolBar(gameControler,this);
        topToolbar = toolBars.createTopToolBar();
        bottomToolBar = toolBars.createBottomToolBar();
        gridGUI = new GridGUI();
    }


    /**
     * holds the GUI componenets on the top and bottom of the game
     * @return the ConwayBar holding the components.
     */
    public ConwayToolBar getConwayBars()
    {
        return this.toolBars;
    }

    /**
     * creates a new GridGUI, which will host the actual game information.
     * @param gridSize the size of the grid
     */
    public void newGrid(int gridSize)
    {
        this.gridGUI = null;
        gridGUI = new GridGUI(gridSize);
    }

    /**
     * @return the GridGUI
     */
    public GridGUI getGrid()
    {
        return this.gridGUI;
    }


    /**
     * @return the top conway Toolbar
     */
    public ToolBar getTopToolBar()
    {
        return this.topToolbar;
    }

    /**
     * @return the bottom conway toolbar.
     */
    public ToolBar getBottomToolBar()
    {
        return this.bottomToolBar;
    }

}
