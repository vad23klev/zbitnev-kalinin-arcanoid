package view;

import java.util.ArrayList;
import java.util.HashMap;

import model.collision.CollisionManagers.PublishingCollisionManager;
import model.ball.Ball;
import model.brick.Brick;
import model.collision.CollidedObject;
import model.interaction.CollisionListener;
import model.paddle.Paddle;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import java.awt.Color;
import java.awt.Dimension;
import model.collision.CollisionManagers.ModelCollisionManager;
import model.collision.CollisionManagers.PublishingCollisionBoundsManager;
import model.interaction.GenericEventListener;

/**
 * Игровое поле арканоида. Содержит все обекты игры, ответственнен за обновление, рендеринг и
 * проверку стоклновений 
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField  implements GenericEventListener{
	
	private ArrayList<IngameObjectView> _objectViews = new ArrayList<>();
	private ArrayList<CollisionListener> _collisionListners;
        private PublishingCollisionManager[] managers;
	
	public GameFieldView(Dimension dim) {
		
            _collisionListners = new ArrayList<>();
            SpriteGroup balls = new SpriteGroup("balls");
            SpriteGroup bricks = new SpriteGroup("bricks");
            SpriteGroup paddles = new SpriteGroup("paddles");
            this.addGroup(balls);
            this.addGroup(bricks);
            this.addGroup(paddles);

            // Добавить на поле менеджеры коллизий для обработки столкновений
            ModelCollisionManager manager = new ModelCollisionManager();
            managers = new PublishingCollisionManager[3];
            managers[0] = new PublishingCollisionManager(manager);
            managers[1] = new PublishingCollisionManager(manager);
            managers[2] = new PublishingCollisionManager(manager);
            this.addCollisionGroup(balls, paddles, managers[0]);
            this.addCollisionGroup(balls, bricks, managers[1]);
            this.addCollisionGroup(balls, balls, managers[2]);
            this.addCollisionGroup(balls, null, new PublishingCollisionBoundsManager(
                    new ColorBackground(Color.white, ((int)dim.getWidth()), ((int)dim.getHeight())), manager));
            this.addCollisionGroup(paddles, null, new PublishingCollisionBoundsManager(
                    new ColorBackground(Color.white, ((int)dim.getWidth()), ((int)dim.getHeight())), manager));
	}

	@Override
	public void update(long timeElapsed) {
	    
            //Если комментируем - то работает всё, кроме коллизий.
           super.update(timeElapsed);
            
            for ( PublishingCollisionManager manager: this.managers) {
                manager.collisionOcured();
            }
            
            //Если комментируем - то всё работает.
	    for (IngameObjectView ov : _objectViews) {
	        ((SpriteStorageGTGE)ov.getSpriteStorage()).update(timeElapsed);
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
            SpriteStorageGTGE storage = (SpriteStorageGTGE)ov.getSpriteStorage();
	    if (ov.getIngameObject() instanceof Ball) {
	        getBallsGroup().add(storage.getSprite());
	    } else if (ov.getIngameObject() instanceof Brick) {
	        getBricksGroup().add(storage.getSprite());
	    } else if (ov.getIngameObject() instanceof Paddle) {
	        getPaddlesGroup().add(storage.getSprite());
	    }
	}
	
	/**
	 * Удаляет представление объекта с этого представления поля и из группы спрайтов.
	 * @param ov Представление.
	 */
	public void removeObjectView(IngameObjectView ov) {
	    
	_objectViews.remove(ov);
        ((SpriteStorageGTGE)ov.getSpriteStorage()).getSprite().setActive(false);
	}
	
	/**
	 * Возвращает список представлений объектов на этом поле.
	 * @return Список.
	 */
	public ArrayList<IngameObjectView> getObjectViews() {
	    
	    return (ArrayList<IngameObjectView>) _objectViews.clone();
	}
	
    /**
     * Добавить слушателя событий о произошедших на поле столкновениях
     * @param l Добавляемый слушатель
     */
    public void addCollisionListener(CollisionListener l) {
    	_collisionListners.add(l);
    }
    
    /**
     * Удалить слушателя событий о произошедших на поле столкновениях
     * @param l Удаляемый слушатель
     */
    public void removeCollisionListener(CollisionListener l) {
    	_collisionListners.remove(l);
    }
    
    /**
     * Копирует сообщения о столкновениях из одного словаря в другой
     * @param to Словарь, который будет дополнен новыми сообщениями
     * @param from Словарь, из которого будут скопированы сообщения
     */
    private void attachStorage(HashMap<CollidedObject, ArrayList<CollidedObject>> to,
    		HashMap<CollidedObject, ArrayList<CollidedObject>> from) {
    	
    	for (CollidedObject obj : from.keySet()) {
    		
    		// Если такого ключа не содержится -- просто добавляем новую запись в словарь
        	// Если такой ключ есть -- копируем значения из списка
    		if (!to.containsKey(obj)) {
    			to.put(obj, from.get(obj));
    		}
    		else {
    			
    			for (CollidedObject listobj : from.get(obj)) {
    				
    				if (!to.get(obj).contains(listobj)) {
    					to.get(obj).add(listobj);
    				}
    			}
    		}
    	}
    }

    @Override
    public void destroyed(Object object) {
        this.removeObjectView((IngameObjectView) object);
    }
}
