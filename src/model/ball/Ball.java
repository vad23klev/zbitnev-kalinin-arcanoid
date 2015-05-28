package model.ball;

import java.awt.geom.Point2D;
import model.Bounds;
import model.Form;

import model.GameField;
import model.IngameObject;
import model.Speed2D;
import model.collision.BehaviourPaddleRebound;
import model.collision.BehaviourRebound;
import model.paddle.Paddle;
import model.Round;
import model.collision.BehaviourBoundaryRebound;
import view.IngameObjectView;

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
	public Ball(GameField field, IngameObjectView view) {
		
	    this(field, view, new Point2D.Double(0.0, 0.0), 0.0);
	}
	
	/**
	 * Создает игровой объект с нулевой скоростью.
	 * @param field Игровое поле.
	 * @param pos Позиция объекта.
	 * @param dim Размеры объекта.
	 */
	public Ball(GameField field, IngameObjectView view, Point2D.Double pos, Double rad) {
	    
	    this(field, view, pos, rad, new Speed2D(0.0, 0.0));
	}
	
	/**
	 * Создает игровой объект.
	 * @param field Игровое поле.
	 * @param pos Позиция объекта.
	 * @param rad Радиус объекта.
	 * @param speed Скорость объекта.
	 */
	public Ball(GameField field, IngameObjectView view, Point2D.Double pos, Double rad, Speed2D speed) {
	    
	    if (field == null || view == null || pos == null || rad == null || speed == null) {
	        throw new NullPointerException();
	    }
            view.setObject(this);
	    this._view = view;
	    this._field = field;
	    this._form = new Round(new Point2D.Double(pos.x - rad, pos.y - rad), rad);
            _collisionEventListeners.add(view);
            this.setPosition(pos);
	    this.setSpeed(speed);
            this.addDefaultCollisionBehaviour(BehaviourRebound.getInstance());
            this.addSpecificCollisionBehaviour(Paddle.class, BehaviourPaddleRebound.getInstance(), true);
            this.addSpecificCollisionBehaviour(Bounds.class, BehaviourBoundaryRebound.getInstance(), true);
	}
	
	/**
	 * Здесь должен быть жестко задан скаляр скорости мяча.
	 * @return Скорость, с которой должен двигаться мяч.
	 */
	public abstract float getDefaultSpeedScalar();
	
	@Override
	public void setPosition(Point2D.Double pos) {
	    
	    super.setPosition(pos);
	    _field.ballPositionChanged(this);
	}
	
	/**
	 * Задать позицию шарика, указав координаты его середины
	 * @param center Позиция центра шарика
	 */
	public void setPositionByCenter(Point2D.Double center) {
		//Должно быть setPositionByCenter
		setPosition(new Point2D.Double(center.x - ((Round)_form).getRadius() * 2, center.y - ((Round)_form).getRadius() * 2));
                ((Round)_form).setCenter(center);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Ball clone = (Ball) super.clone();
		return clone;
	}
        
        @Override
        public Form  getForm(){
            ((Round)this._form).setCenter(new Point2D.Double(this._view.getSpriteStorage().getPosition().x + ((Round)this._form).getRadius(),
                        this._view.getSpriteStorage().getPosition().y + ((Round)this._form).getRadius()));
            return this._form;
            
        }
}