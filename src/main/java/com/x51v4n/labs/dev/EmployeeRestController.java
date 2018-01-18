package com.x51v4n.labs.dev;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path="/employees", produces = APPLICATION_JSON_VALUE)
public class EmployeeRestController {
    
    private final EmployeeRepository employeeRepository;
    
    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @RequestMapping(method = GET)
    public List<Employee> list() {
        return (List<Employee>) this.employeeRepository.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        Employee employee = this.employeeRepository.findOne(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } 
        return ResponseEntity.notFound().build();
    }
    
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> post(@RequestBody EmployeeDto input) {
        return ResponseEntity
                .ok(
                        this.employeeRepository.save(
                            new Employee(
                                input.getName(),
                                input.getEmail()
                            )
                        )
                    );
    }
    
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Employee> delete(@PathVariable Long id) {
        Employee employee = this.employeeRepository.findOne(id);
        if (employee != null) {
            this.employeeRepository.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
