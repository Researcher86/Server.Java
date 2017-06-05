package ownradio.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ownradio.domain.Device;
import ownradio.domain.DownloadTrack;
import ownradio.domain.Track;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс репозитория, для хранения истории скаченных треков
 *
 * @author Alpenov Tanat
 */
public interface DownloadTrackRepository extends JpaRepository<DownloadTrack, UUID> {

	@Query(value = "select dt from DownloadTrack dt where dt.device.recid = ?1 order by dt.reccreated desc")
	List<DownloadTrack> getLastTracksByDevice(UUID deviceid, Pageable pageable);
}
