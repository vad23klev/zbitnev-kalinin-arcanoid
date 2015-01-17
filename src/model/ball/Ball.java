package model.ball;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import model.GameField;
import model.IngameObject;
import model.Speed2D;

/**
 * Модель абстрактного шарика
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public abstract class Ball extends IngameObject {

	public Ball(GameField field) {
		super(field);
	}
	
	public Ball(GameField field, Point2D.Float pos, int radius) {
	    
	    super(field, pos, new Dimension(2*radius, 2*radius));
	}
	
	public Ball(GameField field, Point2D.Float pos, int radius, Speed2D speed) {
        
        super(field, pos, new Dimension(2*radius, 2*radius), speed);
    }

	/**
	 * Возвращает радиус мяча.
	 * @return Радиус.
	 */
	public int getRadius() {
	    
	    if (this.size.width != this.size.height) {
	        throw new IllegalStateException("Dimensions of Ball are not the same.");
	    }
	    
	    return this.size.width;
	}
	
	/**
	 * Задает радиус мяча.
	 * @param radius
	 * @return 
	 */
	public void setRadius(int radius) {
	    
	    radius = Math.abs(radius);
	    this.setSize(new Dimension(2*radius, 2*radius));
	}
	
	/**
	 * Внимание! Этот метод способен задать неодинаковые ширину и высоту, если это потребуется,
	 * однако в этом случае вы не сможете использовать метод getRadius().
	 * @param dim Размеры.
	 */
	@Override
	public void setSize(Dimension dim) {
	    
	    super.setSize(dim);
	}
	
	/**
	 * Здесь должен быть жестко задан скаляр скорости мяча.
	 * @return Скорость, с которой должен двигаться мяч.
	 */
	public abstract float getDefaultSpeedScalar();
	
	@Override
	public void setPosition(Point2D.Float pos) {
	    
	    super.setPosition(pos);
	    field.ballPositionChanged(this);
	}
	
	@Override
	public void positionChanged(Point2D.Float newpos) {

	    super.positionChanged(newpos);
	    field.ballPositionChanged(this);
    }
}
