package model;

/**
 * Вектор скорости в двумерном пространстве
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Speed2D {

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
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
}
