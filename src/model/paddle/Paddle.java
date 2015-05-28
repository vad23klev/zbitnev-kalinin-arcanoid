package model.paddle;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.GameField;
import model.IngameObject;
import model.Rectangle;
import model.Round;
import model.Speed2D;
import model.ball.Ball;
import view.IngameObjectView;

/**
 * Модель абстрактной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Paddle extends IngameObject {

    protected ArrayList<Ball> _balls = new ArrayList<>();

    /**
     * Создает игровой объект, координаты (0, 0), нулевая скорость, нулевой размер.
     * @param field Игровое поле.
     */
    public Paddle(GameField field, IngameObjectView view) {

        this(field, view, new Point2D.Double(0.0, 0.0));
    }

    /**
     * Создает игровой объект с нулевой скоростью.
     * @param field Игровое поле.
     * @param pos Позиция объекта.
     * @param dim Размеры объекта.
     */
    public Paddle(GameField field, IngameObjectView view, Point2D.Double pos) {

        this(field, view, pos, new Dimension(0, 0));
    }

    /**
     * Создает игровой объект.
     * @param field Игровое поле.
     * @param pos Позиция объекта.
     * @param rad Радиус объекта.
     */
    public Paddle(GameField field, IngameObjectView view, Point2D.Double pos, Dimension dim) {

        if (field == null || pos == null || dim == null || view == null) {
            throw new NullPointerException();
        }

        this._field = field;
        this._form = new Rectangle(pos, dim);
        view.setObject(this);
        this._view = view;
        _collisionEventListeners.add(view);
        this.setPosition(pos);
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
                b.setPosition(new Point2D.Double(b.getPosition().x, this._view.getSpriteStorage().getPosition().y - ((Round)b.getForm()).getRadius() * 2));

                if (b.getPosition().x < this._view.getSpriteStorage().getPosition().x) {
                    b.setPosition(new Point2D.Double(this._view.getSpriteStorage().getPosition().x, b.getPosition().y));
                }
                if (b.getPosition().x > this._view.getSpriteStorage().getPosition().x + ((Rectangle)this._form).getWidth() - ((Round)b.getForm()).getRadius() * 2) {
                    b.setPosition(new Point2D.Double(this._view.getSpriteStorage().getPosition().x + ((Rectangle)this._form).getWidth() - ((Round)b.getForm()).getRadius() * 2, b.getPosition().y));
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
        
        // Найти два центра расчета вектора.
        Point2D.Double paddleLeftCenter = new Point2D.Double(this._view.getSpriteStorage().getPosition().x + ((Rectangle)this._form).getWidth() / 5 * 2, this._view.getSpriteStorage().getPosition().y);
        Point2D.Double paddleRightCenter = new Point2D.Double(this._view.getSpriteStorage().getPosition().x + ((Rectangle)this._form).getWidth() / 5 * 3, this._view.getSpriteStorage().getPosition().y);
        
        // Центр ракетки
        Point2D.Double paddleCenter = new Point2D.Double(this._view.getSpriteStorage().getPosition().x + ((Rectangle)this._form).getWidth() / 2, this._view.getSpriteStorage().getPosition().y);
        
        // Относительные координаты центра мяча в декартовой системе координат (точка B).
        // Считаем, что paddleCenter - это точка A(0, 0).
        Point2D.Double relBallCenter = new Point2D.Double(ball.getPosition().x + ((Round)ball.getForm()).getRadius() - paddleCenter.x,
                paddleCenter.y - ball.getPosition().y - ((Round)ball.getForm()).getRadius());
        
        // Если мяч между двумя центрами, направляем вектор вверх.
        if (relBallCenter.x <= ((Rectangle)this._form).getWidth() / 10 && relBallCenter.x >= -((Rectangle)this._form).getWidth() / 10) {
            return new Speed2D(0, -ball.getDefaultSpeedScalar());
        }
        
        // В зависимости от трети ракетки, в которой располагается мяч, выбираем центр расчета вектора скорости.
        Point2D.Double paddleNewCenter = relBallCenter.x > ((Rectangle)this._form).getWidth() / 10 ? paddleRightCenter : paddleLeftCenter;
        
        // Рассчитываем относительное положение мяча от выбранного центра.
        relBallCenter = new Point2D.Double(relBallCenter.x + paddleCenter.x - paddleNewCenter.x, relBallCenter.y);
        
        // Коэффициенты уравнения точки пересечения прямой и окружности.
        double a = (Math.pow(relBallCenter.x, 2) + Math.pow(relBallCenter.y, 2)) / Math.pow(relBallCenter.x, 2);
        double b = 0;
        double c = -Math.pow(ball.getDefaultSpeedScalar(), 2);
        
        // Дискриминант.
        double D = Math.pow(b, 2) - 4*a*c;
        
        // Точки пересечения.
        Point2D.Double p1 = new Point2D.Double((float) ((-b + Math.sqrt(D)) / (2*a)), 0);
        Point2D.Double p2 = new Point2D.Double((float) ((-b - Math.sqrt(D)) / (2*a)), 0);
        
        // Находим y{1,2} у точек.
        p1.y = p1.x * relBallCenter.y / relBallCenter.x;
        p2.y = p2.x * relBallCenter.y / relBallCenter.x;
        
        // Нужная точка пересечения имеет положительную y-координату.
        Point2D.Double p = p1.y > 0 ? p1 : p2;
        
        // Находим горизонтальную и вертикальную сооставляющие вектора скорости.
        // y отрицательный, чтобы перейти в экранную систему координат.
        return new Speed2D(p.x, -Math.abs(p.y));
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
    public void setPosition(Point2D.Double pos) {
        super.setPosition(pos);
        for (Ball b : _balls) {
            b.setPosition(new Point2D.Double(b.getPosition().x, b.getPosition().y));
        }
        
        this.fixBallsPosition();
    }

}
