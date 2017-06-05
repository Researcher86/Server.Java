package ownradio.repository;

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
	@Query(value = "select getnexttrackid_string(?1)", nativeQuery = true)
	UUID getNextTrackId(UUID deviceId);

	@Query(value = "select * from getnexttrack_v2(?1)", nativeQuery = true)
//	@Query(value = "select * from getnexttrack(?1)", nativeQuery = true)
	List<Object[]> getNextTrackV2(UUID deviceId);

	@Query(value = "select registertrack(?1, ?2, ?3, ?4)", nativeQuery = true)
	boolean registerTrack(UUID trackId, String localDevicePathUpload, String path, UUID deviceId);
}
