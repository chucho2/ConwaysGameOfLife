import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * creates a SubScene which can hold all of the Box's from within each cell
 * of the game.
 * @author Demitri Maestas
 */
public class GridGUI {


    private SubScene scene;
    final Group root = new Group();
    final Xform axisGroup = new Xform();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static double CAMERA_INITIAL_DISTANCE = -50;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;

    private Box boundary;
    private boolean boundaryVisible;
    private int gridDimension;



    public GridGUI()
    {
         this(30);
    }

    public GridGUI(int gridDimension){
        this.gridDimension = gridDimension;
        CAMERA_INITIAL_DISTANCE = -gridDimension*3.2;
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildAxes();
        makeBoundaryBox();
        scene = new SubScene(root,1024,640,true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.play();

    }


    //Timer in charge of incrementing the rotate for us.
    private Timeline rotate = new Timeline(new KeyFrame(
            Duration.millis(10),
            ae -> incrementRotate()));


    /**
     * adds the given ArrayList of Cells to the scene in the GridGUI object
     * @param cells the cell to be added to the GridGUI Instance.
     */
    public void setBoundary(ArrayList<Cell> cells)
    {
        for(Cell cell: cells)
        {
            int[] positions = cell.getGuiPosition();
            cell.setTranslate(positions[0],positions[1],positions[2]);
            root.getChildren().add(cell);
        }
    }

    /**
     * adds the given ArrayList of Cells to the scene in the GridGUI object
     * @param cells the cell objects to be removed from the GridGUI Instance.
     */
    public void addCells(ArrayList<Cell> cells)
    {
        for(Cell cell: cells)
        {
            int[] positions = cell.getGuiPosition();
            System.out.println(Arrays.toString(positions));

            cell.setTranslate(positions[0],positions[1],positions[2]);
            if(!root.getChildren().contains(cell))root.getChildren().add(cell);
        }
    }


    /**
     * removes the given ArrayList of Cells from the scene in the GridGUI object
     * @param cells the cell objects to be removed from the GridGUI Instance.
     */
    public void removeCells(ArrayList<Cell> cells)
    {
        for(Cell cell: cells)
        {
            root.getChildren().remove(cell);
        }
    }

    /**
     * displays a grey box around the grid, indicating the boundary.
     * @param showing true if the grey box should be showing; else false.
     */
    public void toggleBoundary(boolean showing)
    {
        if(showing)
        {
            boundaryVisible = true;
            boundary.setVisible(true);
        }
        else
        {
            boundaryVisible = false;
            boundary.setVisible(false);
        }

    }

    /**
     * @return if the grey box around the grid indicating the boundary
     *         is showing
     */
    public boolean getBoundary()
    {
         return this.boundaryVisible;
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


    /**
     * @return the SubScene that the 3D grid is located on.
     */
    public SubScene getGridGUI()
    {
        return this.scene;
    }



    /**
     * moves the rotation just a bit by specified factors.
     */
    private void incrementRotate()
    {

        cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 0.05);
        cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 0.05);
    }

    /**
     * Sets the parameters for the boundary box and puts it into a sleep mode
     */
    private void makeBoundaryBox()
   {
       PhongMaterial cellColor = new PhongMaterial();
       cellColor.setDiffuseColor(
               new Color(0.1,0.1,0.1,0.0001));
       boundary= new Box(gridDimension,gridDimension,gridDimension);
       boundary.setMaterial(cellColor);
       boundary.setTranslateX(0);
       boundary.setTranslateY(0);
       boundary.setTranslateZ(0);
       root.getChildren().add(boundary);
       boundary.setVisible(false);
   }


    /**
     * Most of the following methods are stock from the XFrom example in the java tutorials.
     * https://docs.oracle.com/javase/8/javafx/graphics-tutorial/sampleapp3d.htm
     */
    private void buildCamera()
    {
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

    private void buildAxes()
    {

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);
        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(false);
        world.getChildren().addAll(axisGroup);
    }
}
