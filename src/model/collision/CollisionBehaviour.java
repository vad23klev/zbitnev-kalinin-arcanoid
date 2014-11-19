package model.collision;

import model.IngameObject;

/**
 * Поведение столкнувшегося объекта; синглтон.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class CollisionBehaviour {

	/**
	 * Экзмепляр синглтона.
	 */
	private static CollisionBehaviour instance = null;
	
	private CollisionBehaviour() {
		super();
	}
	
	/**
	 * Возвращает экзмепляр поведения объектов. Обязательно переопределяется в классах-наследниках.
	 * @return Экземпляр поведения.
	 */
	public static CollisionBehaviour getInstance() {
		
		if (instance == null) {
			instance = new CollisionBehaviour();
		}
		
		return instance;
	}
	
	/**
	 * Осуществить поведение пассивного объекта в ответ на столкновение.
	 * @param from Активный объект (не изменяется).
	 * @param to Пассивный объект (изменяет состояние в ответ на столкновение).
	 */
	public void invoke(IngameObject from, IngameObject to) {
		
	}
}
