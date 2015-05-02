package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import model.GameField;
import model.GameModel;
import model.Player;
import model.Speed2D;
import model.ball.BasicBall;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;
import model.paddle.BasicPaddle;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.background.ImageBackground;

import controller.GameController;

/**
 * Режим игры
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class ScreenGame extends GameObject {
    
	GameModel _model;
	GameFieldView _fieldView;
	GameController _controller;
	
	public ScreenGame(GameEngine arg0) {
            
            super(arg0);
	}

	@Override
	public void initResources() {
	    
	    // Загрузка ресурсов
	    BufferedImage bgImage               = bsLoader.getImage("default/gfx/misc/bg-blue.png");
	    BufferedImage basicBallImage        = bsLoader.getImage("default/gfx/balls/basic.png");
	    BufferedImage breakableBrickImage   = bsLoader.getImage("default/gfx/bricks/breakable.png");
	    BufferedImage unbreakableBrickImage = bsLoader.getImage("default/gfx/bricks/unbreakable.png");
	    BufferedImage basicPaddleImage      = bsLoader.getImage("default/gfx/paddles/basic.png");
	    
            // Инициализация представления уровня
            _fieldView = new GameFieldView(this.bsGraphics.getSize());

            // Задать фон уровня.
            BufferedImage fieldBg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            //fieldBg.getGraphics().drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), null);
            Graphics g = fieldBg.getGraphics();
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            _fieldView.setBackground(new ImageBackground(fieldBg));
		
            // Инициализация уровня
            GameField field = new GameField(this.bsGraphics.getSize());

            // Фабрика представлений
            DefaultObjectViewFactory viewfact = new DefaultObjectViewFactory(basicBallImage, breakableBrickImage,
                    unbreakableBrickImage, basicPaddleImage, _fieldView);

            // Модель слушает сообщения о коллизиях
            _model = new GameModel();
            _model.setField(field);
            _fieldView.addCollisionListener(_model);

            // Построение уровня
            // TODO: Загрузка уровня из файла (пока уровень захардкоден)
            

            IngameObjectView ballview = viewfact.newBasicBallView();
            IngameObjectView brick1view = viewfact.newBreakableBrickView();
            IngameObjectView brick2view = viewfact.newBreakableBrickView();
            IngameObjectView brick3view = viewfact.newUnbreakableBrickView();
            IngameObjectView paddleView = viewfact.newBasicPaddleView();
            
            BasicBall newball = new BasicBall(field, ballview, new Point2D.Double(40, 160), 8.0, new Speed2D(0.03, -0.01));
            BreakableBrick newbrick = new BreakableBrick(field, brick1view, new Point2D.Double(180, 120), new Dimension(48, 24));
            BreakableBrick newbrick2 = new BreakableBrick(field, brick2view, new Point2D.Double(228, 120), new Dimension(48, 24));
            UnbreakableBrick newbrick3 = new UnbreakableBrick(field, brick3view, new Point2D.Double(276, 120), new Dimension(48, 24));
            BasicPaddle paddle = new BasicPaddle(field, paddleView, new Point2D.Double(0, 584), new Dimension(96, 16));

            _fieldView.addObjectView(ballview);
            _fieldView.addObjectView(brick1view);
            _fieldView.addObjectView(brick2view);
            _fieldView.addObjectView(brick3view);
            _fieldView.addObjectView(paddleView);

            // Контроллер и игрок.
            Player player = new Player(paddle);
            _controller = new GameController(player, bsInput);

                    // ЭКСПЕРИМЕНТ
            paddle.addBall(newball);

            // Тестирование столкновения множества шаров
            IngameObjectView ball01_view = viewfact.newBasicBallView();
            IngameObjectView ball02_view = viewfact.newBasicBallView();
            BasicBall ball01 = new BasicBall(field, ball01_view, new Point2D.Double(213.3975, 250), 16.0, new Speed2D(0.043, -0.025));
            BasicBall ball02 = new BasicBall(field, ball02_view, new Point2D.Double(400, 200), 16.0, new Speed2D(-0.05, 0));

            _fieldView.addObjectView(ball01_view);
            _fieldView.addObjectView(ball02_view);

            field.addObject(newbrick);
            field.addObject(newbrick2);
            // Инициализация закончена. Спрятать курсор мыши перед началом игры.
            this.hideCursor();
	}

	@Override
	public void render(Graphics2D arg0) {

            _fieldView.render(arg0);

            // TODO: Рендер кол-ва очков, другой инофрмации (сейчас игра на весь экран)
	}

	@Override
	public void update(long arg0) {
		
            // Апдейтим всё
            _fieldView.update(arg0);
            _controller.update();
	}

}
