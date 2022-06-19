package fr.rclens.projetpointeuse.facade;

import java.util.List;
import java.util.UUID;

import fr.rclens.projetpointeuse.model.Department;
import fr.rclens.projetpointeuse.model.Employee;

/**
 * Entity class to represent the interface for our source application (the company)
 */
public interface EmployeeManager {

	/**
	 * create and return a new IEmployee with the given name and firstname
	 * @param name the name (lastname) of the employee
	 * @param firstname the firstname of the employee
	 * @return the IEmployee newly created
	 */
	public IEmployee createEmployee(String name , String firstname);
	
	/**
	 * create and return a new IDepartement with the given name
	 * @param name the name of the departement
	 * @return the IDepartment newly created
	 */
	public IDepartment createDepartment(String name);
	
	/**
	 * return the IEmployee corresponding to the given UUID or null if it doesn't exist
	 * @param id the UUId of the IEmployee
	 * @return the IEmployee with the given UUID or null if it doesn't exist
	 */
	public IEmployee getEmployee(UUID id);
	
	/**
	 * return the list of all the IEmployee of the company
	 * @return the list of all the IEmployee of the company
	 */
	public List<IEmployee> getAllEmployees();
	
	/**
	 * return the list of all the IEmployee in the given departement
	 * @param Param the IDepartment with the wanted employees
	 * @return the list of all the IEmployee in the given departement
	 */
	public List<IEmployee> getAllEmployeesFromDepartment(IDepartment Param);
	
	/**
	 * delete the IEmployee with the given UUID
	 * @param id the UUID of the employee to delete
	 */
	public void deleteEmployee(UUID id);
	
	/**
	 * return the list of all the departments of the company
	 * @return the list of all the departments of the company
	 */
	public List<IDepartment> getAllDepartments();
	
	/**
	 * return the IDepartment with the given name
	 * @param nameDepartment the name of the wanted IDepartment
	 * @return the IDepartment with the given name
	 */
	public IDepartment getDepartment(String nameDepartment);
	
	/**
	 * serialize all the datas into a file with the given name
	 * @param filename the name of the file to serialize the datas
	 */
	public void saveData(String filename);
}
