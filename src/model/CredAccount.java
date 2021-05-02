package model;

// this model code is for authorizing credit card transactions
public class CredAccount {

	public int id;
	public String name;
	public long CardNum;
	public int code;
	public float amount;

	public CredAccount() {
	}

	public CredAccount(int id, String name, long CardNum, int code, float amount) {
		this.id = id;
		this.name = name;
		this.CardNum = CardNum;
		this.code = code;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCardNum() {
		return CardNum;
	}

	public void setCardNum(long CardNum) {
		this.CardNum = CardNum;
	}

	public int getcode() {
		return code;
	}

	public void setcode(int code) {
		this.code = code;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}