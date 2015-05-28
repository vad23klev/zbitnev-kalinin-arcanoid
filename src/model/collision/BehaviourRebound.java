package model.collision;

import java.awt.geom.Point2D;

import model.IngameObject;
import model.Speed2D;
import model.ball.Ball;
import model.brick.Brick;
import model.paddle.Paddle;
import math.geom2d.Vector2D;
import model.Rectangle;
import model.Round;

/**
 * Поведение упрогого отскока при столкновении.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourRebound extends CollisionBehaviour {
	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourRebound _instance = null;
	
	protected BehaviourRebound() {
	}
	
	/**
	 * Возвращает экземпляр поведения упрогого отскока.
	 * @return
	 */
	public static BehaviourRebound getInstance() {
		
            if (_instance == null) {
                    _instance = new BehaviourRebound();
            }

            return _instance;
	}
	
	@Override
	public void invoke(CollidedObject from, CollidedObject to) {
		
            // Вектор скорости отражается по-разному в зависимости от геометрической формы
            // активного объекта и пассивного объекта
            IngameObject toobj = to.object();
            IngameObject fromobj = from.object();
            if ((fromobj instanceof Brick || fromobj instanceof Paddle) && toobj instanceof Ball) {

                    Point2D.Double newpos = to.oldPosition();
                    if (to.collisionSide() == CollidedObject.SIDE_TOP) {

                            newpos.y = fromobj.getPosition().y - ((Round)toobj.getForm()).getRadius() * 2 - 1;
                            toobj.setPosition(newpos);
                            toobj.setSpeed(toobj.getSpeed().flipVertical());
                    }
                    else if (to.collisionSide()  == CollidedObject.SIDE_BOTTOM) {

                            newpos.y = fromobj.getPosition().y + ((Rectangle)fromobj.getForm()).getHeight() + 1;
                            toobj.setPosition(newpos);
                            toobj.setSpeed(toobj.getSpeed().flipVertical());
                    }
                    else if (to.collisionSide() == CollidedObject.SIDE_RIGHT) {

                            newpos.x = fromobj.getPosition().x + ((Rectangle)fromobj.getForm()).getWidth() + 1;
                            toobj.setPosition(newpos);
                            toobj.setSpeed(toobj.getSpeed().flipHorizontal());
                    }
                    else if (to.collisionSide() == CollidedObject.SIDE_LEFT) {

                            newpos.x = fromobj.getPosition().x - ((Round)toobj.getForm()).getRadius() * 2;
                            toobj.setPosition(newpos);
                            toobj.setSpeed(toobj.getSpeed().flipHorizontal());
                    }
		}
		else if (fromobj instanceof Ball && toobj instanceof Ball) {
			
                    Ball act = (Ball)fromobj;
                    Ball pass = (Ball)toobj;

                    // Вычисляется точка столкновения
                    double colx =   (
                                        ((Round)act.getForm()).getCenter().x * 
                                        ((Round)pass.getForm()).getRadius() + 
                                        ((Round)pass.getForm()).getCenter().x * 
                                        ((Round)act.getForm()).getRadius()
                                    ) / (
                                        ((Round)act.getForm()).getRadius() + 
                                        ((Round)pass.getForm()).getRadius()
                                    );
                    double coly =   (
                                        ((Round)act.getForm()).getCenter().y * ((Round)pass.getForm()).getRadius() + 
                                        ((Round)pass.getForm()).getCenter().y * ((Round)act.getForm()).getRadius()) / 
                                        (((Round)act.getForm()).getRadius() + ((Round)pass.getForm()).getRadius()
                                    );

                    // Пассивный объект "отодвигается" по линии столкновения (линия, соединяющая центры 
                    // шаров) во избежание повторной коллизии
                    Point2D.Double movevect = new Point2D.Double(((Round)pass.getForm()).getCenter().x - colx, ((Round)pass.getForm()).getCenter().y - coly);
                    Point2D.Double newpos = new Point2D.Double(((Round)pass.getForm()).getCenter().x + movevect.x, ((Round)pass.getForm()).getCenter().y + movevect.y);
                    pass.setPositionByCenter(newpos);

                    // Вычисляется новая скорость для пассивного объекта
                    Vector2D aspeed = new Vector2D(act.getSpeed().x(), act.getSpeed().y());
                    Vector2D pspeed = new Vector2D(pass.getSpeed().x(), pass.getSpeed().y());
                    Vector2D acenter = new Vector2D(((Round)act.getForm()).getCenter().x, ((Round)act.getForm()).getCenter().y);
                    Vector2D pcenter = new Vector2D(((Round)pass.getForm()).getCenter().x, ((Round)pass.getForm()).getCenter().y);
                    Vector2D newPSpeed = pspeed;
                    Vector2D pminusa = pcenter.minus(acenter);
                    newPSpeed = newPSpeed.minus(pminusa.times(
                                    pspeed.minus(aspeed).dot(pminusa) / Math.pow(pminusa.norm(), 2.0)));

                    // Новая скорость назначается пассивному объекту
                    pass.setSpeed(new Speed2D(newPSpeed.x(), newPSpeed.y()));
		}
	}
}
