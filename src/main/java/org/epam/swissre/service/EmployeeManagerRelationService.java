package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;

import java.util.List;

public interface EmployeeManagerRelationService {

    List<TreeNode<Employee>> createEmployeeManagerRelations(List<Employee> employees);

}
