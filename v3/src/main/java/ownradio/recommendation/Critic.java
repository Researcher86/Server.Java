package ownradio.recommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Класс хранит информацию о критиках и их оценках
 *
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class Critic {
	private String name;
	private List<Rating> ratings;

	public Critic(String name) {
		this.name = name;
		this.ratings = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	/**
	 * Добавить новый рейтинг/оценку текущему критику
	 *
	 * @param rating рейтинг
	 */
	public void addRating(Rating rating) {
		ratings.add(rating);
	}

	/**
	 * Получить список общих оценок с указанным критиком
	 *
	 * @param critic критик
	 * @return список общих оценок
	 */
	public Stream<Rating> getEqualsRatings(Critic critic) {
		return ratings.stream().filter(rating -> critic.ratings.contains(rating));
	}

	/**
	 * Возвращаем оценку для рейтинга по его названию
	 *
	 * @param name наименование рейтинга
	 * @return оценка
	 */
	public double getRatingPoint(String name) {
		return ratings.stream().filter(rating -> rating.getName().equals(name)).findFirst().get().getPoint();
	}

	/**
	 * Поиск того что переданный критик не смотрел
	 *
	 * @param critic критик
	 * @return список чего не смотрел
	 */
	public Stream<Rating> notLookingTo(Critic critic) {
		return ratings.stream().filter(rating -> !critic.ratings.contains(rating) || critic.getRatingPoint(rating.getName()) == 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Critic critic = (Critic) o;

		return name != null ? name.equals(critic.name) : critic.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
