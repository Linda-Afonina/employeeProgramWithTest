package pro.sky.employeeProgramWithTest.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeeProgramWithTest.Employee;
import pro.sky.employeeProgramWithTest.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Map<String, Employee> employees = new HashMap<>(Map.of(
            "Елена Новикова",
            new Employee("Елена", "Новикова", 1, 125000),
            "Сергей Голиков",
            new Employee("Сергей", "Голиков", 2, 98000),
            "Олег Кондратьев",
            new Employee("Олег", "Кондратьев", 3, 93000),
            "Инна Соколова",
            new Employee("Инна", "Соколова", 4, 115000),
            "Алексей Петров",
            new Employee("Алексей", "Петров", 5, 133000),
            "Кирилл Григорьев",
            new Employee("Кирилл", "Григорьев", 5, 144000),
            "Иван Шолохов",
            new Employee("Иван", "Шолохов", 4, 88000)

    ));

    @Override
    public Map<String, Employee> printEmployees() {
        return new HashMap<>(employees);
    }

    @Override
    public String addEmployee(String firstName, String lastName, int department, int salary) {
        if (employees.size() >= MAX_OF_EMPLOYEES) {
            throw new RuntimeException("Превышен лимит количества сотрудников в фирме");
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new RuntimeException("Сотрудник уже был добавлен.");
        }
        employees.put(employee.getFullName(), employee);
        return "Сотрудник " + employee + " успешно добавлен!";
    }

    @Override
    public String removeEmployee(String fullName) {
        if (!employees.containsKey(fullName)) {
            throw new RuntimeException("Сотрудник не найден");
        }
        Employee employeeToRemove = employees.get(fullName);
        employees.remove(fullName, employeeToRemove);
        return "Сотрудник " + employeeToRemove + " удален!";
    }

    @Override
    public Employee findEmployee(String fullName) {
        if (!employees.containsKey(fullName)) {
            throw new RuntimeException("Сотрудник не найден.");
        }
        return employees.get(fullName);
    }
}
