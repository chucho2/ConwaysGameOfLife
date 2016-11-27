import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * An assortment of the different kind of Alerts, information, and input
 * pop-ups in the game.
 * @author Demitri Maestas
 */
public class ConwayAlerts {

    //referencing main to start new game or change values if necessary.
    Main gameControler;
    ConwayGUIBuilder gui;

    /**
     * @param main the main program
     */
    public ConwayAlerts(Main main, ConwayGUIBuilder gui)
    {
        this.gameControler = main;
        this.gui = gui;
    }


    /**
     * Creates a pop-up confirming that the player wants
     * to leave progress and start a new game.
     * @return true if player wants new game; else false.
     */
    public boolean newGameConfirmation()
    {
        gameControler.pause();
        gui.getGrid().rotate(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("End Current Game?");
        alert.setHeaderText("Are you sure you want to exit" +
                " this Game?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            gameControler.play();
            return false;
        }
    }

    /**
     * Creates a new random game using main.
     */
    public  void newRandomGame()
    {

    }

    /**
     * creates a new Preset Game
     */
    public void newPresetGame()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create a new Preset 3D Logic game");
        alert.setHeaderText("Please choose which Pre-set you would like run.");
        alert.setContentText("Choose your option.\n"+
                "Choose your option.\n"+
                "Choose your option.\n"+
                "Choose your option.\n"+
                "Choose your option.\n"+
                "Choose your option.\n"+
                "Choose your option.\n");

        ButtonType buttonTypeOne = new ButtonType("One");
        ButtonType buttonTypeTwo = new ButtonType("Two");
        ButtonType buttonTypeThree = new ButtonType("Three");
        ButtonType buttonTypeFour = new ButtonType("Four");
        ButtonType buttonTypeFive = new ButtonType("Five");
        ButtonType buttonTypeSix = new ButtonType("Six");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree,
                buttonTypeFour,buttonTypeFive,buttonTypeSix);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Two"
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    /**
     * Creates a new Preset Game
     */
    public void newCustomGame()
    {

    }
}
