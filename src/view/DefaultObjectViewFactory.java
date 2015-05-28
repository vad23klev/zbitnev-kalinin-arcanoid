package view;

import com.golden.gamedev.engine.BaseLoader;
import java.awt.image.BufferedImage;

import model.ball.BasicBall;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;
import model.paddle.BasicPaddle;

/**
 * Фабрика для создания представлений стандартных игровых объектов:
 * - простой мяч (basic ball)
 * - неразрушаемый кирпич (unbreakable brick)
 * - разрушаемый кирпич (breakable brick)
 * - простая ракетка (basic paddle)
 * 
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class DefaultObjectViewFactory {
    
    protected BufferedImage _basicBallImage = null;
    protected BufferedImage _breakableBrickImage = null;
    protected BufferedImage _unbreakableBrickImage = null;
    protected BufferedImage _basicPaddleImage = null;
    protected GameFieldView _view = null;
    
    public DefaultObjectViewFactory() {
        
    }
    
    /**
     * Создает фабрику.
     * @param basicBallImage
     * @param breakableBrickImage
     * @param unbreakableBrickImage
     * @param basicPaddleImage
     */
    public DefaultObjectViewFactory(GameFieldView view, BaseLoader bsLoader) {
        
    	_view = view;  
	_basicBallImage = bsLoader.getImage("default/gfx/balls/basic.png");
	_breakableBrickImage = bsLoader.getImage("default/gfx/bricks/breakable.png");
	_unbreakableBrickImage = bsLoader.getImage("default/gfx/bricks/unbreakable.png");
	_basicPaddleImage = bsLoader.getImage("default/gfx/paddles/basic.png");
    }
    
    /**
     * Возвращает true, если фабрика настроена и может порождать объекты.
     * @return Валидность фабрики.
     */
    public boolean isValid() {
        
        boolean valid = true;
        
        valid &= _basicBallImage != null;
        valid &= _breakableBrickImage != null;
        valid &= _unbreakableBrickImage != null;
        valid &= _basicPaddleImage != null;
        
        return valid;
    }
    
    /**
     * Проверяет, валидна ли фабрика и выбрасывает исключение, если нет.
     */
    public void ensureValid() {
        
        if (!isValid()) {
            throw new NullPointerException("Fabric was initialized with null images.");
        }
    }
    
    /**
     * Создает представление для простого мяча.
     * @param ball Модель мяча.
     * @return Представление мяча.
     */
    public IngameObjectView newBasicBallView() {
        
        ensureValid();
        
        SpriteStorage ballSprite = createSpriteStorage(_basicBallImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView ballView = new IngameObjectView(ballSprite, _view);
        
        return ballView;
    }
    
    /**
     * Создает представление разрушаемого кирпича.
     * @param brick Разрушаемый кирпич.
     * @return Представление разрушаемого кирпича.
     */
    public IngameObjectView newBreakableBrickView() {
        
        ensureValid();
        
        SpriteStorage brickSprite = createSpriteStorage(_breakableBrickImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView brickView = new IngameObjectView(brickSprite, _view);
        
        return brickView;
    }
    
    /**
     * Создает представление неразрушаемого кирпича.
     * @param brick Неразрушаемый кирпич.
     * @return Представление неразрушаемого кирпича.
     */
    public IngameObjectView newUnbreakableBrickView() {
        
        ensureValid();
        
        SpriteStorage brickSprite = createSpriteStorage(_unbreakableBrickImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView brickView = new IngameObjectView(brickSprite, _view);
        
        return brickView;
    }
    
    /**
     * Создает представление простой ракетки.
     * @param brick Ракетка.
     * @return Представление простой ракетки.
     */
    public IngameObjectView newBasicPaddleView() {
        
        ensureValid();
        
        SpriteStorage paddleSprite = createSpriteStorage(_basicPaddleImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView paddleView = new IngameObjectView(paddleSprite, _view);
        
        return paddleView;
    }
    
    protected SpriteStorage createSpriteStorage(BufferedImage image) {
        return new SpriteStorageGTGE(image);
    }
    
    protected SpriteStorage createSpriteStorage() {        
        return new SpriteStorageGTGE();
    }
}