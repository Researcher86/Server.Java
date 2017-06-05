package ownradio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * Сущность для хранения информации об отданных пользователю треках и истории по ним
 *
 * Created by a.polunina on 07.04.2017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TracksHistory {
	private DownloadTrack downloadTrack;
	private History history;
}
