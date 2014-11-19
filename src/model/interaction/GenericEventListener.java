package model.interaction;

/**
 * Интерфейс слушателя событий создания, удаления.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public interface GenericEventListener {

	/**
	 * Объект был создан.
	 */
	void created();
	
	/**
	 * Объект был уничтожен.
	 */
	void destroyed();
}
