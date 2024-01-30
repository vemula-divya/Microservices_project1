package com.ms.InventoryService.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.ms.InventoryService.Model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
