package model.collision;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import com.golden.gamedev.object.collision.CollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;

import model.IngameObject;

/**
 * Столкнувшийся игровой объект. Содержит ссылку на игровой объект и дополнительные сведения
 * о коллизии (тип коллизии, предыдущая позиция)
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 */
public class CollidedObject implements Cloneable {

	protected IngameObject object		= null;
	protected Point2D.Float oldPosition	= null;
	protected int colSide				= -1;
	protected CollisionShape colShape	= null;
	/**
	 * Создать информацию о столкнувшемся игровом объекте
	 * @param object Игровой объект
	 * @param oldpos Позиция объекта за кадр до столкновения
	 * @param side Сторона объекта, которой он столкнулся
	 * @param shape Форма объекта
	 */
	public CollidedObject(IngameObject object, Point2D.Float oldpos, 
						  int side, CollisionShape shape) {
		
		if (object == null || oldpos == null || shape == null) {
			throw new NullPointerException();
		}
		
		this.object = object;
		this.oldPosition = oldpos;
		this.colSide = side;
		this.colShape = shape;
	}
	
	public IngameObject object() {
		return object;
	}
	
	public Point2D.Float oldPosition() {
		return oldPosition;
	}
	
	public int collisionSide() {
		return colSide;
	}
	
	public CollisionShape collisionShape() {
		return colShape;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		CollidedObject clone = (CollidedObject) super.clone();
		clone.object = (IngameObject) this.object.clone();
		clone.colSide = this.colSide;
		clone.colShape = this.colShape;
		clone.oldPosition = (Float) this.oldPosition.clone();
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		return this.object.equals(((CollidedObject)(obj)).object);
	}
	
	@Override
	public int hashCode() {
		
		return this.object.hashCode();
	}
}
