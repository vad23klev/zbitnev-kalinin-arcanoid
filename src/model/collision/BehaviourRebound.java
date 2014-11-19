package model.collision;

import model.IngameObject;

/**
 * Поведение упрогого отскока при столкновении.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourRebound extends CollisionBehaviour {
	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourRebound instance = null;
	
	protected BehaviourRebound() {
	}
	
	/**
	 * Возвращает экземпляр поведения упрогого отскока.
	 * @return
	 */
	public static BehaviourRebound getInstance() {
		
		if (instance == null) {
			instance = new BehaviourRebound();
		}
		
		return instance;
	}
	
	@Override
	public void invoke(IngameObject from, IngameObject to) {
		// TODO
	}
}
