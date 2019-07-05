package com.jw.es.service;

import com.jw.es.domain.Goods;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by TT on 2019/7/3.
 */
@Log4j2
@Service
public class ESIndex {

    @Autowired
//    @Qualifier("customESTemplate")
    protected ElasticsearchTemplate est;

    public void getESTemplate(){
        Client client=est.getClient();
        log.info("client: "+client);
    }

    public void createIndex(){
        boolean addRes=est.createIndex(Goods.class);
        log.info("------ current index result: "+addRes+" ------");
    }

    public boolean deleteIndex(){
        boolean delRes=est.deleteIndex(Goods.class);
        log.info("------ delete index result: "+delRes+" ------");
        return delRes;
    }
}
