package pro.sky.employeeProgramWithTest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeeProgramWithTest.Employee;
import pro.sky.employeeProgramWithTest.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Map<String, Employee> printAllEmployees() {
        return employeeService.printEmployees();
    }

    @GetMapping("add")
    public String addEmployee(@RequestParam("firstname") String firstName,
                              @RequestParam("lastname") String lastName,
                              @RequestParam("department") int department,
                              @RequestParam("salary") int salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("remove")
    public String removeEmployee(@RequestParam("fullname") String fullName) {
        return employeeService.removeEmployee(fullName);
    }

    @GetMapping("find")
    public Employee findEmployee(@RequestParam("fullname") String fullName) {
        return employeeService.findEmployee(fullName);
    }
}
