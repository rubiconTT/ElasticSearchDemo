package com.jw.es.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by TT on 2019/7/3.
 */
@Document(indexName = "es_goods",type = "docs",shards = 3,replicas = 0)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {

    @Id
    private Long id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title; //标题

    @Field(type = FieldType.Keyword)
    private String category;// 分类

    @Field(type = FieldType.Keyword)
    private String brand; // 品牌

    @Field(type=FieldType.Double)
    private Double price; // 价格

    @Field(type=FieldType.Keyword,index = false)
    private String images; // 图片地址


    /*
    text类型在存储数据的时候会默认进行分词，并生成索引。
    而keyword存储数据的时候，不会分词建立索引,es以此来节省内存；

    “index”参数
    用来配置该字段是否可以被用来搜索，
    true：可以通过搜索该字段检索到文档，
    false为否，配置分词器，用analyzer参数。
     */

}
