package com.ecommerce.aggregatorservice.service;

import com.ecommerce.aggregatorservice.domain.CreateOrderRequestDto;
import com.ecommerce.sharedlibrary.model.OrderDto;

import reactor.core.publisher.Mono;

public interface AggregatorService {

	Mono<OrderDto> createOrder(CreateOrderRequestDto requestDto);

}
