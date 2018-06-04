package boats;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Boat;


public class cuirasser extends Boat
{
    public int getSize() { return Boat.cuirasserSize; }
    public int getType() { return Boat.cuirasserType; }
    public String getName() { return Boat.cuirasserName; }

    public cuirasser(Sprite posCent, Sprite se)
    {
        super(posCent, se);
    }

}
