package view;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import model.IngameObject;
import model.Speed2D;
import model.interaction.GenericEventListener;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;

/**
 * Представление отдельного игрового объекта
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class IngameObjectView
		implements PositionChangeListener, SpeedChangeListener, GenericEventListener {

    protected final IngameObject _ingameObject;
    protected SpriteStorage _spriteStorage = null;
    
    protected GameFieldView _fieldView = null;
    protected ArrayList<PositionChangeListener> _positionListeners = new ArrayList<>();
    protected ArrayList<SpeedChangeListener> _speedListeners = new ArrayList<>();
	
	/**
	 * Создает представление объекта на основе его модели и спрайта.
	 * Этот метод автоматически согласует слушателей и связывает спрайт с объектом представления, которому он принадлежит.
	 * @param obj Модель игрового объекта.
	 * @param sprite Спрайт, которым он будет отображен.
	 */
	public IngameObjectView(IngameObject obj, SpriteStorage spriteStorage, GameFieldView view) {
	    
	    if (spriteStorage == null || obj == null) {
	        throw new NullPointerException();
	    }
	    spriteStorage.setObjectView(this);
	    this._ingameObject = obj;
	    this._spriteStorage = spriteStorage;
	    this._fieldView = view;
	    this._spriteStorage.setPosition(obj.getPosition());
	    this._spriteStorage.setSpeed(obj.getSpeed());
	    this._spriteStorage.setObjectView(this);
	    addPositionChangeListener(obj);
	    addSpeedChangeListener(obj);
	    obj.addPositionChangeListener(this);
	    obj.addSpeedChangeListener(this);
	    obj.addGenericEventListener(this);
	}
    
	@Override
	public void positionChanged(Point2D.Double newpos) {
		
		_spriteStorage.setPosition(new Point2D.Double(newpos.x, newpos.y));
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		
		_spriteStorage.setSpeed(new Speed2D(newspeed.x(), newspeed.y()));
	}
	
	/**
	 * Возвращает модель игрового объекта.
	 * @return IngameObject.
	 */
	public IngameObject getIngameObject() {
	    
	    return _ingameObject;
	}
	
	/**
	 * Добавить спрайт, принадлежащий данному представлению объекта
	 * @param sprite Добавляемый спрайт
	 */
	public void setSpriteStorage(SpriteStorage spriteStorage) {
		
		if (spriteStorage == null) {
			throw new NullPointerException();
		}
            	this._spriteStorage = spriteStorage;
	}
	
	/**
	 * Возвращает спрайт, принадлежащий данному представлению объекта.
	 * @return Спрайт.
	 */
	public SpriteStorage getSpriteStorage() {
	    return _spriteStorage;
	}
	
	/**
	 * Добавить слушателя изменения позиции представления объекта
	 * @param l Новый слушатель
	 */
	public void addPositionChangeListener(PositionChangeListener l) {
		_positionListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения позиции представления объекта
	 * @param l Удаляемый слушатель
	 */
	public void removePositionChangeListener(PositionChangeListener l) {
		_positionListeners.remove(l);
	}
	
	/**
	 * Добавить слушателя изменения скорости представления объекта
	 * @param l Новый слушатель
	 */
	public void addSpeedChangeListener(SpeedChangeListener l) {
		_speedListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения скорости представления объекта
	 * @param l Удаляемый слушатель
	 */
	public void removeSpeedChangeListener(SpeedChangeListener l) {
		_speedListeners.remove(l);
	}

	@Override
	public void destroyed() {
		this._fieldView.removeObjectView(this);
	}
}
