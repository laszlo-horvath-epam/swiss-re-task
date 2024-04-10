package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.TreeNode;

import java.util.List;

public class EmployeeSalaryValidatorService implements EmployeeValidatorService {

    private static final double MINIMUM_MULTIPLIER = 1.2;
    private static final double MAXIMUM_MULTIPLIER = 1.5;

    @Override
    public void validateEmployee(TreeNode<Employee> employeeNode, Object... arg) {
        if (employeeNode.hasChildren()) {
            printInvalidSalary(employeeNode);
        }
    }

    private void printInvalidSalary(TreeNode<Employee> employeeNode) {
        double subordinatesAverageSalary = getSubordinatesAverageSalary(employeeNode);
        int managerSalary = employeeNode.getData().getSalary();
        double minimumSalary = subordinatesAverageSalary * MINIMUM_MULTIPLIER;
        double maximumSalary = subordinatesAverageSalary * MAXIMUM_MULTIPLIER;

        if (managerSalary < minimumSalary) {
            double diff = minimumSalary - managerSalary;
            System.out.printf("Manager has a salary of %d which is %.1f less than the %.1f%% of their subordinates average salary\n",
                    managerSalary, diff, MINIMUM_MULTIPLIER*100);
            System.out.printf("Manager details: %s\n", employeeNode.getData().toString());

        } else if (managerSalary > maximumSalary) {
            double diff = managerSalary - maximumSalary;
            System.out.printf("Manager has a salary of %d which is %.1f more than the %.1f%% of their subordinates average salary\n",
                    managerSalary, diff, MAXIMUM_MULTIPLIER*100);
            System.out.printf("Manager details: %s\n", employeeNode.getData().toString());
        }
    }

    private double getSubordinatesAverageSalary(TreeNode<Employee> managerNode) {
        List<TreeNode<Employee>> subOrdinates = managerNode.getChildren();
        long sumOfSalaries = 0L;
        for (TreeNode<Employee> subordinate: subOrdinates) {
            sumOfSalaries += subordinate.getData().getSalary();
        }

        return sumOfSalaries * 1.0 / subOrdinates.size();
    }

}
