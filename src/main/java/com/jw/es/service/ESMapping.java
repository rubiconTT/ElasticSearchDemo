package com.jw.es.service;

import com.jw.es.domain.Goods;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by TT on 2019/7/3.
 */
@Log4j2
@Service
public class ESMapping {

    @Autowired
    private ElasticsearchTemplate est;

    public void createMapping(){
        boolean putRes=est.putMapping(Goods.class);
        log.info("------ current index put mapping result: "+putRes+" ------");
    }
}
