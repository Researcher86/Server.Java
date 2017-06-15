package ownradio.recommendation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс рекомендатель
 *
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class Recommender {
	private final List<Critic> criticList;
	private final Calculation calculation;

	public Recommender(List<Critic> criticList, Calculation calculation) {
		this.criticList = criticList;
		this.calculation = calculation;
	}

	/**
	 * Возвращает список наилучших соответствий для критика
	 *
	 * @param critic Критик
	 * @return Список коэффициентов сходства
	 */
	public List<Ratio> topMatches(Critic critic) {
		return criticsExcludeTo(critic)
				.map(c -> new Ratio(c.getName(), calculation.similarity(critic, c)))
				.sorted(Comparator.comparing(Ratio::getPoint).reversed())
				.collect(Collectors.toList());
	}

	/**
	 * Получить рекомендации для заданного критика, пользуясь взвешенным средним оценок, данных всеми остальными критиками
	 *
	 * @param critic критик
	 * @return список рекомендаций
	 */
	public List<Ratio> recommendedTo(Critic critic) {
		Map<Rating, Double> totals = new HashMap<>();
		Map<Rating, Double> simSums = new HashMap<>();

		criticsExcludeTo(critic)
				.forEach(other -> {
					double sim = calculation.similarity(critic, other);
					// игнорировать нулевые и отрицательные оценки
					if (sim > 0) {
						// оценивать только то, что критик еще не смотрел
						other.notLookingTo(critic).forEach(rating -> {
							// Коэффициент подобия * Оценка
							totals.put(rating, totals.getOrDefault(rating, 0.0) + other.getRatingPoint(rating.getName()) * sim);
							// Сумма коэффициентов подобия
							simSums.put(rating, simSums.getOrDefault(rating, 0.0) + sim);
						});
					}
				});

		return getRatios(totals, simSums);
	}

	private List<Ratio> getRatios(Map<Rating, Double> totals, Map<Rating, Double> simSums) {
		return totals.entrySet().stream()
				.map(entry -> new Ratio(entry.getKey().getName(), entry.getValue() / simSums.get(entry.getKey())))
				.sorted(Comparator.comparing(Ratio::getPoint).reversed())
				.collect(Collectors.toList());
	}

	private Stream<Critic> criticsExcludeTo(Critic critic) {
		return criticList.stream().filter(c -> !c.equals(critic));
	}

}
