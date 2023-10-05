package com.ecommerce.aggregatorservice.domain;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing product information.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDto {
	
	@ApiModelProperty(notes = "Unique identifier of the Product.", example = "1", required = true)
	@NotNull
	private long productId;
	
	@ApiModelProperty(notes = "Quantity of the product.", example = "2", required = true)
	@NotNull
	private int quantity;
}
