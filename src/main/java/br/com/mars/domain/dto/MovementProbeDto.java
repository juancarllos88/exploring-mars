package br.com.mars.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MovementProbeDto implements Serializable {

	private static final long serialVersionUID = 1820296452459654704L;

	@NotBlank
	@ApiModelProperty(value = "set of instructions for moving", example = "MMRMMRMRRM - (M)ove_foward|(L)eft|(R)ight")
	private String movement;
}
