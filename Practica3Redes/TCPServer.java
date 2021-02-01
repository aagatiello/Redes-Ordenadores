package org.practica3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String args[]) {
		try {
			// Establece número de puerto a utilizar
			int serverPort = 7896;

			// Instancia que espera peticiones del cliente
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {

				// Dejamos conectado servidor esperando a cliente
				Socket clientSocket = listenSocket.accept();

				// Se establece conexión y espera nuevo cliente.
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			// Asocia el socket con el del cliente
			clientSocket = aClientSocket;

			// Crea flujos de entrada y salida de datos y lo asocia al socket cliente
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try {
			// Recibe datos enviados por el cliente
			String data = in.readUTF();
			out.writeUTF(data);
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				/* close failed */}
		}

	}
}