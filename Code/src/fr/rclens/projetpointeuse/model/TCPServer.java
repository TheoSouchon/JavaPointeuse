package fr.rclens.projetpointeuse.model;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Entity class to represent a TCPServer
 */
public class TCPServer implements Runnable {
	private InetSocketAddress isA;
	private boolean mustClose;
	private ServerSocket server;

	public TCPServer(String ipAddress, int port) {
		mustClose = false;
		this.isA = new InetSocketAddress(ipAddress, port);
	}

	/**
	 * @brief Method run to connect the socket to the other application
	 */
	@Override
	public void run() {
		mustClose = false;
		try {
			server = new ServerSocket(isA.getPort());
			while (!mustClose) {
				Socket sock = null;
				sock = server.accept();
				new Thread(new TCPReceiver(sock)).start();
			}
		}
		catch (BindException be) {
			System.out.println("The server coundn't start. Try to change the IP and the port and try again.");
		}
		catch (IOException ioe) {
			//ioe.printStackTrace();
			try {
				if (server != null) {
					server.close();
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	/**
	 * @brief Method to stop the connect with the other application
	 */
	public void stop() {
		mustClose = true;
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To change the adress and the port for the socket
	 * @param ipAddress : String (the list of number as a String)
	 * @param port : int (the port number)
	 */
	public void changeAddress(String ipAddress, int port) {
		this.isA = new InetSocketAddress(ipAddress, port);
	}

	/**
	 * Get the ipAdress
	 * @return String (the ipAdress)
	 */
	public String getIpAddress() {
		return this.isA.getHostName();
	}

	/**
	 * Get the port number
	 * @return int (the port number)
	 */
	public int getPort() {
		return this.isA.getPort();
	}

}
