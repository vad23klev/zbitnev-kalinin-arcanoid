package model.paddle;

import java.util.ArrayList;

import model.GameField;
import model.IngameObject;
import model.ball.Ball;

/**
 * Модель абстрактной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Paddle extends IngameObject {

	public Paddle(GameField field) {
		super(field);
	}

	/**
	 * Поместить шар на ракетку.
	 * @param b Шар.
	 */
	public void addBall(Ball b) {
        
    }
    
	/**
	 * Убрать шар с ракетки.
	 * @param b Шар.
	 */
    public void removeBall(Ball b) {
        
    }
    
    /**
     * Возвращает список шаров на ракетке.
     * @return Список шаров на ракетке.
     */
    public ArrayList<Ball> getBalls() {
        
        return null;
    }
}
