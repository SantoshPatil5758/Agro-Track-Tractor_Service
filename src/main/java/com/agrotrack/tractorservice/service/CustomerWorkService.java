package com.agrotrack.tractorservice.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agrotrack.tractorservice.entity.CustomerDetails;
import com.agrotrack.tractorservice.entity.WorkDetails;
import com.agrotrack.tractorservice.helper.CalculateWorkAmount;
import com.agrotrack.tractorservice.repository.CustomerWorkRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerWorkService {

	@Autowired
	private CustomerWorkRepository customerWorkRepository;

	public CustomerDetails createCustomerWorkDetails(CustomerDetails customerDetails) {
		// Defensive null check to prevent NullPointerException
		if (customerDetails.getWorkDetails() != null) {
			customerDetails.getWorkDetails().forEach(work -> {
				work.setCustomerDetails(customerDetails);

				// Prevent NPEs if area or rate are missing in the child items
				if (work.getArea() != null && work.getRate() != null) {
					work.setTotalWorkAmount(work.getArea().multiply(work.getRate()));
				}

				if (work.getTotalWorkAmount() != null && work.getReciveWorkAmount() != null) {
					work.setPendingWorkAmount(work.getTotalWorkAmount().subtract(work.getReciveWorkAmount()));
				}
			});
		}

		return customerWorkRepository.save(customerDetails);
	}

	// 2. NEW METHOD: To add one more work detail to an existing customer
	public CustomerDetails addWorkToExistingCustomer(String customerName, WorkDetails newWork)
			throws IllegalAccessException {
		CalculateWorkAmount calculateWorkAmount = new CalculateWorkAmount();
		// Fetch the customer from database
		CustomerDetails customer = customerWorkRepository.findByCustomerName(customerName);
		if (customerName == null) {
			throw new IllegalAccessException("Customer not found with customer name: " + customerName);
		}

		// Link the new work to this customer
		newWork.setCustomerDetails(customer);

		// Add it to the existing list
		customer.getWorkDetails().add(newWork);

		// Recalculate everything (both child amounts and global customer totals)
		calculateWorkAmount.calculateWorkAmounts(customer);

		// Save (CascadeType.ALL takes care of inserting the new row into work_details
		// table)
		return customerWorkRepository.saveAndFlush(customer);

	}

	public List<CustomerDetails> getAllCustomerDetails() {
		return customerWorkRepository.findAll();
	}

	public CustomerDetails getByCustomerName(String customerName) {
		return customerWorkRepository.findByCustomerName(customerName);
	}

	@Transactional
	public CustomerDetails deleteByCustomerName(String customerName) {
		return customerWorkRepository.deleteByCustomerName(customerName);
	}

	public CustomerDetails updateCustomerByCustomerName(String customerName, CustomerDetails customerDetails)
			throws IllegalAccessException {
		CustomerDetails customer = customerWorkRepository.findByCustomerName(customerName);
		if (customer == null) {
			throw new IllegalAccessException("Customer not found with customer name: " + customerName);
		}
		customer.setCustomerName(customerDetails.getCustomerName());
		customer.setDescription(customerDetails.getDescription());
		customer.setEmail(customerDetails.getEmail());
		customer.setId(customerDetails.getId());
		customer.setMobilenumber(customerDetails.getMobilenumber());
		return customerWorkRepository.save(customer);
	}

	public CustomerDetails updateWorkDetailsById(String customerName, Long id, List<WorkDetails> workdetails)
			throws IllegalAccessException {
		CustomerDetails customer = customerWorkRepository.findByCustomerName(customerName);
		CalculateWorkAmount calculateWorkAmount = new CalculateWorkAmount();
		if (customer == null) {
			throw new IllegalAccessException("Customer not found with customer name: " + customerName);
		}
		WorkDetails workToUpdate = customer.getWorkDetails().stream().filter(work -> work.getId().equals(id))
				.findFirst().orElseThrow(() -> new EntityNotFoundException(
						"Work Details Entity not found for the customer:" + customerName + "with work ID :" + id));
		if (workdetails.get(0).getLocation() != null)
			workToUpdate.setLocation(workdetails.get(0).getLocation());
		if (workdetails.get(0).getDate() != null)
			workToUpdate.setDate(workdetails.get(0).getDate());
		if (workdetails.get(0).getArea() != null)
			workToUpdate.setArea(workdetails.get(0).getArea());
		if (workdetails.get(0).getRate() != null)
			workToUpdate.setRate(workdetails.get(0).getRate());
		if (workdetails.get(0).getReciveWorkAmount() != null)
			workToUpdate.setReciveWorkAmount(workdetails.get(0).getReciveWorkAmount());
		if (workdetails.get(0).getWorkStatus() != null)
			workToUpdate.setWorkStatus(workdetails.get(0).getWorkStatus());
		if (workdetails.get(0).getWorktype() != null)
			workToUpdate.setWorktype(workdetails.get(0).getWorktype());

		calculateWorkAmount.calculateWorkAmounts(customer);

		return customerWorkRepository.saveAndFlush(customer);

	}
}
