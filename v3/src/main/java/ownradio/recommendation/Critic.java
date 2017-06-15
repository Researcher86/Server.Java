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

	public boolean ratingContains(Rating rating) {
		return ratings.contains(rating);
	}

	/**
	 * Получить список общих оценок с указанным критиком
	 *
	 * @param critic Критик
	 * @return Список общих оценок
	 */
	public List<Rating> getEqualsRatings(Critic critic) {
		return ratings.stream().filter(critic::ratingContains).collect(Collectors.toList());
	}

	public Rating getRatingByName(String name) {
		return ratings.stream().filter(rating -> rating.getName().equals(name)).findFirst().get();
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
