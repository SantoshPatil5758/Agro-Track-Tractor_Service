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
import org.springframework.web.bind.annotation.RestController;

import com.agrotrack.tractorservice.entity.CustomerDetails;
import com.agrotrack.tractorservice.entity.WorkDetails;
import com.agrotrack.tractorservice.service.CustomerWorkService;

@RestController
@RequestMapping("/customerwork")
public class CustomerWorkController {
	
	@Autowired
	private CustomerWorkService customerWorkService;
	
	@PostMapping("/create")
	public ResponseEntity<CustomerDetails>createCustomerWorkDetails(@RequestBody CustomerDetails customerDetails){
		return ResponseEntity.ok(customerWorkService.createCustomerWorkDetails(customerDetails));
	}
	// NEW ENDPOINT: Append a single work detail to a specific customer ID
    @PutMapping("/{customerName}/add-work")
    public ResponseEntity<CustomerDetails> addWorkDetails(
            @PathVariable String customerName, 
            @RequestBody WorkDetails workDetails) throws IllegalAccessException {
        
        CustomerDetails updatedCustomer = customerWorkService.addWorkToExistingCustomer(customerName, workDetails);
        return ResponseEntity.ok(updatedCustomer);
    }
	
    @GetMapping("/getall")
    public ResponseEntity<List<CustomerDetails>>getAllCustomerDetails(){
    	return ResponseEntity.ok(customerWorkService.getAllCustomerDetails());
    }
    
    @GetMapping("/getbycustomername/{customerName}")
    public ResponseEntity<CustomerDetails>getByCustomerName(@PathVariable String customerName){
    	return ResponseEntity.ok(customerWorkService.getByCustomerName(customerName));
    }
    
    @DeleteMapping("/deletebycustomername/{customerName}")
    public ResponseEntity<String>deleteByCustomerName(@PathVariable String customerName){
    	customerWorkService.deleteByCustomerName(customerName);
    	return ResponseEntity.ok("Customer Deleted Successfully");
    }
    
    @PutMapping("/updatecustomer/{customerName}")
    public ResponseEntity<CustomerDetails>updateCustomerByCustomerName(@PathVariable String customerName,@RequestBody CustomerDetails customerDetails) throws IllegalAccessException{
    	return ResponseEntity.ok(customerWorkService.updateCustomerByCustomerName(customerName,customerDetails));
    }
    
    @PutMapping("/{customerName}/updateworkdetails/{id}")
    public ResponseEntity<CustomerDetails>updateWorkDetailsById(@PathVariable String customerName,@PathVariable Long id,@RequestBody List<WorkDetails> workdetails) throws IllegalAccessException{
		return ResponseEntity.ok(customerWorkService.updateWorkDetailsById(customerName,id,workdetails));
    	
    }

}
