import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

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

        displayCell.setMaterial(cellColor);
        cellColor.setSpecularColor(Color.GREEN);
        cellColor.setDiffuseColor(Color.GREEN);
    }


}

