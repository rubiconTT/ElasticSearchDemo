package com.jw.es.service;

import com.jw.es.domain.Goods;
import com.jw.es.repository.GoodsRepository;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TT on 2019/7/3.
 */
@Log4j2
@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public void addNewGoods(){

        Goods goods=new Goods(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");

        Goods goodsNew=goodsRepository.save(goods);

        log.info("new goods: "+goodsNew.toString());

    }

    public void addAllGoods(){
        List<Goods> goodsList=new ArrayList<Goods>(){
            {
                add(new Goods(2L, "小米手机2", "手机",
                        "小米", 1499.00, "http://image.baidu.com/13124.jpg"));
                add(new Goods(3L, "小米手机3", "手机",
                        "小米", 2499.00, "http://image.baidu.com/13125.jpg"));
                add(new Goods(4L, "小米手机4", "手机",
                        "小米", 3299.00, "http://image.baidu.com/13126.jpg"));
                add(new Goods(5L, "小米手机5", "手机",
                        "小米", 3699.00, "http://image.baidu.com/13127.jpg"));
                add(new Goods(6L, "小米手机6", "手机",
                        "小米", 2699.00, "http://image.baidu.com/13133.jpg"));
                add(new Goods(7L, "小米手机8", "手机",
                        "小米", 3899.00, "http://image.baidu.com/13143.jpg"));

            }
        };

        goodsRepository.saveAll(goodsList);
    }

    public void updateGoodsById(Long id){
        Goods goods=new Goods(id, "华为Mate20", "手机",
                "HUAWEI", 4499.00, "http://image.baidu.com/13453.jpg");

        Goods goodsNew=goodsRepository.save(goods);

        log.info("----- update goods: "+goodsNew.toString());
    }

    public void updateAllGoods(){
        List<Goods> goodsList=new ArrayList<Goods>(){
            {
                add(new Goods(2L, "小米手机2", "手机",
                        "小米", 1199.00, "http://image.baidu.com/13124.jpg"));
                add(new Goods(3L, "锤子3", "手机",
                        "锤子", 2299.00, "http://image.baidu.com/13125.jpg"));
                add(new Goods(4L, "红米手机4", "手机",
                        "小米", 3099.00, "http://image.baidu.com/13126.jpg"));
                add(new Goods(5L, "iphone8", "手机",
                        "Apple", 3299.00, "http://image.baidu.com/13127.jpg"));
                add(new Goods(6L, "Mate 20", "手机",
                        "huawei", 2699.00, "http://image.baidu.com/13133.jpg"));
                add(new Goods(7L, "荣耀9", "手机",
                        "huawei", 3899.00, "http://image.baidu.com/13143.jpg"));
                add(new Goods(8L, "Glaxy20", "手机",
                        "三星", 8899.00, "http://image.baidu.com/13143.jpg"));
                add(new Goods(9L, "iphone xs", "手机",
                        "Apple", 9899.00, "http://image.baidu.com/13143.jpg"));
                add(new Goods(10L, "OPPO", "手机",
                        "OPPO", 1899.00, "http://image.baidu.com/13143.jpg"));

            }
        };

        goodsRepository.saveAll(goodsList);

        log.info("----- update goods list success ------ ");
    }

    public void deleteGoods(Long id){
        goodsRepository.deleteById(id);

        log.info("------ delete goods success,id: "+Long.valueOf(id));
    }

    public Goods queryGoodsById(Long id){

        Goods goods=goodsRepository.findById(id).get();
        log.info("------- query result: "+goods.toString());
        return goods;
    }

    public List<Goods> queryAllGoods(){

        List<Goods> goodsList=new ArrayList<>();
        Iterator<Goods> goods=goodsRepository.findAll(Sort.by("price").ascending()).iterator();
        while (goods.hasNext()){
            Goods goods1=goods.next();
            goodsList.add(goods1);
        }

        log.info("------ query goods count: "+goodsList.size());
        return goodsList;
    }

    public void batchSave(List<Goods> goodsList){
        
    }
}
