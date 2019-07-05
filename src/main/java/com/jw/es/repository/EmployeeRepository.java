package com.jw.es.repository;

import com.jw.es.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Created by TT on 2019/7/4.
 */
@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {

}
