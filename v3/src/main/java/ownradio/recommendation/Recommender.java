package ownradio.recommendation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс рекомендатель
 *
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class Recommender {
	private List<Critic> criticList;
	private final CollaborativeFiltering filteringInterface;

	public Recommender(List<Critic> criticList, CollaborativeFiltering filteringInterface) {
		this.criticList = criticList;
		this.filteringInterface = filteringInterface;
	}

	public Critic getCriticByName(String name) {
		return this.criticList.stream().filter(critic -> critic.equals(new Critic(name))).findFirst().get();
	}

	/**
	 * Возвращает список наилучших соответствий для критика
	 *
	 * @param critic Критик
	 * @return Список коэффициентов сходства вкусов
	 */
	public List<Ratio> topMatches(Critic critic) {
		return this.criticList.stream()
				.filter(c1 -> !c1.equals(critic))
				.map(c1 -> new Ratio(c1.getName(), filteringInterface.similarity(critic, c1)))
				.sorted(Comparator.comparing(Ratio::getPoint).reversed())
				.collect(Collectors.toList());
	}


	/**
	 * Получить рекомендации для заданного критика, пользуясь взвешенным средним оценок, данных всеми остальными критиками
	 *
	 * @param critic Критик
	 * @return Список рекомендаций
	 */
	public List<Ratio> recommendedTo(Critic critic) {
		Map<Rating, Double> totals = new HashMap<>();
		Map<Rating, Double> simSums = new HashMap<>();

		this.criticList.stream()
				.filter(other -> !other.equals(critic)) // сравнивать критика с собой же, не нужно
				.forEach(other -> {
					double sim = filteringInterface.similarity(critic, other);
					// игнорировать нулевые и отрицательные оценки
					if (sim > 0) {
						other.getRatings().stream()
								// оценивать только то, что критик еще не смотрел
								.filter(rating -> !critic.ratingContains(rating) || critic.getRatingByName(rating.getName()).getPoint() == 0)
								.forEach(rating -> {
									// Коэффициент подобия * Оценка
									totals.put(rating, totals.getOrDefault(rating, 0.0) + other.getRatingByName(rating.getName()).getPoint() * sim);
									// Сумма коэффициентов подобия
									simSums.put(rating, simSums.getOrDefault(rating, 0.0) + sim);
								});
					}
				});

		return totals.entrySet().stream()
				.map(entry -> new Ratio(entry.getKey().getName(), entry.getValue() / simSums.get(entry.getKey())))
				.sorted(Comparator.comparing(Ratio::getPoint).reversed())
				.collect(Collectors.toList());
	}

}
