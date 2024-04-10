package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeManagerRelationServiceImpl implements EmployeeManagerRelationService {

    @Override
    public List<TreeNode<Employee>> createEmployeeManagerRelations(List<Employee> employees) {
        // Create a map of employees with EmployeeId as key
        Map<Integer, TreeNode<Employee>> employeesById = employees.stream().collect(Collectors.toMap(Employee::getId, TreeNode::new));

        // Returning employees with their managers set, and the CEO (root) as the first element
        return employeesById.values()
                .stream()
                .map(e -> setManagerForEmployeeNode(e, employeesById))
                .sorted(getEmployeeComparator())
                .collect(Collectors.toList());
    }

    private TreeNode<Employee> setManagerForEmployeeNode(TreeNode<Employee> employeeNode, Map<Integer, TreeNode<Employee>> employeesById) {
        if (employeeNode.getData().getManagerId() == null) {
            return employeeNode;
        }
        TreeNode<Employee> manager = employeesById.get(employeeNode.getData().getManagerId());
        employeeNode.setParent(manager);
        return employeeNode;
    }

    private Comparator<TreeNode<Employee>> getEmployeeComparator() {
        Comparator<TreeNode<Employee>> comparing = Comparator.comparing(TreeNode::isRoot);
        return comparing.reversed();
    }

}
