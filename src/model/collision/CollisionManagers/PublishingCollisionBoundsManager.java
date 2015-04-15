package model.collision.CollisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

/**
 * Менеджер столкновений игровых объектов с границами игрового поля.
 * @author Tenk0Kugen
 */
public class PublishingCollisionBoundsManager extends CollisionBounds{

    public PublishingCollisionBoundsManager(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    @Override
    public void collided(Sprite sprite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
