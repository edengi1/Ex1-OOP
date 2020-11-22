package ex1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class WGraph_Algo implements weighted_graph_algorithms, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	weighted_graph graphAlgo;

	public WGraph_Algo() {

	}

	@Override
	public void init(weighted_graph g) {
		this.graphAlgo=g;

	}

	@Override
	public weighted_graph getGraph() {

		return graphAlgo;
	}

	@Override
	public weighted_graph copy() {
		weighted_graph copy= new WGraph_DS(graphAlgo);
		return copy;
	}

	@Override
	public boolean isConnected() {

		boolean fl = true;
		int key =0;
		//search for the first node
		for (int i=0; i<graphAlgo.nodeSize()&& fl; i++) {
			if (graphAlgo.getNode(i)!= null) {
				key = graphAlgo.getNode(i).getKey();
				fl=false;		
			}
		}

		BFS(key);
		boolean flag =true;
		for (node_info i: graphAlgo.getV()) {
			if (i.getTag()==0) flag= false;
			i.setTag(0); //restart back to unvisut
		}
		return flag;
	}
	void BFS(int s) 
	{ 
		// Mark all the vertices as not visited(By default 
		// set as false) 
		if (graphAlgo.getNode(s)==null) return;
		int size = graphAlgo.nodeSize();
		node_info first = graphAlgo.getNode(s);
		if (size==0) return ;


		// Create a queue for BFS 
		LinkedList<Integer> queue = new LinkedList<Integer>(); 

		// Mark the current node as visited and enqueue it 
		first.setTag(1); //visit
		//add to the queue
		queue.add(s); 
		System.out.println("start bfs");
		while (queue.size() != 0) 
		{ 
			// Dequeue a vertex from queue and print it 
			s = queue.poll();
			node_info sNode= graphAlgo.getNode(s);


			// Get all adjacent vertices of the dequeued vertex s 
			// If a adjacent has not been visited, then mark it 
			// visited and enqueue it 
			Collection<node_info> NiS=graphAlgo.getV(s);
			if (NiS!=null)
				for (node_info i : NiS) {
					// if unvisit
					if(i.getTag()==0) {
						//maerk visit
						i.setTag(1);

						queue.add(i.getKey());
					}
				} //end for nei

		}// end while

		System.out.println("end bfs");

	} //end function
	@Override
	public double shortestPathDist(int src, int dest) {
		if (graphAlgo.getNode(src)!=null&& graphAlgo.getNode(dest)!=null) {

			if (src==dest) return 0;
			// all the nodes set to infinty
			for (node_info i : graphAlgo.getV()) {
				i.setTag(Double.POSITIVE_INFINITY);
				for (node_info j : graphAlgo.getV(i.getKey()))
					j.setTag(Double.POSITIVE_INFINITY);

			}
			node_info Nsrc = graphAlgo.getNode(src);
			Nsrc.setTag(0.);
			int keyS = Nsrc.getKey();
			Nsrc.setInfo("" + keyS);
			LinkedList<node_info> Q = new LinkedList<node_info>();
			//add all nodes to the queue
			for (node_info i : graphAlgo.getV()) {
				Q.add(i);
			}
			comp C =new comp();

			while (!Q.isEmpty()) {
				Q.sort(C);
				node_info u = Q.poll();
				//neighbor of u
				for (node_info nei : graphAlgo.getV(u.getKey())) {
					//compute the distance to nei- until u+ u -> v
					node_info newNei = graphAlgo.getNode(nei.getKey());
					double alt = u.getTag()+ graphAlgo.getEdge(u.getKey(), newNei.getKey());
					if (alt<newNei.getTag()) {
						newNei.setTag(alt);
						newNei.setInfo(u.getInfo()+ " " + newNei.getKey());
					}
				}

			}
			System.out.println(graphAlgo.getNode(dest).getInfo());
			return graphAlgo.getNode(dest).getTag();			

		}
		return -1;
	}

	@Override
	public List<node_info> shortestPath(int src, int dest) {
		double w = shortestPathDist(src, dest);
		if (w==-1) return null;
		String path =graphAlgo.getNode(dest).getInfo();
		// split space and add to the arr 
		String[] splited = path.split("\\s+");
		int size = splited.length;
		// create a list to add the node_info 
		LinkedList<node_info> list = new LinkedList<node_info>();
		for (int i = 0; i < size; i++) {
			int key = Integer.parseInt(splited[i]);
			node_info nod = graphAlgo.getNode(key);
			list.add(nod);
		}
		return list;
	}

	@Override
	public boolean save(String file){

		boolean ans = false;

		try {
			FileOutputStream fout = new FileOutputStream(file, true);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(graphAlgo);
			oos.close();
			fout.close();
			ans= true;
		}

		catch (IOException e) {e.printStackTrace();}
		return ans;
	}
	@Override
	public boolean load(String file) {
		try {
			FileInputStream streamIn = new FileInputStream(file+ ".txt");
			BufferedInputStream buf = new BufferedInputStream(streamIn);
			ObjectInputStream objectinputstream = new ObjectInputStream(buf);
			WGraph_DS readCase = (WGraph_DS) objectinputstream.readObject();
			streamIn.close();
			buf.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	class comp implements Comparator<node_info>{

		@Override
		public int compare(node_info o1, node_info o2) {

			return (int)(o1.getTag()-o2.getTag());
		}
	}
}


