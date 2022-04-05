package br.com.mars.domain.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.mars.domain.shared.Direction;
import br.com.mars.domain.shared.Position;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProbeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "x axis and y axis of probe")
	@NotNull
	private Position position;

	@ApiModelProperty(value = "current probe directions")
	@NotNull
	private Direction direction;

	@ApiModelProperty(value = "identification from field")
	@NotNull
	private UUID fieldId;

}
