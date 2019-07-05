package com.jw.es.repository;

import com.jw.es.domain.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TT on 2019/7/3.
 */
@Component
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

    List<Goods> findByPriceBetween(double lowPrice, double highPrice);

    List<Goods> findByCategoryOrTitle(String category,String title);




}
