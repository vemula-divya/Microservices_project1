package com.ms.InventoryService.Controller;


import java.util.List;

import com.ms.InventoryService.DTO.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ms.InventoryService.Service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
    private  InventoryService inventoryService;

	  @GetMapping
	    @ResponseStatus(HttpStatus.OK)
	    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) throws InterruptedException {
	        return inventoryService.isInStock(skuCode);
	    }
	  
	/*
	 * @GetMapping
	 * 
	 * @ResponseStatus(HttpStatus.OK) public List<InventoryResponse>
	 * isInStock(@RequestParam List<String> skuCode) { return
	 * inventoryService.isInStock(skuCode); }
	 */
}
