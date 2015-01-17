package controller;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Float;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.engine.input.AWTInput;
import model.Player;

/**
 * Слушает ввод и управляет игрой.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameController {

	private Player _player;
	private BaseInput _input;
	
	/**
	 * Создаёт контроллер ввода
	 * @param player Модель игрока, которой будет управлять контроллер.
	 * @param input Менеджер ввода.
	 */
	public GameController(Player player, BaseInput input) {
		
		if (player == null || input == null) {
		    throw new NullPointerException();
		}
		
		_player = player;
		_input = input;
	}
	
	/**
	 * Проверяет состояние ввода и управляет моделью игрока.
	 */
	public void update() {
		
	    if (_input.getMouseDX() != 0) {
	        _player.setPaddlesPositionX(_input.getMouseX());
	    }
	    
	    if (_input.isMouseDown(MouseEvent.BUTTON1)) {
	        _player.firePaddles();
	    }
		// TODO Управление с клавиатуры.
	}
}
