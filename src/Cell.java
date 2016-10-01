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


    public void grow()
    {
        if((int)displayCell.getDepth()< 1)
        {
            displayCell.setDepth(displayCell.getDepth()+0.005);
            displayCell.setHeight(displayCell.getHeight()+0.005);
            displayCell.setWidth(displayCell.getWidth()+0.005);
        }

    }


    public void setDead()
    {

    }
}

