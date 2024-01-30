package com.ms.OrderService.Model;

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
public class OrderLineItems {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or another strategy if needed
	private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
	
	

}
