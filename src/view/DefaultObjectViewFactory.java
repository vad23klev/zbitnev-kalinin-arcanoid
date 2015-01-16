package view;

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
    
    public DefaultObjectViewFactory() {
        
    }
    
    /**
     * Создает фабрику.
     * @param basicBallImage
     * @param breakableBrickImage
     * @param unbreakableBrickImage
     * @param basicPaddleImage
     */
    public DefaultObjectViewFactory(BufferedImage basicBallImage, BufferedImage breakableBrickImage,
                                    BufferedImage unbreakableBrickImage, BufferedImage basicPaddleImage) {
        
        _basicBallImage = basicBallImage;
        _breakableBrickImage = breakableBrickImage;
        _unbreakableBrickImage = unbreakableBrickImage;
        _basicPaddleImage = basicPaddleImage;
    }
    
    /**
     * Возвращает true, если фабрика настроена и может порождать объекты.
     * @return Валидность фабрики.
     */
    public boolean is_valid() {
        
        boolean valid = true;
        
        valid &= _basicBallImage != null;
        valid &= _breakableBrickImage != null;
        //valid &= _unbreakableBrickImage != null;
        valid &= _basicPaddleImage != null;
        
        return valid;
    }
    
    /**
     * Проверяет, валидна ли фабрика и выбрасывает исключение, если нет.
     */
    public void ensure_valid() {
        
        if (!is_valid()) {
            throw new NullPointerException("Fabric was initialized with null images.");
        }
    }
    
    // TODO set get 
    
    /**
     * Создает представление для простого мяча.
     * @param ball Модель мяча.
     * @return Представление мяча.
     */
    public IngameObjectView newBasicBallView(BasicBall ball) {
        
        ensure_valid();
        
        PublishingSprite ballSprite = new PublishingSprite();
        ballSprite.setImage(_basicBallImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView ballView = new IngameObjectView(ball, ballSprite);
        
        return ballView;
    }
    
    /**
     * Создает представление разрушаемого кирпича.
     * @param brick Разрушаемый кирпич.
     * @return Представление разрушаемого кирпича.
     */
    public IngameObjectView newBreakableBrickView(BreakableBrick brick) {
        
        ensure_valid();
        
        PublishingSprite brickSprite = new PublishingSprite();
        brickSprite.setImage(_breakableBrickImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView brickView = new IngameObjectView(brick, brickSprite);
        
        return brickView;
    }
    
    /**
     * Создает представление неразрушаемого кирпича.
     * @param brick Неразрушаемый кирпич.
     * @return Представление неразрушаемого кирпича.
     */
    public IngameObjectView newUnbreakableBrickView(UnbreakableBrick brick) {
        
        ensure_valid();
        
        PublishingSprite brickSprite = new PublishingSprite();
        brickSprite.setImage(_unbreakableBrickImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView brickView = new IngameObjectView(brick, brickSprite);
        
        return brickView;
    }
    
    /**
     * Создает представление простой ракетки.
     * @param brick Ракетка.
     * @return Представление простой ракетки.
     */
    public IngameObjectView newBasicPaddleView(BasicPaddle paddle) {
        
        ensure_valid();
        
        PublishingSprite paddleSprite = new PublishingSprite();
        paddleSprite.setImage(_basicPaddleImage);
        
        // Напоминание: этот конструктор сам установит объекты слушателями друг друга.
        IngameObjectView paddleView = new IngameObjectView(paddle, paddleSprite);
        
        return paddleView;
    }
}
