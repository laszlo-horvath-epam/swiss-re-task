package org.epam.swissre.reader;

import org.epam.swissre.model.Employee;

import java.util.List;

public interface EmployeeFileReader {

    List<Employee> readEmployees(String input);

}
