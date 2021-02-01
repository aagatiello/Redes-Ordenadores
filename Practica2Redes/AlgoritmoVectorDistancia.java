package org.practica2;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoVectorDistancia {

	private Grafo network;
	private List<Router> routers;

	public AlgoritmoVectorDistancia(Grafo network) {
		this.network = network;
		this.routers = new ArrayList<Router>();
	}

	public void addRouters(Router r) {
		routers.add(r);
	}

	private void generateSummary() {
		for (int i = 0; i < network.getNumberOfNodes(); i++) {
			System.out.println("Router: " + i);

		}
	}

	public void sendNeighbours(int idRouter) {
		for (int i = 0; i < routers.size(); i++) {
			if (idRouter != routers.get(i).getId()) { 
				if (routers.get(idRouter).isNeigh(routers.get(i).getId())) {
					// aux = vecinos de mi vecinos
					int[][] aux = routers.get(i).getNeighbours();        
					for (int row = 0; row < aux.length; row++) {
						// recorro aux filas
						if (row != idRouter)                        
							// recorro aux columnas
							for (int col = 0; col < aux.length; col++) { 
								// tu via(col) no la inspeccionas
								if (col != idRouter) {              
									// si tu vecino tiene vecinos
									if (aux[row][col] != 0) {      
										// me guardo el costo a su vecino
										int value = aux[row][col];       
										// actualizo tabla de minimas distancias
										routers.get(idRouter).actualizateTables(routers.get(i).getId(), row, col,
												value);                
									}
								}
							}
					}
				}
			}
		}
	}

	public void start() {
		for (int iteracion = 0; iteracion < routers.size(); iteracion++) {
			for (int i = 0; i < routers.size(); i++) {
				sendNeighbours(routers.get(i).getId());
			}
			for (int r = 0; r < routers.size(); r++) {
				routers.get(r).actualizate();
			}
		}
		generateSummary();
	}

}