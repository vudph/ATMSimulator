package mum.mpp.atm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mum.mpp.atm.database.entity.BankAccount;
import mum.mpp.atm.model.CustomerConsole.InvalidCard;
import mum.mpp.atm.model.Message;

public class DatabaseApi {
	
	public static Connection dbConnection = getConnection();
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm_simulator", "postgres", "123456");
			return connection;
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isValidPIN(Message message) throws InvalidCard {
		Statement st;
		try {
			st = dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM \"BankCard\" WHERE \"cardNumber\" = " + message.getCard().getNumber());
			int cardPIN = -1;
			while (rs.next())
			{
				cardPIN = rs.getInt(2);
			}
			System.out.println("PIN: " + cardPIN);
			rs.close();
			st.close();
			if (cardPIN == -1) {
				throw new InvalidCard();
			}
			return cardPIN == message.getPIN();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<BankAccount> getBankAccounts(Message message) {
		Statement st;
		try {
			st = dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM \"BankAccount\" WHERE \"cardNumber\" = " + message.getCard().getNumber());
			List<BankAccount> accounts = new ArrayList<>();
			while (rs.next())
			{
				BankAccount acc = new BankAccount();
				acc.setAccountNumber(rs.getInt(1));
				acc.setCardNumber(rs.getInt(2));
				acc.setBalance(rs.getInt(3));
				acc.setAvailable(rs.getInt(4));
				acc.setType(rs.getString(5));
				accounts.add(acc);
				
			}
			rs.close();
			st.close();
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BankAccount getBankAccount(int cardNumber, int type) {
		Statement st;
		try {
			st = dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM \"BankAccount\" WHERE \"cardNumber\" = " + cardNumber + " AND \"type\" = " + type);
			BankAccount acc = null;
			while (rs.next())
			{
				acc = new BankAccount();
				acc.setAccountNumber(rs.getInt(1));
				acc.setCardNumber(rs.getInt(2));
				acc.setBalance(rs.getInt(3));
				acc.setAvailable(rs.getInt(4));
				acc.setType(rs.getString(5));
				
			}
			rs.close();
			st.close();
			return acc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updateBankAccount(BankAccount account) {
		try {
			PreparedStatement st = dbConnection.prepareStatement("Update \"BankAccount\" SET \"balance\" = " + account.getBalance() + ", \"available\" = " + account.getAvailable() + " WHERE \"accountNumber\" = " + account.getAccountNumber());
			int rowsUpdated = st.executeUpdate();
			System.out.println(rowsUpdated + " rows updated");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static long getWithdrawalAmountToday(int cardNumber) {
		Statement st;
		try {
			st = dbConnection.createStatement();
			String today = "'" + LocalDate.now().toString() + "'";
			System.out.println("SELECT * FROM \"WithdrawalActivity\" WHERE \"cardNumber\" = " + cardNumber + " and \"withdrawalDate\" = " + today);
			ResultSet rs = st.executeQuery("SELECT * FROM \"WithdrawalActivity\" WHERE \"cardNumber\" = " + cardNumber + " and \"withdrawalDate\" = " + today);
			long amount = 0;
			while (rs.next())
			{
				amount += rs.getLong(3);
			}
			rs.close();
			st.close();
			return amount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void updateWithdrawalAmountToday(int cardNumber, long amount) {
		try {
			PreparedStatement st = dbConnection.prepareStatement("Update \"WithdrawalActivity\" SET \"withdrawalAmount\" = " + amount + " WHERE \"cardNumber\" = " + cardNumber);
			int rowsUpdated = st.executeUpdate();
			System.out.println(rowsUpdated + " rows updated");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertWithdrawalAmountToday(int cardNumber, long amount) {
//		insert into "WithdrawalActivity" ("cardNumber", "withdrawalAmount", "withdrawalDate") values (1, 4000, '2017-09-13')
		
		try {
			String today = "'" + LocalDate.now().toString() + "'";
			System.out.println("Insert into \"WithdrawalActivity\" (\"cardNumber\", \"withdrawalAmount\", \"withdrawalDate\") values (" + cardNumber + ", " + amount + ", " + today + ")");
			PreparedStatement st = dbConnection.prepareStatement("Insert into \"WithdrawalActivity\" (\"cardNumber\", \"withdrawalAmount\", \"withdrawalDate\") values (" + cardNumber + ", " + amount + ", " + today + ")");
			int rowsUpdated = st.executeUpdate();
			System.out.println(rowsUpdated + " rows inserted");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getDailyWithdrawalLimit() {
		Statement st;
		try {
			int amount = 0;
			st = dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM \"ATMConfiguration\" WHERE \"id\" = 1");
			while (rs.next())
			{
				amount = rs.getInt(2);
			}
			System.out.println("DAILY_WITHDRAWAL_LIMIT: " + amount);
			rs.close();
			st.close();
			return amount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
