package ownradio.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ownradio.domain.History;
import ownradio.domain.TracksHistory;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс репозитория, для хранения истории прослушанных треков
 *
 * @author Alpenov Tanat
 */
public interface HistoryRepository extends JpaRepository<History, UUID> {

//	@Query(value = "select * from getTracksHistoryByDevice(?1, ?2)", nativeQuery = true)
//	List<Object[]> getTracksHistoryByDevice(UUID deviceId, Integer countRows);

//	@Query(value = "select new ownradio.domain.TracksHistory(dt, h) " +
//			"from DownloadTrack dt left join History h where dt.device = h.device and dt.track = h.track and h.device.recid = ?1 " +
//			"order by dt.reccreated desc, h.reccreated desc, h.lastListen desc"
//	)
	List<TracksHistory> getTracksHistoryByDevice(UUID deviceId, Pageable pageable);

//	@Query(value = "select dt, h " +
//			"from DownloadTrack dt left join History h where dt.device = h.device and dt.track = h.track and h.device.recid = ?1" +
//			"order by dt.reccreated desc, h.reccreated desc, h.lastListen desc"
//	)
//	List<Object[]> getTracksHistoryByDevice2(UUID deviceId, Pageable pageable);
}
