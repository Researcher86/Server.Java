package ownradio.recommendation;

/**
 * Класс для хранения рейтингов
 *
 * @author Tanat
 * @version 1.0 14.06.17.
 */
public class Rating {
	private String name;
	private double point;

	public Rating(String name, double point) {
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

		Rating rating = (Rating) o;

		return name != null ? name.equals(rating.name) : rating.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
