package model.collision;

import model.IngameObject;

/**
 * Поведение разрушения при столкновении.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class BehaviourDestroy extends CollisionBehaviour {

	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourDestroy instance = null;
	
	protected BehaviourDestroy() {
	}
	
	/**
	 * Возвращает экземпляр поведения разрушения
	 * @return
	 */
	public static BehaviourDestroy getInstance() {
		
		if (instance == null) {
			instance = new BehaviourDestroy();
		}
		
		return instance;
	}
	
	@Override
	public void invoke(CollidedObject from, IngameObject to) {
		
		to.destroy();
	}
}
