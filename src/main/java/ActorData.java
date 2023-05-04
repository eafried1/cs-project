import java.util.HashMap;
import java.util.HashSet;

public class ActorData {
	public enum kind{
		PRODUCER,
		SUPPLIER,
		CONSUMER
	}
	
	String IP;
	HashSet<String> Neighbors;
	HashMap<String, Integer> consumption;
	HashMap<String, Integer> production;
	kind actortype;
	
	public ActorData(kind actortype, String IP) {
		this.actortype = actortype;
		this.IP = IP;
		this.consumption = new HashMap<String, Integer>();
		this.production = new HashMap<String, Integer>();
		this.Neighbors = new HashSet<String>();
	}

	public void addNeighbor(String nieghbor) {
		this.Neighbors.add(nieghbor);
	}
	
	public void removeNeighbor(String neighbor) {
		this.Neighbors.remove(neighbor);
	}
	
	public void addProduce(String produce, Integer ammount) {
		production.put(produce, ammount);
	}
	
	public void removeProduce(String produce, Integer ammount) {
		production.remove(produce, ammount);
	}
	
	public void addInput(String produce, Integer ammount) {
		consumption.put(produce, ammount);
	}
	
	public void removeInput(String produce, Integer ammount) {
		consumption.remove(produce,ammount);
	}
	
	@Override
	public int hashCode() {
		return IP.hashCode();
	}
}
