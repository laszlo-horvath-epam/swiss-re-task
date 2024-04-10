package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeReportingLineValidatorServiceTest {

    @Test
    public void testValidateEmployeeWithinDepthLimit() {
        // Create Employee node with depth level within limit
        TreeNode<Employee> employeeNode = new TreeNode<>(
                new Employee(1, "John", "Doe", 5000, 10)
        );

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call validateEmployee method with depth level within limit
        EmployeeReportingLineValidatorService service = new EmployeeReportingLineValidatorService();
        service.validateEmployee(employeeNode, 3);

        // Assert printed output (expecting no output for depth level within limit)
        assertTrue(outContent.toString().isBlank());
    }

    @Test
    public void testValidateEmployeeExceedingDepthLimit() {
        // Create Employee node with depth level exceeding limit
        TreeNode<Employee> employeeNode = new TreeNode<>(
                new Employee(1, "John", "Doe", 5000, 10)
        );

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call validateEmployee method with depth level exceeding limit
        EmployeeReportingLineValidatorService service = new EmployeeReportingLineValidatorService();
        service.validateEmployee(employeeNode, 6);

        // Assert printed output
        String expectedMessage = String.format("Employee found with reporting line length of 6 , which is longer than the maximum depth of 5 \n" +
                "Employee details: %s\n", employeeNode.getData().toString());
        assertEquals(expectedMessage, outContent.toString().trim());
    }

}
