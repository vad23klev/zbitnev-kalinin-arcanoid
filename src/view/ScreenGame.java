package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import model.GameField;
import model.GameModel;
import model.ball.BasicBall;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Режим игры
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class ScreenGame extends GameObject {
	
	GameModel model;				// игровая модель
	GameFieldView fieldView;		// представление игрового поля

	public ScreenGame(GameEngine arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initResources() {
		
		// Инициализация представления уровня
		fieldView = new GameFieldView();
		Image bgimage = bsLoader.getImage("default/gfx/misc/bg-blue.png");
		BufferedImage bufferedBgImage = new BufferedImage(this.getWidth(), this.getHeight(), 
														  BufferedImage.TYPE_4BYTE_ABGR);
		bufferedBgImage.getGraphics().drawImage(bgimage, 0, 0, this.getWidth(), 
												this.getHeight(), null);
		fieldView.setBackground(new ImageBackground(bufferedBgImage));
		
		// Построение уровня
		// TODO: Загрузка уровня из файла (пока уровень захардкоден)
		// TODO: Сообщить окну о габаритах уровня, чтобы то адаптировалось
		GameField field = new GameField(new Dimension(640, 420));
	}

	@Override
	public void render(Graphics2D arg0) {

		fieldView.render(arg0);
		
		// TODO: Рендер кол-ва очков, другой инофрмации (сейчас игра на весь экран)
	}

	@Override
	public void update(long arg0) {
		
		// Апдейтим всё
		fieldView.update(arg0);
	}

}
