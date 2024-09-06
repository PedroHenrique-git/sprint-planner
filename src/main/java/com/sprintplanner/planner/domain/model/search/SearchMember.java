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
@Document(indexName = "member-index")
public class SearchMember {
    @Id
    String id;

    @Field(type = FieldType.Text)
    String username;

    @Field(type = FieldType.Text)
    String firstName;

    @Field(type = FieldType.Text)
    String lastName;

    @Field(type = FieldType.Text)
    String avatar;

    @Field(type = FieldType.Text)
    String email;

    @Field(type = FieldType.Text)
    List<String> teams;

    @Field(type = FieldType.Text)
    List<String> tasks;

    @Field(type = FieldType.Text)
    List<String> sprints;
}
