import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
	
	public HashSet<E>getAllConnectedTo(E a) {
		node<E> node = null;
		for(node<E> ex : nodes) {
			if(ex.datum.equals(a)) {
				node = ex;
			}
		}
		if(node == null) {
			throw new NoSuchElementException();
		}
		HashSet<E> output = new HashSet<E>();
		for(edge<node<E>> edge : edges) {
			if(edge.contains(new node<E>(a))) {
				output.add(edge.getother(node).datum);
			}
		}
		return output;
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
		
		public boolean contains(T a) {
			return set.contains(a);
		}
		
		public T getother(T a) {
			Iterator<T> iter = set.iterator();
			T t = iter.next();
			if(t.equals(a)) {
				return iter.next();
			}
			return t;
		}
		
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
