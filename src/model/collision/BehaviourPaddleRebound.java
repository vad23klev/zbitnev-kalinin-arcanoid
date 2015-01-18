package model.collision;

import java.awt.geom.Point2D;

import model.IngameObject;
import model.ball.Ball;
import model.paddle.Paddle;

/**
 * Поведение отскока от ракетки при столкновении.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourPaddleRebound extends CollisionBehaviour {
	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourPaddleRebound instance = null;
	
	protected BehaviourPaddleRebound() {
	}
	
	/**
	 * Возвращает экземпляр поведения отражения от ракетки.
	 * @return
	 */
	public static BehaviourPaddleRebound getInstance() {
		
		if (instance == null) {
			instance = new BehaviourPaddleRebound();
		}
		
		return instance;
	}
	
	@Override
	public void invoke(IngameObject from, IngameObject to) {
		
		if (from instanceof Paddle && to instanceof Ball) {
		
			to.setPosition(new Point2D.Float(to.getPosition().x, 
									 		 from.getPosition().y - to.getSize().height));
			to.setSpeed(((Paddle)from).getFireSpeed((Ball)to));
		}
	}
}
