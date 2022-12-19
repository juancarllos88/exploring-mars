package br.com.mars.domain.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID id;

	private Integer axisX;

	private Integer axisY;

	public Integer getAxisX() {
		return this.axisX > 0 ? this.axisX - 1 : this.axisX;
	}


	public Integer getAxisY() {
		return this.axisY > 0 ? this.axisY - 1 : this.axisY;
	}



}
