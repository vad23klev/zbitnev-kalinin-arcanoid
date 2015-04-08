package view;

import com.golden.gamedev.object.Sprite;

/**
 * Спрайт, знающий о том, частью какого игрового объекта (IngameObjectView) он является
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingSprite extends Sprite {
	
    private IngameObjectView _objectView = null;
    
	/**
	 * Задать представление игрового объекта, которому принаджелит спрайт
	 * @param object Игровой объект
	 */
	public void setObjectView(IngameObjectView object) {
	    
		if (object == null) {
		    throw new NullPointerException();
		}
	    _objectView = object;
	}
	
	/**
	 * Получить игровой объект, которому принадлежит спрайт.
	 * @return Игровой объект.
	 */
	public IngameObjectView getObjectView() {
		
		return _objectView;
	}
}
