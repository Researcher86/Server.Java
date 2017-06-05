package ownradio.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Tanat on 005 05.06.17.
 */
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryServiceTest {
	@Autowired
	private HistoryService historyService;

	@Test
	public void getTracksHistoryByDevice() throws Exception {
		historyService.getTracksHistoryByDevice(UUID.fromString("00000000-0000-0000-0000-000000000002"), null);
	}

}