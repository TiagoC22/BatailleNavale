package boats;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Boat;


public class croiseur extends Boat
{
    public int getSize() { return Boat.croiseurSize; }
    public int getType() { return Boat.croiseurType; }
    public String getName() { return Boat.croiseurName; }

    public croiseur(Sprite posCent, Sprite se)
    {
        super(posCent, se);
    }
}
