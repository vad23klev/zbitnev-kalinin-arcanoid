package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D.Double;

import model.GameField;
import model.IngameObject;
import model.Speed2D;

/**
 * Модель абстрактного кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Brick extends IngameObject {

	public Brick(GameField field) {
		
	    super(field);
	}

    public Brick(GameField field, Double pos, Dimension dim, Speed2D speed) {
        
        super(field, pos, dim, speed);
    }

    public Brick(GameField field, Double pos, Dimension dim) {
        
        super(field, pos, dim);
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	
    	Brick clone = (Brick) super.clone();
    	return clone;
    }
}
