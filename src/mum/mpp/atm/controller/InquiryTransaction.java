package mum.mpp.atm.controller;import mum.mpp.atm.core.ATM;import mum.mpp.atm.core.Session;import mum.mpp.atm.model.AccountInformation;import mum.mpp.atm.model.Card;import mum.mpp.atm.model.CustomerConsole;import mum.mpp.atm.model.Message;import mum.mpp.atm.model.Money;import mum.mpp.atm.model.Receipt;public class InquiryTransaction extends Transaction {	private int from;		public InquiryTransaction(ATM atm, Session session, Card card, int pin) {		super(atm, session, card, pin);	}	protected Message getCustomerInputs() throws CustomerConsole.Cancelled {		from = atm.getCustomerConsole().readMenuChoice("Account to inquire from", AccountInformation.ACCOUNT_NAMES);		return new Message(Message.INQUIRY, card, pin, transactionId, from, -1, new Money(0));	}	protected Receipt completeTransaction() {		return new Receipt(this.atm, this.card, this, this.balance) {			{				detailsPortion = new String[2];				detailsPortion[0] = "INQUIRY FROM: " + AccountInformation.ACCOUNT_ABBREVIATIONS[from];				detailsPortion[1] = "";			}		};	}}