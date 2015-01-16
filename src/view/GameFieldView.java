package view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.IngameObject;
import model.ball.BasicBall;
import model.brick.BreakableBrick;
import model.interaction.GenericEventListener;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Игровое поле арканоида. Содержит все обекты игры, ответственнен за обновление, рендеринг и
 * проверку стоклновений 
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField implements GenericEventListener {

	// Загруженные изображения для игровых объектов
	public BufferedImage basicBallImg;
	public BufferedImage breakableBrickImg;
	
	// Игровые объекты
	ArrayList<IngameObjectView> objectViews;
	
	public GameFieldView() {
		basicBallImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		breakableBrickImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		
		SpriteGroup balls = new SpriteGroup("balls");
		SpriteGroup bricks = new SpriteGroup("bricks");
		this.addGroup(balls);
		this.addGroup(bricks);
	}

	@Override
	public void created(IngameObject obj) {
		
		// TODO Адекватное проектное решение для генерации представления объекта
		if (obj instanceof BasicBall) {
			IngameObjectView newobj = new IngameObjectView();
			
			PublishingSprite sprite = new PublishingSprite();
			sprite.setImage(basicBallImg);
			sprite.setLocation(obj.getPosition().x, obj.getPosition().y);
			sprite.setObjectView(newobj);
			newobj.addPublishingSprite(sprite);
			
			this.getGroup("balls").add(sprite);
			
			obj.addSpeedChangeListener(newobj);
			obj.addPositionChangeListener(newobj);
		}
		else if (obj instanceof BreakableBrick) {
			IngameObjectView newobj = new IngameObjectView();
			
			PublishingSprite sprite = new PublishingSprite();
			sprite.setImage(breakableBrickImg);
			sprite.setLocation(obj.getPosition().x, obj.getPosition().y);
			sprite.setObjectView(newobj);
			newobj.addPublishingSprite(sprite);
			
			this.getGroup("bricks").add(sprite);

			obj.addSpeedChangeListener(newobj);
			obj.addPositionChangeListener(newobj);
		}
	}

	@Override
	public void destroyed(IngameObject obj) {
		// TODO Auto-generated method stub
		
	}
}
