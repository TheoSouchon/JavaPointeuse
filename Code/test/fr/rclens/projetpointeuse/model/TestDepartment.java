package fr.rclens.projetpointeuse.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.rclens.projetpointeuse.facade.IDepartment;
import fr.rclens.projetpointeuse.facade.IEmployee;

public class TestDepartment {
	
	@Test
	public void Test_Department_addEmployee() {
		Company Test = new Company();
		IDepartment param1 = Test.createDepartment("Attaquant");
		IEmployee param = Test.createEmployee("Florian", "Sotoca");
		Test.getDepartment("Attaquant").addEmployee(param);
		List<IEmployee> testListe = new LinkedList<IEmployee>();
		testListe.add(param);
		assertEquals(Test.getEmployee((param.getUUID())).getDepartment(),param1);
	}
	@Test
	public void Test_Department_removeEmployee() {
		Company Test = new Company();
		Test.createDepartment("Attaquant");
		IEmployee param = Test.createEmployee("Florian", "Sotoca");
		IEmployee param1 = Test.createEmployee("Seko", "Fofana");
		Test.getDepartment("Attaquant").addEmployee(param);
		Test.getDepartment("Attaquant").addEmployee(param1);
		Test.getDepartment("Attaquant").removeEmployee(param1);
		List<IEmployee> testListe = new LinkedList<IEmployee>();
		testListe.add(param);
		assertEquals(Test.getAllEmployeesFromDepartment(Test.getDepartment("Attaquant")) ,testListe);
	}
}
