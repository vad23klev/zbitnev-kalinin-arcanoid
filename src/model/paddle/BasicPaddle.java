package model.paddle;

import java.awt.Dimension;
import java.awt.geom.Point2D.Double;

import model.GameField;
import model.Speed2D;

/**
 * Модель обычной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BasicPaddle extends Paddle {

    public BasicPaddle(GameField field, Double pos, Dimension dim) {
        
        super(field, pos, dim);
    }

    public BasicPaddle(GameField field) {
		
        super(field);
	}
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	
    	BasicPaddle clone = (BasicPaddle) super.clone();
    	return clone;
    }

}
