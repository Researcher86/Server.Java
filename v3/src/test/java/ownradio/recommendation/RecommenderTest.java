package ownradio.recommendation;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Тестируем класс рекомендаций
 *
 * @author Tanat
 * @version 1.0 14.06.17.
 */
public class RecommenderTest {
	private List<Critic> criticList;
	private EuclideanDistance euclideanDistance;
	private CoefficientPearson correlationPearson;
	private Recommender recommender;

	@Before
	public void setUp() throws Exception {
		criticList = SimpleData.create();
		euclideanDistance = new EuclideanDistance();
		correlationPearson = new CoefficientPearson();
	}

	@Test
	public void topMatches() throws Exception {
		recommender = new Recommender(criticList, correlationPearson);
		Critic toby = recommender.getCriticByName("Toby");
		recommender.topMatches(toby).forEach(System.out::println);
	}

	@Test
	public void getRecommendationsEuclideanDistance() throws Exception {
		recommender = new Recommender(criticList, euclideanDistance);
		Critic toby = recommender.getCriticByName("Toby");
		recommender.recommendedTo(toby).forEach(System.out::println);
	}
	@Test
	public void getRecommendationsCorrelationPearson() throws Exception {
		recommender = new Recommender(criticList, correlationPearson);
		Critic toby = recommender.getCriticByName("Toby");
		recommender.recommendedTo(toby).forEach(System.out::println);
	}

}