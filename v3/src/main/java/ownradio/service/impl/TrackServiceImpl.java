package ownradio.service.impl;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ownradio.domain.NextTrack;
import ownradio.domain.Track;
import ownradio.recommendation.*;
import ownradio.repository.RatingRepository;
import ownradio.repository.RatioRepository;
import ownradio.repository.TrackRepository;
import ownradio.service.DeviceService;
import ownradio.service.TrackService;
import ownradio.util.DecodeUtil;
import ownradio.util.ResourceUtil;

import java.util.*;

@Service
public class TrackServiceImpl implements TrackService {

	private final TrackRepository trackRepository;
	private final DeviceService deviceService;
	private final RatingRepository ratingRepository;
	private final RatioRepository ratioRepository;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository, DeviceService deviceService, RatingRepository ratingRepository, RatioRepository ratioRepository) {
		this.trackRepository = trackRepository;
		this.deviceService = deviceService;
		this.ratingRepository = ratingRepository;
		this.ratioRepository = ratioRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Track getById(UUID id) {
		return trackRepository.findOne(id);
	}

	@Override
	@Transactional
	public UUID getNextTrackId(UUID deviceId) {
		deviceService.registerDevice(deviceId, null);
		List<Track> tracks = trackRepository.getNextTrackId(deviceId, new PageRequest(0, 1));
		return !tracks.isEmpty() ? tracks.get(0).getRecid() : null;
	}

	@Override
	@Transactional
	public NextTrack getNextTrackIdV2(UUID deviceId) {
//		NextTrack nextTrack = new NextTrack();
//		List<Object[]> objects = trackRepository.getNextTrackV2(deviceId);
//		if(objects != null) {
//			nextTrack.setTrackid(UUID.fromString((String) objects.get(0)[0]));
//			nextTrack.setMethodid((Integer) objects.get(0)[1]);
//			if(objects.get(0)[2] != null) nextTrack.setUseridrecommended(UUID.fromString((String) objects.get(0)[2]));
//			if(objects.get(0)[3] != null) nextTrack.setTxtrecommendedinfo((String) objects.get(0)[3]);
//			if(objects.get(0)[4] != null) nextTrack.setTimeexecute((String) objects.get(0)[4]);
//			return nextTrack;
//		}else{
		return null;
//		}
	}

	@Override
	@Transactional
	public void save(Track track, MultipartFile file) {
		deviceService.registerDevice(track.getDevice().getRecid(), "New device recname");
		trackRepository.save(track);

		String dirName = track.getDevice().getUser().getRecid().toString();
		String fileName = track.getRecid() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
		String filePath = ResourceUtil.save(dirName, fileName, file);

		track.setPath(filePath);
	}

	@Override
	@Transactional
	public void setTrackInfo(UUID trackid) {
		String artist = null;
		String title = null;
		boolean artistFlag = false;
		boolean titleFlag = false;

		if (trackid != null) {
			try {
				Track track = trackRepository.findOne(trackid);
				Mp3File mp3File = new Mp3File(track.getPath());
				track.setLength((int) mp3File.getLengthInSeconds());//duration track
				track.setSize((int) mp3File.getLength() / 1024);//size in kilobytes

				if (mp3File.hasId3v2Tag()) {
					ID3v2 id3v2Tag2 = mp3File.getId3v2Tag();
					title = DecodeUtil.Decode(id3v2Tag2.getTitle());
					artist = DecodeUtil.Decode(id3v2Tag2.getArtist());
				}
				if (mp3File.hasId3v1Tag()) {
					ID3v1 id3v1Tag1 = mp3File.getId3v1Tag();
					if (title == null || title.equals("null") || title.isEmpty())
						title = DecodeUtil.Decode(id3v1Tag1.getTitle());
					if (artist == null || artist.equals("null") || artist.isEmpty())
						artist = DecodeUtil.Decode(id3v1Tag1.getArtist());
				}

				if (title != null && !title.equals("null") && !title.isEmpty()) {
					track.setRecname(title.replaceAll("\u0000", ""));
					titleFlag = true;
				}
				if (artist != null && !artist.equals("null") && !artist.isEmpty()) {
					track.setArtist(artist.replaceAll("\u0000", ""));
					artistFlag = true;
				}

				if (artistFlag && titleFlag)
					track.setIsfilledinfo(1);
				trackRepository.saveAndFlush(track);
			} catch (Exception ex) {
//				ex.printStackTrace();
			}
		}
	}

	@Override
	public Track getRecommendedTracks(UUID userId) {
		List<Object[]> ratings = ratingRepository.getRatingTracks(userId);

		Map<String, Critic> critics = new HashMap<>();

		for (Object[] rating : ratings) {
			if (critics.containsKey(rating[0].toString())) {
				critics.get(rating[0].toString()).addRating(new Rating(rating[1].toString(), Double.parseDouble(rating[2].toString())));
			} else {
				Critic critic = new Critic(rating[0].toString());
				critic.addRating(new Rating(rating[1].toString(), Double.parseDouble(rating[2].toString())));
				critics.put(critic.getName(), critic);
			}
		}

		List<Critic> criticList = new ArrayList<>(critics.values());
		Recommender recommender = new Recommender(criticList, new EuclideanDistance());
		Critic critic2 = criticList.stream().filter(critic -> critic.equals(new Critic(userId.toString()))).findFirst().get();
		List<Ratio> ratios = recommender.recommendedTo(critic2);

		if (ratings.isEmpty()) {
			return null;
		} else {
			Track track = new Track();
			track.setRecid(UUID.fromString(ratios.get(0).getName()));
			return track;
		}

	}

}
