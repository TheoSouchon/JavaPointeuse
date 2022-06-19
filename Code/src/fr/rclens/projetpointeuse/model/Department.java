package fr.rclens.projetpointeuse.model;

import java.io.Serializable;
import java.util.*;

import fr.rclens.projetpointeuse.facade.IDepartment;
import fr.rclens.projetpointeuse.facade.IEmployee;

/**
 * Entity class to represent a deaprtment
 */
public class Department implements IDepartment, Serializable{
	private static final long serialVersionUID = -8546339906165609941L;
	private String name;
	private List<IEmployee> listEmployee;
	
	/**
	 * @brief Constructor of {@link Department}
	 * 
	 * @param name,	the name of the {@link Department}
	 */
	public Department(String name) {
		this.name = name;
		listEmployee = new LinkedList<IEmployee>();
	}

	/**
	 * @brief Add an employee in this {@link Department}
	 * @param employee : IEmployee (an employee)
	 */
	public void addEmployee(IEmployee employee) {
		if(!listEmployee.contains(employee)) {
			listEmployee.add(employee);
			((Employee)employee).addDepartment(this);
		}
	}

	/**
	 * Re-define the comparator method equals for the {@link Department}
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Department)) {
			return false;
		}
		
		Department dep = (Department)obj;
		
		if(dep.name.equals(name) &&
			dep.getEmployeesList().equals(listEmployee)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @brief Get the name of this {@link Department}
	 * @return String (the name of the {@link Department}
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * @brief Get the list of employees for this {@link Department}
	 * @return List<IEmployee> (the list of employees)
	 */
	@Override
	public List<IEmployee> getEmployeesList() {
		return listEmployee;
	}
	
	/**
	 * @brief remove an employee in this {@link Department}
	 * @param emp : IEmployee (the employee to remove)
	 */
	@Override
	public void removeEmployee(IEmployee emp) {
		if (listEmployee.contains(emp)) {
			listEmployee.remove(emp);
			((Employee)emp).removeDepartment();
		}
	}
}

