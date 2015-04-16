package model.collision.CollisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import view.SpriteGTGE;

/**
 * Менеджер столкновений игровых объектов с границами игрового поля.
 * @author Tenk0Kugen
 */
public class PublishingCollisionBoundsManager extends CollisionBounds{
    protected ModelCollisionManager _modelmanager;

    public PublishingCollisionBoundsManager(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    @Override
    public void collided(Sprite sprite) {   
        _modelmanager.boundsCollisionOcured(((SpriteGTGE)sprite).getSpriteStorage().getObjectView().getIngameObject());
    }
    
    public void setModelCollisionManager(ModelCollisionManager manager) {
        if (manager == null) {
                throw new NullPointerException();
        }
        _modelmanager = manager;
    }
}
