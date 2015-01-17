package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D.Float;

import model.GameField;
import model.Speed2D;

/**
 * Модель разрушаемого кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BreakableBrick extends Brick {

	public BreakableBrick(GameField field) {
		
	    super(field);
	}
	

	public BreakableBrick(GameField field, Float pos, Dimension dim, Speed2D speed) {
        
	    super(field, pos, dim, speed);
    }


    public BreakableBrick(GameField field, Float pos, Dimension dim) {
        
        super(field, pos, dim);
    }


    /**
	 * Разрушает кирпич.
	 */
	@Override
	public void destroy() {
	    super.destroy();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
	
		BreakableBrick clone = (BreakableBrick) super.clone();
		return clone;
	}
}
