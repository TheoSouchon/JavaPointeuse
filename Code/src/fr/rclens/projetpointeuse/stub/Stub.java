package fr.rclens.projetpointeuse.stub;

import fr.rclens.projetpointeuse.facade.EmployeeManager;
import fr.rclens.projetpointeuse.model.Company;

/**
 * Entity class to represent a class Stub
 * allowing to stock data for the application
 */
public class Stub {
	public static EmployeeManager managerData() {
		EmployeeManager managerEmployee = Company.getInstance();
		/*
		IEmployee IEmployee1 = managerEmployee.createEmployee("Theo", "Souchon");
		System.out.println("UUID employee 1 : " + IEmployee1.getUUID().toString());
		IEmployee IEmployee2 = managerEmployee.createEmployee("Vincent", "Brunet");
		IEmployee IEmployee3 = managerEmployee.createEmployee("Baptiste", "Asselin");
		IEmployee IEmployee4 = managerEmployee.createEmployee("Corentin", "Germain");

		System.out.println("UUID employee 1 : " + IEmployee1.getUUID().toString());

		managerEmployee.createDepartment("DI");
		managerEmployee.createDepartment("GEA");
		managerEmployee.createDepartment("DII");
		managerEmployee.getDepartment("DI").addEmployee(IEmployee1);
		managerEmployee.getDepartment("DI").addEmployee(IEmployee2);
		managerEmployee.getDepartment("GEA").addEmployee(IEmployee3);
		managerEmployee.getDepartment("DII").addEmployee(IEmployee4);

		// Add a planning to the employees
		IPlanning planning = new Planning();
		planning.setDayTimeSheet(DayOfWeek.MONDAY, new TimeSheet(LocalTime.of(8, 0), LocalTime.of(17, 0)));
		planning.setDayTimeSheet(DayOfWeek.TUESDAY, new TimeSheet(LocalTime.of(8, 0), LocalTime.of(17, 0)));
		planning.setDayTimeSheet(DayOfWeek.WEDNESDAY, new TimeSheet(LocalTime.of(8, 0), LocalTime.of(17, 0)));
		planning.setDayTimeSheet(DayOfWeek.THURSDAY, new TimeSheet(LocalTime.of(8, 0), LocalTime.of(17, 0)));
		planning.setDayTimeSheet(DayOfWeek.FRIDAY, new TimeSheet(LocalTime.of(8, 0), LocalTime.of(17, 0)));
		IEmployee1.setPlaning(planning);
		IEmployee2.setPlaning(planning);
		IEmployee3.setPlaning(planning);
		IEmployee4.setPlaning(planning);
		*/
		/*
		 * for (IEmployee employee : managerEmployee.getAllEmployees()) {
		 * employee.getAllDayCheck() }
		 */
		return managerEmployee;
	}

	/*
	 * public static ArrayList<IDepartment> DepartementData() {
	 * 
	 * ArrayList<IEmployee> departementList = new ArrayList<IEmployee>();
	 * EmployeeManager managerEmployee = Company.getInstance();
	 * departementList.add(managerEmployee.createEmployee("Souchon", "Theo"));
	 * departementList.add(managerEmployee.createEmployee("Brunet", "Vincent"));
	 * departementList.add(managerEmployee.createEmployee("Asselin", "Baptiste"));
	 * departementList.add(managerEmployee.createEmployee("Germain", "Corentin"));
	 * 
	 * // managerEmployee.createDepartment("DI"); //
	 * managerEmployee.createDepartment("GEA"); //
	 * managerEmployee.createDepartment("DII");
	 * 
	 * return departementList; }
	 */
}
