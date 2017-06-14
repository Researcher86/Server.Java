package ownradio.recommendation;

/**
 * Оценка по евклидову расстоянию
 *
 * @author Tanat
 * @version 1.0 14.06.17.
 */
public class EuclideanDistance implements CollaborativeFiltering {

	@Override
	public double similarity(Critic с1, Critic с2) {
		// Получить список предметов, оцененных обоими
		long count = с1.getRatings().stream().filter(rating -> с2.getRatings().contains(rating)).count();

		// Если нет ни одной общей оценки, вернуть 0
		if (count == 0) {
			return 0;
		}

		// Сложить квадраты разностей
		double sumOfSquares = с1.getRatings().stream()
				.filter(rating -> с2.getRatings().contains(rating))
				.mapToDouble(r1 -> Math.pow(r1.getPoint() - с2.getRatingByName(r1.getName()).getPoint(), 2))
				.sum();

		return 1 / (1 + sumOfSquares);
	}
}
