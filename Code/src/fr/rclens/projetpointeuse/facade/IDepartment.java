package fr.rclens.projetpointeuse.facade;

import java.util.List;

/**
 * Entity class to represent the interface of Department
 */
public interface IDepartment {
	
	/**
	 * return the name of this department
	 * @return the name of this department
	 */
	String getName();
	
	/**
	 * return the list of employees of this department
	 * @return the list of employees of this department
	 */
	List<IEmployee> getEmployeesList();
	
	/**
	 * add the given employee to this department
	 * @param emp the employee to add to the department
	 */
	void addEmployee(IEmployee emp);
	
	/**
	 * remove the given employee of this department
	 * @param emp the employee to remove from the department
	 */
	void removeEmployee(IEmployee emp);
}
