package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeSalaryValidatorServiceTest {

    @Test
    public void testValidateManagerSalaryBelowMinimum() {

        // Create employees with low manager salary
        TreeNode<Employee> manager = createManager(2200, 2000);

        // Set output stream to read validation content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call validate method
        EmployeeValidatorService validatorService = new EmployeeSalaryValidatorService();
        validatorService.validateEmployee(manager);

        // Assert validation failed
        String expectedSalaryMessage = String.format(
                "Manager has a salary of %d which is %.1f less than the %.1f%% of their subordinates average salary\nManager details: %s\n",
                2200, 200.0, 120.0, manager.getData().toString()
        );

        assertEquals(expectedSalaryMessage, outputStream.toString().trim());
    }

    @Test
    public void testValidateManagerSalaryAboveMaximum() {

        // Create employees with high manager salary
        TreeNode<Employee> manager = createManager(4000, 2000);

        // Set output stream to read validation content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call validate method
        EmployeeValidatorService validatorService = new EmployeeSalaryValidatorService();
        validatorService.validateEmployee(manager);

        // Assert validation failed
        String expectedSalaryMessage = String.format(
                "Manager has a salary of %d which is %.1f more than the %.1f%% of their subordinates average salary\nManager details: %s\n",
                4000, 1000.0, 150.0, manager.getData().toString()
        );

        assertEquals(expectedSalaryMessage, outputStream.toString().trim());
    }

    @Test
    public void testValidateManagerSalaryInRange() {

        //Create employee nodes with in-range manager salary
        TreeNode<Employee> manager = createManager(2700, 2000);

        // Set output stream to read validation content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call validate method
        EmployeeValidatorService validatorService = new EmployeeSalaryValidatorService();
        validatorService.validateEmployee(manager);

        // Assert validation succeeded (no message printed)
        assertTrue(outputStream.toString().isBlank());
    }

    @Test
    public void testValidateNoSubordinates() {

        //Create employee with no subordinates
        TreeNode<Employee> employeeNode = new TreeNode<>(new Employee(1, "John", "Doe", 2700, 10));

        // Set output stream to read validation content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call validate method
        EmployeeValidatorService validatorService = new EmployeeSalaryValidatorService();
        validatorService.validateEmployee(employeeNode);

        // Assert validation succeeded (no message printed)
        assertTrue(outputStream.toString().isBlank());
    }

    private TreeNode<Employee> createManager(int managerSalary, int subordinateSalary) {

        TreeNode<Employee> managerNode = new TreeNode<>(new Employee(1, "John", "Doe", managerSalary, 10));
        TreeNode<Employee> subordinate1 = new TreeNode<>(new Employee(2, "Jack", "Doe", subordinateSalary, 1));
        TreeNode<Employee> subordinate2 = new TreeNode<>(new Employee(3, "Jim", "Doe", subordinateSalary, 1));

        subordinate1.setParent(managerNode);
        subordinate2.setParent(managerNode);

        return managerNode;
    }


}
