package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;

public interface EmployeeValidatorService {

    void validateEmployee(TreeNode<Employee> employeeNode, Object... arg);

}
