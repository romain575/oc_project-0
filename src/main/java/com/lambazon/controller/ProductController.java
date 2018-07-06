package com.lambazon.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lambazon.domain.Product;
import com.lambazon.service.ProductService;


@Controller
public class ProductController {
	
	@Inject
	private ProductService productService;
	
	@GetMapping("/products")
	public String products	(Model model) {
		model.addAttribute("prods", productService.products());
		model.addAttribute("totalInventoryAmount", calculateTotalInventoryAmount());
		return "products";
	}
	
	@GetMapping("/products/{id}/details")
	public String product	(@PathVariable Integer id, Model model) {
		model.addAttribute("prod", productService.product(id));
		return "product";
	}
	
	/**
	 * Displays the total price of all products in stock
	 * @return The total stock price
	 */
	private double calculateTotalInventoryAmount() {
		double totalInventoryAmount = 0.0;
		for (Product p : productService.products()) { // For each product, add their values to get the total value of the stock
			totalInventoryAmount += p.getInventoryPrice();
		}
		return totalInventoryAmount;
	}
}
