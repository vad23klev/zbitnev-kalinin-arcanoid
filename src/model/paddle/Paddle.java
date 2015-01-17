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
	    
	    b.setSpeed(new Speed2D(0, 0));
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
     * Возвращает скорость мяча при запуске его с ракетки или отскока от ракетки.
     * @param b Мяч.
     * @return Вектор скорости.
     */
    public Speed2D getFireSpeed(Ball ball) {
        
        Point2D.Float paddleCenter  = new Point2D.Float(this.position.x + this.size.width / 2, this.position.y);
        
        // Относительные координаты центра мяча в декартовой системе координат (точка B).
        // Считаем, что paddleCenter - это точка A(0, 0).
        Point2D.Float relBallCenter = new Point2D.Float(ball.getPosition().x - paddleCenter.x,
                paddleCenter.y - ball.getPosition().y - ball.getSize().height / 2);
        
        // Коэффициенты уравнения точки пересечения прямой и окружности.
        double a = (Math.pow(relBallCenter.x, 2) + Math.pow(relBallCenter.y, 2)) / Math.pow(relBallCenter.x, 2);
        double b = 0;
        double c = -Math.pow(ball.getDefaultSpeedScalar(), 2);
        
        // Дискриминант.
        double D = Math.pow(b, 2) - 4*a*c;
        
        // Точки пересечения.
        Point2D.Float p1 = new Point2D.Float((float) ((-b + Math.sqrt(D)) / (2*a)), 0);
        Point2D.Float p2 = new Point2D.Float((float) ((-b - Math.sqrt(D)) / (2*a)), 0);
        
        // Находим y{1,2} у точек.
        p1.y = p1.x * relBallCenter.y / relBallCenter.x;
        p2.y = p2.x * relBallCenter.y / relBallCenter.x;
        
        // Нужная точка пересечения имеет положительную y-координату.
        Point2D.Float p = p1.y > 0 ? p1 : p2;
        
        // Находим горизонтальную и вертикальную сооставляющие вектора скорости.
        // y отрицательный, чтобы перейти в экранную систему координат.
        return new Speed2D(Math.abs(p.x), -p.y);
    }
    
    /**
     * Запускает шары с ракетки.
     */
    public void fireBalls() {
        
        while (!_balls.isEmpty()) {
            Ball b = _balls.get(0);
            b.setSpeed(getFireSpeed(b));
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
