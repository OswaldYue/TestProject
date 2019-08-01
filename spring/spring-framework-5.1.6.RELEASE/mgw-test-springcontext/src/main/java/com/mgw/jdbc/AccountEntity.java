package com.mgw.jdbc;


public class AccountEntity {

	private String username;

	private Integer balance;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountEntity{" +
				"username='" + username + '\'' +
				", balance=" + balance +
				'}';
	}
}
