package model.collision;

/**
 * Поведение разрушения при столкновении.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class BehaviourDestroy extends CollisionBehaviour {

	/**
	 * Возвращает экземпляр поведения разрушения
	 * @return
	 */
	public static BehaviourDestroy getInstance() {
		
		if (instance == null) {
			instance = new BehaviourDestroy();
		}
		
		return (BehaviourDestroy)instance;
	}
}
