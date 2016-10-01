import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;


/**
 * is in charge of handling the Conway Object, as well as running all the timers
 * that are firing in order to create the Animations. GUI is modeled extensively
 * after the Molecule object from the JavaFX Tutorials.
 * GUI has start() in it.
 * @author Demitri Maestas
 */
public class GUI extends Application {

    /*Found in the JavaFX tutorials*/
    final Group root = new Group();
    final Xform axisGroup = new Xform();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -100;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;
    private Xform gridXform;

    /*This is the object that holdes all of the Game Logic*/
    private Conway gameOfLife;


   /*These timeles are used in tandem with the Arrow keys to control zooming*/
    Timeline zoomIn = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> zoom(true)));
    Timeline zoomOut = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> zoom(false)));

    /*this is in charge of firing the main game every "Wall second"*/
    private Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(1000),
            ae -> updateGame()));

    /*This will fire to rotate the camera Angle using AutoAnimate*/
    private Timeline rotate = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> autoAnimate()));


    /*These methods are all documented in the Java Moluclue Sample*/
    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(false);
        world.getChildren().addAll(axisGroup);
    }
    /*End pre-written methods*/


    /**
     * Auto Animate is in charge of rotating the Camera.
     * When the "rotate" timer fires, it add's some
     * amount to every angle degree.
     */
    private void autoAnimate() {

        cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 0.05);
        cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 0.05);
    }


    /**
     * will adjust the Z axis angle to let someone zoom in.
     * @param in is true if we are zooming in.
     */
    private void zoom(boolean in)
    {
        if(in)camera.setTranslateZ(camera.getTranslateZ()+0.5);
        else camera.setTranslateZ(camera.getTranslateZ()-0.5);
    }

    /**
     * will check cases, to see if up or down was pressed,
     * and will then call zoom to adjust the Z axis accordingly.
     * @param scene of which everything is located.
     */
    private void handleKeyboard(Scene scene) {
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
                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
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
                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                }
            }
        });
    }

    /**
     * this is called by a timer every second to continue the itteration of the game.
     * it will write the previous version of the game onto the screen (while the new one is loading)
     * it loops over every space in the grid, translates it so we can see a center rotation, and writes
     * the new boxes onto the screen. When that's done, it animates all the boxes, and sets the next
     * itteration to begin loading.
     */
    private void updateGame() {
        Cell[][][] update = gameOfLife.getGrid();
        world.getChildren().remove(gridXform);
        gridXform = new Xform();
        for(int i = -15;i<15;i++)
        {
            for(int j = -15; j<15;j++)
            {
                for(int k = -15;k<15;k++)
                {
                    if(update[i+16][j+16][k+16]!= null )
                    {
                        Cell current = update[i+16][j+16][k+16];
                        current.setTranslate(i,j,k);
                        gridXform.getChildren().add(current);
                    }

                }
            }
        }
        world.getChildren().addAll(gridXform);
        for(Cell c:gameOfLife.liveCells())
        {
            c.setAlive();
        }

        gameOfLife.step();
    }

    /*This actually takes care of building the simulation for us,
    * will be called by the splash screen at some point with custom
    * parameters.
    * */
    private Scene startGame(int preset, int[] r)
    {
        gameOfLife = new Conway(r);
        gridXform = new Xform();

        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildAxes();
        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        handleKeyboard(scene);

        /*Getting the actual game loop going*/
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        /*Setting the GUI to rotate*/
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.play();
        return scene;
    }

    /*Kind of a hard coded hack, creates the R values box on the splash screen*/
    private Group createRValues(Stage primaryStage)
    {
        Group customR = new Group();
        TextArea r1 = new TextArea("R1");
        r1.setMaxSize(5,2);
        customR.getChildren().add(r1);
        TextArea r2 =new TextArea("R2");
        r2.setMaxSize(5,5);
        r2.setTranslateX(50);
        customR.getChildren().add(r2);
        TextArea r3 = new TextArea("R3");
        r3.setMaxSize(5,5);
        r3.setTranslateX(100);
        customR.getChildren().add(r3);
        TextArea r4 = new TextArea("R4");
        r4.setMaxSize(5,5);
        r4.setTranslateX(150);
        customR.getChildren().add(r4);
        Button fire = new Button("Run Custom Simulation");
        fire.setTranslateX(200);
        fire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int[] r = new int[4];
                r[0] = Integer.parseInt(r1.getText());
                r[1] = Integer.parseInt(r2.getText());
                r[2] = Integer.parseInt(r3.getText());
                r[3] = Integer.parseInt(r4.getText());
                primaryStage.setScene(startGame(-1,r));
            }
        });
        customR.getChildren().add(fire);
        customR.setTranslateX(400);
        customR.setTranslateY(200);
        return customR;
    }


    /**
     * fires up the splash screen and bulids the components to it.
     * will be passed to private methods to determine the layout
     * of the actual simulation.
     * @param primaryStage the JavaFX primary stage.
     */
    @Override
    public void start(Stage primaryStage) {

        Text topWelcome = new Text(120,70,"Conway's Game of Life - 3D Simulation");
        topWelcome.setFont(new Font(40));
        topWelcome.setFill(Color.WHITE);

        Group root = new Group();
        root.getChildren().add(topWelcome);
        root.getChildren().add(createRValues(primaryStage));
        Button startRandom = new Button("Run a Random Simulation");
        startRandom.setTranslateX(400);
        startRandom.setTranslateY(500);
        root.getChildren().add(startRandom);
        startRandom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(startGame(-1,new int[]{3,6,3,6}));
            }
        });
        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("Conway's Game of Life - Demitri Maestas");//Except for this.
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
