package model.ball;

import java.awt.geom.Point2D;

import model.GameField;
import model.Speed2D;
import model.swarm.CanBeInSwarm;
import view.IngameObjectView;

/**
 * Модель обычного шарика
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class BasicBall extends Ball implements CanBeInSwarm {

	public BasicBall(GameField field, IngameObjectView view) {
		
	    super(field, view);
	}

	public BasicBall(GameField field, IngameObjectView view, Point2D.Double pos, Double radius) {
	    
	    super(field, view, pos, radius);
	}
	
	public BasicBall(GameField field, IngameObjectView view, Point2D.Double pos, Double radius, Speed2D speed) {
	    
	    super(field, view, pos, radius, speed);
	}

    @Override
    public float getDefaultSpeedScalar() {
        
        return (float) 0.2;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	
    	BasicBall clone = (BasicBall) super.clone();
    	return clone;
    }
}
