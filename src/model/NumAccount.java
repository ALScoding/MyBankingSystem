package model;

import java.util.Random;

// this model code is for debit/credit transactions
// randomized account numbers are also generated
public class NumAccount {

	public int AccNum;
	public float amount;

	public NumAccount() {
	}

	public NumAccount(int AccNum, float amount) {
		this.AccNum = AccNum;
		this.amount = amount;
	}

	public void setAccNum(int AccNum) {
		this.AccNum = AccNum;
	}

	public int getAccNum() {
		Random rand = new Random();
		AccNum = rand.nextInt(900000) + 100000;
		return AccNum;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}