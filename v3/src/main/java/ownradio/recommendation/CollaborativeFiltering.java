package ownradio.recommendation;

/**
 * Коллаборативная фильтрация
 *
 * @author Tanat
 * @version 1.0 14.06.17.
 */
public interface CollaborativeFiltering {

	/**
	 * Возвращает оценку подобия между двумя критиками
	 *
	 * @param с1 Исходный критик
	 * @param с2 Сравниваемы критик
	 * @return Оценка подобия
	 */
	double similarity(Critic с1, Critic с2);
}
