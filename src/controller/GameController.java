package controller;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.engine.input.AWTInput;
import model.Player;

/**
 * Слушает ввод и управляет игрой.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameController {

	private Player player;
	private AWTInput input;
	
	/**
	 * Создаёт контроллер ввода
	 * @param player Модель игрока, которой будет управлять контроллер.
	 * @param input Менеджер ввода.
	 */
	GameController(Player player, BaseInput input) {
		
		// TODO Method stub
	}
	
	/**
	 * Проверяет состояние ввода и управляет моделью игрока.
	 */
	void update() {
		
		// TODO Method stub
	}
}
