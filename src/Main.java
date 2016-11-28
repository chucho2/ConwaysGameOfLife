import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * is in charge of directing the game, holds a GUI component
 * and a game logic component, and manages game timer as well as
 * interaction with the game components.
 * @author Demitri Maestas
 */
public class Main extends Application {

    //Creates and manages the GUI for the Game
    private ConwayGUIBuilder gui;

    //The game logic for the Simulation
    private Logic gameOfLife;

    //game can interact with the keybored
    private HandleInput keyboard;

    //this is in charge of updating the main game every "Wall second"
    private Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(1000),
            ae -> updateGame()));


    /**
     * stops the game timer, "pausing" the game
     */
    public void pause()
    {
        timeline.pause();
    }

    /**
     * starts the game timer, "resuming" the game
     */
    public void play()
    {
        timeline.play();
    }

    public boolean isRunning()
    {
        if(timeline.getStatus() == Animation.Status.PAUSED)return false;
        else return true;
    }

    /**
     * Calls for a GUI, calls for 3D Game Of Life Logic,
     * Sets proper GUI components in place and fires up the
     * timer to begin a Game with default R values and
     * random Alive Cell locations.
     * @param primaryStage the JavaFX primary stage.
     */
    @Override
    public void start(Stage primaryStage) {


        //Build the GUI and fire up the game.
        gui = new ConwayGUIBuilder(this);

        //Building the Master pane, adding the main game in the respective toolbars in their positions.
        BorderPane pane = new BorderPane();
        pane.setTop(gui.getTopToolBar());
        pane.setCenter(gui.getGrid().getGameBoard());
        pane.setBottom(gui.getBottomToolBar());

        //Wrapping things up and throwing them to the screen.
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Logic's 3D Game of Life - Demitri Maestas");//Except for this.
        primaryStage.setResizable(false);
        primaryStage.show();

        //Making keyboard and mouse interactive in game
        keyboard = new HandleInput(scene,gui.getGrid());

        //Starting the game
        newGame();


    }

    /**
     * step the logic of the game 1 itteration
     */
    public void updateGame()
    {
       gameOfLife.step();
    }

    /**
     * make a new game with the assistance of the Game Builder Class
     * called anytime a new game is made.
     */
    public void newGame()
    {
        timeline.stop();

        GameBuilder gameCreator = new GameBuilder();
        gameOfLife = new Logic(gameCreator.getGridSize(),gameCreator.getRValues(),
                gameCreator.getGrid(),gameCreator.getAliveCells(),
                gameCreator.getDeadCells(), gameCreator.getBufferCells());
        gui.getGrid().purge();
        gui.getGrid().addCells(gameOfLife.getBufferCells());
        gui.getGrid().addCells(gameOfLife.getAliveCells());
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public ConwayGUIBuilder getGui()
    {
        return this.gui;
    }


    //http://www.java2s.com/Tutorials/Java/JavaFX/0440__JavaFX_Checkbox.htm
    //https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
    //http://docs.oracle.com/javafx/2/ui_controls/text-field.htm
}
