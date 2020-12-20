package ai1;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.*;
import javax.xml.*;

public class Hill {
	public class Graph{
		private List<Node> nodes=new ArrayList<Node>();
		private int[][] adjesancyMatrix;
		private int size;
		private Node currentNode;
		private List<Node> currentPath=new ArrayList<Node>();
		
		public Graph() {
			
		}
		public Graph(List<Node> nodes) {
			this.nodes=nodes;
			
		}
		public List<Node> getNodes(){
			return nodes;
		}
		public int[][] getAdjesancyMatrix(){
			return adjesancyMatrix;
		}
		public void setAdjesancyMatrix(int[][] adjesancyMatrix) {
			this.adjesancyMatrix=adjesancyMatrix;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size=size;
		}
		public Node getCurrentNode() {
			return currentNode;
		}
		public void setCurrentNode(Node currentNode) {
			this.currentNode=currentNode;
		}
		
		public List<Node> getCurrentPath(){
			return currentPath;
		}
		
		public void setCurrentPath(List<Node> currentPath) {
			this.currentPath=currentPath;
		}
		public void addNodeToCurrentPath(Node node) {
			currentPath.add(node);
		}
		
		public void connectNode(Node start,Node end) {
			if(adjesancyMatrix==null) {
				size=nodes.size();
				adjesancyMatrix=new int[size][size];
			}
			int startIndex=nodes.indexOf(start);
			int endIndex=nodes.indexOf(end);
			
			adjesancyMatrix[startIndex][endIndex]=1;
			adjesancyMatrix[endIndex][startIndex]=1;
		}
		public void clearVisitedNodes() {
			int i=0;
			while(i<size) {
				nodes.get(i).setVisited(false);
				i++;
			}
		}
		public Node getMinUnvisitedNeibour(Node currentNode) {
			Node minNode=null;
			double minHuristcValue=Double.MAX_VALUE;
			int currentNodeIndex=nodes.indexOf(currentNode);
			
			for(int j=0;j<nodes.size();j++) {
		if(adjesancyMatrix[currentNodeIndex][j]==1&&!nodes.get(j).isVisited()&&nodes.get(j).getHeuristic()<=minHuristcValue) {
			minNode=nodes.get(j);
			minHuristcValue=nodes.get(j).getHeuristic();
		}
			}
			if(minNode!=null) {
				nodes.get(nodes.indexOf(minNode)).setVisited(true);
				setCurrentNode(minNode);
				addNodeToCurrentPath(minNode);
			}
			return minNode;
		}
		public Node getNodeByLabelName(String label) {
			Node node=null;
			for(int i=0;i<size;i++){
				if(nodes.get(i).getLabel().equals(label)) {
					node=nodes.get(i);
					break;
					}
				}
			return node;
		}
		
		
	public class Node {
	
	private String label;
	private boolean isVisited = false;
	private double heuristic;
	private String path;
	/**
	 * @param label : The node name
	 * 
	 */
	public Node(String label) {
		this.label = label;
	}
	
	/**
	 * 
	 * @param label : The node name
	 * @param heuristic : heuristic value of the node 
	 */
	public Node(String label,double heuristic) {
		this.label = label;
		this.heuristic = heuristic;
		this.path="";
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
	public void hillClimb(Graph graph, Node start, Node end) {
		graph.setCurrentNode(start);
		graph.addNodeToCurrentPath(start);
		int currentIndex=graph.getNodes().indexOf(start);
		graph.getNodes().get(currentIndex).setVisited(true);
		
		if(start.equals(end)) {
			System.out.println("Start and end Nodes are equal");
		}else {
			System.out.println("----------started hill climb----------");
			System.out.print(start.getLabel());
			Node currentNode;
			do {
				currentNode=graph.getMinUnvisitedNeighbour(graph.getCurrentNode());
				if(currentNode==null) {
					System.out.println("\nReached dead end...Not able to continuee.. ");
				}else if(currentNode.equals(end)) {
					System.out.print("-->"+currentNode.getLabel());
					System.out.println("\nReached the destination .. Thank you..");
				}else {
					System.out.print("--->"+currentNode.getLabel());
				}
				
			}while(currentNode!=null&&!currentNode.equals(end));
		}
	}

}
	public static void main(String args[]) {
		Hill h=new Hill();
		try {
			File configXmlFile=new File("C:\\Users\\tmddms2292\\Desktop\\straight-line.xml");
			DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
			Document doc=dBuilder.parse(configXmlFile);
			doc.getDocumentElement().normalize();
			String city=doc.getElementsByTagName("city").item(0).getFirstChild().getTextContent();
			
			Graph graph=new Graph();
			graph.setSize(20);
			
			String dist=doc.getElementsByTagName("distance").item(0).getFirstChild().getTextContent();
			//NodeList edgeList=doc.getElementsByTagName("distance");
			List<String> nodelist=Arrays.asList(city.split("\\s*,\\s*"));
			List<String> nodedist=Arrays.asList(dist.split("\\s*,\\s*"));
			List<Node> list=new ArrayList<Node>();
			for(int i=0;i<graph.getSize();i++) {
				list.add(new Node(nodelist.get(i).trim(),Double.parseDouble(nodelist.get(i).trim())));
			}
			graph.setNodes(list);
			
			for(int i=0;i<edgeList.getLength();i++) {
				
			}
		}
	}	
}
