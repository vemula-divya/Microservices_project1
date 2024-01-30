package com.ms.OrderService.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`order`")
public class Order {
	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or another strategy if needed
	private Long id;
	private String ordernumber;
    @OneToMany(cascade = CascadeType.ALL)
	private List<OrderLineItems> orderlineitems;

}
