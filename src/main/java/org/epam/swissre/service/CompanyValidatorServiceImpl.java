package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.Tree;
import org.epam.swissre.model.TreeNode;

import java.util.List;

public class CompanyValidatorServiceImpl implements CompanyValidatorService {

    private List<EmployeeValidatorService> validators;

    @Override
    public void validateCompany(Tree<Employee> company) {
        TreeNode<Employee> ceo = company.getRoot();
        validators = List.of(
                new EmployeeReportingLineValidatorService(),
                new EmployeeSalaryValidatorService()
        );
        validateEmployeeAndSubordinates(ceo, 0);
    }

    private void validateEmployeeAndSubordinates(TreeNode<Employee> employeeNode, int depthLevel) {
        for (EmployeeValidatorService validatorService: validators) {
            validatorService.validateEmployee(employeeNode, depthLevel);
        }

        for (TreeNode<Employee> subordinate: employeeNode.getChildren()) {
            validateEmployeeAndSubordinates(subordinate, depthLevel+1);
        }
    }

}
