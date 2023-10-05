package com.ecommerce.aggregatorservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.aggregatorservice.domain.CreateOrderRequestDto;
import com.ecommerce.aggregatorservice.domain.ProductInfoDto;
import com.ecommerce.aggregatorservice.service.AggregatorService;
import com.ecommerce.sharedlibrary.model.CustomerDto;
import com.ecommerce.sharedlibrary.model.ItemDto;
import com.ecommerce.sharedlibrary.model.OrderDto;
import com.ecommerce.sharedlibrary.model.OrderStatus;
import com.ecommerce.sharedlibrary.model.ProductDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AggregatorServiceImpl implements AggregatorService {

	private WebClient customerClient = WebClient.create("http://localhost:8080/api/customers");
	private WebClient productClient = WebClient.create("http://localhost:8080/api/products");
	private WebClient orderClient = WebClient.create("http://localhost:8080/api/orders");

	@Override
	public Mono<OrderDto> createOrder(CreateOrderRequestDto requestDto) {

		OrderDto order = new OrderDto();
		order.setItems(new ArrayList<>());

		// Get customer information
		Mono<CustomerDto> customer = getCustomer(requestDto.getUserId());
		customer.subscribe(data -> {
			order.setUserId(data.getId());
		});

		// Check product availability
		List<Long> productIds = requestDto.getProductInfo().stream().map(ProductInfoDto::getProductId)
				.collect(Collectors.toList());
		Flux<ProductDto> products = fetchProducts(productIds);
		products.subscribe(product -> {
			ItemDto item = new ItemDto();
			long productId = product.getId();
			ProductInfoDto info = requestDto.getProductInfo().stream().filter(c -> c.getProductId() == productId)
					.findAny().orElse(null);
			if (info != null && info.getQuantity() <= product.getAvailability()) {
				int quantity = info.getQuantity();
				BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
				item.setProductId(productId);
				item.setQuantity(quantity);
				item.setSubTotal(subTotal);
			} else {
				// Handle shortage
			}

			order.getItems().add(item);
		});

		order.setOrderedDate(LocalDate.now());
		order.setStatus(OrderStatus.PICKUPAVAILABLE);
		order.setTotal(order.getItems().stream().map(ItemDto::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add));

		// Create Order
		Mono<OrderDto> savedOrder = createOrderWithUserAndProduct(order);

		// Check Customer
		return savedOrder;
	}

	public Mono<CustomerDto> getCustomer(Long userId) {
		Mono<CustomerDto> customer = customerClient.get().uri("/" + userId).retrieve().bodyToMono(CustomerDto.class);
		return customer;
	}

	public Mono<ProductDto> getProduct(Long productId) {
		Mono<ProductDto> product = productClient.get().uri("/" + productId).retrieve().bodyToMono(ProductDto.class);
		return product;
	}

	public Flux<ProductDto> fetchProducts(List<Long> productIds) {
		return Flux.fromIterable(productIds).flatMap(this::getProduct);
	}

	public Mono<OrderDto> createOrderWithUserAndProduct(OrderDto order) {
		Mono<OrderDto> savedOrder = orderClient.post().uri("/").retrieve().bodyToMono(OrderDto.class);
		return savedOrder;
	}
}
