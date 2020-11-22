# Ex1-OOP
public interface node_info {

 * Return the key (id) associated with this node.
 * Note: each node_data should have a unique key.
 
public int getKey();

 * return the remark (meta data) associated with this node.
 
public String getInfo();

 * Allows changing the remark (meta data) associated with this node.

public void setInfo(String s);

 * Temporal data (aka distance, color, or state)
 * which can be used be algorithms

public double getTag();

 * Allow setting the "tag" value for temporal marking an node - common
 * practice for marking by algorithms.
 
public void setTag(double t);
}

This interface represents an Undirected (positive) Weighted Graph Theory algorithms including:
clone(); (copy)
init(graph);
isConnected();
double shortestPathDist(int src, int dest);
List<node_data> shortestPath(int src, int dest);
Save(file);
Load(file);
public interface weighted_graph_algorithms {

 * Init the graph on which this set of algorithms operates on.
 
public void init(weighted_graph g);
 * Return the underlying graph of which this class works.
 
public weighted_graph getGraph();

 * Compute a deep copy of this weighted graph.

public weighted_graph copy();

 * Returns true if and only if (iff) there is a valid path from EVREY node to each
 * other node. NOTE: assume ubdirectional graph.

public boolean isConnected();
 * returns the length of the shortest path between src to dest

public double shortestPathDist(int src, int dest);
/**
 * returns the the shortest path between src to dest - as an ordered List of nodes:
 * src--> n1-->n2-->...dest
 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
 * Note if no such path --> returns null;
 
public List<node_info> shortestPath(int src, int dest);

 * Saves this weighted (undirected) graph to the given
 * file name

public boolean save(String file);

 * This method load a graph to this graph algorithm.
 * if the file was successfully loaded - the underlying graph
 * of this class will be changed (to the loaded one), in case the
 * graph was not loaded the original graph should remain "as is".
 
public boolean load(String file);
}

This interface represents an undirectional weighted graph.
It should support a large number of nodes (over 10^6, with average degree of 10).
The implementation should be based on an efficient compact representation
public interface weighted_graph {

 * return the node_data by the node_id,

public node_info getNode(int key);

 * return true iff (if and only if) there is an edge between node1 and node2
 
public boolean hasEdge(int node1, int node2);

 * return the weight if the edge (node1, node1). In case
 * there is no such edge - should return -1

public double getEdge(int node1, int node2);
/**
 * add a new node to the graph with the given key.
 * Note: this method should run in O(1) time.
 * Note2: if there is already a node with such a key -> no action should be performed.
 * @param key
 */
public void addNode(int key);

 * Connect an edge between node1 and node2, with an edge with weight >=0.

public void connect(int node1, int node2, double w);

 * This method return a pointer (shallow copy) for a
 * Collection representing all the nodes in the graph.
 
public Collection<node_info> getV();

 * This method returns a Collection containing all the
 * nodes connected to node_id
 
public Collection<node_info> getV(int node_id);

 * Delete the node (with the given ID) from the graph -
 * and removes all edges which starts or ends at this node.

public node_info removeNode(int key);

 * Delete the edge from the graph,

public void removeEdge(int node1, int node2);
* return the number of vertices (nodes) in the graph.

public int nodeSize();

 * return the number of edges (undirectional graph).

public int edgeSize();

 * return the Mode Count - for testing changes in the graph.
 * Any change in the inner state of the graph should cause an increment in the ModeCount

public int getMC();
}
