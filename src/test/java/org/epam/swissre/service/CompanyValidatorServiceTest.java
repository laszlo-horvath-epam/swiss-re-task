package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.Tree;
import org.epam.swissre.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CompanyValidatorServiceTest {

    @Test
    public void testCompanyValidation() {
        TreeNode<Employee> ceoNode = new TreeNode<>(new Employee(1, "John", "Doe", 8000, null));
        TreeNode<Employee> managerNode = new TreeNode<>(new Employee(2, "Jack", "Doe", 6000, 1));
        managerNode.setParent(ceoNode);
        Tree<Employee> company = new Tree<>(ceoNode);

        // Set output stream to read validation content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        CompanyValidatorService service = new CompanyValidatorServiceImpl();
        service.validateCompany(company);

        // Assert no validation error is printed
        Assertions.assertTrue(outputStream.toString().isBlank());
    }

    //NOTE if Mockito could be used, we could verify these Validator services were called from this service

}