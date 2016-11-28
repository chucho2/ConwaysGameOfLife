import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by chucho2 on 11/27/16.
 */
public class GridGUI {


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

    private ArrayList<Cell> cells = new ArrayList<Cell>();






    /*This will fire to setRotate the camera Angle using AutoAnimate*/
    private Timeline rotate = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> autoAnimate()));


    /*These methods are all documented in the Java Moluclue Sample*/
    private void buildCamera() {

        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);

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
     * When the "setRotate" timer fires, it add's some
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
    public void zoom(boolean in)
    {
        if(in)camera.setTranslateZ(camera.getTranslateZ()+0.5);
        else camera.setTranslateZ(camera.getTranslateZ()-0.5);
    }



    /**
     * @param rotate true if the grid should be rotating; else false.
     */
    public void setRotate(boolean rotate)
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
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        //handleKeyboard(scene);



        /*Setting the Main to setRotate*/
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.play();
        return scene;
    }



    public void addCells(ArrayList<Cell> cells)
    {
        this.cells = cells;
        for(Cell cell: cells)
        {
            int[] positions = cell.getPosition();
            cell.setTranslate(positions[0],positions[1],positions[2]);
            root.getChildren().add(cell);
        }
    }

    public void purge()
    {
        if(cells != null)
        {
            for(Cell cell: cells)
            {
                int[] positions = cell.getPosition();
                cell.setTranslate(positions[0],positions[1],positions[2]);
                root.getChildren().remove(cell);
            }
        }

    }
}