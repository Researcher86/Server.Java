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
public class CollaborativeFilteringTest {

	private List<Critic> criticList;
	private EuclideanDistance euclideanDistance;
	private CoefficientPearson correlationPearson;

	private Critic lisa;
	private Critic gene;

	private Critic findPersonByName(String name) {
		return criticList.stream().filter(c -> c.equals(new Critic(name))).findFirst().get();
	}

	@Before
	public void setUp() throws Exception {
		criticList = TestData.create();
		euclideanDistance = new EuclideanDistance();
		correlationPearson = new CoefficientPearson();

		lisa = findPersonByName("Lisa Rose");
		gene = findPersonByName("Gene Seymour");
	}

	@Test
	public void euclideanDistance() throws Exception {
		assertEquals(0.148148148148, euclideanDistance.similarity(lisa, gene), 0.001);
	}

	@Test
	public void correlationPearson() throws Exception {
		assertEquals(0.396059017191, correlationPearson.similarity(lisa, gene), 0.001);
	}
}