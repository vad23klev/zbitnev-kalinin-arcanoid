package view;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
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
		implements PositionChangeListener, SpeedChangeListener {

	protected PublishingSprite sprite = null;
	protected Point2D.Float position = null;
	protected ArrayList<PositionChangeListener> positionListeners;
	protected ArrayList<SpeedChangeListener> speedListeners;
	
	/**
	 * Создает представление объекта на основе его модели и спрайта.
	 * Этот метод автоматически согласует слушателей.
	 * @param obj Модель игрового объекта.
	 * @param sprite Спрайт, которым он будет отображен.
	 */
	public IngameObjectView(IngameObject obj, PublishingSprite sprite) {
	    
	    if (sprite == null || obj == null) {
	        throw new NullPointerException();
	    }
	    
	    this.sprite   = sprite;
	    this.position = obj.getPosition();
	    addPositionChangeListener(obj);
	    addSpeedChangeListener(obj);
	    obj.addPositionChangeListener(this);
	    obj.addSpeedChangeListener(this);
	}
	
	/**
	 * Создает представление с заданным спрайтом и позицией.
	 * Этот метод не согласует слушателей, вам необходимо позаботиться об этом самостоятельно.
	 * @param pos Позиция.
	 * @param sprite Спрайт, которым будет отображен объект.
	 */
	public IngameObjectView(Point2D.Float pos, PublishingSprite sprite) {
	    
	    if (pos == null || sprite == null) {
	        throw new NullPointerException();
	    }
	    
	    this.sprite   = sprite;
	    this.position = pos;
	}
	
    /**
     * Необходимо использовать вместо прямого обращения к спрайту.
     * @param timeElapsed Прошедшее время.
     */
    public void update(int timeElapsed) {
        
    	sprite.update(timeElapsed);
    	
    	// TODO Сообщаем об изменениях слушателям (модели, то бишь)
    }
    
    public void render(Graphics2D g) {
    	
    	sprite.render(g);
    }
    
	@Override
	public void positionChanged(Point2D.Float newpos) {
		
		// TODO Здесь всё будет далеко не так тривиально
		sprite.setLocation(newpos.x, newpos.y);
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		
		sprite.setSpeed(newspeed.x(), newspeed.y());
	}
	
	/**
	 * Добавить спрайт, принадлежащий данному представлению объекта
	 * @param sprite Добавляемый спрайт
	 */
	public void setSprite(PublishingSprite sprite) {
		
		if (sprite == null) {
			throw new NullPointerException();
		}
		
		this.sprite = sprite;
	}
	
	/**
	 * Добавить слушателя изменения позиции представления объекта
	 * @param l Новый слушатель
	 */
	public void addPositionChangeListener(PositionChangeListener l) {
		positionListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения позиции представления объекта
	 * @param l Удаляемый слушатель
	 */
	public void removePositionChangeListener(PositionChangeListener l) {
		positionListeners.remove(l);
	}
	
	/**
	 * Добавить слушателя изменения скорости представления объекта
	 * @param l Новый слушатель
	 */
	public void addSpeedChangeListener(SpeedChangeListener l) {
		speedListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения скорости представления объекта
	 * @param l Удаляемый слушатель
	 */
	public void removeSpeedChangeListener(SpeedChangeListener l) {
		speedListeners.remove(l);
	}
}
