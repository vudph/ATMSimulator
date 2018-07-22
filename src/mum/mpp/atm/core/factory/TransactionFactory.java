package mum.mpp.atm.core.factory;

import mum.mpp.atm.controller.DepositTransaction;
import mum.mpp.atm.controller.InquiryTransaction;
import mum.mpp.atm.controller.Transaction;
import mum.mpp.atm.controller.TransferTransaction;
import mum.mpp.atm.controller.WithdrawalTransaction;
import mum.mpp.atm.core.ATM;
import mum.mpp.atm.core.Session;
import mum.mpp.atm.model.Card;

public class TransactionFactory {
	public static final int WITHDRAWAL = 0;
	public static final int DEPOSIT = 1;
	public static final int TRANSFER = 2;
	public static final int BALANCE_INQUIRY = 3;
	
	public static Transaction createTransaction(int transType, ATM atm, Session session, Card card, int pin) {
		switch (transType) {
		case WITHDRAWAL:
			return new WithdrawalTransaction(atm, session, card, pin);
		case DEPOSIT:
			return new DepositTransaction(atm, session, card, pin);
		case TRANSFER:
			return new TransferTransaction(atm, session, card, pin);
		case BALANCE_INQUIRY:
			return new InquiryTransaction(atm, session, card, pin);
		default:
			return null;
		}
	}
}
