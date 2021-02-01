package org.practica2;

public class Grafo {

	private int[][] adjMatrix;
	private final int numVertices;

	public Grafo(int numVertices) {
		this.numVertices = numVertices;
		adjMatrix = new int[numVertices][numVertices];
	}

	public Grafo(int numVertices, int[][] adjMatrix) {
		this.numVertices = numVertices;
		this.adjMatrix = adjMatrix;

	}

	public void addNode(int i, int j, int value) {
		adjMatrix[i][j] = value;
	}

	public int getNode(int i, int j) {
		return adjMatrix[i][j];
	}

	public int getNumberOfNodes() {
		return numVertices;
	}

	public boolean isNode(int i, int j) {
		return adjMatrix[i][j] != 0;
	}

	public static void main(String[] args) {

		Grafo grafo = new Grafo(7);
		grafo.addNode(0, 1, 2);
		grafo.addNode(0, 2, 5);
		grafo.addNode(0, 6, 1);
		grafo.addNode(1, 0, 2);
		grafo.addNode(1, 2, 3);
		grafo.addNode(1, 6, 3);
		grafo.addNode(2, 1, 3);
		grafo.addNode(2, 3, 1);
		grafo.addNode(2, 5, 1);
		grafo.addNode(2, 6, 3);
		grafo.addNode(3, 2, 1);
		grafo.addNode(3, 5, 2);
		grafo.addNode(5, 2, 1);
		grafo.addNode(5, 6, 1);
		grafo.addNode(6, 0, 1);
		grafo.addNode(6, 1, 2);
		grafo.addNode(6, 1, 2);
		grafo.addNode(6, 2, 3);
		grafo.addNode(6, 5, 1);

		Router ro = new Router(grafo, 6);
		ro.setNeighbours();

		System.out.println("-------------------------------");

		AlgoritmoVectorDistancia vd = new AlgoritmoVectorDistancia(grafo);
		vd.start();
		System.out.println(vd);

		System.out.println("-------------------------------");

		AlgoritmoInundacion in = new AlgoritmoInundacion(grafo);
		in.start();
		System.out.println(in);

	}
}
