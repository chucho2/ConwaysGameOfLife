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
    final Box displayCell = new Box(1,1,1);

    public Cell()
    {
        this.getChildren().add(displayCell);
    }

    public void setAlive()
    {

    }


    public void setDead()
    {

    }
}

