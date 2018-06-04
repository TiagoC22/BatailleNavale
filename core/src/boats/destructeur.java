package boats;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Boat;


public class destructeur extends Boat
{
    public int getSize() { return Boat.destructeurSize; }
    public int getType() { return Boat.destructeurType; }
    public String getName() { return Boat.destructeurName; }

    public destructeur(Sprite posCent, Sprite se)
    {
        super(posCent, se);
    }
}
