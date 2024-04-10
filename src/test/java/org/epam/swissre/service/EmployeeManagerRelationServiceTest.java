package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeManagerRelationServiceTest {

    @Test
    public void testCreateEmployeeManagerRelations() {
        // Create sample employees
        Employee ceo = new Employee(1, "John", "Doe", 10000, null);
        Employee manager1 = new Employee(2, "Alice", "Brown", 8000, 1);
        Employee manager2 = new Employee(3, "Bob", "Mayer", 7500, 1);
        Employee employee1 = new Employee(4, "Eva", "Jackson", 6000, 2);
        Employee employee2 = new Employee(5, "Peter", "Smith", 7000, 2);
        Employee employee3 = new Employee(6, "Jenna", "Black", 6500, 3);

        // Create a list of employees
        List<Employee> employees = Arrays.asList(ceo, manager1, manager2, employee1, employee2, employee3);

        // Create EmployeeManagerRelationService instance
        EmployeeManagerRelationServiceImpl service = new EmployeeManagerRelationServiceImpl();

        // Call createEmployeeManagerRelations method
        List<TreeNode<Employee>> employeeNodes = service.createEmployeeManagerRelations(employees);

        // Assert that the returned list is not null
        assertTrue(employeeNodes != null && !employeeNodes.isEmpty());

        // Assert CEO (root) node
        TreeNode<Employee> ceoNode = employeeNodes.get(0);
        assertEquals(ceo, ceoNode.getData());
        assertTrue(ceoNode.isRoot());

        // Assert manager nodes
        TreeNode<Employee> manager1Node = ceoNode.getChildren().get(0);
        assertEquals(manager1, manager1Node.getData());
        assertEquals(ceoNode, manager1Node.getParent());

        TreeNode<Employee> manager2Node = ceoNode.getChildren().get(1);
        assertEquals(manager2, manager2Node.getData());
        assertEquals(ceoNode, manager2Node.getParent());

        // Assert employee nodes
        TreeNode<Employee> employee1Node = manager1Node.getChildren().get(0);
        assertEquals(employee1, employee1Node.getData());
        assertEquals(manager1Node, employee1Node.getParent());

        TreeNode<Employee> employee2Node = manager1Node.getChildren().get(1);
        assertEquals(employee2, employee2Node.getData());
        assertEquals(manager1Node, employee2Node.getParent());

        TreeNode<Employee> employee3Node = manager2Node.getChildren().get(0);
        assertEquals(employee3, employee3Node.getData());
        assertEquals(manager2Node, employee3Node.getParent());
    }

}