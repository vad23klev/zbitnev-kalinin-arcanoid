package model.collision.CollisionManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

/**
 * Менеджер столкновений игровых объектов с границами игрового поля.
 * @author Tenk0Kugen
 */
public class PublishingCollisionBoundsManager extends CollisionBounds{

    @Override
    public void collided(Sprite sprite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public PublishingCollisionBoundsManager(int x, int y, int w, int h){
        //TODO
    }
}
