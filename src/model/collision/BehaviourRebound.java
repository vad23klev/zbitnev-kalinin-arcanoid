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
			
			Ball act = (Ball)from;
			Ball pass = (Ball)to;
			
			// Вычисляется точка столкновения
			float colx = (act.getCenter().x * pass.getRadius() 
					      + pass.getCenter().x * act.getRadius()) 
					   / (act.getRadius() + pass.getRadius());
			float coly = (act.getCenter().y * pass.getRadius() 
				      + pass.getCenter().y * act.getRadius()) 
				   / (act.getRadius() + pass.getRadius());
			
			System.out.println("COLLISION! At point " + colx + "; " + coly);
			System.out.println("\tactive ball is " + act.getCenter());
			System.out.println("\tpassive ball is " + pass.getCenter());
			
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
			System.out.println("\tactive speed is " + aspeed.toString());
			System.out.println("\tpassive speed before is " + pspeed.toString());
			Vector2D newPSpeed = pspeed;
			Vector2D pminusa = pcenter.minus(acenter);
			newPSpeed = newPSpeed.minus(pminusa.times(
					pspeed.minus(aspeed).dot(pminusa) / Math.pow(pminusa.norm(), 2.0)));
			System.out.println("\tpassive speed after is " + newPSpeed.toString());
			
			// Новая скорость назначается пассивному объекту
			pass.setSpeed(new Speed2D(newPSpeed.x(), newPSpeed.y()));
		}
	}
}
