package com.assignment.Week2Assignment.controllers;

import com.assignment.Week2Assignment.dtos.DepartmentDTO;
import com.assignment.Week2Assignment.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping(path = "/departments")
    public ResponseEntity<List<DepartmentDTO>> getAll(){
        List<DepartmentDTO> results=departmentService.getAllDepartments();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(path = "/departments/{departmentid}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentid") Long departmentId){
        DepartmentDTO result=departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping(path = "/departments")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO department){
        DepartmentDTO result=departmentService.addDepartment(department);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @PutMapping(path = "/departments/{departmentid}")
    public ResponseEntity<DepartmentDTO> updateOrCreateDepartment(@PathVariable(name = "departmentid") Long departmentId,@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO result=departmentService.updateDepartmentAllData(departmentId,departmentDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PatchMapping(path = "/departments/{departmentid}")
    public ResponseEntity<DepartmentDTO> updateDepartmentPartially(@PathVariable(name = "departmentid")Long departmentId, @RequestBody Map<String,Object> updates){
        DepartmentDTO result=departmentService.updateDepartment(departmentId,updates);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping(path = "/departments/{departmentid}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable(name = "departmentid") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>("Department with Id: "+departmentId+ " successfully deleted",HttpStatus.OK);
    }
}
