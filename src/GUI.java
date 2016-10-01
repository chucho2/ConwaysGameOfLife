import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
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


    @Override
    public void start(Stage primaryStage) {

        gameOfLife = new Conway(new int[]{3,6,3,6});
        gridXform = new Xform();


        /*Written for me by Sample Molecule*/
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildAxes();
        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.BLACK);
        handleKeyboard(scene);
        primaryStage.setTitle("Conway's Game of Life - Demitri Maestas");//Except for this.
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        /*End Pre-Written*/

        /*Getting the actual game loop going*/
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        /*Setting the GUI to rotate*/
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.play();
    }
}
