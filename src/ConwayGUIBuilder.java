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

    //
    private GridGUI gridGUI;
    private ConwayToolBar toolBars;
    private ToolBar topToolbar;
    private ToolBar bottomToolBar;



    public ConwayGUIBuilder(Main main)
    {
        this.gameControler = main;
        toolBars = new ConwayToolBar(gameControler,this);
        topToolbar = toolBars.createTopToolBar();
        bottomToolBar = toolBars.createBottomToolBar();
        gridGUI = new GridGUI();
    }


    public ConwayToolBar getConwayBars()
    {
        return this.toolBars;
    }
    public void newGrid(int gridSize)
    {
        this.gridGUI = null;
        gridGUI = new GridGUI(gridSize);
    }
    public GridGUI getGrid()
    {
        return this.gridGUI;
    }


    public ToolBar getTopToolBar()
    {
        return this.topToolbar;
    }

    public ToolBar getBottomToolBar()
    {
        return this.bottomToolBar;
    }

}
