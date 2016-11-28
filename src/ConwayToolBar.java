import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
            throw new UnsupportedOperationException("Not Implemented Yet");
        });
        Button newCustom = new Button("Custom");
        newCustom.setOnAction(e->{
            if(alerts.newGameConfirmation())
            {
                alerts.newCustomGame();
            }
            throw new UnsupportedOperationException("Not Implemented Yet");
        });
        Label infoMessage = new Label(" Grid: 30 x 30    R[0,0,0,0]      60FPS");

        Button options = new Button("Game Options");
        options.setOnAction(e->{
            throw new UnsupportedOperationException("Not Implemented Yet");
        });
        Button help = new Button("Help");
        help.setOnAction(e->{
            alerts.displayHelp();
        });



        ToolBar toolBar = new ToolBar();

        HBox left = new HBox(newGame,newRandom,newPreset,newCustom);
        HBox center = new HBox(infoMessage);
        HBox right = new HBox(options,help);

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
        Button importGame = new Button("Import");
        importGame.setOnAction(e->{
            throw new UnsupportedOperationException("Not Implemented Yet");
        });
        Button exportGame = new Button("Export");
        exportGame.setOnAction(e->{
            throw new UnsupportedOperationException("Not Implemented Yet");
        });

        Button back = new Button("<<");
        back.setOnAction(e->{
            throw new UnsupportedOperationException("Not Implemented Yet");
        });
        Button playAndPause = new Button("||");
        playAndPause.setOnAction(e->{
            System.out.println("Play and Pause");
            if(playAndPause.getText().equals("||"))
            {
                playAndPause.setText(">");
                gameControler.pause();
            }
            else
            {
                playAndPause.setText("||");
                gameControler.play();
            }
        });

        Button forward = new Button(">>");
        forward.setOnAction(e->{
            if(gameControler.isRunning())
            {
                sendMessage("Game must be paused to manually step.",MessageType.WARNING);
            }
            else gameControler.updateGame();
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
        CheckBox music = new CheckBox("Music");
        music.setSelected(true);
        music.setOnAction(e->{
            throw new UnsupportedOperationException("Not Implemented Yet");
        });




        ToolBar toolBar = new ToolBar();

        HBox left = new HBox(back, playAndPause, forward,rotate,music);
        HBox center = new HBox(messageToUser);
        HBox right = new HBox(importGame, exportGame);

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




    public void sendMessage(String message, MessageType type)
    {
         messageTimer.stop();
         if(type == MessageType.WARNING)
         {
             messageToUser.setText(message);
         }
         messageTimer.play();

    }

    private enum MessageType
    {
        INFO, WARNING;
    }
}
