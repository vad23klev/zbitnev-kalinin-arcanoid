package model;

import java.awt.Dimension;
import java.util.ArrayList;

import model.ball.Ball;
import model.ball.BallPositionChangedListener;

/**
 * Модель игрового поля.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameField implements BallPositionChangedListener {

	private ArrayList<IngameObject> objects;
	private Dimension dimensions;
	
    /**
     * Инициализирует поле заданного размера.
     * @param size Размер поля.
     */
    public GameField(Dimension size) {
    	
    	objects = new ArrayList<>();
    	dimensions = size;
    }
    
	/**
	 * Добавить объект на поле
	 * @param object Объект для добавления
	 */
	public void addObject(IngameObject object) {
		
		objects.add(object);
	}
	
	/**
	 * Убрать объект с поля
	 * @param object Объект для удаления
	 */
	public void removeObject(IngameObject object) {
		
		objects.remove(object);
	}
	
	/** 
	 * Получить размеры игрового поля (в пикселях).
	 * @return Размеры поля.
	 */
	public Dimension getSize() {
		
		return dimensions;
	}

	/**
	 * Реализация этого метода отражает мяч от границ поля.
	 */
    @Override
    public void ballPositionChanged(Ball ball) {
        
        if (ball.getPosition().y < 0) {
            ball.setSpeed(ball.getSpeed().flipVertical());
        }
        
        if (ball.getPosition().x < 0 || ball.getPosition().x + ball.getSize().width > dimensions.width) {
            ball.setSpeed(ball.getSpeed().flipHorizontal());
        }
    }
}
