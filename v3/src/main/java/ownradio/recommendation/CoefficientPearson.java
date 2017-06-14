package ownradio.recommendation;

/**
 * Коэффициент корреляции Пирсона
 *
 * @author Tanat
 * @version 1.0 14.06.17.
 */
public class CoefficientPearson implements CollaborativeFiltering {

	@Override
	public double similarity(Critic с1, Critic с2) {
		// Получить список предметов, оцененных обоими
		long count = с1.getRatings().stream().filter(rating -> с2.getRatings().contains(rating)).count();

		// Если нет ни одной общей оценки, вернуть 0
		if (count == 0) {
			return 0;
		}

		// Вычислить сумму всех предпочтений
		double sum1 = с1.getRatings().stream()
				.filter(rating -> с2.getRatings().contains(rating))
				.mapToDouble(Rating::getPoint).sum();
		double sum2 = с2.getRatings().stream()
				.filter(rating -> с1.getRatings().contains(rating))
				.mapToDouble(Rating::getPoint).sum();

		// Вычислить сумму квадратов
		double sum1Sq = с1.getRatings().stream()
				.filter(rating -> с2.getRatings().contains(rating))
				.mapToDouble(rating -> Math.pow(rating.getPoint(), 2))
				.sum();
		double sum2Sq = с2.getRatings().stream()
				.filter(rating -> с1.getRatings().contains(rating))
				.mapToDouble(rating -> Math.pow(rating.getPoint(), 2))
				.sum();

		// Вычислить сумму произведений
		double pSum = с1.getRatings().stream()
				.filter(rating -> с2.getRatings().contains(rating))
				.mapToDouble(rating -> rating.getPoint() * с2.getRatingByName(rating.getName()).getPoint())
				.sum();

		// Вычислить коэффициент Пирсона
		double num = pSum - (sum1 * sum2 / count);
		double den = Math.sqrt((sum1Sq - Math.pow(sum1, 2) / count) * (sum2Sq - Math.pow(sum2, 2) / count));

		return den == 0 ? 0 : num / den;
	}
}
