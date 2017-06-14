package ownradio.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Сущность для хранения информации о треке
 *
 * @author Alpenov Tanat
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracks")
public class Track extends AbstractEntity {

	private String path;

	@ManyToOne
	@JoinColumn(name = "deviceid")
	private Device device;

	@Column(nullable = false)
	private String localdevicepathupload;

	private Integer length;

	private String artist;

	private Integer size;

	private Integer iscorrect;

	private Integer isfilledinfo;

	private Integer iscensorial;

	private Integer isexist;

}
