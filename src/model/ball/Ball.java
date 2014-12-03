package model.ball;

import model.GameField;
import model.IngameObject;

/**
 * Модель абстрактного шарика
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public abstract class Ball extends IngameObject {

	public Ball(GameField field) {
		super(field);
	}

}
