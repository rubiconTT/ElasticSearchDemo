package com.jw.es.service;

import com.jw.es.domain.Employee;
import com.jw.es.repository.EmpRepositoryImpl;
import com.jw.es.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TT on 2019/7/4.
 */
@Log4j2
@Service
public class EmpService {

    @Autowired
    private EmpRepositoryImpl empRepositoryImpl;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeeList(){

        List<Employee> employeeList= empRepositoryImpl.getEmpList();

        log.info("------ get employee list count: "+employeeList.size());
        return employeeList;
    }

    public boolean createAndInputIndex(){
        boolean createIndex=empRepositoryImpl.creatEmpIndex();
        boolean putMapping=empRepositoryImpl.putEmpMapping();

        if(createIndex && putMapping){
            log.info("------ create employee index and put mapping success ------");
            return true;
        }
        return false;
    }

    public List<Employee> batchSaveEmployee2Es(List<Employee> employeeList){
        final List<Employee> esEmpList=new ArrayList<>();
        employeeRepository.saveAll(employeeList).forEach(x->esEmpList.add(x));

        log.info("------ save success result size: "+esEmpList.size());

        return esEmpList;

    }
}
