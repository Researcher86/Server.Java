package ownradio.recommendation;

/**
 * Расчет суммы произведения схожести интересов
 *
 * @author Tanat
 * @version 1.0
 * @since 15.06.17.
 */
public class SimpleCalculation implements Calculation {
	@Override
	public double similarity(Critic с1, Critic с2) {
		// Если нет ни одной общей оценки, вернуть 0
		if (с1.getEqualsRatings(с2).size() == 0) {
			return 0;
		}

		// Сложить произведение рейтингов
		return с1.getEqualsRatings(с2).stream()
				.mapToDouble(r -> r.getPoint() * с2.getRatingByName(r.getName()).getPoint())
				.sum();
	}
}
