package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.collision.CollidedObject;
import model.collision.CollisionBehaviour;
import model.collision.SpecialBehaviours;
import model.interaction.GenericEventListener;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;
import view.IngameObjectView;

/**
 * Класс игрового объекта.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class IngameObject implements Cloneable, PositionChangeListener, SpeedChangeListener {
    
    protected Boolean _isDestroyed = false;
    
	protected Point2D.Double _position = null;
	protected IngameObjectView _view = null;
        protected Form _form = null;
	protected ArrayList<CollisionBehaviour> _defaultColBehaviour = new ArrayList<>();
	protected HashMap<Class<?>, SpecialBehaviours> _specialColBehaviours 
		= new HashMap<>();
	protected GameField _field = null;
	protected ArrayList<PositionChangeListener> _positionListeners = new ArrayList<>();
	protected ArrayList<SpeedChangeListener> _speedListeners = new ArrayList<>();
	protected ArrayList<GenericEventListener> _geneventListeners = new ArrayList<>();
	
        /**
	 * Возвращает форму объекта.
	 * @return Форма.
	 */
	public Form getForm() {
	    
	    return this._form;
	}
        
	/**
	 * Возвращает поле, на котором находится объект.
	 * @return Игровое поле.
	 */
	public GameField getField() {
	    
	    return this._field;
	}
	
	/**
	 * Получить скорость.
	 * @return Текущая скорость.
	 */
	public Speed2D getSpeed() {

		return this._view.getSpeed();
	}
	
        public void setView(IngameObjectView view) {
            if (view == null) {
	        throw new NullPointerException();
	    }
            this._view = view;
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

		return (Point2D.Double) _position.clone();
	}
	
	/**
	 * Задать позицию.
	 * @param pos Новая позиция.
	 */
	public void setPositionByPoint(Point2D.Double pos) {

	    if (pos == null) {
	        throw new NullPointerException();
	    }
		_position = pos;
		for (PositionChangeListener l : _positionListeners) {
			l.positionChanged(this._position);
		}
	}

	/**
	 * Сдвинуть объект.
	 * @param delta Величина изменения позиции
	 */
	public void move(Point2D.Double delta) {
		
		this.setPositionByPoint(new Point2D.Double(this.getPosition().x + delta.x, this.getPosition().y + delta.y));
	}
	
	/**
	 * Обрабатывает столкновение с другим объектом.
	 * @param curr Текущий объект
	 * @param other Объект, столкнувшийся с данным.
	 */
	public void processCollision(CollidedObject curr, CollidedObject other) {

		// Вызываем специализированные коллизии, если таковые имеются
	    boolean foundSpecial = false;
	    
	    Iterator i = _specialColBehaviours.entrySet().iterator();
	    while (i.hasNext()) {
	        Map.Entry<Class<?>, SpecialBehaviours> entry = (Map.Entry)i.next();
	        if (entry.getValue()._flagCheckDerived) {
	            if (entry.getKey().isInstance(other.object())) {
	                foundSpecial = true;
	                for (CollisionBehaviour cb : entry.getValue()._behaviours) {
	                    cb.invoke(other, curr);
	                }
	            }
	        } else if (entry.getKey().equals(other.object().getClass())) {
                foundSpecial = true;
                for (CollisionBehaviour cb : entry.getValue()._behaviours) {
                    cb.invoke(other, curr);
                }
            }
	    }
		
		// Если их нет, тогда вызываем коллизию по умолчанию
		// Если и она не определена, то ничего не происходит
	    if (!foundSpecial) {
    		for (CollisionBehaviour cb : _defaultColBehaviour) {
    			cb.invoke(other, curr);
    		}
	    }
	}
	
	/**
	 * Получить список поведений по умолчанию при столкновении
	 * @return Список объектов поведения
	 */
	public ArrayList<CollisionBehaviour> getDefaultCollisionBehaviours() {
		
		return _defaultColBehaviour;
	}
	
	/**
	 * Добавить поведение по умолчанию при столкновении
	 * @param behaviour Добавляемое поведение
	 */
	public void addDefaultCollisionBehaviour(CollisionBehaviour behaviour) {
	
		_defaultColBehaviour.add(behaviour);
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
		
		return _specialColBehaviours;
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
	public void cleanDefaultCollisionBehaviours() {
		
		_defaultColBehaviour.clear();
	}
	
	/**
	 * Очистить списки специальных поведений при столкновении для всех классов объектов
	 */
	public void cleanAllSpecificCollisionBehaviours() {
		
		_specialColBehaviours.clear();
	}
	
	/**
	 * Очистить список специальных поведений при столкновений для класса объектов
	 * @param cl Класс объектов, для которых очищается список поведений
	 */
	public void cleanSpecificCollisionBehaviours(Class<?> cl) {
		
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
	    for (GenericEventListener l : _geneventListeners) {
	    	l.destroyed();
	    }
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
	public void positionChanged(Point2D.Double newpos) {
		
	    this._position = newpos;
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		
		this._view.setSpeed(newspeed);
	}
	
	/**
	 * Добавить слушателя изменения позиции объекта.
	 * @param l Новый слушатель.
	 */
	public void addPositionChangeListener(PositionChangeListener l) {
		_positionListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения позиции объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removePositionChangeListener(PositionChangeListener l) {
		_positionListeners.remove(l);
	}
	
	/**
	 * Добавить слушателя изменения скорости объекта.
	 * @param l Новый слушатель.
	 */
	public void addSpeedChangeListener(SpeedChangeListener l) {
		_speedListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения скорости объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removeSpeedChangeListener(SpeedChangeListener l) {
		_speedListeners.remove(l);
	}
	
	/**
	 * Добавить слушателя событий жизни объекта.
	 * @param l Добавляемый слушатель.
	 */
	public void addGenericEventListener(GenericEventListener l) {
		
		if (l == null) {
			return;
		}

		_geneventListeners.add(l);
	}
	
	/**
	 * Удалить слушателя событий жизни объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removeGenericEventListener(GenericEventListener l) {
		_geneventListeners.remove(l);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		IngameObject clone = (IngameObject)super.clone();
		clone._isDestroyed = this._isDestroyed;
		clone._field = this._field;    // ссылка на поле просто копируется, да
		clone._position = (Point2D.Double) this._position.clone();
		clone._form = this._form;
		clone._view = (IngameObjectView) this._view.clone();
		
		return clone;
	}
}
