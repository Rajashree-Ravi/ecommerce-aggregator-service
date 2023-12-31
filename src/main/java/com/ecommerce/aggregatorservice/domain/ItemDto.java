package com.ecommerce.aggregatorservice.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a item in e-commerce application.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

	@ApiModelProperty(notes = "Unique identifier of the Item.", example = "1")
	@JsonIgnore
	private Long id;

	@ApiModelProperty(notes = "Quantity of the products in Item.", example = "2", required = true)
	@NotNull
	private int quantity;

	@ApiModelProperty(notes = "Item Total.", example = "200.00", required = true)
	@NotNull
	private BigDecimal subTotal;

	@ApiModelProperty(notes = "Product in Item.", example = "5", required = true)
	@NotNull
	private Long productId;
}
