package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferedImage;

import model.GameField;
import model.GameModel;
import model.Player;
import model.Speed2D;
import model.ball.BasicBall;
import model.brick.BreakableBrick;
import model.paddle.BasicPaddle;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

import controller.GameController;

/**
 * Режим игры
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class ScreenGame extends GameObject {
    
	GameModel model;
	GameFieldView fieldView;
	GameController controller;
	
	public ScreenGame(GameEngine arg0) {
		super(arg0);
	}

	@Override
	public void initResources() {
	    
	    // Загрузка ресурсов
	    BufferedImage bgImage             = bsLoader.getImage("default/gfx/misc/bg-blue.png");
	    BufferedImage basicBallImage      = bsLoader.getImage("default/gfx/balls/basic.png");
	    BufferedImage breakableBrickImage = bsLoader.getImage("default/gfx/bricks/breakable.png");
	    BufferedImage basicPaddleImage    = bsLoader.getImage("default/gfx/paddles/basic.png");
	    
		// Инициализация представления уровня
		fieldView = new GameFieldView();
		
		// Задать фон уровня.
		BufferedImage fieldBg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		fieldBg.getGraphics().drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), null);
		fieldView.setBackground(new ImageBackground(fieldBg));
		
		// Инициализация уровня
        GameField field = new GameField(this.bsGraphics.getSize());
		
		// Фабрика представлений
		DefaultObjectViewFactory viewfact = new DefaultObjectViewFactory(basicBallImage, breakableBrickImage, null, basicPaddleImage);
		
		// Модель слушает сообщения о коллизиях
		model = new GameModel();
		model.setField(field);
		fieldView.addCollisionListener(model);
		
		// Построение уровня
		// TODO: Загрузка уровня из файла (пока уровень захардкоден)
		BasicBall newball = new BasicBall(field, new Point2D.Float(55, 500), 16, new Speed2D(-0.2, -0.1));
		BreakableBrick newbrick = new BreakableBrick(field, new Point2D.Float(180, 120), new Dimension(48, 24));
        BreakableBrick newbrick2 = new BreakableBrick(field, new Point2D.Float(228, 120), new Dimension(48, 24));
        BasicPaddle paddle = new BasicPaddle(field, new Point2D.Float(0, 584), new Dimension(100, 16));
        
        IngameObjectView ballview = viewfact.newBasicBallView(newball);
        IngameObjectView brick1view = viewfact.newBreakableBrickView(newbrick);
        IngameObjectView brick2view = viewfact.newBreakableBrickView(newbrick2);
        IngameObjectView paddleView = viewfact.newBasicPaddleView(paddle);
        
        fieldView.addObjectView(ballview);
        fieldView.addObjectView(brick1view);
        fieldView.addObjectView(brick2view);
        fieldView.addObjectView(paddleView);
        
        // Контроллер и игрок.
        Player player = new Player(paddle);
        controller = new GameController(player, bsInput);
		
		// ЭКСПЕРИМЕНТ
		//newball.setSpeed(new Speed2D(-0.12, -0.1));
        paddle.addBall(newball);
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
		controller.update();
	}

}
