package org.epam.swissre.reader;

import org.epam.swissre.model.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeCSVFileReader implements EmployeeFileReader {

    @Override
    public List<Employee> readEmployees(String input) {

        List<Employee> employeesInFile = new ArrayList<>();

        //read file from input
        try (Scanner scanner = new Scanner(new File(input))) {
            // The first line contains the field names, we should not parse that
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                Employee employee = createEmployeeFromCSVLine(scanner.nextLine());
                employeesInFile.add(employee);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found!");
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format!");
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println("Error during file processing!");
            throw new RuntimeException(e);
        }

        return employeesInFile;
    }

    private Employee createEmployeeFromCSVLine(String csvLine) {
        String[] employeeData = csvLine.split(",", -1);
        Integer id = Integer.valueOf(employeeData[0]);
        String firstName = employeeData[1];
        String lastName = employeeData[2];
        Integer salary = Integer.valueOf(employeeData[3]);
        Integer managerId = employeeData[4].isBlank() ? null : Integer.valueOf(employeeData[4]);
        return new Employee(id, firstName, lastName, salary, managerId);
    }

}
