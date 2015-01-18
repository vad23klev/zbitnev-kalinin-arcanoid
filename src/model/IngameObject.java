package model;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.collision.CollisionBehaviour;
import model.collision.SpecialBehaviours;
import model.interaction.GenericEventListener;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;

/**
 * Класс игрового объекта.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class IngameObject implements Cloneable, PositionChangeListener, SpeedChangeListener {
    
    protected Boolean _isDestroyed = false;
    
	protected Point2D.Float position = null;
	protected Dimension size = null;
	protected Speed2D speed = null;
	protected ArrayList<CollisionBehaviour> defaultColBehaviour = new ArrayList<>();
	protected HashMap<Class<?>, SpecialBehaviours> specialColBehaviours 
		= new HashMap<>();
	protected GameField field = null;
	protected ArrayList<PositionChangeListener> positionListeners = new ArrayList<>();
	protected ArrayList<SpeedChangeListener> speedListeners = new ArrayList<>();
	protected ArrayList<GenericEventListener> geneventListeners = new ArrayList<>();

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
		
		this.setPosition(new Point2D.Float(this.getPosition().x + delta.x, this.getPosition().y + delta.y));
	}
	
	/**
	 * Обрабатывает столкновение с другим объектом.
	 * @param other Объект, столкнувшийся с данным.
	 */
	public void processCollision(IngameObject other) {

		// Вызываем специализированные коллизии, если таковые имеются
	    boolean foundSpecial = false;
	    
	    Iterator i = specialColBehaviours.entrySet().iterator();
	    while (i.hasNext()) {
	        Map.Entry<Class<?>, SpecialBehaviours> entry = (Map.Entry)i.next();
	        if (entry.getValue().flagCheckDerived) {
	            if (entry.getKey().isInstance(other)) {
	                foundSpecial = true;
	                for (CollisionBehaviour cb : entry.getValue().behaviours) {
	                    cb.invoke(other, this);
	                }
	            }
	        } else if (entry.getKey().equals(other.getClass())) {
                foundSpecial = true;
                for (CollisionBehaviour cb : entry.getValue().behaviours) {
                    cb.invoke(other, this);
                }
            }
	    }
		
		// Если их нет, тогда вызываем коллизию по умолчанию
		// Если и она не определена, то ничего не происходит
	    if (!foundSpecial) {
    		for (CollisionBehaviour cb : defaultColBehaviour) {
    			cb.invoke(other, this);
    		}
	    }
	}
	
	/**
	 * Получить список поведений по умолчанию при столкновении
	 * @return Список объектов поведения
	 */
	public ArrayList<CollisionBehaviour> getDefaultCollisionBehaviours() {
		
		return defaultColBehaviour;
	}
	
	/**
	 * Добавить поведение по умолчанию при столкновении
	 * @param behaviour Добавляемое поведение
	 */
	public void addDefaultCollisionBehaviour(CollisionBehaviour behaviour) {
	
		defaultColBehaviour.add(behaviour);
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
	 * @return Ключ -- класс объектов, значение -- флаги и список поведений, определённых при столкновении 
	 *         с этим объектом
	 */
	public HashMap<Class<?>, SpecialBehaviours> getSpecificCollisionBehaviours() {
		
		return specialColBehaviours;
	}
	
	/**
	 * Добавить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определяемое при столкновении с этим классом объектов
	 * @param checkDerived Если true, то будут также проверяться наследники класса объектов.
	 *                     Игнорируется, если для класса уже задано какое-либо поведение.
	 */
	public void addSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb, boolean checkDerived) {
		
		if (!c.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (!specialColBehaviours.containsKey(c)) {
			
			SpecialBehaviours newsb = new SpecialBehaviours(cb);
			newsb.flagCheckDerived = checkDerived;
			specialColBehaviours.put(c, newsb);
		}
		else {
			specialColBehaviours.get(c).behaviours.add(cb);
		}
	}
	
	/**
     * Добавить специальное поведение при столкновении
     * @param c Класс объектов
     * @param cb Поведение, определяемое при столкновении с этим классом объектов
     */
	public void addSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
	    
	    this.addSpecificCollisionBehaviour(c, cb, false);
	}
	
	/**
	 * Удалить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определённое при столкновении с этим классом объектов
	 */
	public void removeSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
		
		if (!c.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (specialColBehaviours.containsKey(c)) {
			specialColBehaviours.get(c).behaviours.remove(cb);
			if (specialColBehaviours.get(c).behaviours.isEmpty()) {
			    specialColBehaviours.remove(c);
			}
		}
	}
	
	/**
	 * Очистить список поведений при столкновении по умолчанию
	 */
	public void cleanDefaultCollisionBehaviours() {
		
		defaultColBehaviour.clear();
	}
	
	/**
	 * Очистить списки специальных поведений при столкновении для всех классов объектов
	 */
	public void cleanAllSpecificCollisionBehaviours() {
		
		specialColBehaviours.clear();
	}
	
	/**
	 * Очистить список специальных поведений при столкновений для класса объектов
	 * @param cl Класс объектов, для которых очищается список поведений
	 */
	public void cleanSpecificCollisionBehaviours(Class<?> cl) {
		
		if (!cl.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (specialColBehaviours.containsKey(cl)) {
			specialColBehaviours.remove(cl);
		}
	}
	
	/**
	 * Уничтожает объект.
	 * По умолчанию ничего не освобождает.
	 */
	public void destroy() {
		
	    this._isDestroyed = true;
	    this.field.removeObject(this);
	    for (GenericEventListener l : geneventListeners) {
	    	l.destroyed();
	    }
	}
	
	//-------------------------------------------------------------------------------------------//
	
	@Override
	public void positionChanged(Point2D.Float newpos) {
		
	    this.position = newpos;
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		
		this.speed = newspeed;
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
	
	/**
	 * Добавить слушателя событий жизни объекта.
	 * @param l Добавляемый слушатель.
	 */
	public void addGenericEventListener(GenericEventListener l) {
		
		if (l == null) {
			return;
		}

		geneventListeners.add(l);
	}
	
	/**
	 * Удалить слушателя событий жизни объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removeGenericEventListener(GenericEventListener l) {
		geneventListeners.remove(l);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		IngameObject clone = (IngameObject)super.clone();
		clone._isDestroyed = this._isDestroyed;
		clone.field = this.field;    // ссылка на поле просто копируется, да
		clone.position = (Point2D.Float) this.position.clone();
		clone.size = (Dimension) this.size.clone();
		clone.speed = (Speed2D) this.speed.clone();
		
		return clone;
	}
}
