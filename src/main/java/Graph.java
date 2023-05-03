import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Graph<E> {
	HashSet<E> nodes;
	HashSet<edge<E>> edges; 
		
	public Graph() {
		this.nodes = new HashSet<E>();
		this.edges = new HashSet<edge<E>>();
	}
	
	public void connect(E a, E b) {
		edges.add(new edge<E>(a, b));
	}
	
	public void addnode(E a) {
		nodes.add(a);
	}
	
	public HashSet<E>getAllConnectedTo(E a) {
		E node = null;
		for(E ex : nodes) {
			if(ex.equals(a)) {
				node = ex;
			}
		}
		if(node == null) {
			throw new NoSuchElementException();
		}
		HashSet<E> output = new HashSet<E>();
		for(edge<E> edge : edges) {
			if(edge.contains(a)) {
				output.add(edge.getother(node));
			}
		}
		return output;
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
