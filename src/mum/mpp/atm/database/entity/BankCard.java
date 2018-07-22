package mum.mpp.atm.database.entity;

import java.io.Serializable;

public class BankCard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cardNumber;
	private int cardPIN;
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public int getCardPIN() {
		return cardPIN;
	}
	
	public void setCardPIN(int cardPIN) {
		this.cardPIN = cardPIN;
	}
	
	@Override
	public String toString() {
		return "BankAccount[cardNumber="+ cardNumber + ", cardPIN=" + cardPIN + "]";
	}
}
