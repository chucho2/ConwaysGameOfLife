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
public class GUIComponents {

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

    private Main gameControler;
    private ToolBar topToolbar;
    private ToolBar bottomToolBar;




    /*These timeles are used in tandem with the Arrow keys to control zooming*/
    Timeline zoomIn = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> zoom(true)));
    Timeline zoomOut = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> zoom(false)));



    /*This will fire to rotate the camera Angle using AutoAnimate*/
    private Timeline rotate = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> autoAnimate()));



    public GUIComponents(Main main)
    {
        this.gameControler = main;
        ConwayToolBar toolBars = new ConwayToolBar(gameControler,this);
        topToolbar = toolBars.createTopToolBar();
        bottomToolBar = toolBars.createBottomToolBar();
    }
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
    private void handleKeyboard(SubScene scene) {
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
     * @param rotate true if the grid should be rotating; else false.
     */
    public void rotate(boolean rotate)
    {
       if(rotate)this.rotate.play();
       else  this.rotate.pause();
    }
   /*This actually takes care of building the simulation for us,
    * will be called by the splash screen at some point with custom
    * parameters.
    * */
    protected SubScene getGameBoard()
    {

        gridXform = new Xform();

        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildAxes();
        SubScene scene = new SubScene(root,1024,640);
        handleKeyboard(scene);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        //handleKeyboard(scene);



        /*Setting the Main to rotate*/
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.play();
        return scene;
    }


    public ToolBar getTopToolBar()
    {
        return this.topToolbar;
    }

    public ToolBar getBottomToolBar()
    {
        return this.bottomToolBar;
    }
    public void removeCells(ArrayList<Cell> cells)
    {
         //TODO fill out this
    }

    public void addCells(ArrayList<Cell> cells)
    {

        for(Cell cell: cells)
        {
            int[] positions = cell.getPosition();
            cell.setTranslate(positions[0],positions[1],positions[2]);
            root.getChildren().add(cell);
        }
    }
}
