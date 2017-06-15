package ownradio.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	public void addRating(Rating rating) {
		ratings.add(rating);
	}

	public List<Rating> getRatings() {
		return Collections.unmodifiableList(ratings);
	}

	/**
	 * Проверяем оценивал ли критик
	 *
	 * @param rating рейтинг
	 * @return true если оценивал
	 */
	public boolean ratingContains(Rating rating) {
		return ratings.contains(rating);
	}

	/**
	 * Получить список общих оценок с указанным критиком
	 *
	 * @param critic критик
	 * @return список общих оценок
	 */
	public List<Rating> getEqualsRatings(Critic critic) {
		return ratings.stream().filter(critic::ratingContains).collect(Collectors.toList());
	}

	/**
	 * Возвращаем рейтинг по его названию
	 *
	 * @param name наименование рейтинга
	 * @return рейтинг
	 */
	public Rating getRatingByName(String name) {
		return ratings.stream().filter(rating -> rating.getName().equals(name)).findFirst().get();
	}

	/**
	 * Возвращаем оценку для рейтинга по его названию
	 *
	 * @param name наименование рейтинга
	 * @return оценка
	 */
	public double getRatingPointByName(String name) {
		return getRatingByName(name).getPoint();
	}

	/**
	 * Проверка того что критик смотрел
	 *
	 * @param rating рейтинг
	 * @return true если смотрел
	 */
	public boolean isLooking(Rating rating) {
		return ratingContains(rating) && getRatingPointByName(rating.getName()) != 0;
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
