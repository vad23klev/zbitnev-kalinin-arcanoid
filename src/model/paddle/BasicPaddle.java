package model.paddle;

import java.awt.Dimension;
import java.awt.geom.Point2D.Double;

import model.GameField;
import model.Speed2D;
import view.IngameObjectView;

/**
 * Модель обычной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BasicPaddle extends Paddle {

    public BasicPaddle(GameField field, IngameObjectView view, Double pos, Dimension dim) {
        
        super(field, view, pos, dim);
    }

    public BasicPaddle(GameField field,IngameObjectView view) {
		
        super(field, view);
	}
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	
    	BasicPaddle clone = (BasicPaddle) super.clone();
    	return clone;
    }

}
