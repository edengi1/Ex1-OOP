package ex1;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;



public class WGraph_DS implements weighted_graph {
	//field
	private HashMap<Integer ,node_info> V;
	//integer in left side for src Node, and int inside hash for dest. the double is for the weight
	private HashMap<Integer, HashMap<Integer, Double>> Nei;
	private int MC;
	private int EdgeCo;
	//constractor
	public WGraph_DS() {
		this.Nei= new HashMap<Integer, HashMap<Integer, Double>>();
		this.V= new HashMap<Integer, node_info>();
		this.MC=0;
		this.EdgeCo=0;
	}
	//copy constructor
	public WGraph_DS(weighted_graph other) {
		this.Nei= new HashMap<Integer, HashMap<Integer, Double>>();
		this.V= new HashMap<Integer, node_info>();
		this.MC=other.getMC();
		this.EdgeCo=other.edgeSize();
		
		//COPY THE HASH V
		for (node_info i: other.getV()) {
			this.V.put(i.getKey(), i);
			//create new hash every key in the edge Map
			this.Nei.put(i.getKey(), new HashMap<Integer,Double>());
		}
		
		//copy nei Hash
		// for to all the nodes
		for (node_info src : other.getV()) {
			// for to every node all his neighbors
			for (node_info dest: other.getV(src.getKey())) {
				//get the weight between src to dest
				double w = other.getEdge(src.getKey(), dest.getKey());
				this.Nei.get(src.getKey()).put(dest.getKey(), w);
			}
			
		}
	}
	static class Nodes implements node_info{
		//field
		int key;
		String info;
		double tag;
		
		public Nodes(int key2) {
		this.key = key2;
		}

		@Override
		public int getKey() {
			
			return this.key;
		}

		@Override
		public String getInfo() {
			
			return this.info;
		}

		@Override
		public void setInfo(String s) {
			this.info=s;
			
			
		}

		@Override
		public double getTag() {
			
			return this.tag;
		}

		@Override
		public void setTag(double t) {
			this.tag=t;
			
		}
		
	} //end of inner class
	
	
	@Override
	public node_info getNode(int key) {
		
		return V.get(key);
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		if (V.containsKey(node1)&&V.containsKey(node2)) {
		if (Nei.get(node1).containsKey(node2)||Nei.get(node2).containsKey(node1))
		return true;
		}
		return false;
	}

	@Override
	public double getEdge(int node1, int node2) {
		if(hasEdge(node1, node2)) {
			//get to hash with key node1, and then get the Object weight Type Double
			return Nei.get(node1).get(node2);
		}
		return -1;
	}

	@Override
	public void addNode(int key) {
		if(V.containsKey(key)) {
			return;
		}
		node_info N= new Nodes(key);
		
		V.put(key, N);
		
		Nei.put(key, new HashMap<Integer,Double>());
		MC++;
	}

	@Override
	public void connect(int node1, int node2, double w) {
		if (node1!=node2 && V.containsKey(node1)&&V.containsKey(node2)) {
		if(Nei.containsKey(node1) && Nei.containsKey(node2)&&!hasEdge(node1, node2)) {
			Nei.get(node1).put(node2, w);
			Nei.get(node2).put(node1, w);
			EdgeCo++;
			MC++;
		}
		}
	}

	@Override
	public Collection<node_info> getV() {
		return V.values();
	}

	@Override
	public Collection<node_info> getV(int node_id) {
		//check if existmnode_id in the Nei hash
		if (!Nei.containsKey(node_id)) return null;
		//create list for add the node neighbors
		Collection<node_info> NeiNode = new LinkedList<node_info>();
		// for each on the key of hashmap. every key is nei of nodeid.
		
		for ( Integer key : Nei.get(node_id).keySet() ) {
			//crate node_info from the key
			node_info I = new Nodes(key);
			//add to the list
			NeiNode.add(I);
		}
		return NeiNode;
	}

	@Override
	public node_info removeNode(int key) {
		if(Nei.containsKey(key)) {
			//for each on the nei of NodeKey
			
		for (node_info i: getV(key)) {
			// remove the node from hash Edge
			Nei.get(i.getKey()).remove(key);
			EdgeCo--;
		}	
		
		node_info del = V.get(key);
		Nei.remove(key);
		V.remove(key);
		return del;
		}
		return null;
	}

	@Override
	public void removeEdge(int node1, int node2) {
		if(hasEdge(node1, node2)) {
		Nei.get(node1).remove(node2);
		Nei.get(node2).remove(node1);
		EdgeCo--;
		}
		MC++;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return V.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return EdgeCo;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return MC;
	}

}
