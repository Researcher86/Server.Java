package ownradio.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность для хранения информации о прослушанных треках
 *
 * @author Alpenov Tanat
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "histories")
public class History extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "track_id")
	private Track track;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(nullable = false)
	private Date lastListen;

	@Column(nullable = false)
	private Integer isListen; // 1, -1

	@Column(nullable = false)
	private String method;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private Device device;
}
