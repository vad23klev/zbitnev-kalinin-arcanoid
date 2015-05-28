package view;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import model.IngameObject;
import model.Speed2D;
import model.interaction.CollisionEventListener;
import model.interaction.GenericEventListener;

/**
 * Представление отдельного игрового объекта
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class IngameObjectView implements CollisionEventListener {

    protected IngameObject _ingameObject = null;
    protected SpriteStorage _spriteStorage = null;
    
    protected ArrayList<GenericEventListener> _genericEventListeners = new ArrayList<>();
	
	/**
	 * Создает представление объекта на основе его модели и спрайта.
	 * Этот метод автоматически связывает спрайт с объектом представления, которому он принадлежит.
	 * @param spriteStorage Хранилище спрайтов.
	 * @param view представление поля.
	 */
	public IngameObjectView(SpriteStorage spriteStorage, GameFieldView view) {
	    
	    if (spriteStorage == null) {
	        throw new NullPointerException();
	    }
	    spriteStorage.setObjectView(this);
	    this._spriteStorage = spriteStorage;
            this._genericEventListeners.add(view);
	}
	
	/**
	 * Возвращает модель игрового объекта.
	 * @return IngameObject.
	 */
	public IngameObject getIngameObject() {
	    
	    return _ingameObject;
	}
	
	/**
	 * Возвращает спрайт, принадлежащий данному представлению объекта.
	 * @return Спрайт.
	 */
	public SpriteStorage getSpriteStorage() {
	    return _spriteStorage;
	}

	public void destroyed() {
            for(int i = 0; i < _genericEventListeners.size(); i++) {
                _genericEventListeners.get(i).destroyed(this);
            }
	}
        
        public void setSpeed(Speed2D speed) {
            this._spriteStorage.setSpeed(speed);
        }
        
        public Speed2D getSpeed() {
            return this._spriteStorage.getSpeed();
        }
        
        public void setObject(IngameObject object) {
            if (this._ingameObject == null)
                this._ingameObject = object;
        }
        
        @Override
        public Object clone() {
            return this;            
        }

    @Override
    public void collisionOccured() {
    }
}
