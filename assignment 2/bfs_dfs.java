package ai1;
import java.util.*;

/**

 * Uninformed Search Algorithms.

 * 

 * Check ReadMe for details on this program and on how to use it.

 * 

 * Author: Yasser Ghamlouch

 * Student Number: 6276898

 */

import java.util.ArrayList;

import java.util.Comparator;

import java.util.HashMap;

import java.util.LinkedList;

import java.util.PriorityQueue;

import java.util.Queue;

import java.util.Stack;

public class bfs_dfs {
	public final static int NUM_OF_CITIES = 20;

	// Every city is assigned an integer

	private final static int ORADEA = 12;

	private final static int ZERIND = 19;

	private final static int ARAD = 0;

	private final static int TIMISORA = 16;

	private final static int LUGOJ = 9;

	private final static int MEHADIA = 10;

	private final static int DROBETA = 3;

	private final static int CRAIOVA = 2;

	private final static int RIMNICU_VILCEA = 14;

	private final static int SIBIU = 15;

	private final static int YAGARAS = 5;

	private final static int PITESTI = 13;

	private final static int BUCHAREST = 1;

	private final static int GIURGIU = 6;

	private final static int URZICENI = 17;

	private final static int VASLUI = 18;

	private final static int IASI = 8;

	private final static int NEAMT = 11;

	private final static int HIRSOVA = 7;

	private final static int EFORIE = 4;



	// Every city is represented by a Node instance

	private Node oradea;

	private Node zerind;

	private Node arad;

	private Node timisora;

	private Node lugoj;

	private Node mehadia;

	private Node drobeta;

	private Node craiova;

	private Node rimnicuVilcea;

	private Node sibiu;

	private Node yagaras;

	private Node pitesti;

	private Node bucharest;

	private Node giurgiu;

	private Node urziceni;

	private Node vaslui;

	private Node iasi;

	private Node neamt;

	private Node hirsova;

	private Node eforie;


	private Integer[][] map = new Integer[NUM_OF_CITIES][NUM_OF_CITIES];

	
	private HashMap<Integer, Node> cities = new HashMap<Integer, bfs_dfs.Node>(); 


	private int bfs_Num_Of_Nodes = 0;

	private int bfs_Max_NumOfNodes= 0;

	

	private int dfs_Num_Of_Nodes_= 0;

	private int dfs_Max_NumOfNodes= 0;

	

	public bfs_dfs(){

		initData(this.map);

		createNodes();

		initHashMap();

	}
	private void initData(Integer[][] map){

		map[ORADEA][ZERIND] = 71;

		map[ORADEA][SIBIU] = 151;

		map[ZERIND][ORADEA] = 71;

		map[ZERIND][ARAD] = 75;

		map[ARAD][ZERIND] = 75;

		map[ARAD][SIBIU] = 140;

		map[ARAD][TIMISORA] = 118;

		map[TIMISORA][ARAD] = 118;

		map[TIMISORA][LUGOJ] = 111;

		map[LUGOJ][TIMISORA] = 111;

		map[LUGOJ][MEHADIA] = 70;

		map[MEHADIA][LUGOJ] = 70;

		map[MEHADIA][DROBETA] = 75;

		map[DROBETA][MEHADIA] = 75;

		map[DROBETA][CRAIOVA] = 120;

		map[CRAIOVA][RIMNICU_VILCEA] = 146;

		map[CRAIOVA][DROBETA] = 120;

		map[CRAIOVA][PITESTI] = 138;

		map[RIMNICU_VILCEA][SIBIU] = 80;

		map[RIMNICU_VILCEA][PITESTI] = 97;

		map[RIMNICU_VILCEA][CRAIOVA] = 146;

		map[SIBIU][ORADEA] = 151;

		map[SIBIU][ARAD] = 140;

		map[SIBIU][YAGARAS] = 99;

		map[SIBIU][RIMNICU_VILCEA] = 80;

		map[YAGARAS][SIBIU] = 99;

		map[YAGARAS][BUCHAREST] = 211;

		map[PITESTI][RIMNICU_VILCEA] = 87;

		map[PITESTI][CRAIOVA] = 138;

		map[PITESTI][BUCHAREST] = 101;

		map[BUCHAREST][PITESTI] = 101;

		map[BUCHAREST][YAGARAS] = 211;

		map[BUCHAREST][GIURGIU] = 90;

		map[BUCHAREST][URZICENI] = 85;

		map[GIURGIU][BUCHAREST] = 90;

		map[URZICENI][BUCHAREST] = 85;

		map[URZICENI][VASLUI] = 142;

		map[URZICENI][HIRSOVA] = 98;

		map[VASLUI][IASI] = 92;

		map[VASLUI][URZICENI] = 142;

		map[IASI][NEAMT] = 87;

		map[IASI][VASLUI] = 92;

		map[NEAMT][IASI] = 87;

		map[HIRSOVA][URZICENI] = 98;

		map[HIRSOVA][EFORIE] = 86;

		map[EFORIE][HIRSOVA] = 86;

	}


	private void createNodes(){

		oradea = new Node(ORADEA);

		zerind = new Node(ZERIND);

		arad = new Node(ARAD);

		timisora = new Node(TIMISORA);

		lugoj = new Node(LUGOJ);

		mehadia = new Node(MEHADIA);

		drobeta = new Node(DROBETA);

		craiova = new Node(CRAIOVA);

		rimnicuVilcea = new Node(RIMNICU_VILCEA);

		sibiu = new Node(SIBIU);

		yagaras = new Node(YAGARAS);

		pitesti = new Node(PITESTI);

		bucharest = new Node(BUCHAREST);

		giurgiu = new Node(GIURGIU);

		urziceni = new Node(URZICENI);

		vaslui = new Node(VASLUI);

		iasi = new Node(IASI);

		neamt = new Node(NEAMT);

		hirsova = new Node(HIRSOVA);

		eforie = new Node(EFORIE);

	}

	
	private void initHashMap(){

		this.cities.put(ORADEA, oradea);

		this.cities.put(ZERIND, zerind);

		this.cities.put(ARAD, arad);

		this.cities.put(TIMISORA, timisora);

		this.cities.put(LUGOJ, lugoj);

		this.cities.put(MEHADIA, mehadia);

		this.cities.put(DROBETA, drobeta);

		this.cities.put(CRAIOVA, craiova);

		this.cities.put(RIMNICU_VILCEA, rimnicuVilcea);

		this.cities.put(SIBIU, sibiu);

		this.cities.put(YAGARAS, yagaras);

		this.cities.put(PITESTI, pitesti);

		this.cities.put(BUCHAREST, bucharest);

		this.cities.put(GIURGIU, giurgiu);

		this.cities.put(URZICENI, urziceni);

		this.cities.put(VASLUI, vaslui);

		this.cities.put(IASI, iasi);

		this.cities.put(NEAMT, neamt);

		this.cities.put(HIRSOVA, hirsova);

		this.cities.put(EFORIE, eforie);

	}


	private ArrayList<Node> findAdjNodes(int n){

		ArrayList<Node> adjNodes = new ArrayList<bfs_dfs.Node>();

		for(int i = 0; i < NUM_OF_CITIES; i++){

			if(map[n][i] != null){

				adjNodes.add(cities.get(i));

			}

		}

		return adjNodes;

	}

	private ArrayList<Edge> findAdjEdges(int n){

		ArrayList<Edge> adjEdges = new ArrayList<bfs_dfs.Edge>();

		for(int i = 0; i < NUM_OF_CITIES; i++){

			if(map[n][i] != null){

				Node child = new Node(i);
				adjEdges.add(new Edge(cities.get(n), child, map[n][i]));

			}

		}

		return adjEdges;

	}

	

	private void resetParam(){

		bfs_Num_Of_Nodes = 0;

		bfs_Max_NumOfNodes = 0;

		

		dfs_Num_Of_Nodes_= 0;

		dfs_Max_NumOfNodes= 0;


	}

	public ArrayList<Node> bfs(Node src, Node dest){
		
		Queue<Node> q = new LinkedList<Node>();

		q.add(src);


		ArrayList<Node> visited = new ArrayList<Node>();
		while(!q.isEmpty()){

			Node closed = q.remove();
			visited.add(closed);
	
			if(closed.city == dest.city) 
				break;
			
			closed.expandNode(findAdjNodes(closed.city), findAdjEdges(closed.city));
			
			bfs_Max_NumOfNodes++;
			
			ArrayList<Node> childNodes = closed.aNode;
			
			if(!childNodes.isEmpty()){
				
				for(int i = 0; i < childNodes.size(); i++){
					
					Node child = childNodes.get(i);
					
					if(!visited.contains(child) && !q.contains(child)){						
							 	q.add(child);
							 	
					}
				}
			}
		}

		bfs_Num_Of_Nodes= visited.size();

		return visited;

	}
	public ArrayList<Node> dfs(Node src, Node dest)

	{
		Stack<Node> st =new Stack<Node>();
		st.push(src);
		// a list to keep track of the visited nodes

		ArrayList<Node> visited = new ArrayList<Node>();
		while(!st.isEmpty())
		{
			Node last = st.pop();
			visited.add(last);
			// if destination has been reached

			if(last.city == dest.city) 
				break;
			last.expandNode(findAdjNodes(last.city), findAdjEdges(last.city));

			dfs_Max_NumOfNodes++;

			ArrayList<Node> childNodes = last.aNode;

			if(!childNodes.isEmpty()){

				for(int i = 0; i < childNodes.size(); i++){

					Node child = childNodes.get(i);

					if(!visited.contains(child) && !st.contains(child)){

						st.push(child);

					}
				}
			}
		}

		int dfs_Num_Of_Nodes = visited.size();

		return visited;

	}

	
	private class Node{

		private int city;

		private ArrayList<Node> aNode;

		private ArrayList<Edge> aEdge;

		private int tempCost = 0;

		public Node(int value){

			this.city = value;

		}

	

		public int getCost(){

			return this.tempCost;

		}

		@SuppressWarnings("unused")

		public void resetCost(){

			tempCost = 0;

		}
	
		public void expandNode(ArrayList<Node> aNode, 

				ArrayList<Edge> aEdge){

			this.aNode = aNode;

			this.aEdge = aEdge;

		}

		

		@SuppressWarnings("unused")

		public void print(){

			String s = "";

			s = s + ("Node: "+this.city);

			if(aNode != null){


				for(int i = 0; i < aNode.size(); i++){

					s = s + "\n open -> Node "+aNode.get(i).city + ": ";

					s = s + aEdge.get(i).cost + ", ";

				}

			}
			System.out.println(s);

		}

	}
	private class Edge{
		@SuppressWarnings("unused")
		private Node src;
		@SuppressWarnings("unused")
		private Node dest;
		private int cost;
		
		public Edge(Node src, Node dest, int cost){
			this.src = src;
			this.dest = dest;
			this.cost = cost;
		}
	}

	private class NodeComparator implements Comparator<Node>

	{
		@Override
		public int compare(Node x, Node y)
		{

			if (x.getCost() < y.getCost())
			{
				return -1;
			}

			if (x.getCost() > y.getCost())
			{
				return 1;
			}
			return 0;
		}
	}

	public static void main(String[] args) {

		bfs_dfs us = new bfs_dfs();	
		ArrayList<Node> bfsPath = us.bfs(us.timisora, us.bucharest);
		ArrayList<Node> dfsPath = us.dfs(us.timisora, us.bucharest);
	
		System.out.println("**Breadth First Search");

		for(int i = 0; i < bfsPath.size(); i++){
			bfsPath.get(i).print();
		}
		System.out.println("\n\n**Depth First Search");

		for(int i = 0; i < dfsPath.size(); i++){
			dfsPath.get(i).print();
		}

}
	
}