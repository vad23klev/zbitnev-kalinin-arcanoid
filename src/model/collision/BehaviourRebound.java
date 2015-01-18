package model.collision;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import model.IngameObject;
import model.Speed2D;
import model.ball.Ball;
import model.brick.Brick;
import model.paddle.Paddle;

/**
 * Поведение упрогого отскока при столкновении.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourRebound extends CollisionBehaviour {
	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourRebound instance = null;
	
	protected BehaviourRebound() {
	}
	
	/**
	 * Возвращает экземпляр поведения упрогого отскока.
	 * @return
	 */
	public static BehaviourRebound getInstance() {
		
		if (instance == null) {
			instance = new BehaviourRebound();
		}
		
		return instance;
	}
	
	@Override
	public void invoke(IngameObject from, IngameObject to) {
		
		// Вектор скорости отражается по-разному в зависимости от геометрической формы
		// активного объекта и пассивного объекта
		if ((from instanceof Brick || from instanceof Paddle) && to instanceof Ball) {
			
			float upperb = from.getPosition().y;
			float lowerb = from.getPosition().y + from.getSize().height;
			float leftb = from.getPosition().x;
			float rightb = from.getPosition().x + from.getSize().width;
			Point2D.Float topos = to.getPosition();
			
			// Столкновение с нижней гранью
			if (topos.y <= lowerb && lowerb <= topos.y + to.getSize().height) {
				
				topos.y = lowerb;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipVertical());
			}
			// Столкновение с верхней гранью
			else if (topos.y <= upperb && upperb <= topos.y + to.getSize().height) {
				
				topos.y = upperb - to.getSize().height;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipVertical());
			}
			// Столкновение с правой гранью
			else if (topos.x <= rightb && rightb <= topos.x + to.getSize().width) {
				
				topos.x = rightb;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipHorizontal());
			}
			// Столкновение с левой гранью
			else if (topos.x <= leftb && leftb <= topos.x + to.getSize().width) {
				
				topos.x = leftb - to.getSize().width;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipHorizontal());
			}
		}
		else if (from instanceof Ball && to instanceof Ball) {
			
		}
	}
}
