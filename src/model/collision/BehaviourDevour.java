package model.collision;

import model.IngameObject;

/**
 * Поведение поглощения при столкновении. 
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourDevour extends CollisionBehaviour {
	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourDevour instance = null;
	
	protected BehaviourDevour() {
	}
	
	/**
	 * Возвращает экземпляр поведения поглощения.
	 * @return
	 */
	public static BehaviourDevour getInstance() {
		
		if (instance == null) {
			instance = new BehaviourDevour();
		}
		
		return instance;
	}
	
	@Override
	public void invoke(CollidedObject from, CollidedObject to) {
		// TODO
	}
}
