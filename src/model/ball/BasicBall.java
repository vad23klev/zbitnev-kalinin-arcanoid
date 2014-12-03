package model.ball;

import model.GameField;
import model.swarm.CanBeInSwarm;

/**
 * Модель обычного шарика
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class BasicBall extends Ball implements CanBeInSwarm {

	public BasicBall(GameField field) {
		super(field);
	}

}
