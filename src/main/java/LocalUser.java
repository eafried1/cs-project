import java.util.HashMap;
import java.util.HashSet;

public class LocalUser {
	private Graph<String> network = new Graph<String>();
	private ActorData data;
	private HashMap<String, ActorData> networkdata = new HashMap<String, ActorData>();
	Internetwork net;
	
	private ActorData getNodeData(String IP) {
		ActorData pingdata =  net.query(IP);
		networkdata.put(IP, pingdata);
		return pingdata;
	}
		
	public LocalUser(String IP, String type, Internetwork net) {
		type = type.toUpperCase();
		if(!type.equals("PRODUCER") && !type.equals("SUPPLIER") && !type.equals("CONSUMER")) {
			throw new IllegalArgumentException("actor type not recognized. Please enter one of: PRODUCER, SUPPLIER, CONSUMER");
		}
		if(type.equals("PRODUCER")) {
			data = new ActorData(ActorData.kind.PRODUCER, IP);
		}
		if(type.equals("SUPPLIER")) {
			data = new ActorData(ActorData.kind.SUPPLIER, IP);
		}
		if(type.equals("CONSUMER")) {
			data = new ActorData(ActorData.kind.CONSUMER, IP);
		}		
		this.net = net;
		network.addnode(IP);
	}

	public void addNeighbor(String nieghbor) {
		data.addNeighbor(nieghbor);
		network.addnode(nieghbor);
		network.connect(data.IP, nieghbor);
	}
	
	public void addNeighbors(HashSet<String> phonebook) {
		for(String str : phonebook) {
			this.addNeighbor(str);
		}
	}
	
	public void removeNeighbor(String neighbor) {
		data.removeNeighbor(neighbor);
		network.disconnect(data.IP, neighbor);
	}
	
	public void removeNeighbors(HashSet<String> phonebook) {
		for(String str : phonebook) {
			this.removeNeighbor(str);
		}
	}
	
	public void addProduce(String produce, Integer ammount) {
		data.addProduce(produce, ammount);
	}
	
	public void removeProduce(String produce, Integer ammount) {
		data.removeProduce(produce, ammount);
	}
	
	public void addInput(String produce, Integer ammount) {
		data.addInput(produce, ammount);
	}
	
	public void removeInput(String produce, Integer ammount) {
		data.removeInput(produce, ammount);
	}
}
