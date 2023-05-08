import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class LocalUser {
	private Graph<String> network = new Graph<String>();
	private ActorData data;
	private HashMap<String, ActorData> networkdata = new HashMap<String, ActorData>();
	Internetwork net;
	
	private class limitqueue{
		String str;
		int depth;
		public limitqueue(String s, int i) {
			str = s;
			depth = i;
		}
	}
	
	private ActorData getNodeData(String IP) {
		ActorData pingdata =  net.query(IP);
		networkdata.put(IP, pingdata);
		return pingdata;
	}
	
	public Graph<String> getNetwork() {
		return this.network;
	}
	
	public ActorData getData() {
		return this.data;
	}
	
	//gets every single actor on the network. Can take very long for large networks
	public void floodNetwork() {
		HashSet<String> visited = new HashSet<String>();
		Stack<String> checklist = new Stack<String>();
		String node = data.IP;
		network.addnode(node);
		do {
			HashSet<String> neighbors = retrieve(node).Neighbors;
			for(String str : neighbors) {
				network.addnode(str);
				if(!(visited.contains(str))){
					network.connect(str, node);
					checklist.push(str);
				}
			}
			visited.add(node);
			//getNodeData(node); unsure if I need this
			node = checklist.pop();
		} while(checklist.size() > 0);
	}
	
	public void floodWithLimit(int n) throws InterruptedException {
		HashSet<String> visited = new HashSet<String>();
		LinkedBlockingQueue<limitqueue> checklist = new LinkedBlockingQueue<limitqueue>();
		String node = data.IP;
		network.addnode(node);
		checklist.put(new limitqueue(node,0));
		do {
			limitqueue shiny = checklist.poll();
			if(shiny.depth > n) break;
			HashSet<String> neighbors = retrieve(shiny.str).Neighbors;
			for(String str : neighbors) {
				network.addnode(str);
				if(!(visited.contains(str))){
					network.connect(str, shiny.str);
					checklist.put(new limitqueue(str,shiny.depth+1));
				}
			}
			visited.add(node);
		} while(checklist.size() > 0);
	}
	
	
	private ActorData retrieve(String IP) {
		if(networkdata.containsKey(IP)) {
			return networkdata.get(IP);
		} else {
			return getNodeData(IP);
		}
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
		this.networkdata.put(IP, data);
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
