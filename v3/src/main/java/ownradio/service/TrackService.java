package ownradio.service;

import org.springframework.web.multipart.MultipartFile;
import ownradio.domain.NextTrack;
import ownradio.domain.Track;

import java.util.UUID;

/**
 * Интерфейс сервиса, для работы с треками
 *
 * @author Alpenov Tanat
 */
public interface TrackService {

	Track getById(UUID id);

	UUID getNextTrackId(UUID deviceId);

	NextTrack getNextTrackIdV2(UUID deviceId);

	void save(Track track, MultipartFile file);

	void setTrackInfo(UUID trackid);
}
