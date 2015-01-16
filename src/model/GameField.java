package model;

import java.awt.Dimension;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;

import model.interaction.GenericEventListener;

/**
 * Модель игрового поля.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameField {

	private ArrayList<GenericEventListener> genericEventListeners;
	private ArrayList<IngameObject> objects;
	private Dimension dimensions;
	
    /**
     * Инициализирует поле заданного размера.
     * @param size Размер поля.
     */
    public GameField(Dimension size) {
    	
    	genericEventListeners = new ArrayList<>();
    	objects = new ArrayList<>();
    	dimensions = size;
    }
    
	/**
	 * Добавить объект на поле
	 * @param object Объект для добавления
	 */
	public void addObject(IngameObject object) {
		
		objects.add(object);
		for (GenericEventListener l : genericEventListeners) {
			l.created(object);
		}
	}
	
	/**
	 * Убрать объект с поля
	 * @param object Объект для удаления
	 */
	public void removeObject(IngameObject object) {
		
		objects.remove(object);
		for (GenericEventListener l : genericEventListeners) {
			l.destroyed(object);
		}
	}
	
	/** 
	 * Получить размеры игрового поля (в пикселях).
	 * @return Размеры поля.
	 */
	public Dimension getSize() {
		
		return dimensions;
	}
	
	/**
	 * Добавить нового слушателя событий добавления/удаления объекта.
	 * @param l Добавляемый слушатель.
	 */
	public void addGenericEventListener(GenericEventListener l) {
		
		genericEventListeners.add(l);
	}
	
	/**
	 * Удалить слушателя событий добавления/удаления объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removeGenericEventListener(GenericEventListener l) {
		
		genericEventListeners.remove(l);
	}
}
