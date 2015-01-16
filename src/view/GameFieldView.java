package view;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Игровое поле арканоида. Содержит все обекты игры, ответственнен за обновление, рендеринг и
 * проверку стоклновений 
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField {

	// Загруженные изображения для игровых объектов
	BufferedImage basicBallImg;
	BufferedImage breakableBrickImg;
	
	public GameFieldView() {

	}
}
