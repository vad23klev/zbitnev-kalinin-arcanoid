package model.collision;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import model.IngameObject;
import model.Speed2D;
import model.ball.Ball;
import model.brick.Brick;
import model.paddle.Paddle;

import math.geom2d.Vector2D;

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
	public void invoke(CollidedObject from, CollidedObject to) {
		
		// Вектор скорости отражается по-разному в зависимости от геометрической формы
		// активного объекта и пассивного объекта
		IngameObject toobj = to.object();
		IngameObject fromobj = from.object();
		if ((fromobj instanceof Brick || fromobj instanceof Paddle) && toobj instanceof Ball) {
			
			// Наименьная разница скажет нам о том, с какой гранью кирпича столкнулся шар
			float uppers = Math.abs(fromobj.getPosition().y 
					                - (toobj.getPosition().y + toobj.getSize().height));
			float lowers = Math.abs(fromobj.getPosition().y + fromobj.getSize().height
									- toobj.getPosition().y);
			float lefts = Math.abs(fromobj.getPosition().x 
	                				- (toobj.getPosition().x + toobj.getSize().width));
			float rights = Math.abs(fromobj.getPosition().x + fromobj.getSize().width
									- toobj.getPosition().x);
			
			Point2D.Float topos = toobj.getPosition();
			// Столкновение с нижней гранью
			if (lowers <= uppers && lowers <= lefts && lowers <= rights) {
				
				topos.y = fromobj.getPosition().y + fromobj.getSize().height;
				toobj.setPosition(topos);
				toobj.setSpeed(toobj.getSpeed().flipVertical());
			}
			// Столкновение с верхней гранью
			else if (uppers <= lowers && uppers <= lefts && uppers <= rights) {
				
				topos.y = fromobj.getPosition().y - toobj.getSize().height;
				toobj.setPosition(topos);
				toobj.setSpeed(toobj.getSpeed().flipVertical());
			}
			// Столкновение с правой гранью
			else if (rights <= lowers && rights <= uppers && rights <= lefts) {
				
				topos.x = fromobj.getPosition().x + fromobj.getSize().width;
				toobj.setPosition(topos);
				toobj.setSpeed(toobj.getSpeed().flipHorizontal());
			}
			// Столкновение с левой гранью
			else if (lefts <= lowers && lefts <= uppers && lefts <= rights) {
				
				topos.x = fromobj.getPosition().x - toobj.getSize().width;
				toobj.setPosition(topos);
				toobj.setSpeed(toobj.getSpeed().flipHorizontal());
			}
		}
		else if (fromobj instanceof Ball && toobj instanceof Ball) {
			
			Ball act = (Ball)fromobj;
			Ball pass = (Ball)toobj;
			
			// Вычисляется точка столкновения
			float colx = (act.getCenter().x * pass.getRadius() 
					      + pass.getCenter().x * act.getRadius()) 
					   / (act.getRadius() + pass.getRadius());
			float coly = (act.getCenter().y * pass.getRadius() 
				      + pass.getCenter().y * act.getRadius()) 
				   / (act.getRadius() + pass.getRadius());
			
			// Пассивный объект "отодвигается" по линии столкновения (линия, соединяющая центры 
			// шаров) во избежание повторной коллизии
			Point2D.Float movevect = new Point2D.Float(pass.getCenter().x - colx,
													   pass.getCenter().y - coly);
			Point2D.Float newpos = new Point2D.Float(pass.getCenter().x + movevect.x,
													 pass.getCenter().y + movevect.y);
			pass.setCenter(newpos);
			
			// Вычисляется новая скорость для пассивного объекта
			Vector2D aspeed = new Vector2D(act.getSpeed().x(), act.getSpeed().y());
			Vector2D pspeed = new Vector2D(pass.getSpeed().x(), pass.getSpeed().y());
			Vector2D acenter = new Vector2D(act.getCenter().x, act.getCenter().y);
			Vector2D pcenter = new Vector2D(pass.getCenter().x, pass.getCenter().y);
			Vector2D newPSpeed = pspeed;
			Vector2D pminusa = pcenter.minus(acenter);
			newPSpeed = newPSpeed.minus(pminusa.times(
					pspeed.minus(aspeed).dot(pminusa) / Math.pow(pminusa.norm(), 2.0)));
			
			// Новая скорость назначается пассивному объекту
			pass.setSpeed(new Speed2D(newPSpeed.x(), newPSpeed.y()));
		}
	}
}
