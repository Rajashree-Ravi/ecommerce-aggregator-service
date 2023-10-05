package com.ecommerce.aggregatorservice.domain;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a create order request.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDto {

	@ApiModelProperty(notes = "Unique identifier of the Customer.", required = true)
	@NotNull
	private long userId;
	
	@ApiModelProperty(notes = "Production information.", required = true)
	@NotEmpty
	private List<ProductInfoDto> productInfo;
}
