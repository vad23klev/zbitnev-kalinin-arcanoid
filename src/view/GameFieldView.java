package view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.IngameObject;
import model.ball.Ball;
import model.ball.BasicBall;
import model.brick.BreakableBrick;
import model.brick.Brick;
import model.interaction.GenericEventListener;
import model.paddle.Paddle;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Игровое поле арканоида. Содержит все обекты игры, ответственнен за обновление, рендеринг и
 * проверку стоклновений 
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField {
	
	// Игровые объекты
	ArrayList<IngameObjectView> _objectViews = new ArrayList<>();
	
	public GameFieldView() {
		
		SpriteGroup balls = new SpriteGroup("balls");
		SpriteGroup bricks = new SpriteGroup("bricks");
		SpriteGroup paddles = new SpriteGroup("paddles");
		this.addGroup(balls);
		this.addGroup(bricks);
		this.addGroup(paddles);
	}

	@Override
	public void update(long timeElapsed) {
	    
	    super.update(timeElapsed);
	    for (IngameObjectView ov : _objectViews) {
	        ov.update(timeElapsed);
	    }
	}
	
	/**
	 * Возвращает группу спрайтов мячей.
	 * @return Группа спрайтов.
	 */
	public SpriteGroup getBallsGroup() {
	    
	    return this.getGroup("balls");
	}
	
	/**
	 * Возвращает группу спрайтов кирпичей.
	 * @return Группа спрайтов.
	 */
	public SpriteGroup getBricksGroup() {
	    
	    return this.getGroup("bricks");
	}
	
	/**
	 * Возвращает группу спрайтов ракеток.
	 * @return Группа спрайтов.
	 */
	public SpriteGroup getPaddlesGroup() {
	    
	    return this.getGroup("paddles");
	}
	
	/**
	 * Добавляет представление объекта на это поле. Этот метод добавляет объект в соответствующую группу спрайтов.
	 * @param ov Представление.
	 */
	public void addObjectView(IngameObjectView ov) {
	    
	    _objectViews.add(ov);
	    if (ov.getIngameObject() instanceof Ball) {
	        getBallsGroup().add(ov.getSprite());
	    } else if (ov.getIngameObject() instanceof Brick) {
	        getBricksGroup().add(ov.getSprite());
	    } else if (ov.getIngameObject() instanceof Paddle) {
	        getPaddlesGroup().add(ov.getSprite());
	    }
	}
	
	/**
	 * Удаляет представление объекта с этого представления поля и из группы спрайтов.
	 * @param ov Представление.
	 */
	public void removeObjectView(IngameObjectView ov) {
	    
	    _objectViews.remove(ov);
	    if (ov.getIngameObject() instanceof Ball) {
            getBallsGroup().remove(ov.getSprite());
        } else if (ov.getIngameObject() instanceof Brick) {
            getBricksGroup().remove(ov.getSprite());
        } else if (ov.getIngameObject() instanceof Paddle) {
            getPaddlesGroup().remove(ov.getSprite());
        }
	}
	
	/**
	 * Возвращает список представлений объектов на этом поле.
	 * @return Список.
	 */
	public ArrayList<IngameObjectView> getObjectViews() {
	    
	    return (ArrayList<IngameObjectView>) _objectViews.clone();
	}
}
