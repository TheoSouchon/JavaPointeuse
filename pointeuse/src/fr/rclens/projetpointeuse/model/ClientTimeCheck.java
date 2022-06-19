package fr.rclens.projetpointeuse.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.rclens.projetpointeuse.facade.ICheck;
import javafx.scene.control.Alert;

public class ClientTimeCheck implements Runnable {

	private Socket s;
	private InetSocketAddress isA;
	private ICheck newCheck;
	
	/**
	 * Create the clientTimeCheck with an IP, a port and a check
	 * @param ipAdress
	 * @param port
	 * @param newCheck
	 */
	public ClientTimeCheck(String ipAdress, int port, ICheck newCheck) {
		super();
		this.s = null;
		this.isA = new InetSocketAddress(ipAdress, port);
		this.newCheck = newCheck;
	}
	
	/**
	 * Create the socket
	 * @throws IOException
	 */
	public void setSocket() throws IOException {
		s = new Socket(isA.getHostName(), isA.getPort());
		s.setSoTimeout(10000);
	}
	
	/**
	 * Save the newCheck in the save.txt file
	 */
	public void saveNewCheck() {
		File file = new File("save.txt");
		ObjectOutputStream oos;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream(file));
			List<ICheck> checks = null;
			try {
				checks = (ArrayList<ICheck>) fileInput.readObject();
				checks.add(newCheck);
			}
			catch (IOException e) {
				checks = new ArrayList<>();
				checks.add(newCheck);
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			fileInput.close();
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(checks) ;
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send the new check and the saved checks to the main app
	 */
	@Override
	public void run() {
		// Envoi du nouveau check
		try {
			setSocket();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(this.newCheck);
			oos.close();
			s.close();
		}
		catch (IOException e) {
			System.out.println("Failed to send the new check, it will be saved locally.");
			saveNewCheck();
			System.out.println("Check saved successfully");
			return;
		}
		
		// Send saved checks
		ObjectOutputStream socketOutput = null;
		ObjectInputStream fileInput = null;
		ObjectOutputStream fileOutput = null;
		File inFile = new File("save.txt");
		File outFile =  new File("tmp.txt");
		List<ICheck> checks = null;
		int boucle = 0;
		try {
			setSocket();
			socketOutput = new ObjectOutputStream(s.getOutputStream());
			fileInput = new ObjectInputStream(new FileInputStream(inFile));
			checks = (ArrayList<ICheck>) fileInput.readObject();
			if (checks != null) {
				for (boucle = 0; boucle < checks.size(); boucle++) {
				    socketOutput.writeObject(checks.get(boucle));
				}
			}
			if (fileInput != null) {
				fileInput.close();
			}
			if (socketOutput != null) {
				socketOutput.close();
			}
			inFile.delete();
			inFile.createNewFile();
			s.close();
			System.out.println("All timechecks has been sent.");
		}
		catch (EOFException e) {
			try {
				if (fileInput != null) {
					fileInput.close();
				}
				if (socketOutput != null) {
					socketOutput.close();
				}
				//inFile.delete();
				s.close();
				System.out.println("All timechecks has been sent.");
				return;
			}
			catch (IOException e2) {
				System.out.println("ERROR WHILE CLOSING FILES STREAMS");
			}
		}
		catch (IOException e) {
			try {
				System.out.println("Failed to send some of the saved timechecks.");
				e.printStackTrace();
				if (outFile.exists()) {
					outFile.delete();
				}
				outFile.createNewFile();
				fileOutput = new ObjectOutputStream(new FileOutputStream(outFile));
				List<ICheck> checksNotSend = new ArrayList<>();
				if (checks != null) {
					for (boucle = boucle; boucle < checks.size(); boucle++) {
						checksNotSend.add(checks.get(boucle));
					}
				}
				socketOutput.writeObject(checksNotSend);
				if (fileOutput != null) {
					fileOutput.close();
				}
				if (fileInput != null) {
					fileInput.close();
				}
				if (socketOutput != null) {
					socketOutput.close();
				}
				if (inFile.exists()) {
					inFile.delete();
				}
				outFile.renameTo(inFile);
				s.close();
				System.out.println("All timechecks has been saved.");
			}
			
			catch (IOException e2) {
				try {
					if (fileOutput != null) {
						fileOutput.close();
					}
					if (fileInput != null) {
						fileInput.close();
					}
					if (socketOutput != null) {
						socketOutput.close();
					}
					if (inFile.exists()) {
						inFile.delete();
					}
					outFile.renameTo(inFile);
					s.close();
					System.out.println("All timechecks has been saved.");
				}
				catch (IOException e3) {
					System.out.println("ERROR WHILE CLOSING FILES STREAMS");
				}
				return;
			}
			return;
		} 
		catch (ClassNotFoundException e) {
			return;
		}
	}

}
