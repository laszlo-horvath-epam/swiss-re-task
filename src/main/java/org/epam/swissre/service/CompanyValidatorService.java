package org.epam.swissre.service;

import org.epam.swissre.model.Employee;
import org.epam.swissre.model.Tree;

public interface CompanyValidatorService {

    void validateCompany(Tree<Employee> company);

}
