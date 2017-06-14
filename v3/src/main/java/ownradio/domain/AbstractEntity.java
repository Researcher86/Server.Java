package ownradio.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/**
 * Базовый класс для всех сущностей
 * Предназначен для хранения технической информации
 *
 * @author Alpenov Tanat
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@Id
	@Column(unique = true)
	private UUID recid;

	private String recname;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar reccreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar recupdated;

	@PrePersist
	public void beforePersist() {
		reccreated = Calendar.getInstance();
		if (recid == null) {
			recid = UUID.randomUUID();
		}
	}

	@PreUpdate
	public void beforeUpdate() {
		recupdated = Calendar.getInstance();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractEntity that = (AbstractEntity) o;

		return recid != null ? recid.equals(that.recid) : that.recid == null;

	}

	@Override
	public int hashCode() {
		return recid != null ? recid.hashCode() : 0;
	}
}