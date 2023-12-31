package com.ecommerce.aggregatorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.aggregatorservice.domain.CreateOrderRequestDto;
import com.ecommerce.aggregatorservice.service.AggregatorService;
import com.ecommerce.aggregatorservice.domain.OrderDto;

import io.swagger.annotations.Api;

@RestController
@Api(produces = "application/json", value = "Operations pertaining to aggregation logic in e-commerce application")
@RequestMapping("/api/ecommerce")
public class AggregatorController {

	@Autowired
	private AggregatorService aggregatorService;

	@PostMapping("/createOrder")
	public OrderDto createOrder(@RequestBody CreateOrderRequestDto requestDto) {
		return aggregatorService.createOrder(requestDto);
	}

}
