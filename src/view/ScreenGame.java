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
	
	GameModel model;
	
	Background background;
	BufferedImage basicBallImg;
	BufferedImage breakableBrickImg;
	
	SpriteGroup spritesBalls;
	SpriteGroup spritesBricks;
	SpriteGroup spritesPaddles;

	public ScreenGame(GameEngine arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initResources() {
		
		// Загрузка фона
		Image bgimage = bsLoader.getImage("default/gfx/misc/bg-blue.png");
		BufferedImage bufferedBgImage = new BufferedImage(this.getWidth(), this.getHeight(), 
														  BufferedImage.TYPE_4BYTE_ABGR);
		bufferedBgImage.getGraphics().drawImage(bgimage, 0, 0, this.getWidth(), 
												this.getHeight(), null);
		background = new ImageBackground(bufferedBgImage);
		
		// Загрузка изображений для игровых объектов
		basicBallImg = bsLoader.getImage("default/gfx/balls/basic.png");
		breakableBrickImg = bsLoader.getImage("default/gfx/bricks/breakable.png");
		spritesBalls = new SpriteGroup("Balls");
		spritesBricks = new SpriteGroup("Bricks");
		spritesPaddles = new SpriteGroup("Paddles");
		
		// Загрузка игрового уровня
		// TODO: Загрузка уровня из файла (пока уровень захардкоден)
		// TODO: Сообщить окну о габаритах уровня, чтобы то адаптировалось
		GameField field = new GameField(new Dimension(640, 420));
	}

	@Override
	public void render(Graphics2D arg0) {

		// Рендерим последовательно фон и все игровые объекты
		background.render(arg0);
		spritesBalls.render(arg0);
		spritesBricks.render(arg0);
		spritesPaddles.render(arg0);
		
		// TODO: Рендер кол-ва очков, другой инофрмации (сейчас игра на весь экран)
	}

	@Override
	public void update(long arg0) {
		
		// Апдейтим всё
		spritesBalls.update(arg0);
		spritesBricks.update(arg0);
		spritesPaddles.update(arg0);
		
		// TODO: Проверка всех коллизий
		// TODO: Апдейт событий пользовательского ввода
	}

}
