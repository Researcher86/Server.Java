package ownradio.recommendation;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания тестовых данных
 *
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class TestData {
	public static List<Critic> create() {
		List<Critic> criticList = new ArrayList<>();
		Critic critic;
		critic = new Critic("Lisa Rose");
		critic.addRating(new Rating("Lady in the Water", 2.5));
		critic.addRating(new Rating("Snakes on a Plane", 3.5));
		critic.addRating(new Rating("Just My Luck", 3.0));
		critic.addRating(new Rating("Superman Returns", 3.5));
		critic.addRating(new Rating("You, Me and Dupree", 2.5));
		critic.addRating(new Rating("The Night Listener", 3.0));
		criticList.add(critic);

		critic = new Critic("Gene Seymour");
		critic.addRating(new Rating("Lady in the Water", 3.0));
		critic.addRating(new Rating("Snakes on a Plane", 3.5));
		critic.addRating(new Rating("Just My Luck", 1.5));
		critic.addRating(new Rating("Superman Returns", 5.0));
		critic.addRating(new Rating("The Night Listener", 3.0));
		critic.addRating(new Rating("You, Me and Dupree", 3.5));
		criticList.add(critic);

		critic = new Critic("Michael Phillips");
		critic.addRating(new Rating("Lady in the Water", 2.5));
		critic.addRating(new Rating("Snakes on a Plane", 3.0));
		critic.addRating(new Rating("Superman Returns", 3.5));
		critic.addRating(new Rating("The Night Listener", 4.0));
		criticList.add(critic);

		critic = new Critic("Claudia Puig");
		critic.addRating(new Rating("Snakes on a Plane", 3.5));
		critic.addRating(new Rating("Just My Luck", 3.0));
		critic.addRating(new Rating("The Night Listener", 4.5));
		critic.addRating(new Rating("Superman Returns", 4.0));
		critic.addRating(new Rating("You, Me and Dupree", 2.5));
		criticList.add(critic);

		critic = new Critic("Mick LaSalle");
		critic.addRating(new Rating("Lady in the Water", 3.0));
		critic.addRating(new Rating("Snakes on a Plane", 4.0));
		critic.addRating(new Rating("Just My Luck", 2.0));
		critic.addRating(new Rating("Superman Returns", 3.0));
		critic.addRating(new Rating("The Night Listener", 3.0));
		critic.addRating(new Rating("You, Me and Dupree", 2.0));
		criticList.add(critic);

		critic = new Critic("Jack Matthews");
		critic.addRating(new Rating("Lady in the Water", 3.0));
		critic.addRating(new Rating("Snakes on a Plane", 4.0));
		critic.addRating(new Rating("The Night Listener", 3.0));
		critic.addRating(new Rating("Superman Returns", 5.0));
		critic.addRating(new Rating("You, Me and Dupree", 3.5));
		criticList.add(critic);

		critic = new Critic("Toby");
		critic.addRating(new Rating("Snakes on a Plane", 4.5));
		critic.addRating(new Rating("You, Me and Dupree", 1.0));
		critic.addRating(new Rating("Superman Returns", 4.0));
		criticList.add(critic);

		return criticList;
	}
}
