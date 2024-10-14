package com.sprintplanner.planner.domain.model.search;

import com.sprintplanner.planner.domain.enumeration.Complexity;
import com.sprintplanner.planner.domain.enumeration.Priority;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "project-index")
public class SearchProject {
    @Id
    String id;

    @Field(type = FieldType.Text)
    String name;

    @Field(type = FieldType.Text)
    String description;

    @Field(type = FieldType.Text)
    List<String> teams;
}
