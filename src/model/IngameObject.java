package model;

import com.golden.gamedev.object.collision.CollisionShape;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.collision.CollidedObject;
import model.collision.CollisionBehaviour;
import model.collision.SpecialBehaviours;
import model.interaction.CollisionEventListener;
import model.interaction.GenericEventListener;
import view.IngameObjectView;

/**
 * Класс игрового объекта.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class IngameObject implements Cloneable {
    
    protected Boolean _isDestroyed = false;
    
	protected IngameObjectView _view = null;
        protected Form _form = null;
	protected ArrayList<CollisionBehaviour> _defaultColBehaviour = new ArrayList<>();
	protected HashMap<Class<?>, SpecialBehaviours> _specialColBehaviours 
		= new HashMap<>();
	protected GameField _field = null;
	protected ArrayList<CollisionEventListener> _collisionEventListeners = new ArrayList<>();
	
        /**
	 * Возвращает форму объекта.
	 * @return Форма.
	 */
	public Form getForm() {
	    
	    return this._form;
	}
        
	/**
	 * Возвращает размер игрового поля, на котором находится объект.
	 * @return Игрового поля размер.
	 */
	public Dimension getFieldSize() {
	    
	    return this._field.getSize();
	}
	
	/**
	 * Получить скорость.
	 * @return Текущая скорость.
	 */
	public Speed2D getSpeed() {

		return this._view.getSpeed();
	}
	
	/**
	 * Установить скорость.
	 * @param speed Новая скорость.
	 */
	public void setSpeed(Speed2D speed) {

		this._view.setSpeed(speed);
                /*
		for (SpeedChangeListener l : _speedListeners) {
			l.speedChanged(this._speed);
		}*/
	}
	
	/**
	 * Получить позицию.
	 * @return Текущая позиция.
	 */
	public Point2D.Double getPosition() {

		return (Point2D.Double) _view.getSpriteStorage().getPosition().clone();
	}
	
	/**
	 * Задать позицию.
	 * @param pos Новая позиция.
	 */
	public void setPosition(Point2D.Double pos) {

	    if (pos == null) {
	        throw new NullPointerException();
	    }
		_view.getSpriteStorage().setPosition(pos);
	}

	/**
	 * Сдвинуть объект.
	 * @param delta Величина изменения позиции
	 */
	public void move(Point2D.Double delta) {
            if (delta == null) {
	        throw new NullPointerException();
	    }
		this.setPosition(new Point2D.Double(this.getPosition().x + delta.x, this.getPosition().y + delta.y));
	}
	
	/**
	 * Обрабатывает столкновение с другим объектом.
	 * @param curr Текущий объект
	 * @param other Объект, столкнувшийся с данным.
	 */
	public void processCollision(CollidedObject other, int collisionSide, Shape collisionShape) {
            if (other == null || collisionShape == null) {
	        throw new NullPointerException();
	    }
		// Вызываем специализированные коллизии, если таковые имеются
	    boolean foundSpecial = false;
	    CollidedObject curr  = new CollidedObject(this, getPosition(), collisionSide, collisionShape);
	    Iterator i = _specialColBehaviours.entrySet().iterator();
	    while (i.hasNext()) {
	        Map.Entry<Class<?>, SpecialBehaviours> entry = (Map.Entry)i.next();
	        if (entry.getValue()._flagCheckDerived) {
	            if (entry.getKey().isInstance(other.object())) {
	                foundSpecial = true;
	                for (CollisionBehaviour cb : entry.getValue()._behaviours) {
	                    cb.invoke(other, curr);
                            for(CollisionEventListener cel: _collisionEventListeners)
                                cel.collisionOccured();
	                }
	            }
	        } else if (entry.getKey().equals(other.object().getClass())) {
                    foundSpecial = true;
                    for (CollisionBehaviour cb : entry.getValue()._behaviours) {
                        cb.invoke(other, curr);
                        for(CollisionEventListener cel: _collisionEventListeners)
                            cel.collisionOccured();
                    }
                }   
	    }
		
		// Если их нет, тогда вызываем коллизию по умолчанию
		// Если и она не определена, то ничего не происходит
	    if (!foundSpecial) {
    		for (CollisionBehaviour cb : _defaultColBehaviour) {
    			cb.invoke(other, curr);
                        for(CollisionEventListener cel: _collisionEventListeners)
                            cel.collisionOccured();
    		}
	    }
	}
	
	/**
	 * Получить список поведений по умолчанию при столкновении
	 * @return Список объектов поведения
	 */
	protected ArrayList<CollisionBehaviour> getDefaultCollisionBehaviours() {
            
		return _defaultColBehaviour;
	}
	
	/**
	 * Добавить поведение по умолчанию при столкновении
	 * @param behaviour Добавляемое поведение
	 */
	protected void addDefaultCollisionBehaviour(CollisionBehaviour behaviour) {
            if ( behaviour == null) {
                throw new NullPointerException();
            }
		_defaultColBehaviour.add(behaviour);
	}
	
	/**
	 * Удалить поведение по умолчанию при столкновении
	 * @param behaviour Поведение для удаления
	 */
	protected void removeDafaultCollisionBehavior(CollisionBehaviour behaviour) {
            if ( behaviour == null) {
                throw new NullPointerException();
            }
		_defaultColBehaviour.remove(behaviour);
	}
	
	/**
	 * Получить словарь специальных поведений при столкновении
	 * @return Ключ -- класс объектов, значение -- флаги и список поведений, определённых при столкновении 
	 *         с этим объектом
	 */
	protected HashMap<Class<?>, SpecialBehaviours> getSpecificCollisionBehaviours() {
		
		return _specialColBehaviours;
	}
	
	/**
	 * Добавить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определяемое при столкновении с этим классом объектов
	 * @param checkDerived Если true, то будут также проверяться наследники класса объектов.
	 *                     Игнорируется, если для класса уже задано какое-либо поведение.
	 */
	protected void addSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb, boolean checkDerived) {
            if ( c == null || cb ==null) {
                throw new NullPointerException();
            }	
		if (!c.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (!_specialColBehaviours.containsKey(c)) {
			
			SpecialBehaviours newsb = new SpecialBehaviours(cb);
			newsb._flagCheckDerived = checkDerived;
			_specialColBehaviours.put(c, newsb);
		}
		else {
			_specialColBehaviours.get(c)._behaviours.add(cb);
		}
	}
	
	/**
         * Добавить специальное поведение при столкновении
         * @param c Класс объектов
         * @param cb Поведение, определяемое при столкновении с этим классом объектов
         */
	protected void addSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
	    if ( c == null || cb ==null) {
                throw new NullPointerException();
            }
	    this.addSpecificCollisionBehaviour(c, cb, false);
	}
	
	/**
	 * Удалить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определённое при столкновении с этим классом объектов
	 */
	protected void removeSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
            if ( c == null || cb ==null) {
                throw new NullPointerException();
            }
		if (!c.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (_specialColBehaviours.containsKey(c)) {
			_specialColBehaviours.get(c)._behaviours.remove(cb);
			if (_specialColBehaviours.get(c)._behaviours.isEmpty()) {
			    _specialColBehaviours.remove(c);
			}
		}
	}
	
	/**
	 * Очистить список поведений при столкновении по умолчанию
	 */
	protected void cleanDefaultCollisionBehaviours() {
		
		_defaultColBehaviour.clear();
	}
	
	/**
	 * Очистить списки специальных поведений при столкновении для всех классов объектов
	 */
	protected void cleanAllSpecificCollisionBehaviours() {
		
		_specialColBehaviours.clear();
	}
	
	/**
	 * Очистить список специальных поведений при столкновений для класса объектов
	 * @param cl Класс объектов, для которых очищается список поведений
	 */
	protected void cleanSpecificCollisionBehaviours(Class<?> cl) {
            if ( cl == null ) {
                throw new NullPointerException();
            }
		if (!cl.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (_specialColBehaviours.containsKey(cl)) {
			_specialColBehaviours.remove(cl);
		}
	}
	
	/**
	 * Уничтожает объект.
	 * По умолчанию ничего не освобождает.
	 */
	public void destroy() {
		
	    this._isDestroyed = true;
	    this._field.removeObject(this);  
            
            this._view.destroyed();
	}
	
	/**
	 * Возвращает true, если объект считается уничтоженным.
	 * @return Уничтожен ли объект.
	 */
	public boolean isDestroyed() {
	    
	    return _isDestroyed;
	}
	
	//-------------------------------------------------------------------------------------------//
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		IngameObject clone = (IngameObject)super.clone();
		clone._isDestroyed = this._isDestroyed;
		clone._field = this._field;    // ссылка на поле просто копируется, да
		clone._form = this._form;
		clone._view = (IngameObjectView) this._view.clone();
		
		return clone;
	}
}
