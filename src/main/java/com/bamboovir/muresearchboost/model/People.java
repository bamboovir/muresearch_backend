package com.bamboovir.muresearchboost.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

@ApiModel(description = "Class representing a person tracked by the application.")
public class Person {
    @ApiModelProperty(notes = "Unique identifier of the person. No two persons can have the same id.", example = "1", required = true, position = 0)
    private int id;
    @ApiModelProperty(notes = "First name of the person.", example = "John", required = true, position = 1)
    private String firstName;
    @ApiModelProperty(notes = "Last name of the person.", example = "Doe", required = true, position = 2)
    private String lastName;
    @ApiModelProperty(notes = "Age of the person. Non-negative integer", example = "42", position = 3)
    private int age;

    // … Constructor, getters, setters, ...
}

 */
@Document(collection = "people")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "people", type = "people", replicas = 1, refreshInterval = "-1")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class People implements MuResearchModel{
    @Id
    private String id;
    private String fullname;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private String imageMediumUrl;
    private String imageSmailUrl;
    private String imageLargeUrl;
    private List<String> career;
    // Organization扩展为对象？
    private List<String> organization;
    private String information;
    private String imageUrl;
    private Boolean isUser;
    private String userId;

    public People mock() {
        return new People()
                .setId("666")
                .setUserId("666")
                .setIsUser(Boolean.FALSE)
                .setEmail("hd945@mail.missouri.edu")
                .setCareer(Stream.of("Software Engineer", "Professor").collect(Collectors.toList()))
                .setImageUrl("http://google.com")
                .setInformation("Hello World")
                .setOrganization(Stream.of("University Of Missouri", "Google").collect(Collectors.toList()));
    }
}
