package ownradio.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.UUIDGenerationStrategy;
import org.hibernate.id.UUIDGenerator;
import ownradio.annotation.DisplayName;

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
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@DisplayName(key = "id")
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",  strategy="ownradio.util.IdOrGenerate")
	@Column(unique = true)
//	@Column(insertable=true, updatable=true, unique=true, nullable=false)
	private UUID recid;

	private String recname;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar reccreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar recupdated;

	@PrePersist
	public void beforePersist() {
		setReccreated(Calendar.getInstance());
	}

	@PreUpdate
	public void beforeUpdate() {
		setRecupdated(Calendar.getInstance());
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