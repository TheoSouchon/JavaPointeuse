package fr.rclens.projetpointeuse.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import fr.rclens.projetpointeuse.facade.EmployeeManager;
import fr.rclens.projetpointeuse.facade.IDepartment;
import fr.rclens.projetpointeuse.facade.IEmployee;

/**
 * Entity class to represent a company
 */
public class Company implements EmployeeManager, Serializable {

	private static final long serialVersionUID = -420133801413785015L;
	private static Company instance = null;
	private List<IEmployee> employeeList;
	private List<IDepartment> departmentList;
	
	
	/**
	 * @brief Default constructor of {@link Company}
	 */
	protected Company() {
		employeeList = new LinkedList<IEmployee>();
		departmentList = new LinkedList<IDepartment>();
	}
	/**
	 * @brief allow loading data saved in a file
	 * @param filename, the name of the file
	 */
	public void loadData(String filename) {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			try {
				departmentList = (List<IDepartment>) ois.readObject();
				employeeList = (List<IEmployee>) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save date with serialization
	 * 
	 * @param filename	The name of destination file for saving data
	 */
	public void saveData(String filename) {
		File file = new File(filename);
		file.delete();
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, false))) {
			oos.writeObject(departmentList);
			oos.flush();
			oos.writeObject(employeeList);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @brief get the unique instance of this class or create it before if it doesn't exist
	 * @return return the unique instance of Company
	 */
	public static Company getInstance() {
		if(instance == null) {
			instance = new Company();
			File f = new File("test.dat");
			if(f.exists()) {
				instance.loadData("test.dat");
			}
		}
		return instance;
	}

	/**
	 * @brief generate a random unique UUID
	 * @return the generated UUID
	 */
	private UUID generateUniqueRandomUUID() {
		UUID res;
		boolean isUnique = true;
		do {
			res = UUID.randomUUID();
			for(IEmployee emp : employeeList) {
				if(emp.getUUID().equals(res)) {
					isUnique = false;
					break; // on sort de la boucle si on trouve un doublon et on essaye d'en générer un nouveau
				}
			}
		} while(!isUnique);
		return res;
	}

	/**
	 * @brief Create a new employee and add it to the company
	 * @re
	 */
	@Override
	public IEmployee createEmployee(String name, String firstname) {
		Employee emp = new Employee(name, firstname);
		emp.setUUID(generateUniqueRandomUUID());
		employeeList.add(emp);
		System.out.println("Employee added : "+emp.getUUID());
		return emp;
	}

	/**
	 * @brief get an employee by his UUID or null if it doen't exist
	 * @return the employee corresponding to the given UUID
	 */
	@Override
	public IEmployee getEmployee(UUID id) {
		for(IEmployee emp : employeeList) {
			if(emp.getUUID().equals(id)) {
				return emp;
			}
		}
		return null;
	}

	/**
	 * @brief delete the employee corresponding to the given UUID if it exist
	 * @param id the UUID of the employee to delete
	 */
	@Override
	public void deleteEmployee(UUID id) {
		IEmployee emp = getEmployee(id);
		if(emp != null) {
			employeeList.remove(emp);
			if(emp.getDepartment() != null) {
				emp.getDepartment().removeEmployee(emp);
			}
		}
	}
	/**
	 * @brief create a department with the name "nameDepartment"
	 * @param String that contains the department name
	 */
	@Override
	public IDepartment createDepartment(String nameDepartment) {
		Department department = new Department(nameDepartment);
		departmentList.add(department);
		return department;
	}

	/**
	 * Get all employees from a specific department
	 * 
	 * @param 	Param	The specific department
	 * @return	A list of employees
	 */
	@Override
	public List<IEmployee> getAllEmployeesFromDepartment(IDepartment Param) {
		return Param.getEmployeesList();
	}

	/**
	 * Get all employees of the company
	 * 
	 * @return The list of all employees
	 */
	@Override
	public List<IEmployee> getAllEmployees() {
		return employeeList;
	}
	
	/**
	 * Get all departments
	 * 
	 * @return The list of all departments
	 */
	@Override
	public List<IDepartment> getAllDepartments(){
		return departmentList;
	}
	
	/**
	 * Delete a specific department
	 * @param nameDepartment, name of the department we want to delete
	 */
	public void deleteDepartment(String nameDepartment) {
		IDepartment buf = null;
		for (IDepartment cur : departmentList) {
			if (cur.getName() == nameDepartment) {
				buf = cur;
				break;
			}
		}
		for (IEmployee cur :buf.getEmployeesList() ) {
			((Employee)cur).removeDepartment();
		}
		departmentList.remove(buf);
	}
	
	/**
	 * Get a specific department
	 * @param nameDepartment,name of the department we want
	 * @return The IDepartement type of this department
	 */
	public IDepartment getDepartment(String nameDepartment) {
		for (IDepartment cur : departmentList) {
			if (cur.getName() == nameDepartment) {
				return cur;
			}
		}
		return null;
	}
}
