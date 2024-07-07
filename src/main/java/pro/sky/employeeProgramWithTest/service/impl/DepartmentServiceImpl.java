package pro.sky.employeeProgramWithTest.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeeProgramWithTest.Employee;
import pro.sky.employeeProgramWithTest.service.DepartmentService;
import pro.sky.employeeProgramWithTest.service.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Map<Integer, List<Employee>> printAllEmployees() {
        Map<Integer, List<Employee>> collect = employeeService.printEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return collect;
    }

    public List<Employee> printEmployeeOfDepartment(int department) {
        if (department != 1 && department != 2 && department != 3 && department != 4 && department != 5) {
            throw new RuntimeException("Неверный номер отдела!");
        }
        return employeeService.printEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> getEmployeeWithMaxSalary(int department) {

        return employeeService.printEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(employee -> employee.getSalary()));
    }

    @Override
    public Optional<Employee> getEmployeeWithMinSalary(int department) {
        return employeeService.printEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(employee -> employee.getSalary()));
    }

    public Integer getSumSalary(int department) {
        return employeeService.printEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
