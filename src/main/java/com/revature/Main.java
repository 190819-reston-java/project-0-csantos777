package com.revature;

import java.util.ArrayList;
import java.security.SecureRandom;

import com.revature.controller.ATMController;
/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) {
		//ATMController.start();
		
		SecureRandom n = new SecureRandom();
		System.out.println(n.getAlgorithm());
	}
}
