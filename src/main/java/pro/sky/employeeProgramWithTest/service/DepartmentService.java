package pro.sky.employeeProgramWithTest.service;

import pro.sky.employeeProgramWithTest.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {

    List<Employee> printEmployeeOfDepartment(int department);

    Map<Integer, List<Employee>> printAllEmployees();

    Optional<Employee> getEmployeeWithMaxSalary(int department);

    Optional<Employee> getEmployeeWithMinSalary(int department);

    Integer getSumSalary(int department);
}
