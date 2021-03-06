package com.revature.model;
import java.util.ArrayList;

public class UserAcc {
	
	private String name;
	private String username;
	private String password;
	private String address;
	private String city;
	private String zipcode;
	private String state;
	private String country;
	private ArrayList<BankAcc> bankAccs;
	
	public UserAcc() {
		bankAccs = new ArrayList<BankAcc>();
		username = "User";
		name = "";
		password = "1234";
		address = "";
		city = "";
		zipcode = "";
		state = "";
		country = "";
	}
	
	public UserAcc(String username, String name, String password) {
		
		bankAccs = new ArrayList<BankAcc>();
			
		this.password = password;
		this.username = username;
		this.name = name;

		address = "";
		city = "";
		zipcode = "";
		state = "";
		country = "";
		
		bankAccs.add(new BankAcc());
		
	}
	
public UserAcc(String username, String name, String password, double amount) {
		
		bankAccs = new ArrayList<BankAcc>();
			
		this.password = password;
		this.username = username;
		this.name = name;

		address = "";
		city = "";
		zipcode = "";
		state = "";
		country = "";
		
		BankAcc m = new BankAcc();
		m.setBalance(amount);
		bankAccs.add(m);
		
		
	}
	
	public UserAcc(String username, String name, String password, String address,
			String city, String state, String zipcode, String country) {
			
		this.name = name;
		this.password = password;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.username = username;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setBalance(double balance) {
		bankAccs.get(0).setBalance(balance);
	}
	
	public double getBalance() {
		return bankAccs.get(0).getBalance();
	}
	
	public void setBankAccProperties(String name, int accNumber) {
		bankAccs.get(0).setName(name);
		bankAccs.get(0).setAccNumber(accNumber);
	}
	
	public String displayBankAcc() {
		return bankAccs.get(0).toString();
	}
	
	@Override
	public String toString() {
		return "UserAcc [name=" + name + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", city=" + city + ", zipcode=" + zipcode + ", state=" + state + ", country=" + country + "]";
	}
	
}
