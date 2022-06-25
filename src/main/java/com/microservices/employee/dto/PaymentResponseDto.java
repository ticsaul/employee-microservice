package com.microservices.employee.dto;

import java.math.BigDecimal;

public class PaymentResponseDto {
	private BigDecimal payment;
	private boolean success;

	public PaymentResponseDto() {
		super();
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
