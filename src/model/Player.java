package model;

import java.util.ArrayList;

import model.paddle.Paddle;

/**
 * Модель игрока, управляющего ракеткой.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Player {

	/**
	 * Инициализировать игрока
	 * @param paddle Подконтрольная игроку ракетка
	 */
	public Player(Paddle paddle) {
		
	}
	
	public Player() {
		
	}

	/**
	 * Получить контролируемые ракетки
	 * @return Список контролируемых ракеток
	 */
	public ArrayList<Paddle> getPaddles() {
		
		return null;
	}
	
	/**
	 * Добавить ракетку под контроль игрока
	 * @param paddle Ракетка для добавления
	 */
	public void addPaddle(Paddle paddle) {
		
	}
	
	/**
	 * Убрать ракетку из-под контроля игрока
	 * @param paddle Ракетка
	 */
	public void removePaddle(Paddle paddle) {
		
	}
	
}
