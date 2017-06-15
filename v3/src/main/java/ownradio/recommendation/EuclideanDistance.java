package ownradio.recommendation;

/**
 * Расчет суммы схожести интересов по формуле "Евклидового расстояния"
 *
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class EuclideanDistance implements Calculation {

	@Override
	public double similarity(Critic с1, Critic с2) {
		// Если нет ни одной общей оценки, вернуть 0
		if (с1.getEqualsRatings(с2).size() == 0) {
			return 0;
		}

		// Сложить квадраты разностей
		double sumOfSquares = с1.getEqualsRatings(с2).stream()
				.mapToDouble(r -> Math.pow(r.getPoint() - с2.getRatingByName(r.getName()).getPoint(), 2))
				.sum();

		return 1 / (1 + sumOfSquares);
	}
}
