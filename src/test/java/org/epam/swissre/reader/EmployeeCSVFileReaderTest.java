package org.epam.swissre.reader;

import org.epam.swissre.model.Employee;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCSVFileReaderTest {

    @Test
    public void testReadEmployeesFromFile() {
        // Prepare test data
        String filePath = "src/test/resources/test_employees.csv";
        EmployeeFileReader reader = new EmployeeCSVFileReader();

        // Call readEmployees method
        List<Employee> employees = reader.readEmployees(filePath);

        // Assert that employees list is not null and contains expected number of entries
        assertNotNull(employees);
        assertEquals(4, employees.size());

        // Assert individual employee data
        assertEquals(1, employees.get(0).getId());
        assertEquals("John", employees.get(0).getFirstName());
        assertEquals("Doe", employees.get(0).getLastName());
        assertEquals(9000, employees.get(0).getSalary());
        assertNull(employees.get(0).getManagerId());

        assertEquals(2, employees.get(1).getId());
        assertEquals("Alice", employees.get(1).getFirstName());
        assertEquals("Smith", employees.get(1).getLastName());
        assertEquals(6000, employees.get(1).getSalary());
        assertEquals(1, employees.get(1).getManagerId());

        assertEquals(3, employees.get(2).getId());
        assertEquals("Bob", employees.get(2).getFirstName());
        assertEquals("Jones", employees.get(2).getLastName());
        assertEquals(7000, employees.get(2).getSalary());
        assertEquals(1, employees.get(2).getManagerId());

        assertEquals(4, employees.get(3).getId());
        assertEquals("Jenna", employees.get(3).getFirstName());
        assertEquals("Black", employees.get(3).getLastName());
        assertEquals(5000, employees.get(3).getSalary());
        assertEquals(2, employees.get(3).getManagerId());

    }

    @Test
    public void testReadEmployeesFromNonExistingFile() {
        // Prepare test data
        String filePath = "non_existing_file.csv";
        EmployeeCSVFileReader reader = new EmployeeCSVFileReader();

        // Set output stream to read validation content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        // Call readEmployees method and expect FileNotFoundException
        Exception thrownException = assertThrows(RuntimeException.class, () -> reader.readEmployees(filePath));
        assertTrue(thrownException.getCause() instanceof FileNotFoundException);
        assertEquals("Input file not found!", outputStream.toString().trim());
    }

    @Test
    public void testReadEmployeesWithWrongData() {
        // Prepare test data
        String filePath = "src/test/resources/test_wrong_data.csv";
        EmployeeCSVFileReader reader = new EmployeeCSVFileReader();

        // Set output stream to read validation content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        // Call readEmployees method and expect FileNotFoundException
        Exception thrownException = assertThrows(RuntimeException.class, () -> reader.readEmployees(filePath));
        assertTrue(thrownException.getCause() instanceof NumberFormatException);
        assertEquals("Invalid number format!", outputStream.toString().trim());
    }

}