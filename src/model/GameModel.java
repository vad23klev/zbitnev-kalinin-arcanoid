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

	private GameField field;
	
	@Override
	public void collisionOccured(
			HashMap<IngameObject, ArrayList<IngameObject>> storage) {
		
		field.collisionOccured(storage);
	}

	/**
	 * Назначить игровое поле
	 * @param field
	 */
	public void setField(GameField field) {
		
		this.field = field;
	}
	
	/**
	 * Получить игровое поле
	 * @return Текущее поле
	 */
	public GameField getField() {
		
		return this.field;
	}
	
	/**
	 * Добавить модели нового игрока
	 * @param player
	 */
	public void addPlayer(Player player) {
		
		// TODO
	}
	
	/**
	 * Убрать игрока из модели
	 * @param player
	 */
	public void removePlayer(Player player) {
		
		// TODO
	}
	
	/**
	 * Полуить игроков модели
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		
		// TODO
		return null;
	}
}
