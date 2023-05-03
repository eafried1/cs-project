import java.util.HashSet;

public class Graph<E> {
	HashSet<node<E>> nodes;
	HashSet<edge<node<E>>> edges; 
		
	public Graph() {
		this.nodes = new HashSet<node<E>>();
		this.edges = new HashSet<edge<node<E>>>();
	}
	
	public void connect(E a, E b) {
		edges.add(new edge<node<E>>(new node<E>(a), new node<E>(b)));
	}
	
	public void addnode(E a) {
		nodes.add(new node<E>(a));
	}
	
	//creates a complete graph K_n; TODO: update for more kinds of graphs.
	public Graph(String str, int n) {
		this.nodes = new HashSet<node<E>>();
		this.edges = new HashSet<edge<node<E>>>();
		if(str.equals("complete")|| str.equals("Complete")) {
			for(int i = 0; i < n; i++) {
				nodes.add(new node<E>());
			}
			for(node<E> nd : nodes) {
				for(node<E> de : nodes) {
					edges.add(new edge<node<E>>(nd,de));
				}
			}
		}
	}
	
	private class node<T>{
		public T datum;
		public node() {
			
		}
		public node(T datum) {
			this.datum = datum;
		}
	}
	
	private class edge<T>{
		private final HashSet<T> set;
		
		public edge(T a, T b){
			set = new HashSet<T>();
			set.add(a);
			set.add(b);
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof edge)) {
				return false;
			}
			@SuppressWarnings("unchecked")
			edge<T> other = (edge<T>) o;
				return other.set.equals(this.set);
		}
		
		@Override 
		public int hashCode() {
			return set.hashCode();
		}
	}

}
