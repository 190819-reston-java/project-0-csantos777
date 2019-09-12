package com.revature.service;

import static org.junit.Assert.*;
import com.revature.model.UserAcc;

import org.junit.Test;

import com.revature.exception.NegativeBalanceException;

public class ATMOperationsTest {

	@Test
	public void depositToBalance() {
		UserAcc test = new UserAcc("ASDF", "TEte", 100.00);
		
		ATMOperations.depositMoney(75, test);
		assertEquals(175.00,test.getBalance(),0.01);
	} 
	
	@Test
	public void withdrawFromBalance() {	
		UserAcc test = new UserAcc("ASDF", "TEte", 100.00);
		
		ATMOperations.withdrawMoney(75, test);
		assertEquals(25.00,test.getBalance(),0.01);
	}
	
	@Test (expected = NegativeBalanceException.class)
	public void excessBalanceToWithdraw() {
		UserAcc test = new UserAcc("ASDF", "TEte", 100.00);
		
		ATMOperations.withdrawMoney(125, test);
		
		assertEquals(-25.00,ATMOperations.getCurrBalance(),0.01);
	}

}
