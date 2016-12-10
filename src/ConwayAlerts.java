import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
     * @param main the main program, so the alerts can create a new game.
     * @param gui the main gui, so alerts can update as needed.
     */
    public ConwayAlerts(Main main, ConwayGUIBuilder gui)
    {
        this.gameControler = main;
        this.gui = gui;
    }


    /**
     * Creates a new random game using main.
     */
    public void newRandomGame()
    {
        gameControler.endCurrentGame();
        gameControler.newGame();
    }

    /**
     * creates a new Preset Game
     */
    public void newPresetGame()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create a new Preset 3D Logic game");
        alert.setHeaderText("Please choose which Pre-set you would like run.");
        alert.setContentText("Preset 1 - Two sets of 2x2 grids, one space apart. R = {4,4,3,4}\n"+
                "Preset 2 - Two sets of 2x2 grids, one space apart. R = {4,4,4,3}\n"+
                "Preset 3 - Two sets of 2x2 grids, one space apart. R = {4,5,3,4}.\n"+
                "Preset 4 - 2 Adjacent layers, both the same 5 cube pattern.\n"+
                "L7 - A good indicator that the basic game works.\n"+
                "The Rubix Cube - Shows the game rules are synchronized everywhere.\n");

        ButtonType buttonTypeOne = new ButtonType("Preset 1");
        ButtonType buttonTypeTwo = new ButtonType("Preset 2");
        ButtonType buttonTypeThree = new ButtonType("Preset 3");
        ButtonType buttonTypeFour = new ButtonType("Preset 4");
        ButtonType buttonTypeFive = new ButtonType("L7");
        ButtonType buttonTypeSix = new ButtonType("The Cube");
        ButtonType buttonTypeSeven = new ButtonType("The Wall");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree,
                buttonTypeFour,buttonTypeFive,buttonTypeSix,buttonTypeSeven);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            gameControler.endCurrentGame();
            Presets zero = new Presets(0);
            gameControler.newGame(new GameBuilder(
                    zero.getGridSize(),zero.getRValues(),
                    zero.getGetGrid()
            ));
        } else if (result.get() == buttonTypeTwo) {
            gameControler.endCurrentGame();
            Presets zero = new Presets(1);
            gameControler.newGame(new GameBuilder(
                    zero.getGridSize(),zero.getRValues(),
                    zero.getGetGrid()
            ));
        } else if (result.get() == buttonTypeThree) {
            gameControler.endCurrentGame();
            Presets zero = new Presets(2);
            gameControler.newGame(new GameBuilder(
                    zero.getGridSize(),zero.getRValues(),
                    zero.getGetGrid()
            ));
        } else if (result.get() == buttonTypeFour) {
            gameControler.endCurrentGame();
            Presets zero = new Presets(3);
            gameControler.newGame(new GameBuilder(
                    zero.getGridSize(),zero.getRValues(),
                    zero.getGetGrid()
            ));
        } else if (result.get() == buttonTypeFive) {
            gameControler.endCurrentGame();
            Presets zero = new Presets(4);
            gameControler.newGame(new GameBuilder(
                    zero.getGridSize(),zero.getRValues(),
                    zero.getGetGrid()
            ));
        } else if (result.get() == buttonTypeSix) {
            gameControler.endCurrentGame();
            Presets zero = new Presets(5);
            gameControler.newGame(new GameBuilder(
                    zero.getGridSize(),zero.getRValues(),
                    zero.getGetGrid()
            ));
        } else if (result.get() == buttonTypeSeven) {
            gameControler.endCurrentGame();
            Presets zero = new Presets(6);
            gameControler.newGame(new GameBuilder(
                    zero.getGridSize(),zero.getRValues(),
                    zero.getGetGrid()
            ));
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }


    //http://stackoverflow.com/questions/31556373/javafx-dialog-with-2-input-fields
    /**
     * Creates a new Custom game
     */
    public void newCustomGame()
    {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Custom Conway's 3D Game");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField gridDimension = new TextField();
        TextField spawn = new TextField();
        TextField R1 = new TextField();
        TextField R2 = new TextField();
        TextField R3 = new TextField();
        TextField R4 = new TextField();

        gridDimension.setPromptText("30");
        spawn.setPromptText("25");
        R1.setPromptText("3");
        R2.setPromptText("3");
        R3.setPromptText("3");
        R4.setPromptText("2");

        gridPane.add(new Label("Grid Dimension:"), 0, 0);
        gridPane.add(new Label("Chance of \ninitial spawn:"), 0, 1);
        gridPane.add(new Label("out of 100."), 3, 1);
        gridPane.add(new Label("R1:"), 0, 2);
        gridPane.add(new Label("R2:"), 0, 3);
        gridPane.add(new Label("R3:"), 0, 4);
        gridPane.add(new Label("R4:"), 0, 5);
        gridPane.add(gridDimension, 1, 0);
        gridPane.add(spawn, 1, 1);
        gridPane.add(R1, 1, 2);
        gridPane.add(R2, 1, 3);
        gridPane.add(R3, 1, 4);
        gridPane.add(R4, 1, 5);

        dialog.getDialogPane().setContent(gridPane);


        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                try
                {
                    int r1 = Integer.parseInt(R1.getText());
                    int r2 = Integer.parseInt(R2.getText());
                    int r3 = Integer.parseInt(R3.getText());
                    int r4 = Integer.parseInt(R4.getText());

                    int grid = Integer.parseInt(gridDimension.getText());
                    int s = Integer.parseInt(spawn.getText());

                    //Trying to sanitize basic input
                    if(r2<r1 || r1 == 0 || r2==26)throw new Exception();
                    if(r3<r4 || r4 == 0 || r3==26)throw new Exception();
                    if(grid <3 || grid>30)throw new Exception();
                    if(s<1 || s>99)throw new Exception();
                gameControler.newGame(new GameBuilder(
                        grid,
                        new int[]{r1,r2,r3,r4},
                       s));
                } catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText("It looks like your input for a " +
                            "custom game was invalid.");
                    alert.showAndWait();
                }


            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();

    }
}
