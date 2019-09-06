package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.exception.NegativeBalanceException;

public class ATMOperationsTest {

	@Test
	public void depositToBalance() {
		double orig = 50.00;
				ATMOperations.setCurrBalance(orig);		
		ATMOperations.depositMoney(50.50);
		assertEquals(100.5,ATMOperations.getCurrBalance(),0.01);
	}
	
	@Test
	public void withdrawFromBalance() {
		double orig = 100.00;
				ATMOperations.setCurrBalance(orig);		
		ATMOperations.withdrawMoney(50.50);
		assertEquals(49.50,ATMOperations.getCurrBalance(),0.01);
	}
	
	@Test (expected = NegativeBalanceException.class)
	public void excessBalanceToWithdraw() {
		double orig = 25.00;
		ATMOperations.setCurrBalance(orig);		
		ATMOperations.withdrawMoney(30);
		
		assertEquals(-5.00,ATMOperations.getCurrBalance(),0.01);
	}

}
