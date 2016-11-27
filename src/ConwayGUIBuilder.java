import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * @author Demitri Maestas
 */
public class ConwayGUIBuilder {

    private Xform gridXform;

    private Main gameControler;
    private GridGUI gridGUI;
    private ToolBar topToolbar;
    private ToolBar bottomToolBar;





    public ConwayGUIBuilder(Main main)
    {
        this.gameControler = main;
        ConwayToolBar toolBars = new ConwayToolBar(gameControler,this);
        topToolbar = toolBars.createTopToolBar();
        bottomToolBar = toolBars.createBottomToolBar();
        gridGUI = new GridGUI();
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
