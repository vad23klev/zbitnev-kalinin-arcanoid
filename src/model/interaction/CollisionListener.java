package model.interaction;

import java.util.ArrayList;
import java.util.HashMap;
import model.IngameObject;


/**
 * Интерфейс слушателя событий о столкновениях игровых объектов.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public interface CollisionListener {

	/**
	 * Произошла коллизия между игровыми объектами.
	 * @param storage Ключ -- столкнувшийся объект, Значение -- список объектов, с которыми он
	 *                столкнулся.
	 */
	void collisionOccured(HashMap<IngameObject, ArrayList<IngameObject>> storage);
}
