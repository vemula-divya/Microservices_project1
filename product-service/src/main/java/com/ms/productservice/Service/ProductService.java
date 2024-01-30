package com.ms.productservice.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.productservice.DTO.ProductRequest;
import com.ms.productservice.DTO.ProductResponse;
import com.ms.productservice.Repository.ProductRepository;
import com.ms.productservice.model.Product;

@Service

public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;


	public void createProduct(ProductRequest pr) {
		Product product= Product.builder().name(pr.getName()).price(pr.getPrice())
				.description(pr.getDescription()).build();
		productRepository.save(product);
		logger.info("ProductService:createProduct --product {} is saved",product.getId());
	}

	public List<ProductResponse> getAllProducts() {
	List<Product> products=productRepository.findAll();
	List<ProductResponse> pr=	products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
	return pr;
	}

	private ProductResponse mapToProductResponse(Product p) {
		return ProductResponse.builder().id(p.getId())
				.name(p.getName())
				.description(p.getDescription())
				.price(p.getPrice()).
				build();
	}
}
