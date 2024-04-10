package org.epam.swissre;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.Tree;
import org.epam.swissre.model.TreeNode;
import org.epam.swissre.reader.EmployeeCSVFileReader;
import org.epam.swissre.reader.EmployeeFileReader;
import org.epam.swissre.service.*;

import java.util.List;

public class Main {

    // Provide input file here
    private static final String INPUT_FILE = "src/main/resources/test_employees.csv";

    public static void main(String[] args) {

        // Read all employees from input
        EmployeeFileReader employeeFileReader = new EmployeeCSVFileReader();
        List<Employee> employeesInFile = employeeFileReader.readEmployees(INPUT_FILE);

        // Creating managers - subordinates relations
        EmployeeManagerRelationService relationService = new EmployeeManagerRelationServiceImpl();
        List<TreeNode<Employee>> employeeNodes = relationService.createEmployeeManagerRelations(employeesInFile);

        // Creating Tree with CEO as root element
        TreeNode<Employee> ceoNode = employeeNodes.get(0);
        Tree<Employee> companyTree = new Tree<>(ceoNode);

        // Validating company
        CompanyValidatorService companyValidatorService = new CompanyValidatorServiceImpl();
        companyValidatorService.validateCompany(companyTree);

    }

}