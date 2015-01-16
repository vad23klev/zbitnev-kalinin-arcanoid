package view;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

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

	private ArrayList<PublishingSprite> sprites = new ArrayList<>();
	private Point2D.Float position = null;
	private ArrayList<PositionChangeListener> positionListeners;
	private ArrayList<SpeedChangeListener> speedListeners;
	
    /**
     * Необходимо использовать вместо прямого обращения к спрайту.
     * @param timeElapsed Прошедшее время.
     */
    public void update(int timeElapsed) {
        
    	for (PublishingSprite s : sprites) {
    		s.update(timeElapsed);
    	}
    	
    	// TODO Сообщаем об изменениях слушателям (модели, то бишь)
    }
    
    public void render(Graphics2D g) {
    	
    	for (PublishingSprite s : sprites) {
    		s.render(g);
    	}
    }
    
	@Override
	public void positionChanged(Point2D.Float newpos) {
		
		for (PublishingSprite s : sprites) {
			
			// TODO Здесь всё будет далеко не так тривиально
			s.setLocation(newpos.x, newpos.y);
    	}
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		
		for (PublishingSprite s : sprites) {
    		s.setSpeed(newspeed.x(), newspeed.y());
    	}
	}
	
	/**
	 * Добавить спрайт, принадлежащий данному представлению объекта
	 * @param sprite Добавляемый спрайт
	 */
	public void addPublishingSprite(PublishingSprite sprite) {
		
		if (sprite == null) {
			// TODO Выброс исключения
			return;
		}
		
		sprites.add(sprite);
	}
	
	/**
	 * Удалить спрайт из данного представления объекта
	 * @param sprite Удаляемый спрайт
	 */
	public void removePublishingSprite(PublishingSprite sprite) {
		
		sprites.remove(sprite);
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
