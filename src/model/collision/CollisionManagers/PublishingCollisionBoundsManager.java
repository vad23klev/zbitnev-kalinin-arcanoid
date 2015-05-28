package model.collision.CollisionManagers;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import model.Direction;
import model.collision.CollidedObject;
import view.SpriteGTGE;

/**
 * Менеджер столкновений игровых объектов с границами игрового поля.
 * @author Tenk0Kugen
 */
public class PublishingCollisionBoundsManager extends CollisionBounds{
    protected ModelCollisionManager _modelmanager;

    public PublishingCollisionBoundsManager(Background back, ModelCollisionManager manager) {
        super(back);
        if (manager == null || back == null) {
                throw new NullPointerException();
        }
        _modelmanager = manager;
    }

    @Override
    public void collided(Sprite sprite) {   
        int collisionSide = -1;
        
        collisionSide = isCollisionSide(RIGHT_COLLISION)? CollidedObject.SIDE_RIGHT: collisionSide;
        collisionSide = isCollisionSide(LEFT_COLLISION)? CollidedObject.SIDE_LEFT: collisionSide;
        collisionSide = isCollisionSide(BOTTOM_COLLISION)? CollidedObject.SIDE_BOTTOM: collisionSide;
        collisionSide = isCollisionSide(TOP_COLLISION)? CollidedObject.SIDE_TOP: collisionSide;
        _modelmanager.boundsCollisionOcured(((SpriteGTGE)sprite).getSpriteStorage().getObjectView().getIngameObject(), 
                collisionSide ,new Rectangle2D.Double(getCollisionShape1(sprite).getX(),
                                                        getCollisionShape1(sprite).getY(),
                                                        getCollisionShape1(sprite).getWidth(),
                                                        getCollisionShape1(sprite).getHeight()));
    }
}
