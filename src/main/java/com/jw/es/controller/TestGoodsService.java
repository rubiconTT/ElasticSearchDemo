package com.jw.es.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jw.es.domain.Goods;
import com.jw.es.service.CustomGoodsService;
import com.jw.es.service.GoodsAggregateService;
import com.jw.es.service.GoodsService;
import lombok.extern.log4j.Log4j2;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TT on 2019/7/3.
 */
@Log4j2
@RestController
@RequestMapping("/test")
public class TestGoodsService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomGoodsService customGoodsService;

    @Autowired
    private GoodsAggregateService aggregateService;

    @GetMapping("/goods/add")
    public String addNewGoods(){

        goodsService.addNewGoods();

        return "------ add new goods success ------";
    }

    @GetMapping("/goods/addlist")
    public String addGoodsList(){

        goodsService.addAllGoods();

        return "------ add new goods success ------";
    }

    @GetMapping("/goods/update/{id}")
    public String updateGoods(@PathVariable("id") Long id){

        goodsService.updateGoodsById(id);

        return "------ update goods success ------";
    }

    @GetMapping("/goods/updateall")
    public String updateGoodsList(){

        goodsService.updateAllGoods();

        return "------ update goods success ------";
    }

    @GetMapping("/goods/delete")
    public String deleteGoodsById(@RequestParam("id") Long id){
        goodsService.deleteGoods(id);

        return "------ delete goods success,id: "+Long.valueOf(id)+" ------";
    }

    @GetMapping("/goods/find/{id}")
    public String queryGoodsById(@PathVariable("id") Long id){
        Goods goods=goodsService.queryGoodsById(id);

        return goods.toString();
    }

    @RequestMapping(value = "/goods/findall",method = RequestMethod.GET,produces ={"application/json;charset=utf-8"})
    @ResponseBody
    public List<Goods> queryGoodsAll(){
        List<Goods> goodsList=goodsService.queryAllGoods();

        log.info("------- query list count: "+goodsList.size());

        return goodsList;
    }

    @RequestMapping(value = "/goods/find/{lowprice}/{highprice}",method = RequestMethod.GET,produces ={"application/json;charset=utf-8"})
    @ResponseBody
    public List<Goods> queryGoodsByCondition(@PathVariable("lowprice")double lowprice,@PathVariable("highprice")double highprice){
        List<Goods> goodsList=customGoodsService.findGoodsByPrice(lowprice,highprice);

        log.info("------- query list count: "+goodsList.size());

        return goodsList;
    }

    @RequestMapping(value = "/goods/find/condition",method = RequestMethod.GET,produces ={"application/json;charset=utf-8"})
    @ResponseBody
    public List<Goods> queryGoodsByCategoryOrTitle(@RequestParam("category")String category,@RequestParam("title")String title){
        List<Goods> goodsList=customGoodsService.findGoodsByCateOrTitle(category,title);

        log.info("------- query list count: "+goodsList.size());

        return goodsList;
    }

    @GetMapping(value = "/goods/query",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public List<Goods> customQueryByTitle(@RequestParam("title") String title){
        List<Goods> goodsList=customGoodsService.customQuery(title);

        return goodsList;
    }

    @GetMapping(value = "/goods/query/term",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public List<Goods> customTermQuery(@RequestParam("condition") String condition){
        List<Goods> goodsList=customGoodsService.customTermQuery(condition);

        return goodsList;
    }

    @GetMapping(value = "/goods/query/fuzzy",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public List<Goods> customFuzzyQuery(@RequestParam("title") String title){
        List<Goods> goodsList=customGoodsService.customFuzzyQuery(title);

        return goodsList;
    }

    @GetMapping(value = "/goods/query/boolean",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public List<Goods> customBooleanQuery(@RequestParam("condition") String condition){
        List<Goods> goodsList=customGoodsService.customBooleanQuery(condition);

        return goodsList;
    }

    @GetMapping(value = "/goods/query/bucket",produces = {"application/json;charset=utf-8"})
    public String customBucketAggregate(){
        List<Goods> goodsList=aggregateService.bucketAggregate();

        return "------ finished bucket aggregate ------";
    }


}
