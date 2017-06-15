package ownradio.recommendation;

/**
 * Интерфейс расчета суммы схожести интересов
 *
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public interface Calculation {

	/**
	 * Возвращает оценку подобия между двумя критиками
	 *
	 * @param с1 исходный критик
	 * @param с2 сравниваемы критик
	 * @return оценка подобия
	 */
	double similarity(Critic с1, Critic с2);
}
