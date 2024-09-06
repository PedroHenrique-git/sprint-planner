package com.sprintplanner.planner.domain.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sprintplanner.planner.domain.enumeration.Complexity;
import com.sprintplanner.planner.domain.enumeration.Priority;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "task-index")
public class SearchTask {
    @Id
    String id;

    @Field(type = FieldType.Text)
    String name;

    @Field(type = FieldType.Text)
    String description;

    @Field(type = FieldType.Text)
    Complexity complexity;

    @Field(type = FieldType.Text)
    Priority priority;

    @Field(type = FieldType.Integer)
    int punctuation;

    @Field(type = FieldType.Text)
    String sprintId;

    @Field(type = FieldType.Text)
    String memberId;
}
