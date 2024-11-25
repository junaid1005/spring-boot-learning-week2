package com.assignment.Week2Assignment.services;

import com.assignment.Week2Assignment.dtos.DepartmentDTO;
import com.assignment.Week2Assignment.entities.Department;
import com.assignment.Week2Assignment.exceptions.NoResourceFoundException;
import com.assignment.Week2Assignment.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository,ModelMapper modelMapper){
        this.departmentRepository=departmentRepository;
        this.modelMapper=modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments(){
        List<Department> result=departmentRepository.findAll();
        if (result.isEmpty()) throw new NoResourceFoundException("There are currently no departments, go ahead and create new departments");
        return result
                .stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
    }


    public DepartmentDTO getDepartmentById(Long departmentId){
        Department result=departmentRepository.findById(departmentId)
                .orElseThrow(
                        ()->new NoResourceFoundException("There are currently no department with Id: "+departmentId)
                );
        return modelMapper.map(result, DepartmentDTO.class);
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDetails){
        Department departmentToBeCreated=modelMapper.map(departmentDetails,Department.class);
        LocalDateTime currentTimeStamp=LocalDateTime.now();
        departmentToBeCreated.setCreatedAt(currentTimeStamp);
        departmentToBeCreated.setUpdatedAt(currentTimeStamp);
        Department departmentCreated=departmentRepository.save(departmentToBeCreated);
        return modelMapper.map(departmentCreated, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentAllData(Long departmentId,DepartmentDTO departmentDetails){
        Department department=modelMapper.map(departmentDetails,Department.class);
        boolean exists=departmentRepository.existsById(departmentId);
        if(!exists) department.setCreatedAt(LocalDateTime.now());
        department.setUpdatedAt(LocalDateTime.now());
        Department departmentReplacedBy=departmentRepository.save(department);
        return modelMapper.map(departmentReplacedBy, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(Long departmentId, Map<String,Object> updates){
        boolean exists=departmentRepository.existsById(departmentId);
        if(!exists) throw new NoResourceFoundException("Cannot update as no such Department with Id:"+departmentId);
        Department departmentToBeUpdated=departmentRepository.findById(departmentId).get();
        updates.forEach(
                (key,value)->{
                    Field fieldToBeUpdated=ReflectionUtils.findRequiredField(Department.class,key);
                    fieldToBeUpdated.setAccessible(true);
                    ReflectionUtils.setField(fieldToBeUpdated,departmentToBeUpdated,value);
                }
        );
        departmentToBeUpdated.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(departmentRepository.save(departmentToBeUpdated),DepartmentDTO.class);
    }

    public void deleteDepartment(Long departmentId){
        boolean exists=departmentRepository.existsById(departmentId);
        if(!exists) throw new NoResourceFoundException("Cannot delete the department with Id:"+departmentId+ " that does not exists.");
        departmentRepository.deleteById(departmentId);
    }


}
