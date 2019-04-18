package com.bamboovir.muresearchboost.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document(collection = "people")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "people", type = "people", replicas = 1, refreshInterval = "-1")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class People implements MuResearchModel{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String information;
    private String imageUrl;
    private List<String> career;
    private List<String> organization;

    public People mock() {
        return new People()
                .setId("1")
                .setFirstName("Ding")
                .setLastName("Hao")
                .setEmail("hd945@mail.missouri.edu")
                .setImageUrl("http://google.com")
                .setInformation("Hello World")
                .setOrganization(Stream.of("University Of Missouri", "Google").collect(Collectors.toList()))
                .setCareer(Stream.of("Software Engineer", "Professor").collect(Collectors.toList()));
    }
}
