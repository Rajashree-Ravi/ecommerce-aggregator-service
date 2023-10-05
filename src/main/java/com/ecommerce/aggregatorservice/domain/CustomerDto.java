package com.ecommerce.aggregatorservice.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a customer in e-commerce application.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	@ApiModelProperty(notes = "Unique identifier of the Customer.", example = "1")
	private Long id;

	@ApiModelProperty(notes = "Username of the Customer.", example = "Abhishek_09", required = true)
	@NotBlank
	private String userName;

	@ApiModelProperty(notes = "Password of the Customer.", example = "Test123", required = true)
	@NotBlank
	private String userPassword;

	@ApiModelProperty(notes = "Name of the Customer.", example = "Abhishek Agarwal", required = true)
	@NotBlank
	private String name;

	@ApiModelProperty(notes = "Email address of the Customer.", example = "abhishek.agarwal@gmail.com", required = true)
	@Email(message = "Enter valid email")
	private String email;

	@ApiModelProperty(notes = "Country of the Customer.", example = "India", required = false)
	private String country;
}
