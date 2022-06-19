package fr.rclens.projetpointeuse.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.rclens.projetpointeuse.facade.IEmployee;

public class TestInstance {
	@Test
	public void Test_Company_Instance() {
		Company Test = Company.getInstance();
		Company Test2 = Company.getInstance();
		Test.createEmployee("Florian","Sotoca");
		assertEquals(Test.getAllEmployees(),Test2.getAllEmployees());
	}
}
