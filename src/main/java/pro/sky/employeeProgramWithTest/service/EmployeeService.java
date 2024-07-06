package pro.sky.employeeProgramWithTest.service;

import pro.sky.employeeProgramWithTest.Employee;

import java.util.HashMap;
import java.util.Map;

public interface EmployeeService {

    Map<Integer, Employee> employees = new HashMap<>(Map.of());
    int MAX_OF_EMPLOYEES = 10;

    Map<String, Employee> printEmployees();

    String addEmployee(String firstName, String lastName, int department, int salary);

    String removeEmployee(String fullName);

    Employee findEmployee(String fullName);
}
