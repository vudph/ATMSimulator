package mum.mpp.atm.model;import mum.mpp.atm.view.Simulation;public class NetworkToBank {		public NetworkToBank() {	}	public void openConnection() {		// Since the network is simulated, we don't have to do anything	}	public void closeConnection() {		// Since the network is simulated, we don't have to do anything	}	public Status sendMessage(Message message, Balance balances) {		// Simulate the sending of the message - here is where the real code		// to actually send the message over the network would go		Status result = Simulation.getInstance().sendMessage(message, balances);		return result;	}}