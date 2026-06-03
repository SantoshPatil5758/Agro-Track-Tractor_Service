package com.agrotrack.tractorservice.service;

import java.math.BigDecimal;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrotrack.tractorservice.entity.CustomerWorkDetails;
import com.agrotrack.tractorservice.repository.CustomerWorkDetailsRepository;

@Service
public class CustomerWorkDetailsService {

	@Autowired
	private CustomerWorkDetailsRepository customerWorkDetailsRepository;

	public CustomerWorkDetails createCustomerDetails(CustomerWorkDetails customerWorkDetails) {

		if (customerWorkDetails.getRate() == null) {
			throw new IllegalArgumentException("Rate cannot be null");
		}

		BigDecimal totalAmount = customerWorkDetails.getArea().multiply(customerWorkDetails.getRate());
		customerWorkDetails.setTotalAmount(totalAmount);

		BigDecimal pendingAmount = totalAmount.subtract(customerWorkDetails.getReciveAmount());
		customerWorkDetails.setPendingAmount(pendingAmount);

		return customerWorkDetailsRepository.save(customerWorkDetails);
	}

	public CustomerWorkDetails findByCustomerName(String customerName) {
		CustomerWorkDetails customer = customerWorkDetailsRepository.findBycustomerName(customerName);
		if (customer == null) {
			throw new RuntimeException("Customer Not Found");
		}
		return customerWorkDetailsRepository.findBycustomerName(customerName);
	}

	@Transactional
	public String deleteByCustomerName(String customerName) {
		CustomerWorkDetails customer = findByCustomerName(customerName);
		customerWorkDetailsRepository.delete(customer);
		return "Customer Deleted Successfully : " + customerName;

	}

	public CustomerWorkDetails updateByCustomerName(String customerName, CustomerWorkDetails customerWorkDetails) {
		CustomerWorkDetails customer = findByCustomerName(customerName);
		//CustomerWorkDetails customer = customerWorkDetailsRepository.findBycustomerName(customerName);
		customer.setDate(customerWorkDetails.getDate());
		customer.setCustomerName(customerWorkDetails.getCustomerName());
		customer.setEmail(customerWorkDetails.getEmail());
		customer.setLocation(customerWorkDetails.getLocation());
		customer.setArea(customerWorkDetails.getArea());
		customer.setMobilenumber(customerWorkDetails.getMobilenumber());
		customer.setRate(customerWorkDetails.getRate());
		customer.setDescription(customerWorkDetails.getDescription());
		customer.setWorktype(customerWorkDetails.getWorktype());
		customer.setReciveAmount(customerWorkDetails.getReciveAmount());

		BigDecimal totalAmount = customerWorkDetails.getRate().multiply(customerWorkDetails.getArea());
		customer.setTotalAmount(totalAmount);

		BigDecimal pendingAmount = customer.getTotalAmount().subtract(customerWorkDetails.getReciveAmount());
		customer.setPendingAmount(pendingAmount);

		return customerWorkDetailsRepository.save(customer);

	}

	public List<CustomerWorkDetails> getAllCustomerDetails() {
		return customerWorkDetailsRepository.findAll();
	}

}
