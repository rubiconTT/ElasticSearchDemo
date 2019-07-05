package com.jw.es.controller;

import com.jw.es.service.ESIndex;
import com.jw.es.service.ESMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TT on 2019/7/3.
 */
@Log4j2
@RestController
@RequestMapping("/test")
public class TestES {

    @Autowired
    private ESIndex esIndex;

    @Autowired
    private ESMapping esMapping;

    @GetMapping("/create/index")
    public String createESIndex(){

        log.info("------ current location: createESIndex ------");

        esIndex.createIndex();

        return "create es index success!";
    }

    @GetMapping("/put/mapping")
    public String putMapping(){
        log.info("------ current location: put mapping ------");

        esMapping.createMapping();

        return "put mapping success!";
    }
}
