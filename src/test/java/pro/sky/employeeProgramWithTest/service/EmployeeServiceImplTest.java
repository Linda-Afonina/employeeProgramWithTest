package pro.sky.employeeProgramWithTest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.employeeProgramWithTest.Employee;
import pro.sky.employeeProgramWithTest.service.impl.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceImplTest {

    EmployeeServiceImpl out;
    Map<String, Employee> employeesForTest;

    @BeforeEach
    public void setUp() {
        out = new EmployeeServiceImpl();
        employeesForTest = new HashMap<>(Map.of(
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
    }

    @Test
    public void shouldPrintEmployees() {
        Map<String, Employee> expectedEmployees = employeesForTest;
        Map<String, Employee> actualEmployees = out.printEmployees();
        assertEquals(actualEmployees, expectedEmployees);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenListSizeIsMax() {

        out.addEmployee("Ольга1", "Медведева", 4, 82500);
        out.addEmployee("Ольга2", "Медведева", 4, 82500);
        out.addEmployee("Ольга3", "Медведева", 4, 82500);

        assertThrows(RuntimeException.class,
                () -> out.addEmployee("Ольга", "Медведева", 4, 82500));
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenEmployeeWasAlreadyAdded() {
        out.addEmployee("Ольга", "Медведева", 4, 82500);
        assertThrows(RuntimeException.class,
                () -> out.addEmployee("Ольга", "Медведева", 4, 82500));
    }

    @Test
    public void shouldCorrectAddEmployee() {
        Employee expectedEmployee = new Employee("Ольга", "Медведева", 4, 82500);
        String expected = "Сотрудник " + expectedEmployee + " успешно добавлен!";
        String actual = out.addEmployee(
                expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary()
        );
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenEmployeeIsNotFind() {
        Employee expectedEmployee = new Employee("Ольга", "Медведева", 4, 82500);
        String fullNameOfExpectedEmployee = expectedEmployee.getFirstName() + " " + expectedEmployee.getLastName();
        assertThrows(RuntimeException.class,
                () -> out.findEmployee(fullNameOfExpectedEmployee));
    }

    @Test
    public void shouldCorrectRemoveEmployee() {
        Employee expectedEmployee = new Employee("Иван", "Шолохов", 4, 88000);
        String fullNameOfExpectedEmployee = expectedEmployee.getFirstName() + " " + expectedEmployee.getLastName();
        String expected = "Сотрудник " + expectedEmployee + " удален!";
        String actual = out.removeEmployee(fullNameOfExpectedEmployee);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCorrectFindEmployee() {
        Employee expectedEmployee = new Employee("Иван", "Шолохов", 4, 88000);
        String fullNameOfExpectedEmployee = expectedEmployee.getFirstName() + " " + expectedEmployee.getLastName();
        Employee actualEmployee = out.findEmployee(fullNameOfExpectedEmployee);
        assertEquals(expectedEmployee, actualEmployee);
    }
}
