package ownradio.web.rest.v4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ownradio.service.DeviceService;

import java.util.UUID;

/**
 * Created by a.polunina on 03.04.2017.
 */
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/v4/devices")
public class DeviceController {
	private final DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@RequestMapping(value = "/{deviceId}/registerdevice", method = RequestMethod.GET)
	public ResponseEntity<?> registerDevice(@PathVariable UUID deviceId) {
		return getResponseEntityRegisterDevice(deviceId, null);
	}

	@RequestMapping(value = "/{deviceId}/{deviceName}/registerdevice", method = RequestMethod.GET)
	public ResponseEntity<?> registerDevice(@PathVariable UUID deviceId, @PathVariable String deviceName) {
		return getResponseEntityRegisterDevice(deviceId, deviceName);
	}

	private ResponseEntity<?> getResponseEntityRegisterDevice(UUID deviceId, String deviceName) {
		deviceService.registerDevice(deviceId, deviceName);
		return new ResponseEntity(HttpStatus.OK);
	}
}
