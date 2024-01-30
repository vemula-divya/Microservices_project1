package com.ms.productservice.Controller;

import com.ms.productservice.DTO.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ms.productservice.DTO.ProductRequest;
import com.ms.productservice.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


	@Autowired 
	private ProductService ps;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest prodRequest) {
		ps.createProduct(prodRequest);

		logger.info("ProductController:createProduct ProductRequest -{} "+prodRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	private List<ProductResponse> getAllProducts(){
		return ps.getAllProducts();
	}
	
	

}
