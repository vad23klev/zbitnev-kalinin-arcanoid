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
			
			// Наименьная разница скажет нам о том, с какой гранью кирпича столкнулся шар
			float uppers = Math.abs(from.getPosition().y 
					                - (to.getPosition().y + to.getSize().height));
			float lowers = Math.abs(from.getPosition().y + from.getSize().height
									- to.getPosition().y);
			float lefts = Math.abs(from.getPosition().x 
	                				- (to.getPosition().x + to.getSize().width));
			float rights = Math.abs(from.getPosition().x + from.getSize().width
									- to.getPosition().x);
			
			Point2D.Float topos = to.getPosition();
			// Столкновение с нижней гранью
			if (lowers <= uppers && lowers <= lefts && lowers <= rights) {
				
				topos.y = from.getPosition().y + from.getSize().height;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipVertical());
			}
			// Столкновение с верхней гранью
			else if (uppers <= lowers && uppers <= lefts && uppers <= rights) {
				
				topos.y = from.getPosition().y - to.getSize().height;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipVertical());
			}
			// Столкновение с правой гранью
			else if (rights <= lowers && rights <= uppers && rights <= lefts) {
				
				topos.x = from.getPosition().x + from.getSize().width;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipHorizontal());
			}
			// Столкновение с левой гранью
			else if (lefts <= lowers && lefts <= uppers && lefts <= rights) {
				
				topos.x = from.getPosition().x - to.getSize().width;
				to.setPosition(topos);
				to.setSpeed(to.getSpeed().flipHorizontal());
			}
		}
		else if (from instanceof Ball && to instanceof Ball) {
			
		}
	}
}
