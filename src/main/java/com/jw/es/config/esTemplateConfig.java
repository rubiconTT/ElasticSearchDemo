package com.jw.es.config;

import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by TT on 2019/7/3.
 */
@Log4j2
//@Configuration
public class esTemplateConfig {

    @Value("${spring.data.elasticsearch.properties.transport.tcp.connect_timeout}")
    private String timeout;

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

//    @Value("${}")
//    private String transport;

//    @Bean(name = "customESTemplate")
    public ElasticsearchTemplate getESTemplate(){

        ElasticsearchTemplate est=null;
        TransportClientFactoryBean clientFactoryBean=new TransportClientFactoryBean();
        try {
            clientFactoryBean.setClientPingTimeout(timeout);
            clientFactoryBean.setClusterName(clusterName);
            clientFactoryBean.setClusterNodes(clusterNodes);
//            clientFactoryBean.setProperties();

            TransportClient client= clientFactoryBean.getObject();
            if(client !=null){
                est=new ElasticsearchTemplate(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return est;
    }
}
