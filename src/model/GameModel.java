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
		
		// TODO
	}
	
	/**
	 * Получить игровое поле
	 * @return Текущее поле
	 */
	public GameField getField() {
		
		// TODO
		return null;
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
