package com.agrotrack.tractorservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrotrack.tractorservice.entity.CustomerWorkDetails;
import com.agrotrack.tractorservice.service.CustomerWorkDetailsService;


@RestController
@RequestMapping("/customerdetails")
public class CustomerWorkDetailsController {

	@Autowired
	private CustomerWorkDetailsService customerWorkDetailsService;
	
	@PostMapping("/create")
	public ResponseEntity<CustomerWorkDetails> createCustomerDetails(@RequestBody CustomerWorkDetails customerWorkDetails){
		return ResponseEntity.ok(customerWorkDetailsService.createCustomerDetails(customerWorkDetails));
		
	}
	
	@GetMapping("/customerName")
	public ResponseEntity<CustomerWorkDetails>getByCustomerName(@RequestParam String customerName){
		return ResponseEntity.ok(customerWorkDetailsService.findByCustomerName(customerName));
	}
	
	@DeleteMapping("/delete/{customerName}")
	public ResponseEntity<String> deleteByCustomerName(@PathVariable String customerName) {
		customerWorkDetailsService.deleteByCustomerName(customerName);
		return ResponseEntity.ok("Customer Deleted Successfully");
	}
	
	@PutMapping("/update/{customerName}")
	public ResponseEntity<String>updateByCustomerName(@PathVariable String customerName, @RequestBody CustomerWorkDetails customerWorkDetails){
		customerWorkDetailsService.updateByCustomerName(customerName,customerWorkDetails);
		return ResponseEntity.ok("Update Customer Successfully");
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<CustomerWorkDetails>> getAllCustomerDetails(){
		return ResponseEntity.ok(customerWorkDetailsService.getAllCustomerDetails());
	}
}
