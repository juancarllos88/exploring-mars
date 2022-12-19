package br.com.mars.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ApiModelProperty(value = "field x axis")
	@Min(value = 0)
	@Max(value = 100)
	private Integer axisX;

	@NotNull
	@ApiModelProperty(value = "field y axis")
	@Min(value = 0)
	@Max(value = 100)
	private Integer axisY;
}
