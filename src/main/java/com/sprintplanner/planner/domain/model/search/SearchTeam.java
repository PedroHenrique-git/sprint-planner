package com.sprintplanner.planner.domain.model.search;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "team-index")
public class SearchTeam {
    @Id
    String id;

    @Field(type = FieldType.Text)
    String name;

    @Field(type = FieldType.Text)
    String description;

    @Field(type = FieldType.Text)
    List<String> sprints;

    @Field(type = FieldType.Text)
    List<String> members;
}
