package fr.rclens.projetpointeuse.model;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;

import fr.rclens.projetpointeuse.facade.EmployeeManager;
import fr.rclens.projetpointeuse.facade.ICheck;

/**
 * Entity class to represent a TCPReceiver
 */
public class TCPReceiver implements Runnable {

	private Socket sock;
	private EmployeeManager empManager;
	
	/**
	 * @brief constructor for the class {@link TCPReceiver}
	 * 
	 * @param s : Socket (the socket connected to the other application)
	 */
	public TCPReceiver(Socket s) {
		sock = s;
		empManager = Company.getInstance();
	}
	
	/**
	 * @brief Method run to get the data from the other application
	 */
	@Override
	public void run() {
		System.out.println("Debut reciver");
		try(ObjectInputStream osr = new ObjectInputStream(sock.getInputStream())) {
			while(true) {
				ICheck check = (ICheck) osr.readObject();
				System.out.println(check.getDate() + " : " + check.getUUID());
				if(!(check.getDate().getDayOfWeek().equals(DayOfWeek.SATURDAY) || check.getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
					Employee emp = (Employee)empManager.getEmployee(check.getUUID());
					emp.addTimeSheetToCalendar(check);
				}
			}
		} catch (EOFException eof) {
			System.out.println("fin de lecture");
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}