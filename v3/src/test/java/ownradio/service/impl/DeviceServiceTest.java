package ownradio.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ownradio.service.DeviceService;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Tanat on 005 05.06.17.
 */
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceTest {
	public static final UUID EMPTY_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

	@Autowired
	private DeviceService deviceService;

	@Test
	public void getUserDevice() throws Exception {
		deviceService.getUserDevices(EMPTY_UUID);
	}

	@Test
	public void getLastDevices() throws Exception {
		deviceService.getLastDevices();
	}

	@Test
	public void registerDevice() throws Exception {
		deviceService.registerDevice(EMPTY_UUID, null);
	}

}