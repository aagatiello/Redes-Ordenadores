package org.practica3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class TCPClient {

	public static void latenciaMedia(Vector<Double> times) {
		double sum = 0, media = 0;
		for (int i = 0; i < times.size(); i++) {
			sum = sum + times.elementAt(i);
		}
		media = sum / times.size();
		System.out.println("Latencia media de 100 request: " + media + " ns");
	}

	// args proporciona el mensaje
	public static void main(String args[]) {
		Socket s = null;
		try {
			// Establece puerto a utilizar en la variable serverPort
			int serverPort = 7896;

			Vector<Double> times = new Vector<Double>();
			for (int i = 0; i < 100; i++) {
				long inicio = System.nanoTime();

				// Crea nuevo socket con el nombre de host y el puerto establecido anteriormente
				s = new Socket("localhost", serverPort);

				// Crea un nuevo flujo de entrada y salida de datos usando el socket creado.
				DataInputStream in = new DataInputStream(s.getInputStream());
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				out.writeUTF(args[0]);

				// Obtiene la respuesta del servidor
				String data = in.readUTF();

				long fin = System.nanoTime();
				double latencia = (double) (fin - inicio);
				times.addElement(latencia);

				System.out.println("Received en " + latencia + " ns: " + data);
			}
			s.close();
			latenciaMedia(times);
		} catch (UnknownHostException e) {
			System.out.println("Socket:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}
