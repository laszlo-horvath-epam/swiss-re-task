package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;

public class EmployeeReportingLineValidatorService implements EmployeeValidatorService {

    public static final int MAX_DEPTH = 5;

    @Override
    public void validateEmployee(TreeNode<Employee> employeeNode, Object... arg) {
        Integer depthLevel = (Integer) arg[0];
        if (depthLevel > MAX_DEPTH) {
            System.out.printf("Employee found with reporting line length of %d , which is longer than the maximum depth of %d \n", depthLevel, MAX_DEPTH);
            System.out.printf("Employee details: %s\n", employeeNode.getData().toString());
        }
    }
}
