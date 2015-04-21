package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import model.GameField;
import model.Speed2D;
import model.collision.BehaviourDestroy;
import view.IngameObjectView;

/**
 * Модель разрушаемого кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BreakableBrick extends Brick {

	public BreakableBrick(GameField field, IngameObjectView view) {
		
	    this(field, view, new Point2D.Double(0, 0), new Dimension(0, 0));
	}
	

	public BreakableBrick(GameField field, IngameObjectView view, Double pos, Dimension dim, Speed2D speed) {
        
	    super(field, view, pos, dim, speed);
	    this.addDefaultCollisionBehaviour(BehaviourDestroy.getInstance());
    }


    public BreakableBrick(GameField field, IngameObjectView view, Double pos, Dimension dim) {
        
        this(field, view, pos, dim, new Speed2D(0, 0));
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
