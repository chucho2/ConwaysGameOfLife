import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;

/**
 * Created by CEP Computer Tech on 9/28/2016.
 */
public class Cell extends Xform {

    final PhongMaterial cellColor = new PhongMaterial();
    Box displayCell= new Box(0.1,0.1,0.1);

    public Cell()
    {
        this.getChildren().add(displayCell);
        displayCell.setMaterial(cellColor);
    }


    private void growStep()
    {
        displayCell.setDepth(displayCell.getDepth()+0.3);
        displayCell.setWidth(displayCell.getWidth()+0.3);
        displayCell.setHeight(displayCell.getHeight()+0.3);
    }

    private void shrinkStep()
    {
        displayCell.setDepth(displayCell.getDepth()-0.3);
        displayCell.setWidth(displayCell.getWidth()-0.3);
        displayCell.setHeight(displayCell.getHeight()-0.3);
    }

    public void setAlive()
    {
        cellColor.setDiffuseColor(Color.GREEN);
        Timeline growing = new Timeline(new KeyFrame(
                Duration.millis(200),
                ae -> growStep()));
        growing.setCycleCount(3);
        growing.play();

    }


    public void setDead()
    {
        cellColor.setDiffuseColor(Color.RED);
        Timeline shrinking = new Timeline(new KeyFrame(
                Duration.millis(200),
                ae -> shrinkStep()));
        shrinking.setCycleCount(3);
        shrinking.play();

    }
}

