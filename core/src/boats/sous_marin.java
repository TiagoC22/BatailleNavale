package boats;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Boat;

//Projet bataille navale - UPMC 2018 @Tiago @Gautier

public class sous_marin extends Boat
{
    public int getSize() { return Boat.sousmarinSize; }
    public int getType() { return Boat.sousmarinType; }
    public String getName() { return Boat.sousmarinName; }

    public sous_marin(Sprite posCent, Sprite se)
    {
        super(posCent, se);
    }
}