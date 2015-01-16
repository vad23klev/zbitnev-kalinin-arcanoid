package model.interaction;

import model.IngameObject;

/**
 * Интерфейс слушателя событий создания, удаления.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public interface GenericEventListener {

	/**
	 * Объект был создан.
	 */
	void created(IngameObject obj);
	
	/**
	 * Объект был уничтожен.
	 */
	void destroyed(IngameObject obj);
}
