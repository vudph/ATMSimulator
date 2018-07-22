## 1.	Project Scope
The aim of this project is to simulate the real world Automatic Teller Machine (ATM) – it’s called ATM-Simulator. The application controls a simulated ATM which has a magnetic stripe reader for reading an ATM card, a console (keyboard and display) for interaction with the customer, a dispenser for cash, and a printer for printing customer receipts. The ATM serves only one customer at a time. To make a transaction, the customer will be required to insert an ATM card and then enter a personal identification number (PIN), which will be sent to the bank for validation. The customer then will be able to perform one or more transactions. The card will remain in the machine until the customer indicates he/she has no desire to do any further transaction, and it will be returned.
##  2.	Detailed Requirements
Basically, the ATM Simulator can provide the following operations for the customer:
* Withdrawal: a customer must be able to make a cash withdrawal from an account’s balance linked to the card in multiples of $20.
* Deposit: a customer must be able to add some dollar amount to any account linked to the card.
* Balance inquiry: a customer must be able to make a balance inquiry of any account linked to the card.
* Transfer: a customer must be able to transfer some dollar amount between any two accounts linked to the card. E.g. transfer money from the saving account to the checking account. 
Additional/optional functions:
* PIN is invalid, the customer will be required to re-enter the PIN before a transaction can proceed. (optional function: the card will be permanently retained by the machine if not successful enter the PIN after 3 tries)
* The ATM will support to print receipt for each successful transaction, showing the date, time, detailed transaction,…
* $300 daily withdrawal limit per card is enforced.
