package pro.sky.employeeProgramWithTest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeeProgramWithTest.Employee;
import pro.sky.employeeProgramWithTest.service.impl.DepartmentServiceImpl;
import pro.sky.employeeProgramWithTest.service.impl.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    private final Map<String, Employee> employeesForTest = new HashMap<>(Map.of(
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

    @Mock
    EmployeeServiceImpl employeeServiceImpl;

    @InjectMocks
    DepartmentServiceImpl departmentServiceImpl;

    @Test
    public void shouldPrintAllEmployees() {
        Mockito.when(employeeServiceImpl.printEmployees()).thenReturn(employeesForTest);

        Map<Integer, List<Employee>> expectedEmployees = new HashMap<>(Map.of(
                1, List.of(new Employee("Елена", "Новикова", 1, 125000)),
                2, List.of(new Employee("Сергей", "Голиков", 2, 98000)),
                3, List.of(new Employee("Олег", "Кондратьев", 3, 93000)),
                4, List.of(new Employee("Иван", "Шолохов", 4, 88000),
                        new Employee("Инна", "Соколова", 4, 115000)),
                5, List.of(new Employee("Алексей", "Петров", 5, 133000),
                        new Employee("Кирилл", "Григорьев", 5, 144000))

        ));
        Map<Integer, List<Employee>> actualEmployees = departmentServiceImpl.printAllEmployees();
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void shouldPrintAllEmployeesOfDepartment() {
        int department = 5;
        List<Employee> expectedEmployees = List.of(
                new Employee("Алексей", "Петров", 5, 133000),
                new Employee("Кирилл", "Григорьев", 5, 144000)
        );
        Mockito.when(employeeServiceImpl.printEmployees()).thenReturn(employeesForTest);
        List<Employee> actualEmployees = departmentServiceImpl.printEmployeeOfDepartment(department);
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void shouldGetEmployeeWithMaxSalary() {
        int department = 5;
        Optional<Employee> expected = Optional.of(new Employee("Кирилл", "Григорьев", 5,
                144000));
        Mockito.when(employeeServiceImpl.printEmployees()).thenReturn(employeesForTest);
        Optional<Employee> actual = departmentServiceImpl.getEmployeeWithMaxSalary(department);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetEmployeeWithMinSalary() {
        int department = 5;
        Optional<Employee> expected = Optional.of(new Employee("Алексей", "Петров", 5,
                133000));
        Mockito.when(employeeServiceImpl.printEmployees()).thenReturn(employeesForTest);
        Optional<Employee> actual = departmentServiceImpl.getEmployeeWithMinSalary(department);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSumSalary() {
        int department = 5;
        Integer expected = 133000 + 144000;
        Mockito.when(employeeServiceImpl.printEmployees()).thenReturn(employeesForTest);
        Integer actual = departmentServiceImpl.getSumSalary(department);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenDepartmentIsWrong() {
        int department = 7;
        assertThrows(RuntimeException.class,
                () -> departmentServiceImpl.printEmployeeOfDepartment(department));
    }
}
