package com.ms.productservice.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or another strategy if needed
	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	

}
