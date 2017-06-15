package ownradio.recommendation;

/**
 * Коэффициент корреляции Пирсона
 *
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class CoefficientPearson implements CollaborativeFiltering {

	@Override
	public double similarity(Critic с1, Critic с2) {
		long count = с1.getEqualsRatings(с2).size();

		// Если нет ни одной общей оценки, вернуть 0
		if (count == 0) {
			return 0;
		}

		// Вычислить сумму всех предпочтений
		double sum1 = с1.getEqualsRatings(с2).stream().mapToDouble(Rating::getPoint).sum();
		double sum2 = с2.getEqualsRatings(с1).stream().mapToDouble(Rating::getPoint).sum();

		// Вычислить сумму квадратов
		double sum1Sq = с1.getEqualsRatings(с2).stream().mapToDouble(rating -> Math.pow(rating.getPoint(), 2)).sum();
		double sum2Sq = с2.getEqualsRatings(с1).stream().mapToDouble(rating -> Math.pow(rating.getPoint(), 2)).sum();

		// Вычислить сумму произведений
		double pSum = с1.getEqualsRatings(с2).stream().mapToDouble(r -> r.getPoint() * с2.getRatingByName(r.getName()).getPoint()).sum();

		// Вычислить коэффициент Пирсона
		double num = pSum - (sum1 * sum2 / count);
		double den = Math.sqrt((sum1Sq - Math.pow(sum1, 2) / count) * (sum2Sq - Math.pow(sum2, 2) / count));

		return den == 0 ? 0 : num / den;
	}
}
