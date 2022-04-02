package br.com.mars.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.mars.domain.Position;
import br.com.mars.enums.Direction;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProbeResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID id;

	private Position position;

	private Direction direction;

}
