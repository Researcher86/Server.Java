package ownradio.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ownradio.domain.Track;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс репозитория, для хранения треков
 *
 * @author Alpenov Tanat
 */
public interface TrackRepository extends JpaRepository<Track, UUID> {
//	@Query(value = "select CAST(getnexttrackid(?1) AS VARCHAR)", nativeQuery = true)
	@Query(value = "select t from Track t, Rating r where t = r.track AND r.user.recid = ?1 " +
			" and r.ratingsum >= 0" +
			" ORDER BY random()")
	List<Track> getNextTrackId(UUID deviceId, Pageable pageable);

//	@Query(value = "select * from getnexttrack_v2(?1)", nativeQuery = true)
//	List<Object[]> getNextTrackV2(UUID deviceId);

	@Query(value = "select * from getnexttrack_v2(?1)", nativeQuery = true)
	List<String[]> getNextTrackV2(UUID deviceId);
}
