package org.practica3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

public class UDPClient {

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
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();
			byte[] m = args[0].getBytes();
			InetAddress aHost = InetAddress.getLocalHost();
			int serverPort = 6789;

			// Construye un datagrama para enviar el mensaje al servidor
			DatagramPacket request = new DatagramPacket(m, args[0].length(), aHost, serverPort);

			Vector<Double> times = new Vector<Double>();
			for (int i = 0; i < 100; i++) {
				long inicio = System.nanoTime();

				// Envía el datagrama
				aSocket.send(request);

				// Construye el DatagramPacket que contendrá la respuesta y recibe la respuesta
				// del servidor
				byte[] buffer = new byte[1000];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(reply);
				long fin = System.nanoTime();
				double latencia = (double) (fin - inicio);
				times.addElement(latencia);

				System.out.println("Reply en " + latencia + " ns: " + new String(reply.getData()));
			}
			aSocket.close();
			latenciaMedia(times);
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}
}