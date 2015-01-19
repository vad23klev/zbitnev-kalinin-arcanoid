package view.collision;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import model.collision.CollidedObject;
import view.PublishingSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;

/**
 * Менеджер столкновений, сообщающий о коллизиях между объектами
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingCollisionManager extends AdvanceCollisionGroup {

	private static final Sprite PublishingSprite = null;
	protected HashMap<CollidedObject, ArrayList<CollidedObject>> _storage = new HashMap<>();
	
	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCollide(Sprite s1, Sprite s2, CollisionShape shape1, CollisionShape shape2) {
		
		boolean retval = super.isCollide(s1, s2, shape1, shape2);
		
		// Словарь столкновений будет формироваться в процессе детекции коллизий
		if (retval) {
		
			CollidedObject obj1 = new CollidedObject(
					((PublishingSprite)s1).getObjectView().getIngameObject(), 
					new Point2D.Float((float)s1.getOldX(), (float)s1.getOldY()),
					this.collisionSide,
					shape1);
			CollidedObject obj2 = new CollidedObject(
					((PublishingSprite)s2).getObjectView().getIngameObject(), 
					new Point2D.Float((float)s2.getOldX(), (float)s2.getOldY()),
					this.collisionSide,
					shape2);
			
			if (!_storage.keySet().contains(obj1)) {
				_storage.put(obj1, new ArrayList<CollidedObject>());
			}
			_storage.get(obj1).add(obj2);
		}
		
		return retval;
	}
	
	/**
	 * Получить словарь столкновений объектов в текущем кадре. Объекты представлены в виде 
	 * CollidedObject, содержащих дополнительную информацию о коллизиях
	 * @return Словарь столкновений.
	 */
	public HashMap<CollidedObject, ArrayList<CollidedObject>> getCollidedStorage() {
		return _storage;
	}
	
	/**
	 * Очистить словарь столкновений объектов в текущем кадре.
	 */
	public void clearCollidedStorage() {
		_storage.clear();
	}
}
