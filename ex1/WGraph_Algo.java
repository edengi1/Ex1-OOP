package ex1;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ex0.node_data;

public class WGraph_Algo implements weighted_graph_algorithms {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_info> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean load(String file) {
		// TODO Auto-generated method stub
		return false;
	}

}
