package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import model.paddle.Paddle;

/**
 * Модель игрока, управляющего ракеткой.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Player {

    protected ArrayList<Paddle> paddles = new ArrayList<>();
    
	/**
	 * Инициализировать игрока
	 * @param paddle Подконтрольная игроку ракетка
	 */
	public Player(Paddle paddle) {
		paddles.add(paddle);
	}
	
	public Player() {
		
	}

	/**
	 * Получить контролируемые ракетки
	 * @return Список контролируемых ракеток
	 */
	public ArrayList<Paddle> getPaddles() {
		
		return (ArrayList<Paddle>) paddles.clone();
	}
	
	/**
	 * Добавить ракетку под контроль игрока
	 * @param paddle Ракетка для добавления
	 */
	public void addPaddle(Paddle paddle) {
		
	    if (paddle == null) {
	        throw new NullPointerException();
	    }
	    
	    paddles.add(paddle);
	}
	
	/**
	 * Убрать ракетку из-под контроля игрока
	 * @param paddle Ракетка
	 */
	public void removePaddle(Paddle paddle) {
		
	    paddles.remove(paddle);
	}
	
	/**
	 * Переместить все свои ракетки в указанную позицию по горизонтали
	 * @param pos Позиция
	 */
	public void setPaddlesPositionX(int x) {
		
	    for (Paddle p : paddles) {
	        int actualx;
	        if (x > p.getField().getSize().width - p.getSize().width) {
	            actualx = p.getField().getSize().width - p.getSize().width;
	        } else {
	            actualx = x;
	        }
	        p.setPosition(new Point2D.Float(actualx, p.getPosition().y));
	    }
	}
	
	/**
	 * Переместить все свои ракетки в указанном направлении.
	 * Величину сдвига жёстко задана внутри класса.
	 * @param dir Направление перемещения
	 */
	public void movePaddles(Direction dir) {
		
	    // TODO stub
	}
	
	/**
	 * Заставляет подконтрольные ракетки задать скорость мячам.
	 */
	public void firePaddles() {
	    
	    for (Paddle p : paddles) {
	        p.fireBalls();
	    }
	}
}
