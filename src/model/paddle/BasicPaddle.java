package model.paddle;

import java.awt.Dimension;
import java.awt.geom.Point2D.Float;

import model.GameField;
import model.Speed2D;

/**
 * Модель обычной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BasicPaddle extends Paddle {

    public BasicPaddle(GameField field, Float pos, Dimension dim) {
        
        super(field, pos, dim);
    }

    public BasicPaddle(GameField field) {
		
        super(field);
	}

}
