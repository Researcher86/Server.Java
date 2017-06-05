package ownradio.service;

import ownradio.domain.Device;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса, для работы с девайсами
 *
 * @author Alpenov Tanat
 */
public interface DeviceService {
	void save(Device device);

	Device getById(UUID uuid);

	/**
	 * Метод возвращает весь список устройств, с которых пользователь заходил в систему
	 *
	 * @param userId Идентификатор пользователя
	 * @return Список пользовательских устройств
	 */
	List<Device> getUserDevices(UUID userId);

	List<Device> getLastDevices();

	/**
	 * Регистрация нового устройства
	 *
	 * @param id   Идентификатор устройства
	 * @param name Наименование
	 */
	void registerDevice(UUID id, String name);
}
