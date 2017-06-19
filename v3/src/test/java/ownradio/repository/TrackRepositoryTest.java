package ownradio.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ownradio.domain.Device;
import ownradio.domain.Track;
import ownradio.domain.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TrackRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TrackRepository trackRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	private User user;
	private Device device;

	@Before
	public void setUp() throws Exception {
//		user = userRepository.save(new User());
//		device = deviceRepository.save(new Device(user, "123"));
//		trackRepository.save(new Track("1", device, "1", 0, "", 0, null, null, null, 1));
//		trackRepository.save(new Track("2", device, "1", 0, "", 0, null, null, null, 1));
//		trackRepository.save(new Track("4", device, "1", 0, "", 0, null, null, null, 1));
	}

	@Test
	public void getNextTrackId() throws Exception {
//		Set<Track> trackSet = new HashSet<>();
//
//		for (int i = 0; i < 3; i++) {
//			List<Track> track = trackRepository.getNextTrackId(device.getRecid(), new PageRequest(0, 1));
//			trackSet.add(track.get(0));
//		}
//
//		assertTrue(trackSet.size() > 1);

		trackRepository.getNextTrackId(UUID.fromString("11111111-0000-0888-0000-000000000000"), new PageRequest(0, 1));
	}

}