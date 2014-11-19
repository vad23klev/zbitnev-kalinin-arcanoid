package model.collision;

import model.IngameObject;

public class BehaviourPaddleRebound extends CollisionBehaviour {
	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourPaddleRebound instance = null;
	
	protected BehaviourPaddleRebound() {
	}
	
	/**
	 * Возвращает экземпляр поведения отражения от ракетки.
	 * @return
	 */
	public static BehaviourPaddleRebound getInstance() {
		
		if (instance == null) {
			instance = new BehaviourPaddleRebound();
		}
		
		return instance;
	}
	
	@Override
	public void invoke(IngameObject from, IngameObject to) {
		// TODO
	}
}
