package ownradio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ownradio.domain.DownloadTrack;
import ownradio.domain.TracksHistory;
import ownradio.repository.DownloadTrackRepository;
import ownradio.repository.HistoryRepository;
import ownradio.service.DownloadTrackService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DownloadTrackServiceImpl implements DownloadTrackService {

	private final DownloadTrackRepository downloadTrackRepository;
	private HistoryRepository historyRepository;

	@Autowired
	public DownloadTrackServiceImpl(DownloadTrackRepository downloadTrackRepository, HistoryRepository historyRepository) {
		this.downloadTrackRepository = downloadTrackRepository;
		this.historyRepository = historyRepository;
	}

	@Override
	public void save(DownloadTrack downloadTrack) {
		downloadTrackRepository.saveAndFlush(downloadTrack);
	}

	@Override
	@Transactional
	public List<DownloadTrack> getLastTracksByDevice(UUID deviceId, Integer countTracks) {
		if (countTracks != null) {
			return downloadTrackRepository.getLastTracksByDevice(deviceId, new PageRequest(0, countTracks));
		} else {
			return downloadTrackRepository.getLastTracksByDevice(deviceId, null);
		}
	}
}
