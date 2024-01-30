package com.ms.OrderService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.OrderService.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
