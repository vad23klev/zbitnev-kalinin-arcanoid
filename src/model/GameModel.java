package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.interaction.CollisionListener;

/**
 * Модель игры.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameModel implements CollisionListener {

	@Override
	public void collisionOccured(
			HashMap<IngameObject, ArrayList<IngameObject>> storage) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Назначить игровое поле
	 * @param field
	 */
	public void setField(GameField field) {
		
	}
	
	/**
	 * Получить игровое поле
	 * @return Текущее поле
	 */
	public GameField getField() {
		
		return null;
	}
}
