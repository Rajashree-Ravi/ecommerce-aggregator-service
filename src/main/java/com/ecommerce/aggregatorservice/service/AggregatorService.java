package com.ecommerce.aggregatorservice.service;

import com.ecommerce.aggregatorservice.domain.CreateOrderRequestDto;
import com.ecommerce.aggregatorservice.domain.OrderDto;

public interface AggregatorService {

	OrderDto createOrder(CreateOrderRequestDto requestDto);

}
