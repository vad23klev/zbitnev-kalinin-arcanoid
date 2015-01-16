package model;

import java.awt.Dimension;
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

    protected Boolean _isDestroyed = false;
    
	protected Point2D.Float position = null;
	protected Dimension size = null;
	protected Speed2D speed = null;
	protected ArrayList<CollisionBehaviour> defaultColBehaviour = null;
	protected HashMap<Class<?>, ArrayList<CollisionBehaviour>> specialColBehaviours = null;
	protected GameField field = null;
	protected ArrayList<PositionChangeListener> positionListeners = new ArrayList<>();
	protected ArrayList<SpeedChangeListener> speedListeners = new ArrayList<>();
	
	/**
	 * Создает игровой объект, координаты (0, 0), нулевая скорость, нулевой размер.
	 * @param field Игровое поле.
	 */
	public IngameObject(GameField field) {
		
	    this(field, new Point2D.Float(0, 0), new Dimension(0, 0));
	}
	
	/**
	 * Создает игровой объект с нулевой скоростью.
	 * @param field Игровое поле.
	 * @param pos Позиция объекта.
	 * @param dim Размеры объекта.
	 */
	public IngameObject(GameField field, Point2D.Float pos, Dimension dim) {
	    
	    this(field, pos, dim, new Speed2D(0, 0));
	}
	
	/**
	 * Создает игровой объект.
	 * @param field Игровое поле.
	 * @param pos Позиция объекта.
	 * @param dim Размеры объекта.
	 * @param speed Скорость объекта.
	 */
	public IngameObject(GameField field, Point2D.Float pos, Dimension dim, Speed2D speed) {
	    
	    if (field == null || pos == null || dim == null || speed == null) {
	        throw new NullPointerException();
	    }
	    
	    this.field = field;
	    this.setSize(dim);
        this.setPosition(pos);
	    this.setSpeed(speed);
	}
	
	/**
	 * Возвращает поле, на котором находится объект.
	 * @return Игровое поле.
	 */
	public GameField getField() {
	    
	    return this.field;
	}
	
	/**
	 * Получить скорость.
	 * @return Текущая скорость.
	 */
	public Speed2D getSpeed() {

		return (Speed2D) speed.clone();
	}
	
	/**
	 * Установить скорость.
	 * @param speed Новая скорость.
	 */
	public void setSpeed(Speed2D speed) {

		this.speed = speed;
		for (SpeedChangeListener l : speedListeners) {
			l.speedChanged(this.speed);
		}
	}
	
	/**
	 * Получить позицию.
	 * @return Текущая позиция.
	 */
	public Point2D.Float getPosition() {

		return (Point2D.Float) position.clone();
	}
	
	/**
	 * Задать позицию.
	 * @param pos Новая позиция.
	 */
	public void setPosition(Point2D.Float pos) {

	    if (pos == null) {
	        throw new NullPointerException();
	    }
		if (pos.x < 0 || pos.x > field.getSize().width ||
				pos.y < 0 || pos.y > field.getSize().height) {
			
			// TODO Корректное исключение. Не стоит, думаю можно вывести предупреждение.
			//return;
		}
		position = pos;
		for (PositionChangeListener l : positionListeners) {
			l.positionChanged(this.position);
		}
	}
	
	/**
	 * Задает размер объекта в пикселях.
	 * @param dim
	 */
	public void setSize(Dimension dim) {
	    
	    if (dim == null) {
	        throw new NullPointerException();
	    }
	    size = dim;
	}
	
	/**
	 * Возвращает размер объекта в пикселях.
	 * @return
	 */
	public Dimension getSize() {
	    
	    return (Dimension) size.clone();
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
	public void positionChanged(Point2D.Float newpos) {
		this.position = newpos;
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Добавить слушателя изменения позиции объекта.
	 * @param l Новый слушатель.
	 */
	public void addPositionChangeListener(PositionChangeListener l) {
		positionListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения позиции объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removePositionChangeListener(PositionChangeListener l) {
		positionListeners.remove(l);
	}
	
	/**
	 * Добавить слушателя изменения скорости объекта.
	 * @param l Новый слушатель.
	 */
	public void addSpeedChangeListener(SpeedChangeListener l) {
		speedListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения скорости объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removeSpeedChangeListener(SpeedChangeListener l) {
		speedListeners.remove(l);
	}
}
