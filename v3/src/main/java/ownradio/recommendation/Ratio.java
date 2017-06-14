package ownradio.recommendation;

/**
 * Класс для хранения коэффициента схожести
 *
 * @author Tanat
 * @version 1.0 14.06.17.
 */
public class Ratio {
	private String name;
	private double point;

	public Ratio(String name, double point) {
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public double getPoint() {
		return point;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Ratio ratio = (Ratio) o;

		if (Double.compare(ratio.point, point) != 0) return false;
		return name != null ? name.equals(ratio.name) : ratio.name == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = name != null ? name.hashCode() : 0;
		temp = Double.doubleToLongBits(point);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Ratio{" +
				"name='" + name + '\'' +
				", point=" + point +
				'}';
	}
}
