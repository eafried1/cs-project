import java.util.HashMap;

import java.util.NoSuchElementException;

public class Internetwork extends Network{
	private HashMap<String, ActorData> network;
	public ActorData query(String IP) {
		if(!network.containsKey(IP)) {
			throw new NoSuchElementException("IP not found: " + IP);
		}
		else {
			return network.get(IP);
		}
	}
	
	public void setNetwork(HashMap<String, ActorData> network) {
		this.network = network;
	}
}
	