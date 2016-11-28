import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * will direct all of the keyboard and mouse input into the GridGUI
 * of the game, unless directed to specifically hit one of the
 * control buttons from the toolbars.
 *
 * CURRENT CONTROLS:
 * -UP: Zoom in
 * -DOWN: Zoom out
 *
 *
 * @author Demitri Maestas
 */
public class HandleInput {

   //Held so we can call zoom in and out.
    private GridGUI gui;

    //Used to make the zoom in smooth
    Timeline zoomIn = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> gui.zoom(true)));


    //Used to make the zoom out smooth
    Timeline zoomOut = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> gui.zoom(false)));

    /**
     *
     * @param scene the main scene of the game,
     *              so controls will work from anywhere
     * @param gui the GridGUI holding the simulation of the game.
     */
    public HandleInput(Scene scene, GridGUI gui) {
        this.gui = gui;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        zoomIn.setCycleCount(Animation.INDEFINITE);
                        zoomIn.play();
                        break;
                    case DOWN:
                        zoomOut.setCycleCount(Animation.INDEFINITE);
                        zoomOut.play();
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        zoomIn.stop();
                        break;
                    case DOWN:
                        zoomOut.stop();
                        break;
                }
            }
        });
    }
}
