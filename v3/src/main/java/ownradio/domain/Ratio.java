package ownradio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность для хранения коэффициентов схожести интересов
 *
 * Created by a.polunina on 16.02.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratios")
public class Ratio extends AbstractEntity {
//	@ManyToOne
	@JoinColumn(name = "userid")
	@Column(nullable = false)
	private UUID userid1;

//	@ManyToOne
	@JoinColumn(name = "userid")
	@Column(nullable = false)
	private UUID userid2;

	private Integer ratio;
}
