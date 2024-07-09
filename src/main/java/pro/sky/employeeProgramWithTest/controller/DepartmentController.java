package pro.sky.employeeProgramWithTest.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.employeeProgramWithTest.Employee;
import pro.sky.employeeProgramWithTest.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("employees")
    public Map<Integer, List<Employee>> printEmployeesInDepartment() {
        return departmentService.printAllEmployees();
    }

    @GetMapping("{id}/employees")
    public List<Employee> printEmployeeOfDepartment(@PathVariable("id") int departmentId) {
        return departmentService.printEmployeeOfDepartment(departmentId);
    }

    @GetMapping("{id}/salary/max")
    public Optional<Employee> getEmployeeWithMaxSalary(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("{id}/salary/min")
    public Optional<Employee> getEmployeeWithMinSalary(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("{id}/salary/sum")
    public Integer getSumSalary(@PathVariable("id") int departmentId) {
        return departmentService.getSumSalary(departmentId);
    }
}
