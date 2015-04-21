package model.ball;

import java.awt.geom.Point2D;

import model.GameField;
import model.IngameObject;
import model.Speed2D;
import model.collision.BehaviourPaddleRebound;
import model.collision.BehaviourRebound;
import model.paddle.Paddle;
import model.Round;

/**
 * Модель абстрактного шарика
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public abstract class Ball extends IngameObject {

        /**
	 * Создает игровой объект, координаты (0, 0), нулевая скорость, нулевой размер.
	 * @param field Игровое поле.
	 */
	public Ball(GameField field) {
		
	    this(field, new Point2D.Double(0.0, 0.0), 0.0);
	}
	
	/**
	 * Создает игровой объект с нулевой скоростью.
	 * @param field Игровое поле.
	 * @param pos Позиция объекта.
	 * @param dim Размеры объекта.
	 */
	public Ball(GameField field, Point2D.Double pos, Double rad) {
	    
	    this(field, pos, rad, new Speed2D(0.0, 0.0));
	}
	
	/**
	 * Создает игровой объект.
	 * @param field Игровое поле.
	 * @param pos Позиция объекта.
	 * @param rad Радиус объекта.
	 * @param speed Скорость объекта.
	 */
	public Ball(GameField field, Point2D.Double pos, Double rad, Speed2D speed) {
	    
	    if (field == null || pos == null || rad == null || speed == null) {
	        throw new NullPointerException();
	    }
	    
	    this._field = field;
	    this._form = new Round(new Point2D.Double(pos.x - rad, pos.y - rad), rad);
            this.setPositionByPoint(pos);
	    this.setSpeed(speed);
            this.addDefaultCollisionBehaviour(BehaviourRebound.getInstance());
            this.addSpecificCollisionBehaviour(Paddle.class, BehaviourPaddleRebound.getInstance(), true);
	}
	
	/**
	 * Здесь должен быть жестко задан скаляр скорости мяча.
	 * @return Скорость, с которой должен двигаться мяч.
	 */
	public abstract float getDefaultSpeedScalar();
	
	@Override
	public void setPositionByPoint(Point2D.Double pos) {
	    
	    super.setPositionByPoint(pos);
	    _field.ballPositionChanged(this);
	}
	
	@Override
	public void positionChanged(Point2D.Double newpos) {

	    super.positionChanged(newpos);
	    _field.ballPositionChanged(this);
    }
	
	/**
	 * Задать позицию шарика, указав координаты его середины
	 * @param center Позиция центра шарика
	 */
	public void setPositionByCenter(Point2D.Double center) {
		//Должно быть setPositionByCenter
		setPositionByPoint(new Point2D.Double(center.x - ((Round)_form).getRadius(), center.y - ((Round)_form).getRadius()));
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Ball clone = (Ball) super.clone();
		return clone;
	}
}
