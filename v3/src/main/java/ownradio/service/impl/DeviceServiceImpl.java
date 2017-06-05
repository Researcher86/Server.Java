package ownradio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ownradio.domain.Device;
import ownradio.domain.User;
import ownradio.repository.DeviceRepository;
import ownradio.repository.UserRepository;
import ownradio.service.DeviceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

	private final DeviceRepository deviceRepository;
	private final UserRepository userRepository;

	@Autowired
	public DeviceServiceImpl(DeviceRepository deviceRepository, UserRepository userRepository) {
		this.deviceRepository = deviceRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void save(Device device) {
		deviceRepository.saveAndFlush(device);
	}

	@Override
	public Device getById(UUID uuid) {
		return deviceRepository.findOne(uuid);
	}

	@Override
	public List<Device> getUserDevices(UUID uuid) {
		return deviceRepository.findByUser(uuid);
	}

	@Override
	public List<Device> getLastDevices() {
		return deviceRepository.getLastDevices(new PageRequest(0, 100));
	}

	@Transactional
	@Override
	public void registerDevice(UUID id, String name) {
		String deviceName = Optional.ofNullable(name).orElse("New unknown device");
		Device device = deviceRepository.findOne(id);

		// Если устройства еще нет в БД
		if (device == null) {
			User user = userRepository.findOne(id);
			if (user == null) { // Если пользователя нет, создадим его
				User saveUser = new User(deviceName);
				saveUser.setRecid(id);

				user = userRepository.save(saveUser);
			}

			// Добавляем новое устройство
			device = new Device(user, deviceName);
			device.setRecid(id);
			deviceRepository.save(device);
		}

	}
}
