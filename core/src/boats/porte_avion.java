package boats;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Boat;


public class porte_avion extends Boat
{
    public int getSize() { return Boat.porteavionSize; }
    public int getType() { return Boat.porteavionType; }
    public String getName() { return Boat.porteavionName; }

    public porte_avion(Sprite posCent, Sprite se)
    {
        super(posCent, se);
    }
}
