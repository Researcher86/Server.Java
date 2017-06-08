package ownradio.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ownradio.domain.Device;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс репозитория, для хранения девайсов
 *
 * @author Alpenov Tanat
 */
public interface DeviceRepository extends JpaRepository<Device, UUID> {
	@Query(value = "select d from Device d where d.user.recid = ?1")
	List<Device> findByUser(UUID userId);

	@Query(value = "select d from DownloadTrack dt, Device d where dt.device = d order by dt.reccreated desc")
	List<Device> getLastDevices(Pageable pageable);
}
