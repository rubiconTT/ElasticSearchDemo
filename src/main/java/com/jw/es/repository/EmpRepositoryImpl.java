package com.jw.es.repository;

import com.jw.es.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2019/7/4.
 */
@Repository
public class EmpRepositoryImpl{

    @Autowired
    @Qualifier("mysqlConnection")
    private Connection connection;

    @Autowired
    private ElasticsearchTemplate est;


    public List<Employee> getEmpList(){
        List<Employee> employeeList=new ArrayList<>();
        String sql=" SELECT * FROM employee ";

        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Employee emp=new Employee();
                String empId=rs.getString("emp_id");
                String empName=rs.getString("emp_name");
                String empSn=rs.getString("emp_sn");
                String empGivenName=rs.getString("emp_given_name");
                String empDisplayName=rs.getString("emp_display_name");
                String empMobile=rs.getString("emp_mobile");
                String empEmail=rs.getString("emp_email");
                String orgId=rs.getString("emp_org_id");
                String orgName=rs.getString("emp_org_name");
                String empPosition=rs.getString("emp_position");
                String empStatus=rs.getString("emp_status");
                String empDn=rs.getString("emp_dn");
                String empJwUid=rs.getString("emp_jw_uid");

                String id=empId;

                emp.setId(id);

                emp.setEmpId(empId);
                emp.setEmpName(empName);
                emp.setEmpSn(empSn);
                emp.setEmpGivenName(empGivenName);
                emp.setEmpDisplayName(empDisplayName);
                emp.setEmpMobile(empMobile);
                emp.setEmpEmail(empEmail);
                emp.setOrgId(orgId);
                emp.setOrgName(orgName);
                emp.setEmpPosition(empPosition);
                emp.setEmpStatus(empStatus);
                emp.setEmpDn(empDn);
                emp.setEmpJwUid(empJwUid);

                employeeList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection !=null || !connection.isClosed()){
                    connection.close();
                }
                if(ps !=null || !ps.isClosed() ){
                    ps.close();
                }
                if(rs !=null || !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employeeList;

    }

    public boolean creatEmpIndex(){
        boolean createRes=est.createIndex(Employee.class);

        return createRes;
    }

    public boolean putEmpMapping(){
        boolean putRes=est.putMapping(Employee.class);
        return putRes;
    }




}
