package com.jw.es.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by TT on 2019/7/4.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "es_adidas_employee",type = "employee",shards = 4,replicas = 1)
public class Employee {

    @Id
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String empId;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String empName;

    @Field(type = FieldType.Keyword)
    private String empSn;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String empGivenName;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String empDisplayName;

    @Field(type = FieldType.Keyword)
    private String empMobile;

    @Field(type = FieldType.Keyword)
    private String empEmail;

    @Field(type = FieldType.Keyword)
    private String orgId;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String orgName;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String empPosition;

    @Field(type = FieldType.Keyword)
    private String empStatus;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String empDn;

    @Field(type = FieldType.Keyword)
    private String empJwUid;
}
