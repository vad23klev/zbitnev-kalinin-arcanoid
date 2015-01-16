package model;

/**
 * Вектор скорости в двумерном пространстве
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Speed2D implements Cloneable {

	private double x;
	private double y;
	
	/**
	 * Создаёт экземпляр вектора скорости на декартовой плоскости
	 * @param xspeed Компонента вектора по x
	 * @param yspeed Компонента вектора по y
	 */
	public Speed2D(double xspeed, double yspeed) {
		x = xspeed;
		y = yspeed;
	}
	
	@Override
	public Object clone() {
	    
        return new Speed2D(x, y);
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
	
	public Speed2D flipHorizontal() {
	    return new Speed2D(-x, y);
	}
	
	public Speed2D flipVertical() {
	    return new Speed2D(x, -y);
	}
}
