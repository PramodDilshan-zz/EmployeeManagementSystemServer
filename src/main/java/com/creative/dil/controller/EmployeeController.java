package com.creative.dil.controller;

import com.creative.dil.model.Employee;
import com.creative.dil.model.Skill;
import com.creative.dil.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.creative.dil.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    private SkillRepository skillRepository;

    private Employee employee;
    private Skill skill;

    EmployeeController(EmployeeRepository employeeRepository, SkillRepository skillRepository){
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @PostMapping(value = "/employees/create")
    public Employee createEmployee(@RequestBody Employee employee){
        for (Skill skill: employee.getSkills()) {
            this.skill = skill;
            this.skillRepository.save(skill);
        }
        this.employee = this.employeeRepository.save(employee);
        return this.employee;
    }

    @PostMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        Optional<Employee> empData = this.employeeRepository.findById(id);

        if (empData.isPresent()){
            for (Skill skill: employee.getSkills()) {
                this.skill = skill;
                this.skillRepository.save(skill);
            }
            this.employee = empData.get();
            this.employee.setName(employee.getName());
            this.employee.setEmail(employee.getEmail());
            this.employee.setBirthday(employee.getBirthday());
//            this.employee.setSkills(employee.getSkills());

            return new ResponseEntity<>(this.employeeRepository.save(this.employee), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping(value = "employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        Optional<Employee> empData = this.employeeRepository.findById(id);
        this.employee = empData.get();
        return this.employee;
    }

    @GetMapping(value = "/employees")
    public List<Employee> getAllEmployees(){
//        System.out.println("getAllEmployees method call :  com.creative.dil.controller");
        List<Employee> employeesList = new ArrayList<>();
        this.employeeRepository.findAll().forEach(employeesList::add);
        return employeesList;
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id){
        System.out.println("delete call");
        this.employeeRepository.deleteById(id);
        return new ResponseEntity<>("An employee has been deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/delete")
    public ResponseEntity<String> deleteAllEmployees(){
        System.out.println("delete call");
        this.employeeRepository.deleteAll();
        return new ResponseEntity<>("All employees are deleted", HttpStatus.OK);
    }
}
