package fr.rclens.projetpointeuse.model;

/**
 * Entity class to test a local server (with 8080 as default port number)
 */
public class Test {
	public static void main(String[] args) {
		TCPServer server = new TCPServer("localhost", 8080);
		//server.start();
	}
}

// UUID Example : 123e4567-e89b-12d3-a456-556642440000