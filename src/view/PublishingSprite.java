package view;

import com.golden.gamedev.object.Sprite;

/**
 * Спрайт, знающий о том, частью какого игрового объекта (IngameObjectView) он является
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingSprite extends Sprite {
	
	/**
	 * Задать представление игрового объекта, которому принаджелит спрайт
	 * @param object Игровой объект
	 */
	public void setObjectView(IngameObjectView object) {
		// TODO
	}
	
	/**
	 * Получить игровой объект, которому принадлежит спрайт.
	 * @return Игровой объект.
	 */
	public IngameObjectView getObjectView() {
		
		// TODO
		return null;
	}
}
