package com.jw.es.service;

import com.jw.es.domain.Goods;
import com.jw.es.repository.GoodsRepository;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2019/7/3.
 */
@Log4j2
@Service
public class CustomGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public List<Goods> findGoodsByPrice(double price1,double price2){
        List<Goods> goodsList=goodsRepository.findByPriceBetween(price1,price2);

        return goodsList;
    }

    public List<Goods> findGoodsByCateOrTitle(String category,String title){
        List<Goods> goodsList=goodsRepository.findByCategoryOrTitle(category,title);
        return goodsList;
    }

    public List<Goods> customQuery(String condition){
        List<Goods> goodsList= new ArrayList<>();
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("title",condition));

        Page<Goods> goodsPage=goodsRepository.search(queryBuilder.build());
        // total pages
        long pages=goodsPage.getTotalPages();
        log.info("------ query page count: "+pages+" ------");

        //total elements
        long eles=goodsPage.getTotalElements();
        log.info("------ query elements count: "+eles+" ------");

        for(Goods goods:goodsPage){
            if(goods !=null){
                goodsList.add(goods);
            }
        }
        return goodsList;
    }

    public List<Goods> customTermQuery(Object obj){
        List<Goods> goodsList= new ArrayList<>();

        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("category",obj));

        //分页
        queryBuilder.withPageable(PageRequest.of(0,3));
        //排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        Page<Goods> goodsPage=goodsRepository.search(queryBuilder.build());
        // total pages
        long pages=goodsPage.getTotalPages();
        log.info("------ query page count: "+pages+" ------");

        //total elements
        long eles=goodsPage.getTotalElements();
        log.info("------ query elements count: "+eles+" ------");

        for(Goods goods:goodsPage){
            if(goods !=null){
                goodsList.add(goods);
            }
        }
        return goodsList;
    }

    public List<Goods> customFuzzyQuery(Object obj){
        List<Goods> goodsList= new ArrayList<>();

        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("title",obj));

        //分页
        queryBuilder.withPageable(PageRequest.of(0,3));
        //排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        Page<Goods> goodsPage=goodsRepository.search(queryBuilder.build());
        // total pages
        long pages=goodsPage.getTotalPages();
        log.info("------ query page count: "+pages+" ------");

        //total elements
        long eles=goodsPage.getTotalElements();
        log.info("------ query elements count: "+eles+" ------");

        for(Goods goods:goodsPage){
            if(goods !=null){
                goodsList.add(goods);
            }
        }
        return goodsList;
    }

    public List<Goods> customBooleanQuery(Object obj){

        List<Goods> goodsList= new ArrayList<>();

        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("title",obj))
                        .must(QueryBuilders.matchQuery("category",obj)));

        Page<Goods> goodsPage=goodsRepository.search(queryBuilder.build());
        // total pages
        long pages=goodsPage.getTotalPages();
        log.info("------ query page count: "+pages+" ------");

        //total elements
        long eles=goodsPage.getTotalElements();
        log.info("------ query elements count: "+eles+" ------");

        for(Goods goods:goodsPage){
            if(goods !=null){
                goodsList.add(goods);
            }
        }
        return goodsList;
    }
}
