package br.com.mars.domain.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "fields")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "x_axis")
	private Integer axisX;

	@Column(name = "y_axis")
	private Integer axisY;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "field", fetch = FetchType.LAZY)
	List<Probe> probes;

	/**
	 * Checks if there is space available at the field boundaries
	 *
	 * @param XPosition probe x position
	 * @param YPosition probe y position
	 * @return boolean
	 */
	public boolean isAvailableSpace(int XPosition, int YPosition) {

		boolean XBound = (XPosition >= 0) && (XPosition < this.axisX);
		boolean YBound = (YPosition >= 0) && (YPosition < this.axisY);
		boolean occupiedPositionByAnyProbe = probes.stream()
				.filter(probe -> probe.getPosition().getX() == XPosition && probe.getPosition().getY() == YPosition)
				.findFirst().isPresent();

		return XBound && YBound && !occupiedPositionByAnyProbe;
	}

}
