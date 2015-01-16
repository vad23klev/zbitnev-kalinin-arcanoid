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
