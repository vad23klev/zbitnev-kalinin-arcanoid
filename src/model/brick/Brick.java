package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import model.GameField;
import model.IngameObject;
import model.Rectangle;
import model.Speed2D;

/**
 * Модель абстрактного кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Brick extends IngameObject {

    /**
     * Создает игровой объект, координаты (0, 0), нулевая скорость, нулевой размер.
     * @param field Игровое поле.
     */
    public Brick(GameField field) {

        this(field, new Point2D.Double(0.0, 0.0), new Dimension(0, 0));
    }

    /**
     * Создает игровой объект с нулевой скоростью.
     * @param field Игровое поле.
     * @param pos Позиция объекта.
     * @param dim Размеры объекта.
     */
    public Brick(GameField field, Point2D.Double pos, Dimension dim) {

        this(field, pos, dim, new Speed2D(0.0, 0.0));
    }

    /**
     * Создает игровой объект.
     * @param field Игровое поле.
     * @param pos Позиция объекта.
     * @param rad Радиус объекта.
     * @param speed Скорость объекта.
     */
    public Brick(GameField field, Point2D.Double pos, Dimension dim, Speed2D speed) {

        if (field == null || pos == null || dim == null || speed == null) {
            throw new NullPointerException();
        }

        this._field = field;
        this._form = new Rectangle(pos, dim);
        this.setPositionByPoint(pos);
        this.setSpeed(speed);
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	
    	Brick clone = (Brick) super.clone();
    	return clone;
    }
}
