package ownradio.recommendation;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

/**
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class RecommenderTest {
	private List<Critic> criticList;
	private EuclideanDistance euclideanDistance;
	private CoefficientPearson correlationPearson;
	private SimpleCalculation simpleCalculation;
	private Recommender recommender;
	private Critic toby;

	@Before
	public void setUp() throws Exception {
		criticList = TestData.create();
		euclideanDistance = new EuclideanDistance();
		correlationPearson = new CoefficientPearson();
		simpleCalculation = new SimpleCalculation();
		toby = criticList.stream().filter(critic -> critic.equals(new Critic("Toby"))).findFirst().get();
	}

	@Test
	public void topMatches() throws Exception {
		recommender = new Recommender(criticList, correlationPearson);

		List<Ratio> ratios = recommender.topMatches(toby);

		assertThat(ratios, hasItems(
				new Ratio("Lisa Rose", 0.9912407071619299),
				new Ratio("Mick LaSalle", 0.9244734516419049),
				new Ratio("Claudia Puig", 0.8934051474415647),
				new Ratio("Jack Matthews", 0.66284898035987),
				new Ratio("Gene Seymour", 0.38124642583151164),
				new Ratio("Michael Phillips", -1.0)
		));
	}

	@Test
	public void getRecommendationsEuclideanDistance() throws Exception {
		recommender = new Recommender(criticList, euclideanDistance);

		List<Ratio> ratios = recommender.recommendedTo(toby);

		assertThat(ratios, hasItems(
				new Ratio("The Night Listener", 3.5002478401415877),
				new Ratio("Lady in the Water", 2.7561242939959363),
				new Ratio("Just My Luck", 2.461988486074374)
		));
	}

	@Test
	public void getRecommendationsCorrelationPearson() throws Exception {
		recommender = new Recommender(criticList, correlationPearson);

		List<Ratio> ratios = recommender.recommendedTo(toby);

		assertThat(ratios, hasItems(
				new Ratio("The Night Listener", 3.3477895267131017),
				new Ratio("Lady in the Water", 2.8325499182641614),
				new Ratio("Just My Luck", 2.530980703765565)
		));
	}

	@Test
	public void getRecommendationsSimpleCalculation() throws Exception {
		Critic c1 = new Critic("User1");
		c1.addRating(new Rating("Track1", 1));
		c1.addRating(new Rating("Track2", 1));
		c1.addRating(new Rating("Track3", 3));
		c1.addRating(new Rating("Track4", 3));
		c1.addRating(new Rating("Track6", -2));

		Critic c2 = new Critic("User2");
		c2.addRating(new Rating("Track1", 1));
		c2.addRating(new Rating("Track2", 1));
		c2.addRating(new Rating("Track3", 1));
		c2.addRating(new Rating("Track4", 3));
		c2.addRating(new Rating("Track5", 3));

		recommender = new Recommender(Arrays.asList(c1, c2), simpleCalculation);

		List<Ratio> ratios = recommender.recommendedTo(c1);

		assertThat(ratios, hasItems(
				new Ratio("Track5", 3)
		));
	}

}