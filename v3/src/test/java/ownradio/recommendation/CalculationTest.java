package ownradio.recommendation;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Tanat
 * @version 1.1
 * @since 14.06.17.
 */
public class CalculationTest {

	private List<Critic> criticList;

	private Critic lisa;
	private Critic gene;

	private Critic findPersonByName(String name) {
		return criticList.stream().filter(c -> c.equals(new Critic(name))).findFirst().get();
	}

	@Before
	public void setUp() throws Exception {
		criticList = TestData.create();
		lisa = findPersonByName("Lisa Rose");
		gene = findPersonByName("Gene Seymour");
	}

	@Test
	public void euclideanDistance() throws Exception {
		assertEquals(0.148148148148, new EuclideanDistance().similarity(lisa, gene), 0.001);
	}

	@Test
	public void correlationPearson() throws Exception {
		assertEquals(0.396059017191, new CoefficientPearson().similarity(lisa, gene), 0.001);
	}

	@Test
	public void myCalcRatio() throws Exception {
		Critic c1 = new Critic("User1");
		c1.addRating(new Rating("Track1", 1));
		c1.addRating(new Rating("Track2", 1));
		c1.addRating(new Rating("Track3", 3));

		Critic c2 = new Critic("User2");
		c2.addRating(new Rating("Track1", 2));
		c2.addRating(new Rating("Track2", -1));
		c2.addRating(new Rating("Track3", 1));

		assertEquals(4, new SimpleCalculation().similarity(c1, c2), 0.001);
	}
}