package org.practica2;

public class Router {

	private int[][] intern_transition;
	private int[][] lessvalues;
	private Grafo network;
	private int routerId;
	private int[][] table;

	public Router(Grafo network, int routerId) {
		this.routerId = routerId;
		this.network = network;
		this.table = new int[this.network.getNumberOfNodes()][this.network.getNumberOfNodes()];
		for (int i = 0; i < this.network.getNumberOfNodes(); i++) {
			for (int j = 0; j < this.network.getNumberOfNodes(); j++) {
				this.table[i][j] = 0;
			}
		}
		this.lessvalues = new int[this.network.getNumberOfNodes()][this.network.getNumberOfNodes()];
		for (int i = 0; i < this.network.getNumberOfNodes(); i++) {
			for (int j = 0; j < this.network.getNumberOfNodes(); j++) {
				this.lessvalues[i][j] = 0;
			}
		}
		this.intern_transition = new int[this.network.getNumberOfNodes()][this.network.getNumberOfNodes()];
		for (int i = 0; i < this.network.getNumberOfNodes(); i++) {
			for (int j = 0; j < this.network.getNumberOfNodes(); j++) {
				this.intern_transition[i][j] = 0;
			}
		}
	}

	public void actualizate() {
		for (int i = 0; i < this.network.getNumberOfNodes(); i++) {
			for (int j = 0; j < this.network.getNumberOfNodes(); j++) {
				this.table[i][j] = this.intern_transition[i][j];
			}
		}
	}

	public void actualizateTables(int id, int i, int j, int value) {
		if (intern_transition[i][id] != 0) {
			if (this.intern_transition[id][id] + value < intern_transition[i][id]) {
				this.intern_transition[i][id] = this.intern_transition[id][id] + value;
			}
		} else {
			this.intern_transition[i][id] = this.intern_transition[id][id] + value; // actualizo peso a "nuevos/o no"
																					// vecino
		}
		boolean encontrado = false;

		for (int col = 0; col < this.lessvalues.length; col++) { // quiero fijar el menor de la fila, recorro la fila i
																	// de less value
			if (!encontrado && this.lessvalues[i][col] != 0) {
				if (this.intern_transition[i][id] < this.lessvalues[i][col]) {
					this.lessvalues[i][id] = this.intern_transition[i][id];
					this.lessvalues[i][col] = 0;
					encontrado = true;
				}
				if (this.intern_transition[i][id] >= this.lessvalues[i][col]) {
					encontrado = true;
				}
			}
		}
		if (!encontrado) {
			this.lessvalues[i][id] = this.intern_transition[i][id];
		}
	}

	public int getId() {
		return this.routerId;
	}

	public int getMinValue(int i, int j) {
		return this.lessvalues[i][j];
	}

	public int[][] getMinValues() {
		return this.lessvalues;
	}

	public int[][] getNeighbours() {
		return this.table;
	}

	public int getNumberOfNodes() {
		return this.table.length;
	}

	public boolean isNeigh(int id) {
		boolean b = false;
		for (int i = 0; i < this.network.getNumberOfNodes(); i++) {
			if (table[i][id] != 0) {
				b = true;
			}
		}
		return b;
	}

	public void setInternTable() {
		for (int col = 0; col < network.getNumberOfNodes(); col++) {
			if (network.isNode(routerId, col)) {
				this.table[col][col] = network.getNode(routerId, col);
			}
		}
		for (int i = 0; i < this.network.getNumberOfNodes(); i++) {
			for (int j = 0; j < this.network.getNumberOfNodes(); j++) {
				this.intern_transition[i][j] = this.table[i][j];
			}
		}
		setLessValues();
	}

	public void setLessValues() {
		for (int i = 0; i < this.network.getNumberOfNodes(); i++) {
			int aux = 100000000;
			int row = 0, col = 0;
			boolean b = false;
			for (int j = 0; j < this.network.getNumberOfNodes(); j++) {
				if (this.table[i][j] != 0) {
					if (this.table[i][j] <= aux) {
						b = true;
						row = i;
						col = j;
						aux = this.table[i][j];
					}
				}
			}
			if (b)
				this.lessvalues[row][col] = aux; // !
		}
	}

	// Establece los vecinos
	public void setNeighbours() {
		for (int i = 0; i < network.getNumberOfNodes(); i++) {
			if (network.isNode(routerId, i)) {
				table[routerId][i] = network.getNode(routerId, i);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			s.append(i + ": ");
			for (int j : table[i]) {
				s.append(j + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

}