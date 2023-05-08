import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

public class AgritechTest {
	@Test
	public void GraphTest() {
		Graph<String> gra = new Graph<String>();
		String a = "a";
		String b = "b";
		String c = "c";
		gra.addnode(a);
		gra.addnode(b);
		gra.addnode(c);
		gra.connect(a, b);
		gra.connect(b, c);
		gra.connect(a, c);
		HashSet<String> connecteset = new HashSet<String>();
		connecteset.add(b);
		connecteset.add(c);
		assertEquals(gra.getAllConnectedTo(a), connecteset);
		gra.removenode(b);
		connecteset.remove(b);
		assertEquals(gra.getAllConnectedTo(a), connecteset);
	}
	
	@Test 
	public void NetworkTest() {
		HashMap<String, ActorData> info = new HashMap<String, ActorData>();
		Internetwork internet = new Internetwork();
		LocalUser steve = new LocalUser("192.202.29.0", "PRODUCER", internet);
		info.put("192.202.29.0", steve.getData());
		steve.addInput("Rice", 15000);
		LocalUser martha = new LocalUser("192.801.29.1", "SUPPLIER", internet);
		info.put("192.801.29.1", martha.getData());
		LocalUser bertie = new LocalUser("192.942.30.3", "CONSUMER",  internet);
		info.put(bertie.getData().IP, bertie.getData());
		internet.setNetwork(info);
		steve.addNeighbor("192.801.29.1");
		martha.addNeighbor("192.801.29.1");
		martha.addNeighbor("192.942.30.3");
		bertie.addNeighbor("192.801.29.1");
		steve.floodNetwork();
		System.out.println(steve.getNetwork().getNodes());
		
	}
	
	

}
