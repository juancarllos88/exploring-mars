package br.com.mars.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FieldDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ApiModelProperty(value = "field x axis")
	private Integer axisX;

	@NotNull
	@ApiModelProperty(value = "field y axis")
	private Integer axisY;
}
