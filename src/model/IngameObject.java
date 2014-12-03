package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.HashMap;

import model.collision.CollisionBehaviour;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;

/**
 * Класс игрового объекта.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class IngameObject implements PositionChangeListener, SpeedChangeListener {

    private Boolean _isDestroyed = false;
	private Point2D.Float position = null;
	private Speed2D speed = null;
	private ArrayList<CollisionBehaviour> defaultColBehaviour = null;
	private HashMap<Class<?>, ArrayList<CollisionBehaviour>> specialColBehaviours = null;
	private GameField field = null;
	
	public IngameObject(GameField field) {
		
	}
	
	/**
	 * Получить скорость.
	 * @return Текущая скорость.
	 */
	public Speed2D getSpeed() {

		// TODO Method stub
		return null;
	}
	
	/**
	 * Установить скорость.
	 * @param speed Новая скорость.
	 */
	public void setSpeed(Speed2D speed) {

		// TODO Method stub
	}
	
	/**
	 * Получить позицию.
	 * @return Текущая позиция.
	 */
	public Point2D.Float getPosition() {

		// TODO Method stub
		return null;
	}
	
	/**
	 * Задать позицию.
	 * @param pos Новая позиция.
	 */
	public void setPosition(Point2D.Float pos) {

		// TODO Method stub
	}
	
	/**
	 * Сдвинуть объект.
	 * @param delta Величина изменения позиции
	 */
	public void move(Point2D.Float delta) {
		
		// TODO Method stub
	}
	
	/**
	 * Обрабатывает столкновение с другим объектом.
	 * @param other Объект, столкнувшийся с данным.
	 */
	public void processCollision(IngameObject other) {
	
		// TODO Auto-generated method stub
	}
	
	/**
	 * Получить список поведений по умолчанию при столкновении
	 * @return Список объектов поведения
	 */
	public ArrayList<CollisionBehaviour> getDefaultCollisionBehaviours() {
		
		// TODO Перед возвращением КЛОНИРОВАТЬ коллекцию ВРУЧНУЮ
		return null;
	}
	
	/**
	 * Добавить поведение по умолчанию при столкновении
	 * @param behaviour Добавляемое поведение
	 */
	public void addDefaultCollisionBehaviour(CollisionBehaviour behaviour) {
	
		// TODO Method stub
	}
	
	/**
	 * Удалить поведение по умолчанию при столкновении
	 * @param behaviour Поведение для удаления
	 */
	public void removeDafaultCollisionBehavior(CollisionBehaviour behaviour) {
		
		// TODO Method stub
	}
	
	/**
	 * Получить словарь специальных поведений при столкновении
	 * @return Ключ -- класс объектов, значение -- список поведений, определённых при столкновении 
	 *         с этим объектом
	 */
	public HashMap<Class<?>, ArrayList<CollisionBehaviour>> getSpecificCollisionBehaviours() {
		
		// TODO Клонировать коллекцию
		return null;
	}
	
	/**
	 * Добавить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определяемое при столкновении с этим классом объектов
	 */
	public void addSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
		
		// TODO Method stub
	}
	
	/**
	 * Удалить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определённое при столкновении с этим классом объектов
	 */
	public void removeSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
		
		// TODO Method stub
	}
	
	/**
	 * Очистить список поведений при столкновении по умолчанию
	 */
	public void cleanDefaultCollisionBehaviours() {
		
		// TODO Method stub
	}
	
	/**
	 * Очистить списки специальных поведений при столкновении для всех классов объектов
	 */
	public void cleanAllSpecificCollisionBehaviours() {
		
		// TODO Method stub
	}
	
	/**
	 * Очистить список специальных поведений при столкновений для класса объектов
	 * @param cl Класс объектов, для которых очищается список поведений
	 */
	public void cleanSpecificCollisionBehaviours(Class<?> cl) {
		
		// TODO Method stub
	}
	
	/**
	 * Уничтожает объект.
	 * По умолчанию ничего не освобождает.
	 */
	public void destroy() {
	    this._isDestroyed = true;
	}
	
	//-------------------------------------------------------------------------------------------//
	
	@Override
	public void positionChanged(Float newpos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		// TODO Auto-generated method stub
		
	}
}
