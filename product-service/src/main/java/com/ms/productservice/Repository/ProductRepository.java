package com.ms.productservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	
}
