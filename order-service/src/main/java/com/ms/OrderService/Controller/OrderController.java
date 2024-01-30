package com.ms.OrderService.Controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ms.OrderService.DTO.OrderRequest;
import com.ms.OrderService.Service.OrderService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	  private OrderService orderService;

	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
		//name  inventory should be same as prop file
		//@CircuitBreaker(name="inventory",fallbackMethod = "fallbackMethod")
		//@TimeLimiter(name="inventory")
		//@Retry(name = "inventory")
	    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {

	        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
	    }
//it should have same return type of parent method

		public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
				return CompletableFuture.supplyAsync(()->"Oops something went wrong ,please order some time");
		}



	    

}
