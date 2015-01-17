package model.paddle;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import model.GameField;
import model.IngameObject;
import model.Speed2D;
import model.ball.Ball;

/**
 * Модель абстрактной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Paddle extends IngameObject {

    protected ArrayList<Ball> _balls = new ArrayList<>();

    public Paddle(GameField field, Float pos, Dimension dim) {
        
        super(field, pos, dim);
    }

    public Paddle(GameField field) {
		
        super(field);
	}

	/**
	 * Поместить шар на ракетку.
	 * @param b Шар.
	 */
	public void addBall(Ball b) {
        
	    if (b == null) {
	        throw new NullPointerException();
	    }
	    
	    _balls.add(b);
	    this.fixBallsPosition();
    }
	
	/**
	 * Корректирует координаты мячей.
	 * Они не должны висеть над ракеткой.
	 * Они не должны по горизонтали вылазить за ракетку.
	 */
	protected void fixBallsPosition() {
	    
	    for (Ball b : _balls) {
            b.setPosition(new Point2D.Float(b.getPosition().x, this.position.y - b.getSize().height));
            
            if (b.getPosition().x < this.position.x) {
                b.setPosition(new Point2D.Float(this.position.x, b.getPosition().y));
            }
            if (b.getPosition().x > this.position.x + this.size.width) {
                b.setPosition(new Point2D.Float(this.position.x + this.size.width - b.getSize().width, b.getPosition().y));
            }
	    }
	}
    
	/**
	 * Убрать шар с ракетки.
	 * @param b Шар.
	 */
    public void removeBall(Ball b) {
        _balls.remove(b);
    }
    
    /**
     * Возвращает список шаров на ракетке.
     * @return Список шаров на ракетке.
     */
    public ArrayList<Ball> getBalls() {
        
        return (ArrayList<Ball>) _balls.clone();
    }
    
    /**
     * Запускает шары с ракетки.
     */
    public void fireBalls() {
        
        //float paddleCenterX = this.position.x + this.size.width / 2;
        
        while (!_balls.isEmpty()) {
            Ball b = _balls.get(0);
            
            /*float ballSpeed = b.getDefaultSpeedScalar();
            float ballCenterX = b.getPosition().x + b.getSize().width / 2;
            
            float ballSpeedX = Math.abs(paddleCenterX - ballCenterX);
            float ballSpeedY = (float)Math.sqrt(ballSpeed*ballSpeed - ballSpeedX*ballSpeedX);
            
            System.out.println(ballSpeedX);
            System.out.println(ballSpeedY);
            
            b.setSpeed(new Speed2D(ballSpeedX, ballSpeedY));*/
            b.setSpeed(new Speed2D(-0.2, -0.1)); // TODO Нормальный расчет вектора скорости.
            
            _balls.remove(b);
        }
    }
    
    @Override
    public void setPosition(Point2D.Float pos) {
        
        if (this.position == null) {
            super.setPosition(pos);
            
        } else {
            float dx = pos.x - this.position.x;
            float dy = pos.y - this.position.y;
            
            super.setPosition(pos);
            
            for (Ball b : _balls) {
                b.setPosition(new Point2D.Float(b.getPosition().x + dx, b.getPosition().y + dy));
            }
        }
    }
}
