/**
 * Created by CEP Computer Tech on 9/29/2016.
 */
public class Conway {

    private boolean[][][] grid;


    //If the grid is started without a preset, this will be called.
    public Conway()
    {
        grid = new boolean[32][32][32];
        for(int i=0i<32;i++)
        {
            for(int j = 0; j<32;j++)
            {

            }
        }

    }

    public Conway(boolean[][][] preset)
    {
        grid = preset;
    }
    public void createGrid()
    {

    }

    public void step()
    {

    }
}
