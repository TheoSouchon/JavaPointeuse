package fr.rclens.projetpointeuse.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.rclens.projetpointeuse.facade.IEmployee;

public class TestCompany {

	@Test
	public void Test_Company_CreateEmployee() {
		Company Test = new Company();
		Employee Param1 = new Employee("Florian","Sotoca" );
		Employee Param2 = new Employee("Jules" , "Keita");
		Test.createEmployee("Florian", "Sotoca");
		Test.createEmployee("Jules","Keita");
		Param1.setUUID(Test.getAllEmployees().get(0).getUUID());
		Param2.setUUID(Test.getAllEmployees().get(1).getUUID());
		List<IEmployee> testList = new LinkedList<IEmployee>();
		testList.add(Param1);
		testList.add(Param2);
		assertEquals(testList,Test.getAllEmployees());
	}
	/*@Test
	public void Test_Instance() {
		Company test1 = new Company(); 
		test1.createEmployee("Florian", "Sotoca");
		Company test2 = new Company();
		test2.createEmployee("jules", "Keita");
		assertEquals(test1,test2);
	}*/
	@Test
	public void Test_createDepartment() {
		Company test = new Company();
		test.createDepartment("Attaque");
		test.createDepartment("Milieu");
		List<Department> list = new LinkedList<Department>();
		Department Param1 = new Department("Attaque");
		Department Param2 = new Department("Milieu");
		list.add(Param1);
		list.add(Param2);
		assertEquals(list,test.getAllDepartments());
	}
	@Test
	public void Test_getEmployee() {
		Company Test = new Company();
		IEmployee Param1 = Test.createEmployee("Florian", "Sotoca");
		assertEquals(Test.getEmployee(Param1.getUUID()),Param1);
		
	}
	@Test
	public void Test_saveData() {
		Company test = new Company();
		Company test2 = new Company();
		test.createDepartment("Attaque");
		test.createDepartment("Milieu");
		List<Department> list = new LinkedList<Department>();
		Department Param1 = new Department("Attaque");
		Department Param2 = new Department("Milieu");
		list.add(Param1);
		list.add(Param2);
		
		test.saveData("test.dat");
		
		test2.loadData("test.dat");
		assertEquals(test.getAllDepartments(),test2.getAllDepartments());
		
	}
};