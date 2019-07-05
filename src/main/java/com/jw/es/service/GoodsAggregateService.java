package com.jw.es.service;

import com.jw.es.domain.Goods;
import com.jw.es.repository.GoodsRepository;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2019/7/4.
 */
@Log4j2
@Service
public class GoodsAggregateService {

    @Autowired
    private GoodsRepository goodsRepository;

    public List<Goods> bucketAggregate(){
        List<Goods> goodsList=new ArrayList<>();

        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.addAggregation(AggregationBuilders.terms("brand").field("brand"));

        AggregatedPage<Goods> aggGoodsPage = (AggregatedPage<Goods>) goodsRepository.search(queryBuilder.build());

        StringTerms stringTerms=(StringTerms) aggGoodsPage.getAggregation("brand");

        List<StringTerms.Bucket> bucketList=stringTerms.getBuckets();

        for(StringTerms.Bucket bucket:bucketList){
            log.info("------key: "+bucket.getKeyAsString());
            log.info("------count: "+bucket.getDocCount());
        }

        return null;
    }
}
