package com.jw.es.controller;

import com.jw.es.domain.Employee;
import com.jw.es.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TT on 2019/7/4.
 */
@Log4j2
@RestController
@RequestMapping("/test")
public class TestEmpController {

    @Autowired
    private EmpService empService;

    @GetMapping(value = "/emp/query/all",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public List<Employee> queryEmployeeList(){
        List<Employee> employeeList=empService.getEmployeeList();

        return employeeList;
    }

    @GetMapping(value = "/emp/create/index",produces = {"text/plain;charset=utf-8"})
    public String createEmployeeIndex(){
        boolean createRes=empService.createAndInputIndex();

        if(createRes){
            return "create employee index success!";
        }

        return "create employee index failed!";
    }

    @GetMapping(value = "/emp/save/all",produces = {"application/json;charset=utf-8"})
    public List<Employee> saveAllEmployee(){
        List<Employee> empList=empService.getEmployeeList();
        List<Employee> esEmpList=empService.batchSaveEmployee2Es(empList);

        return esEmpList;
    }
}
