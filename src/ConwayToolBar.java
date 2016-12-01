import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;


/**
 * Created by chucho2 on 11/26/16.
 */
public class ConwayToolBar {

    private Main gameControler;
    private ConwayGUIBuilder topGUI;
    private ConwayAlerts alerts;


    private Label messageToUser;
    private Timeline messageTimer = new Timeline(new KeyFrame(
            Duration.millis(3000),
            ae -> messageToUser.setText("")));



    private Label RValues = new Label("");
    private Label gridSizeLabel = new Label("");
    private Label itterationCounter = new Label("Step: [0]");
    private Label percentGrid = new Label("Grid: 100% (2400/27000)");


    private Button playAndPause = new Button("Pause ||");
    private Button forward = new Button("Step >>");
    private CheckBox boundary = new CheckBox("Boundary");


    public ConwayToolBar(Main main, ConwayGUIBuilder topGUI)
    {
        this.gameControler = main;
        this.topGUI = topGUI;
        messageToUser = new Label("");
        alerts = new ConwayAlerts(gameControler,topGUI);
    }



    protected ToolBar createTopToolBar()
    {
        Label newGame = new Label("New Game:");
        Button newRandom = new Button("Random");
        newRandom.setOnAction(e->{
            if(alerts.newGameConfirmation())
            {
                alerts.newRandomGame();
            }

        });
        Button newPreset = new Button("Preset");
        newPreset.setOnAction(e->{
            if(alerts.newGameConfirmation())
            {
                alerts.newPresetGame();
            }
        });
        Button newCustom = new Button("Custom");
        newCustom.setOnAction(e->{
            if(alerts.newGameConfirmation())
            {
                alerts.newCustomGame();
            }
        });
        Button importGame = new Button("Import");
        TextField speed = new TextField ();
        speed.setMaxSize(50,100);
        speed.clear();
        speed.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
            {
                try
                {
                    int newSpeed = Integer.parseInt(speed.getText());
                    if(newSpeed > 0 && newSpeed <101)
                    {
                    }
                }catch (Exception ex)
                {

                }
                System.out.println("Yes");
            }
        });


        Button options = new Button("Game Options");
        options.setOnAction(e->{
            throw new UnsupportedOperationException("Not Implemented Yet");
        });
        Button help = new Button("Help");
        help.setOnAction(e->{
            alerts.displayHelp();
        });



        ToolBar toolBar = new ToolBar();

        HBox left = new HBox(newGame,newRandom,newPreset,newCustom,importGame);
        HBox center = new HBox(RValues,gridSizeLabel,itterationCounter);
        HBox right = new HBox(percentGrid);

        HBox.setHgrow( left, Priority.ALWAYS );
        HBox.setHgrow( center, Priority.ALWAYS );
        HBox.setHgrow( right, Priority.ALWAYS );
        left.setAlignment(Pos.CENTER_LEFT);
        center.setAlignment(Pos.CENTER);
        right.setAlignment(Pos.CENTER_RIGHT);


        final int spacing = 8;
        toolBar.setPadding( new Insets( 10, spacing, 10, spacing ) );
        left.setSpacing( spacing );
        center.setSpacing( spacing );
        right.setSpacing( spacing );


        toolBar.getItems().addAll(left,center,right);

        return  toolBar;
    }


    protected ToolBar createBottomToolBar()
    {
        Button exportGame = new Button("Export");
        exportGame.setOnAction(e->{
            throw new UnsupportedOperationException("Not Implemented Yet");
        });

        forward.setOnAction(e->{
            if(!gameControler.isRunning())
            {
                gameControler.updateGame();
            }
        });
        forward.setDisable(true);

        playAndPause.setOnAction(e->{
            if(playAndPause.getText().equals("Pause ||"))
            {
                gameControler.pause();

            }
            else
            {
                gameControler.play();
            }
        });



        CheckBox rotate = new CheckBox("Auto Rotate");
        rotate.setSelected(true);
        rotate.setOnAction(e->{
           if(rotate.isSelected())
           {
               topGUI.getGrid().setRotate(true);
           }
           else
           {
               topGUI.getGrid().setRotate(false);
           }
        });

        boundary.setOnAction(e->{
            if(boundary.isSelected())
            {
                topGUI.getGrid().toggleBoundary(true);
            }
            else
            {
                topGUI.getGrid().toggleBoundary(false);
            }
        });
        CheckBox showAxis = new CheckBox("Show Axis");
        showAxis.setOnAction(e->{
            if(showAxis.isSelected())
            {
                topGUI.getGrid().showAxis(true);
            }
            else topGUI.getGrid().showAxis(false);
        });
        Button zoomOut = new Button(" - ");
        zoomOut.setOnAction(e->{
            gameControler.getGui().getGrid().zoom(false);
        });
        Label zoomMessage = new Label("Zoom");
        Button zoomIn= new Button("+");
        zoomIn.setOnAction(e->{
            gameControler.getGui().getGrid().zoom(true);
        });


        ToolBar toolBar = new ToolBar();

        HBox left = new HBox(playAndPause,
                forward,rotate,boundary,showAxis);
        HBox center = new HBox();
        HBox right = new HBox(zoomIn,zoomMessage,zoomOut);

        HBox.setHgrow( left, Priority.ALWAYS );
        HBox.setHgrow( center, Priority.ALWAYS );
        HBox.setHgrow( right, Priority.ALWAYS );
        left.setAlignment(Pos.CENTER_LEFT);
        center.setAlignment(Pos.CENTER);
        right.setAlignment(Pos.CENTER_RIGHT);


        final int spacing = 8;
        toolBar.setPadding( new Insets( 10, spacing, 10, spacing ) );
        left.setSpacing( spacing );
        center.setSpacing( spacing );
        right.setSpacing( spacing );


        toolBar.getItems().addAll(left,center,right);

        return  toolBar;
    }


    public void setGridPercent(int taken, int free)
    {
        int acutalGrid = (free-2)*(free-2)*(free-2);
        long percent = taken/acutalGrid;
        percentGrid.setText("Grid: "+percent+"% ("+taken+"/"+acutalGrid+")");
    }

    public void setItteration(int itteration)
    {
        itterationCounter.setText("Step: ["+itteration+"]");
    }

    public void updateGridSize(int gridSize)
    {
        gridSizeLabel.setText("Grid-Dimensions [x:"+gridSize+
                ",y:"+gridSize+",z:"+gridSize+"]   ");
    }

    public void updateRValues(int[] newRValues)
    {
         RValues.setText("R-Values["+newRValues[0]+
                 ","+newRValues[1]+","+newRValues[2]+","+
                 newRValues[3]+"]   ");
    }
    /**
     * @param play true if the game is paused; else false;
     */
    public void updatePlayAndPauseButton(boolean play)
    {
        if(play)
        {
            playAndPause.setText("Pause ||");
            forward.setDisable(true);
        }
        else
        {
            playAndPause.setText("Play    >");
            forward.setDisable(false);
        }
    }

    public void updateBoundaryCheckbox(boolean on)
    {
        if(on)boundary.setSelected(true);
        else boundary.setSelected(false);
    }
}
