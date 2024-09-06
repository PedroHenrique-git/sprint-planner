package com.sprintplanner.planner.domain.model.search;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "sprint-index")
public class SearchSprint {
    @Id
    String id;

    @Field(type = FieldType.Text)
    String name;

    @Field(type = FieldType.Text)
    String description;

    @Field(type = FieldType.Text)
    String teamId;

    @Field(type = FieldType.Text)
    List<String> tasks;

    @Field(type = FieldType.Text)
    List<String> members;
}
