package com.ms.OrderService.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.ms.OrderService.DTO.InventoryResponse;
import com.ms.OrderService.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.OrderService.DTO.OrderLineItemsDto;
import com.ms.OrderService.DTO.OrderRequest;
import com.ms.OrderService.Model.Order;
import com.ms.OrderService.Model.OrderLineItems;
import com.ms.OrderService.Repository.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	private final WebClient.Builder wc ;

	@Autowired
	private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrdernumber(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();

		order.setOrderlineitems(orderLineItems);
		List<String> skucodes =order.getOrderlineitems().stream().map(orderLineItems1 -> orderLineItems1.getSkuCode()).toList();

		//call inventory service and place order if product is in stock
		InventoryResponse[] inventoryResponseArray=wc.build().get()
				.uri("http://inventory-service:8082/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skucodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		boolean allProductsInStock=Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse -> inventoryResponse.isInStock());

		if (allProductsInStock) {
			orderRepository.save(order);
			kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrdernumber()));
			return "order placed successfully";
		}
		else{
			throw new IllegalArgumentException("product is not in stock ,please try after sometime");
		}


	}
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}

}
