package com.agrotrack.tractorservice.helper;

import java.math.BigDecimal;
import com.agrotrack.tractorservice.entity.CustomerDetails;
import com.agrotrack.tractorservice.entity.WorkDetails;

public class CalculateWorkAmount {
    
    public void calculateWorkAmounts(CustomerDetails customer) {
        BigDecimal customerTotal = BigDecimal.ZERO;
        BigDecimal customerReceived = BigDecimal.ZERO;

        if (customer.getWorkDetails() != null) {
            for (WorkDetails work : customer.getWorkDetails()) {
                
                // Matches items where the list contains "COMPLETED"
            	boolean isCompleted = work.getWorkStatus() != null && work.getWorkStatus().stream()
            	        .filter(java.util.Objects::nonNull)
            	        .anyMatch(status -> "COMPLETED".equalsIgnoreCase(status.toString().trim()));
                
                if (isCompleted) {
                    // Compute individual totals
                    if (work.getArea() != null && work.getRate() != null) {
                        work.setTotalWorkAmount(work.getArea().multiply(work.getRate()));
                    } else {
                        work.setTotalWorkAmount(BigDecimal.ZERO);
                    }
                    
                    // Compute individual pending values
                    BigDecimal received = work.getReciveWorkAmount() != null ? work.getReciveWorkAmount() : BigDecimal.ZERO;
                    if (work.getTotalWorkAmount() != null) {
                        work.setPendingWorkAmount(work.getTotalWorkAmount().subtract(received));
                        
                        // Accumulate towards global customer context
                        customerTotal = customerTotal.add(work.getTotalWorkAmount());
                        customerReceived = customerReceived.add(received);
                    }
                } else {
                    // Reset fields if the state changed from completed back to pending/inprogress
                    work.setTotalWorkAmount(BigDecimal.ZERO);
                    work.setPendingWorkAmount(BigDecimal.ZERO);
                    
                    // Count funds received even if work is still ongoing
                    if (work.getReciveWorkAmount() != null) {
                        customerReceived = customerReceived.add(work.getReciveWorkAmount());
                    }
                }
            }
        }
        
        // Finalize Parent totals
        customer.setTotalAmount(customerTotal);
        customer.setReciveAmount(customerReceived);
        customer.setPendingAmount(customerTotal.subtract(customerReceived));
    }
}