package ownradio.service;

import ownradio.domain.History;
import ownradio.domain.TracksHistory;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса, для работы с историей прослушанных треков
 *
 * @author Alpenov Tanat
 */
public interface HistoryService {
	void save(History history, Boolean isNewHistoryRec);

	History getById(UUID uuid);

	List<TracksHistory> getTracksHistoryByDevice(UUID deviceId, Integer countRows);
}
