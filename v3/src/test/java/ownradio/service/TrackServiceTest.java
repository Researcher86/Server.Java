package ownradio.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ownradio.domain.Device;
import ownradio.domain.Track;
import ownradio.domain.User;
import ownradio.repository.TrackRepository;
import ownradio.service.impl.TrackServiceImpl;

import java.io.File;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static ownradio.util.ResourceUtil.UPLOAD_DIR;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackServiceTest {
	@Autowired
	private TrackService trackService;

	private UUID userId = UUID.randomUUID();
	private UUID trackId = UUID.randomUUID();
	private UUID deviceId = UUID.fromString("7fcd3f5c-c512-4adb-b6b2-4dbf5e9dfd89");
	private Track expected;

	@Before
	public void setUp() throws Exception {
		expected = new Track();
		expected.setRecid(trackId);
		expected.setLocaldevicepathupload("123");

		User user = new User();
		user.setRecid(userId);

		Device device = new Device(user, "123");
		device.setRecid(deviceId);
		expected.setDevice(device);
	}

	@After
	public void tearDown() throws Exception {
		FileUtils.deleteDirectory(new File(UPLOAD_DIR));
	}

	@Test
	public void getNextTrackId() throws Exception {
		UUID actual = trackService.getNextTrackId(deviceId);
	}

	@Test
	public void save() throws Exception {
		MockMultipartFile correctFile = new MockMultipartFile("file", "test.mp3", "text/plain", "Text".getBytes());

		trackService.save(expected, correctFile);

		assertThat(new File(expected.getPath()).exists(), is(true));
	}
}