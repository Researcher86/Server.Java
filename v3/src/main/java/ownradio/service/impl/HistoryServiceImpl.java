package ownradio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ownradio.domain.History;
import ownradio.domain.Rating;
import ownradio.domain.TracksHistory;
import ownradio.repository.DownloadTrackRepository;
import ownradio.repository.HistoryRepository;
import ownradio.repository.RatingRepository;
import ownradio.repository.RatioRepository;
import ownradio.service.HistoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HistoryServiceImpl implements HistoryService {

	private final HistoryRepository historyRepository;
	private final RatingRepository ratingRepository;
	private final RatioRepository ratioRepository;
	private final DownloadTrackRepository downloadTrackRepository;

	@Autowired
	public HistoryServiceImpl(HistoryRepository historyRepository, RatingRepository ratingRepository, RatioRepository ratioRepository, DownloadTrackRepository downloadTrackRepository) {
		this.historyRepository = historyRepository;
		this.ratingRepository = ratingRepository;
		this.ratioRepository = ratioRepository;
		this.downloadTrackRepository = downloadTrackRepository;
	}

	@Override
	public History getById(UUID uuid) {
		return historyRepository.findOne(uuid);
	}

	@Transactional
	@Override
	public void save(History history, Boolean isNewHistoryRec) {
		historyRepository.saveAndFlush(history);

		if(isNewHistoryRec) {
			Rating rating = ratingRepository.findByUserAndTrack(history.getDevice().getUser(), history.getTrack());
			if (rating != null) {
				int ratingsum = rating.getRatingsum() + history.getIsListen();
				rating.setLastlisten(history.getLastListen());
				rating.setRatingsum(ratingsum);
				ratingRepository.saveAndFlush(rating);
			} else {
				rating = new Rating();
				rating.setUser(history.getDevice().getUser());
				rating.setTrack(history.getTrack());
				rating.setLastlisten(history.getLastListen());
				rating.setRatingsum(history.getIsListen());
				ratingRepository.saveAndFlush(rating);
			}

			try {
				ratioRepository.updateRatios(history.getDevice().getRecid());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	@Transactional
	public List<TracksHistory> getTracksHistoryByDevice(UUID deviceId, Integer countRows){
		List<TracksHistory> tracksHistories = new ArrayList<TracksHistory>();
		List<Object[]> objects = historyRepository.getTracksHistoryByDevice(deviceId, countRows);
		if (objects != null) {
			for (int i = 0; i < objects.size(); i++) {
				TracksHistory tracksHistory = new TracksHistory();
				tracksHistory.setDownloadTrack(downloadTrackRepository.findOne(UUID.fromString((String) objects.get(i)[0])));
				if((String) objects.get(i)[1] != null) tracksHistory.setHistory(historyRepository.findOne(UUID.fromString((String) objects.get(i)[1])));

				tracksHistories.add(tracksHistory);
			}
		} else {
			return null;
		}
		return tracksHistories;

//		historyRepository.getTracksHistoryByDevice(deviceId, null);

//		if (countRows != null) {
//			return historyRepository.getTracksHistoryByDevice(deviceId, new PageRequest(0, countRows));
//		} else {
//			return historyRepository.getTracksHistoryByDevice(deviceId, null);
//		}
	}
}
