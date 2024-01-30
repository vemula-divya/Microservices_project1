package com.ms.InventoryService.Service;

import com.ms.InventoryService.DTO.InventoryResponse;
import com.ms.InventoryService.Model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.InventoryService.Repository.InventoryRepository;

import java.util.List;


@Service
@Slf4j
public class InventoryService {

	@Autowired
	private  InventoryRepository inventoryRepository;


	@Transactional(readOnly=true)
	public List<InventoryResponse> isInStock(List<String> skuCode) throws InterruptedException {
		//log.info("wait started");
		//Thread.sleep(10000);
		//log.info("wait ended");


		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
				.map(inventory->
						InventoryResponse.builder().skuCode(inventory.getSkuCode())
								.isInStock(inventory.getQuantity()>0).build()
				).toList();
	}
}
