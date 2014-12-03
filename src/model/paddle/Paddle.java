package model.paddle;

import model.GameField;
import model.IngameObject;

/**
 * Модель абстрактной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Paddle extends IngameObject {

	public Paddle(GameField field) {
		super(field);
	}

}
