package model.collision.CollisionManagers;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import model.Direction;
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
        Direction direction;
        if (isCollisionSide(RIGHT_COLLISION)) {
            direction = Direction.east();
            _modelmanager.boundsCollisionOcured(((SpriteGTGE)sprite).getSpriteStorage().getObjectView().getIngameObject(), direction);
        }
        if (isCollisionSide(TOP_COLLISION)) {
            direction = Direction.north();
            _modelmanager.boundsCollisionOcured(((SpriteGTGE)sprite).getSpriteStorage().getObjectView().getIngameObject(), direction);
        }
        if (isCollisionSide(LEFT_COLLISION)) {
            direction = Direction.west();
            _modelmanager.boundsCollisionOcured(((SpriteGTGE)sprite).getSpriteStorage().getObjectView().getIngameObject(), direction);
        }
        if (isCollisionSide(BOTTOM_COLLISION)){
            direction = Direction.south();
            _modelmanager.boundsCollisionOcured(((SpriteGTGE)sprite).getSpriteStorage().getObjectView().getIngameObject(), direction);
        }
    }
}
