package view;

import com.golden.gamedev.object.Sprite;

/**
 * Спрайт, знающий о том, частью какого игрового объекта (IngameObjectView) он является
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class SpriteGTGE extends Sprite {
	
    private SpriteStorageGTGE _storage = null;
    
	/**
	 * Задать представление игрового объекта, которому принаджелит спрайт
	 * @param storage Игровой объект
	 */
	public void setSpriteStorage(SpriteStorageGTGE storage) {
	    
		if (storage == null) {
		    throw new NullPointerException();
		}
	    _storage = storage;
	}
	
	/**
	 * Получить игровой объект, которому принадлежит спрайт.
	 * @return Игровой объект.
	 */
	public SpriteStorageGTGE getSpriteStorage() {
		
		return _storage;
	}
}
